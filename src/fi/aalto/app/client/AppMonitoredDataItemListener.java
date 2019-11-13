package fi.aalto.app.client;

import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.DateTime;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.stack.core.BrowseDescription;
import com.prosysopc.ua.stack.core.BrowseDirection;
import com.prosysopc.ua.stack.core.BrowseResult;
import com.prosysopc.ua.stack.core.Identifiers;
import com.prosysopc.ua.stack.core.ReferenceDescription;
import com.prosysopc.ua.typedictionary.FieldSpecification;
import com.prosysopc.ua.types.opcua.server.AlarmConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.server.EventManager;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.nodes.UaVariable;

public class AppMonitoredDataItemListener implements MonitoredDataItemListener {

	UaServer server;
	UaClient client;
	
	public AppMonitoredDataItemListener (UaServer server, UaClient client) {
		this.server = server;
		this.client = client;
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
//		    	System.out.println("onDataChange : OffNormalAlarmType");
			    
		    	/*VERY complicated way to get the normalStateValue....*/
			    UaProperty normalStateProperty = alarm.getProperty(new QualifiedName("NormalState"));	//Get property (which stores "normal node"'s nodeId)
			    NodeId normalStateNodeId = NodeId.parseNodeId(normalStateProperty.getValue().getValue().toString());	//Get "normal node"'s nodeId
			    UaVariable normalStateNode = (UaVariable) server.getAddressSpace().getNode(normalStateNodeId);	//Get normal node
			    Variant normalStateValue = normalStateNode.getValue().getValue();	//Finally get normal value
			    
			    
			    /*get the demoserver alarm variable*/
			    UaVariable demoalarm=(UaVariable) alarm.getComponent(new QualifiedName(ns, "AlrmEvtOn"));
//			    AddressSpace sourceAddressSpace = client.getAddressSpace();
//			    UaVariable nodeToAssign = (UaVariable) sourceAddressSpace.getNode(demoalarm.getNodeId(), UaNode.class);

		    	if(!node.getValue().getValue().equals(normalStateValue)) {
			    	//The variable is not in normal mode -> need to trigger the alarm
			    	System.out.println("onDataChange : OffNormalAlarmType triggered");
			    	System.out.println("(Normal value='"+normalStateValue+"', observed='"+node.getValue().getValue()+"')");
			    	triggerEvent(alarm);
			    	client.writeValue(demoalarm.getNodeId(), true);
			    } else 
			    	client.writeValue(demoalarm.getNodeId(), false);
			    
			    	
		    }
		    else if (alarm.getTypeDefinition().getNodeId().equals(Identifiers.NonExclusiveLimitAlarmType)) {
//		    	System.out.println("onDataChange : NonExclusiveLimitAlarmType");
		    	
		    	/*looks like the limit variables are not found because of wrong name space number*/
		    	UaVariable lalrm=(UaVariable) alarm.getComponent(new QualifiedName(ns, "AlrmEvtL"));
		    	UaVariable llalrm=(UaVariable) alarm.getComponent(new QualifiedName(ns, "AlrmEvtLL"));
		    	UaVariable halrm=(UaVariable) alarm.getComponent(new QualifiedName(ns, "AlrmEvtH"));
		    	UaVariable hhalrm=(UaVariable) alarm.getComponent(new QualifiedName(ns, "AlrmEvtHH"));
		    	
		    	
		    	if(node.getValue().getValue().doubleValue() > 100) {
		    		alarm.setSeverity(999);
		    		triggerEvent(alarm);
		    		client.writeValue(hhalrm.getNodeId(), true);
		    	} else 
		    	if(node.getValue().getValue().doubleValue() > 75) {
		    		alarm.setSeverity(749);
		    		triggerEvent(alarm);
		    		client.writeValue(halrm.getNodeId(), true);
		    	} else
		    	if(node.getValue().getValue().doubleValue() > 50) {
		    		alarm.setSeverity(499);
		    		triggerEvent(alarm);
		    		client.writeValue(lalrm.getNodeId(), true);
		    	} else
		    	if(node.getValue().getValue().doubleValue() > 25) {
		    		alarm.setSeverity(249);
		    		triggerEvent(alarm);
		    		client.writeValue(llalrm.getNodeId(), true);
		    	} else {
		    	
			    	client.writeValue(hhalrm.getNodeId(), false);
			    	client.writeValue(halrm.getNodeId(), false);
			    	client.writeValue(lalrm.getNodeId(), false);
			    	client.writeValue(llalrm.getNodeId(), false);
		    	}
		    }
		} catch (Exception e) {
		    System.out.println("OnDataChange Exception: " + e.getMessage());
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