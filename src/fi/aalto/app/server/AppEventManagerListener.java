package fi.aalto.app.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.server.EventManager;
import com.prosysopc.ua.server.EventManagerListener;
import com.prosysopc.ua.server.MonitoredEventItem;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.server.Subscription;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.DateTime;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.core.EventFilter;
import com.prosysopc.ua.stack.core.EventFilterResult;
import com.prosysopc.ua.stack.core.StatusCodes;
import com.prosysopc.ua.types.opcua.server.AcknowledgeableConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.AlarmConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.types.opcua.server.ConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.OffNormalAlarmTypeNode;
import com.prosysopc.ua.types.opcua.server.ShelvedStateMachineTypeNode;

public class AppEventManagerListener implements EventManagerListener {

	private UaServer server; 
	private UaClient client; 
	private AppNodeManager appNodeManager;
	
	public AppEventManagerListener(UaServer server, UaClient client, AppNodeManager appNodeManager) {
		this.server = server;
		this.client = client;
		this.appNodeManager = appNodeManager;
		
	}
	/**
	   * Internal counter for UserEventId:s. Used from {@link #getNextUserEventId()}.
	   */
	  private int eventId = 0;

	  @Override
	  public boolean onAcknowledge(ServiceContext serviceContext, AcknowledgeableConditionTypeNode condition,
	      ByteString eventId, LocalizedText comment) throws StatusException {
	    // Handle acknowledge request to a condition event
		  System.out.println("Acknowledge: Condition=" + condition + "; EventId=" + eventId + "; Comment=" + comment);
	    // If the acknowledged event is no longer active, return an error
	    if (!eventId.equals(condition.getEventId())) {
	      throw new StatusException(StatusCodes.Bad_EventIdUnknown);
	    }
	    if (condition.isAcked()) {
	      throw new StatusException(StatusCodes.Bad_ConditionBranchAlreadyAcked);
	    }

	    final DateTime now = DateTime.currentTime();
	    condition.setAcked(true, now);
	    final ByteString userEventId = getNextUserEventId();
	    // addComment triggers a new event
	    condition.addComment(eventId, comment, now, userEventId);
	    return true; // Handled here
	    // NOTE: If you do not handle acknowledge here, and return false,
	    // the EventManager (or MethodManager) will call
	    // condition.acknowledge, which performs the same actions as this
	    // handler, except for setting Retain
	  }

	  @Override
	  public boolean onAddComment(ServiceContext serviceContext, ConditionTypeNode condition, ByteString eventId,
	      LocalizedText comment) throws StatusException {
	    // Handle add command request to a condition event
		  System.out.println("AddComment: Condition=" + condition + "; Event=" + eventId + "; Comment=" + comment);
	    // Only the current eventId can get comments
	    if (!eventId.equals(condition.getEventId())) {
	      throw new StatusException(StatusCodes.Bad_EventIdUnknown);
	    }
	    // triggers a new event
	    final ByteString userEventId = getNextUserEventId();
	    condition.addComment(eventId, comment, DateTime.currentTime(), userEventId);
	    return true; // Handled here
	    // NOTE: If you do not handle addComment here, and return false,
	    // the EventManager (or MethodManager) will call
	    // condition.addComment automatically
	  }

	  @Override
	  public void onAfterCreateMonitoredEventItem(ServiceContext serviceContext, Subscription subscription,
	      MonitoredEventItem item) {
	    //
	  }

	  @Override
	  public void onAfterDeleteMonitoredEventItem(ServiceContext serviceContext, Subscription subscription,
	      MonitoredEventItem item) {
	    //
	  }

	  @Override
	  public void onAfterModifyMonitoredEventItem(ServiceContext serviceContext, Subscription subscription,
	      MonitoredEventItem item) {
	    //
	  }

	  @Override
	  public void onConditionRefresh(ServiceContext serviceContext, Subscription subscription) throws StatusException {
	    //
//		  OffNormalAlarmTypeNode  ev = appNodeManager.createEvent(OffNormalAlarmTypeNode.class);
//		  ev.triggerEvent(null);
	  }

	  @Override
	  public boolean onConfirm(ServiceContext serviceContext, AcknowledgeableConditionTypeNode condition,
	      ByteString eventId, LocalizedText comment) throws StatusException {
	    // Handle confirm request to a condition event
		  System.out.println("Confirm: Condition=" + condition + "; EventId=" + eventId + "; Comment=" + comment);
	    // If the confirmed event is no longer active, return an error
	    if (!eventId.equals(condition.getEventId())) {
	      throw new StatusException(StatusCodes.Bad_EventIdUnknown);
	    }
	    if (condition.isConfirmed()) {
	      throw new StatusException(StatusCodes.Bad_ConditionBranchAlreadyConfirmed);
	    }
	    if (!condition.isAcked()) {
	      throw new StatusException("Condition can only be confirmed when it is acknowledged.",
	          StatusCodes.Bad_InvalidState);
	    }
	    // If the condition is no longer active, set retain to false, i.e.
	    // remove it from the visible alarms
	    if (!(condition instanceof AlarmConditionTypeNode) || !((AlarmConditionTypeNode) condition).isActive()) {
	      condition.setRetain(false);
	    }

	    final DateTime now = DateTime.currentTime();
	    condition.setConfirmed(true, now);
	    final ByteString userEventId = getNextUserEventId();
	    // addComment triggers a new event
	    condition.addComment(eventId, comment, now, userEventId);
	    return true; // Handled here
	    // NOTE: If you do not handle Confirm here, and return false,
	    // the EventManager (or MethodManager) will call
	    // condition.confirm, which performs the same actions as this
	    // handler
	  }

	  @Override
	  public void onCreateMonitoredEventItem(ServiceContext serviceContext, NodeId nodeId, EventFilter eventFilter,
	      EventFilterResult filterResult) throws StatusException {
	    // Item created
	  }

	  @Override
	  public void onDeleteMonitoredEventItem(ServiceContext serviceContext, Subscription subscription,
	      MonitoredEventItem monitoredItem) {
	    // Stop monitoring the item?
	  }

	  @Override
	  public boolean onDisable(ServiceContext serviceContext, ConditionTypeNode condition) throws StatusException {
	    // Handle disable request to a condition
		  System.out.println("Disable: Condition=" + condition);
	    if (condition.isEnabled()) {
	      DateTime now = DateTime.currentTime();
	      // Setting enabled to false, also sets retain to false
	      condition.setEnabled(false, now);
	      // notify the clients of the change
	      condition.triggerEvent(now, null, getNextUserEventId());
	    }
	    return true; // Handled here
	    // NOTE: If you do not handle disable here, and return false,
	    // the EventManager (or MethodManager) will request the
	    // condition to handle the call, and it will unset the enabled
	    // state, and triggers a new notification event, as here
	  }

	  @Override
	  public boolean onEnable(ServiceContext serviceContext, ConditionTypeNode condition) throws StatusException {
	    // Handle enable request to a condition
	    System.out.println("Enable: Condition=" + condition);
	    if (!condition.isEnabled()) {
	      DateTime now = DateTime.currentTime();
	      condition.setEnabled(true, now);
	      // You should evaluate the condition now, set Retain to true,
	      // if necessary and in that case also call triggerEvent
	      // condition.setRetain(true);
	      // condition.triggerEvent(now, null, getNextUserEventId());
	    }
	    return true; // Handled here
	    // NOTE: If you do not handle enable here, and return false,
	    // the EventManager (or MethodManager) will request the
	    // condition to handle the call, and it will set the enabled
	    // state.

	    // You should however set the status of the condition yourself
	    // and trigger a new event if necessary
	  }

	  @Override
	  public void onModifyMonitoredEventItem(ServiceContext serviceContext, Subscription subscription,
	      MonitoredEventItem monitoredItem, EventFilter eventFilter, EventFilterResult filterResult)
	      throws StatusException {
		  System.out.println("monitored event item modified");
	    // Modify event monitoring, when the client modifies a monitored
	    // item
	  }

	  @Override
	  public boolean onOneshotShelve(ServiceContext serviceContext, AlarmConditionTypeNode condition,
	      ShelvedStateMachineTypeNode stateMachine) throws StatusException {
	    return false;
	  }

	  @Override
	  public boolean onTimedShelve(ServiceContext serviceContext, AlarmConditionTypeNode condition,
	      ShelvedStateMachineTypeNode stateMachine, double shelvingTime) throws StatusException {
	    return false;
	  }

	  @Override
	  public boolean onUnshelve(ServiceContext serviceContext, AlarmConditionTypeNode condition,
	      ShelvedStateMachineTypeNode stateMachine) throws StatusException {
	    return false;
	  }

//	  private void println(String string) {
//	    MyNodeManager.println(string);
//	  }

	  ByteString getNextUserEventId() throws RuntimeException {
	    return EventManager.createEventId(eventId++);
	  }
	  
	  /**
	   * Send an event notification.
	   *
	   * @param event The event to trigger.
	   */
	  private void triggerEvent(BaseEventTypeNode event) {
		  // Trigger event
		  final DateTime now = DateTime.currentTime();
		  // Use your own EventId to keep track of your events, if you need to (for example when alarms
		  // are acknowledged)
		  ByteString myEventId = getNextUserEventId();
		  // If you wish, you can record the full event ID that is provided by triggerEvent, although your
		  // own 'myEventId' is usually enough to keep track of the event.
		  /* ByteString fullEventId = */event.triggerEvent(now, now, myEventId);
	  }
}
