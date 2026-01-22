package org.yago.gatecontroller.polimorfismo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;
import org.yago.gatecontroller.polimorfismo.EventPriority;

/**
 * Centralized logger for events, dispatch decisions, state transitions and actions.
 * Follows the logging model: received -> dispatch -> state change -> action start -> action denied.
 */
public final class EventLogger {
	private static final Logger LOG = Logger.getLogger(EventLogger.class.getName());
	
	private EventLogger() {}
	
	private static String timestamp() {
		return Instant.now().toString();
	}
	
	public static void eventReceived(String eventName, EventPriority priority, String origin) {
		LOG.log(Level.INFO, "{0} | 1. EVENT RECEIVED: event=\"{1}\" origin=\"{2}\" priority={3}",
				new Object[]{timestamp(), eventName, origin, priority.display()});
	}
	
	public static void dispatchAccepted(String eventName, EventPriority priority, String dispatcherId) {
		LOG.log(Level.INFO, "{0} | 2. DISPATCH: ACCEPTED: event=\"{1}\" by=\"{2}\" priority={3}",
				new Object[]{timestamp(), eventName, dispatcherId, priority.display()});
	}
	
	public static void dispatchBlocked(String eventName, EventPriority priority, String dispatcherId, String reason) {
		LOG.log(Level.INFO, "{0} | 2. DISPATCH: BLOCKED: event=\"{1}\" by=\"{2}\" priority={3} reason=\"{4}\"",
				new Object[]{timestamp(), eventName, dispatcherId, priority.display(), reason});
	}
	
	public static void stateChanged(String fromState, String toState, String context) {
		LOG.log(Level.INFO, "{0} | 3. STATE CHANGE: from=\"{1}\" to=\"{2}\" context=\"{3}\"",
				new Object[]{timestamp(), fromState, toState, context});
	}
	
	public static void actionStarted(String actionName, String target, String actor) {
		LOG.log(Level.INFO, "{0} | 4. ACTION START: action=\"{1}\" target=\"{2}\" actor=\"{3}\"",
				new Object[]{timestamp(), actionName, target, actor});
	}
	
	public static void actionDenied(String actionName, String target, EventPriority blockerPriority, String blockerType, String reason) {
		// blockerType: e.g. "Obstacle", "Hazard", "Security"
		LOG.log(Level.INFO, "{0} | 5. ACTION DENIED: action=\"{1}\" target=\"{2}\" blocked-by=\"{3}\" ({4}) reason=\"{5}\"",
				new Object[]{timestamp(), actionName, target, blockerPriority.display(), blockerType, reason});
	}
}
