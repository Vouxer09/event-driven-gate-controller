// src/main/java/org/poo/java/polimorfismo/Dispatcher.java
package org.yago.gatecontroller.polimorfismo;
import org.yago.gatecontroller.polimorfismo.EventLogger;

public final class Dispatcher {
	public static final Dispatcher INSTANCE = new Dispatcher();
	
	
	public Dispatcher() {}
	
	void eventReceived(Event event, Gate gate) {
		EventLogger.eventReceived(event.name(), event.priority(), gate.toString());
	}
	
	public synchronized boolean submit(Event event, Gate gate) {
		// Security enforced centrally
		if (gate.isLocked() && event.requiresUnlocked()) {
			EventLogger.dispatchBlocked(event.name(), event.priority(), gate.toString(),
					"Gate is locked");
			return false;
		}
		
		int gatePriority = Math.round(gate.getPriority());
		if (event.priority().value() >= gatePriority) {
			event.apply(gate);
			EventLogger.dispatchAccepted(event.name(), event.priority(), gate.toString());
			return true;
		} else {
			// dropped due to lower priority
			EventLogger.dispatchBlocked(event.name(), event.priority(), gate.toString(),
					"Event priority lower than gate priority (" + gatePriority + ")");
			return false;
		}
	}
}
