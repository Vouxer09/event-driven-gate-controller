// src/main/java/org/poo/java/polimorfismo/Gate.java
package org.yago.gatecontroller.polimorfismo;
import org.yago.gatecontroller.polimorfismo.EventLogger;
import org.yago.gatecontroller.polimorfismo.events.*;

public class Gate {
	
	private GateState state = new Stopped();
	private boolean locked;
	private boolean obstacle;
	private boolean hazard;
	private Position position = Position.CLOSED;
	
	// ===== Public API now submits events to the central dispatcher =====
	
	public void open() {
		Dispatcher.INSTANCE.submit(new UserOpenEvent(), this);
		EventLogger.actionStarted("Open Request", "Gate", "User");
	}
	
	public void close() {
		Dispatcher.INSTANCE.submit(new UserCloseEvent(), this);
		EventLogger.actionStarted("Close Request", "Gate", "User");
	}
	
	public void stop() {
		// user stop considered user action - require unlocked
		Dispatcher.INSTANCE.submit(new UserStopEvent(), this);
		EventLogger.actionStarted("Stop Request", "Gate", "User");
	}
	
	public void detectObstacle(boolean value) {
		Dispatcher.INSTANCE.submit(new ObstacleEvent(value), this);
	}
	
	public void lock(boolean value) {
		Dispatcher.INSTANCE.submit(new LockEvent(value), this);
	}
	
	public void setHazard(boolean value) {
		Dispatcher.INSTANCE.submit(new HazardEvent(value), this);
	}
	
	
	// ===== Package\-private executors called by events (no public state setter) =====
	
	public void executeOpenRequest() {
		// central dispatcher already checked lock and priority
		EventLogger.stateChanged(this.state.getClass().getSimpleName(), "OPEN","Open Request executed");
		state.open(this);
	}
	
	public void executeCloseRequest() {
		EventLogger.stateChanged(this.state.getClass().getSimpleName(), "CLOSE","Close Request executed");
		state.close(this);
	}
	
	public void executeStopRequest() {
		state.stop(this);
		EventLogger.stateChanged(this.state.getClass().getSimpleName(), "STOP", "Stop Request executed");
	}
	
	public void executeSetHazard(boolean value) {
		this.hazard = value;
		// hazard change may affect state decisions; keep state unchanged here
		// optionally, a hazard might force a stop depending on state; do not add global logic here
	}
	
	public void executeSetObstacle(boolean value) {
		this.obstacle = value;
	}
	
	public void executeLock(boolean value) {
		this.locked = value;
	}
	
	// ===== Transitions internal =====
	
	void setState(GateState state) {
		this.state = state;
	}
	
	boolean isLocked() {
		return locked;
	}
	
	boolean hasObstacle() {
		return obstacle;
	}
	
	boolean hasHazard() {
		return hazard;
	}
	
	void reachOpenEnd() {
		position = Position.OPEN;
		state = new Stopped();
	}
	
	void reachClosedEnd() {
		position = Position.CLOSED;
		state = new Stopped();
	}
	
	public float getPriority() {
		if (hazard) {
			return EventPriority.HAZARD.value();
		} else if (obstacle) {
			return EventPriority.OBSTACLE.value();
		} else {
			return EventPriority.USER.value();
		}
	}
	
	// ===== State Pattern (unchanged, unaware of priority) =====
	
	private interface GateState {
		void open(Gate gate);
		void close(Gate gate);
		void stop(Gate gate);
	}
	
	private static class Stopped implements GateState {
		
		@Override
		public void open(Gate gate) {
			if (gate.locked) return;
			if (gate.position == Position.OPEN) return;
			
			gate.setState(new Opening());
		}
		
		@Override
		public void close(Gate gate) {
			if (gate.locked) return;
			if (gate.position == Position.CLOSED) return;
			
			gate.setState(new Closing());
		}
		
		@Override
		public void stop(Gate gate) {
			// already stopped
		}
	}
	
	private static class Opening implements GateState {
		
		@Override
		public void open(Gate gate) {
			// already opening
		}
		
		@Override
		public void close(Gate gate) {
			gate.setState(new Closing());
		}
		
		@Override
		public void stop(Gate gate) {
			gate.setState(new Stopped());
		}
	}
	
	private static class Closing implements GateState {
		
		@Override
		public void open(Gate gate) {
			gate.setState(new Opening());
		}
		
		@Override
		public void close(Gate gate) {
			// already closing
		}
		
		@Override
		public void stop(Gate gate) {
			gate.setState(new Stopped());
		}
	}
	
	private static class Position {
		static final Position OPEN = new Position();
		static final Position CLOSED = new Position();
	}
	
}
