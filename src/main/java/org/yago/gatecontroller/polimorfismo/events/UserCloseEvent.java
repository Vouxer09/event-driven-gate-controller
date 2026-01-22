// src/main/java/org/poo/java/polimorfismo/events/UserCloseEvent.java
package org.yago.gatecontroller.polimorfismo.events;

import org.yago.gatecontroller.polimorfismo.Event;
import org.yago.gatecontroller.polimorfismo.EventPriority;
import org.yago.gatecontroller.polimorfismo.Gate;

public final class UserCloseEvent implements Event {
	
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
		gate.executeCloseRequest();
	}
	
	@Override
	public String name() {
		return "UserCloseEvent";
	}
}

