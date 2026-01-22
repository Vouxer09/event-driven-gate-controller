package org.yago.gatecontroller.polimorfismo;

public enum EventPriority {
	HAZARD(100),
	OBSTACLE(80),
	USER(10);
	
	private final int value;
	
	EventPriority(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	public Object display() {
		return this.name();
	}
}
