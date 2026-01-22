
package org.yago.gatecontroller.polimorfismo;

public interface Event {
	EventPriority priority();

	boolean requiresUnlocked();

	void apply(Gate gate);
	
	String name();
}
