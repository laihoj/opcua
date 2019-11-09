package fi.aalto.app.client;

import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.NodeId;

import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.server.UaServer;
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
		    //System.out.println("onDataChange: " + name + " " + value.getValue().getValue());
		    //int ns = this.server.getNodeManagerRoot().getNamespaceIndex();
		    int ns = 2;
		    UaVariable node = (UaVariable)this.server.getNodeManagerRoot().getNode(new NodeId(ns, name));
		    node.setValue(value);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
	    }
	}
}