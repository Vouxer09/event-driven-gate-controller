package org.yago.gatecontroller;
import org.yago.gatecontroller.polimorfismo.Dispatcher;
import org.yago.gatecontroller.polimorfismo.Gate;
import org.yago.gatecontroller.polimorfismo.events.*;

public class Main {
	
	public static void main(String[] args) {
		
		Gate gate = new Gate();
		Dispatcher dispatcher = new Dispatcher();
		
		System.out.println("=== System started ===");
		
		// User tries to open the gate
		dispatcher.submit(new UserOpenEvent(), gate);
		
		// Obstacle detected while opening
		dispatcher.submit(new ObstacleEvent(true), gate);
		
		// User tries to close (should not work)
		dispatcher.submit(new UserCloseEvent(), gate);
		
		// Obstacle removed
		dispatcher.submit(new ObstacleEvent(false), gate);
		
		// User stops the gate
		dispatcher.submit(new UserStopEvent(), gate);
		
		// User closes the gate
		dispatcher.submit(new UserCloseEvent(), gate);
		
		// Emergency lock activated
		dispatcher.submit(new LockEvent(true), gate);
		
		// User tries to open again (should be blocked)
		dispatcher.submit(new UserOpenEvent(), gate);
		
		System.out.println("=== Simulation finished ===");
	}
}
