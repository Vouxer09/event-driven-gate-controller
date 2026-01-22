// src/main/java/org/poo/java/polimorfismo/events/ObstacleEvent.java
package org.yago.gatecontroller.polimorfismo.events;

import org.yago.gatecontroller.polimorfismo.Event;
import org.yago.gatecontroller.polimorfismo.EventPriority;
import org.yago.gatecontroller.polimorfismo.Gate;

public final class ObstacleEvent implements Event {
	private final boolean obstacleOn;
	
	public ObstacleEvent(boolean obstacleOn) {
		this.obstacleOn = obstacleOn;
	}
	
	@Override
	public EventPriority priority() {
		return EventPriority.OBSTACLE;
	}
	
	@Override
	public boolean requiresUnlocked() {
		return false;
	}
	
	@Override
	public void apply(Gate gate) {
		gate.executeSetObstacle(obstacleOn);
	}
	
	@Override
	public String name() {
		return obstacleOn ? "ObstacleDetectedEvent" : "ObstacleClearedEvent";
	}
}
