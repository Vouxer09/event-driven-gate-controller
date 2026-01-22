// src/main/java/org/poo/java/polimorfismo/events/LockEvent.java
package org.yago.gatecontroller.polimorfismo.events;

import org.yago.gatecontroller.polimorfismo.Event;
import org.yago.gatecontroller.polimorfismo.EventPriority;
import org.yago.gatecontroller.polimorfismo.Gate;

public final class LockEvent implements Event {
	private final boolean lock;
	
	public LockEvent(boolean lock) {
		this.lock = lock;
	}
	
	@Override
	public EventPriority priority() {
		// lock changes should be high priority so they are applied immediately
		return EventPriority.HAZARD;
	}
	
	@Override
	public boolean requiresUnlocked() {
		return false;
	}
	
	@Override
	public void apply(Gate gate) {
		gate.executeLock(lock);
	}
	
	@Override
	public String name() {
		return lock ? "LockEvent" : "UnlockEvent";
	}
}
