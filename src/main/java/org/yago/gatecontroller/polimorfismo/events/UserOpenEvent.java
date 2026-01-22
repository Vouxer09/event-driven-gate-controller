// src/main/java/org/poo/java/polimorfismo/events/UserOpenEvent.java
package org.yago.gatecontroller.polimorfismo.events;

import org.yago.gatecontroller.polimorfismo.Event;
import org.yago.gatecontroller.polimorfismo.EventPriority;
import org.yago.gatecontroller.polimorfismo.Gate;

public final class UserOpenEvent implements Event {
	
	@Override
	public EventPriority priority() {
		return EventPriority.USER;
	}
	
	@Override
	public boolean requiresUnlocked() {
		return true;
	}
	
	@Override
	public void apply(Gate gate) {
		gate.executeOpenRequest();
	}
	@Override
	public String name() {
		return "UserOpenEvent";
	}
}
