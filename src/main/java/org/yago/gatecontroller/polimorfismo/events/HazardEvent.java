// src/main/java/org/poo/java/polimorfismo/events/HazardEvent.java
package org.yago.gatecontroller.polimorfismo.events;

import org.yago.gatecontroller.polimorfismo.Event;
import org.yago.gatecontroller.polimorfismo.EventPriority;
import org.yago.gatecontroller.polimorfismo.Gate;

public final class HazardEvent implements Event {
	private final boolean hazardOn;
	
	public HazardEvent(boolean hazardOn) {
		this.hazardOn = hazardOn;
	}
	
	@Override
	public EventPriority priority() {
		return EventPriority.HAZARD;
	}
	
	@Override
	public boolean requiresUnlocked() {
		return false;
	}
	
	@Override
	public void apply(Gate gate) {
		gate.executeSetHazard(hazardOn);
	}
	
	@Override
	public String name() {
		return hazardOn ? "HazardDetectedEvent" : "HazardClearedEvent";
	}
}
