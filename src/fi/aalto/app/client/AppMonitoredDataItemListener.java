package fi.aalto.app.client;

import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.DateTime;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.stack.core.Identifiers;
import com.prosysopc.ua.stack.core.ReferenceDescription;
import com.prosysopc.ua.typedictionary.FieldSpecification;
import com.prosysopc.ua.types.opcua.server.AlarmConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.server.EventManager;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.nodes.UaVariable;

public class AppMonitoredDataItemListener implements MonitoredDataItemListener {

	UaServer server; 
	
	public AppMonitoredDataItemListener (UaServer server) {
	    this.server = server;
	}
	
	@Override
	public void onDataChange(MonitoredDataItem sender, DataValue prevValue, DataValue value) {
		try {
		    String name = sender.getNodeId().getValue().toString();
		    //System.out.println("onDataChange: " + name + " " + value.getValue().getValue());
		    /**Update corresponding AppServer's value**/
		    int ns = 2;
		    UaVariable node = (UaVariable)this.server.getNodeManagerRoot().getNode(new NodeId(ns, name));
		    node.setValue(value);
		    
		    /**Checking for alarm triggers**/
		    UaReference ref = node.getReference(Identifiers.HasCondition, false);
		    if(ref==null) return;	//null -> This data is not linked to any alarm, no need to go further
		    System.out.println("onDataChange : an alarm-linked variable has changed");
		    AlarmConditionTypeNode alarm = (AlarmConditionTypeNode) ref.getTargetNode(); //alarm
		    
		    if (alarm.getTypeDefinition().getNodeId().equals(Identifiers.OffNormalAlarmType)) {
		    	System.out.println("onDataChange : OffNormalAlarmType");
			    
		    	/*VERY complicated way to get the normalStateValue....*/
			    UaProperty normalStateProperty = alarm.getProperty(new QualifiedName("NormalState"));	//Get property (which stores "normal node"'s nodeId)
			    NodeId normalStateNodeId = NodeId.parseNodeId(normalStateProperty.getValue().getValue().toString());	//Get "normal node"'s nodeId
			    UaVariable normalStateNode = (UaVariable) server.getAddressSpace().getNode(normalStateNodeId);	//Get normal node
			    Variant normalStateValue = normalStateNode.getValue().getValue();	//Finally get normal value

			    if(!node.getValue().getValue().equals(normalStateValue)) {
			    	//The variable is not in normal mode -> need to trigger the alarm
			    	System.out.println("onDataChange : OffNormalAlarmType triggered");
			    	System.out.println("(Normal value='"+normalStateValue+"', observed='"+node.getValue().getValue()+"')");
			    	triggerEvent(alarm);
			    }
		    }
		    else if (alarm.getTypeDefinition().getNodeId().equals(Identifiers.NonExclusiveLimitAlarmType)) {
		    	System.out.println("onDataChange : NonExclusiveLimitAlarmType");
		    	//TODO: Logic to check if alarm needs to trigger
		    	/*
			     * TODO: find the lowlow low high highhigh limits to compare against for triggering the alarm
			     * */
		    	//triggerEvent(alarm);
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