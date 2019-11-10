package fi.aalto.app.client;

import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.DateTime;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.core.Identifiers;
import com.prosysopc.ua.stack.core.ReferenceDescription;
import com.prosysopc.ua.typedictionary.FieldSpecification;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.server.EventManager;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.nodes.UaVariable;

public class AppMonitoredDataItemListener implements MonitoredDataItemListener {

	UaServer server; 
	
	public AppMonitoredDataItemListener (UaServer server) {
	    this.server = server;
	}
	
	@Override
	public void onDataChange(MonitoredDataItem sender, DataValue prevValue, DataValue value) {
		// TODO
		try {
		    String name = sender.getNodeId().getValue().toString();
//		    sender.get
		    //System.out.println("onDataChange: " + name + " " + value.getValue().getValue());
		    //int ns = this.server.getNodeManagerRoot().getNamespaceIndex();
		    int ns = 2;
		    UaVariable node = (UaVariable)this.server.getNodeManagerRoot().getNode(new NodeId(ns, name));
		    node.setValue(value);
		    
		    //checking for alarm triggers
		    UaReference ref = node.getReference(Identifiers.HasCondition, false);
		    BaseEventTypeNode alarm = (BaseEventTypeNode) ref.getTargetNode(); //alarm
		    if (alarm.getReference(Identifiers.HasTypeDefinition, false).getTargetNode().getNodeId().equals(Identifiers.OffNormalAlarmType)) {
//		    	System.out.println("OffNormalAlarmType");
//		    	System.out.println();
			    
			    UaNode normalstateproperty = alarm.getProperty(new QualifiedName("NormalState"));
			    System.out.println(normalstateproperty);
			    
			    /*
			     * TODO: find the normal state value from the alarm, 
			     * and compare that to the value of the monitoreddataitem value.
			     * If value is different, triggerEvent(alarm); and done
			     * */
			    
//			    System.out.println(normalstateproperty.getAddressSpace().get);
//			    System.out.println(normalstateproperty.getBrowseName());
//			    System.out.println(normalstateproperty.getDisplayName());
//			    System.out.println(normalstateproperty.getComponents());
//			    System.out.println(normalstateproperty.get);
//			    server.getAddressSpace().getNode(normalstateproperty.getDisplayName());
//			    UaNode normalState = server.getAddressSpace().getNode(normalstateproperty.);
//			    System.out.println(normalState);
//			    normalState.get
		    	
//		    	TODO: Logic to check if alarm needs to trigger
//		    	triggerEvent(alarm);
		    	
		    }
		    
		    if (alarm.getReference(Identifiers.HasTypeDefinition, false).getTargetNode().getNodeId().equals(Identifiers.NonExclusiveLimitAlarmType)) {
//		    	System.out.println("NonExclusiveLimitAlarmType");
		    	//TODO: Logic to check if alarm needs to trigger
		    	/*
			     * TODO: find the lowlow low high highhigh limits to compare against for triggering the alarm
			     * */
		    	triggerEvent(alarm);
		    }
		    

		    		
		    
		} catch (Exception e) {
		    System.out.println(e.getMessage());
	    }
		
		
	}
	
private int eventId = 0;
	
	ByteString getNextUserEventId() throws RuntimeException {
		return EventManager.createEventId(eventId++);
	}
	
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