package fi.aalto.app.server;

import java.util.List;

import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;
import com.prosysopc.ua.stack.core.UserTokenType;
import com.prosysopc.ua.stack.core.StatusCodes;
import com.prosysopc.ua.stack.core.MonitoringParameters;
import com.prosysopc.ua.stack.core.MonitoringFilter;
import com.prosysopc.ua.stack.core.AggregateFilterResult;
import com.prosysopc.ua.stack.core.ViewDescription;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.utils.NumericRange;
import com.prosysopc.ua.stack.core.MonitoringMode;
import com.prosysopc.ua.stack.core.Attributes;

import com.prosysopc.ua.server.NodeManagerListener;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaReferenceType;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.server.Subscription;
import com.prosysopc.ua.server.MonitoredDataItem;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.server.UaServer;

import fi.aalto.app.client.AppMonitoredDataItemListener;

public class AppNodeManagerListener implements NodeManagerListener {

	private UaClient client; 
	private UaServer server; 
	
	public AppNodeManagerListener (UaClient client, UaServer server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void onAddNode(ServiceContext serviceContext,
            com.prosysopc.ua.stack.builtintypes.NodeId parentNodeId,
            UaNode parent,
            com.prosysopc.ua.stack.builtintypes.NodeId nodeId,
            com.prosysopc.ua.stack.core.NodeClass nodeClass,
            com.prosysopc.ua.stack.builtintypes.QualifiedName browseName,
            com.prosysopc.ua.stack.core.NodeAttributes attributes,
            UaReferenceType referenceType,
            com.prosysopc.ua.stack.builtintypes.ExpandedNodeId typeDefinitionId,
            UaNode typeDefinition)
            throws StatusException {
		checkUserAccess(serviceContext);
	}

	@Override
	public void onAfterAddNode(ServiceContext serviceContext,
            com.prosysopc.ua.stack.builtintypes.NodeId parentNodeId,
            UaNode parent,
            com.prosysopc.ua.stack.builtintypes.NodeId nodeId,
            UaNode node,
            com.prosysopc.ua.stack.core.NodeClass nodeClass,
            com.prosysopc.ua.stack.builtintypes.QualifiedName browseName,
            com.prosysopc.ua.stack.core.NodeAttributes attributes,
            UaReferenceType referenceType,
            com.prosysopc.ua.stack.builtintypes.ExpandedNodeId typeDefinitionId,
            UaNode typeDefinition)
            throws StatusException {
	}
	
	@Override
	public boolean onBrowseNode(ServiceContext serviceContext,
			ViewDescription view, NodeId nodeId, UaNode node,
			UaReference reference) {
		//System.out.println("onBrowseNode");
		return true;
	}

	@Override
	public void onDeleteNode(ServiceContext serviceContext, NodeId nodeId,
			UaNode node, boolean deleteTargetReferences) throws StatusException {
		checkUserAccess(serviceContext);
	}

	@Override
	public void onGetReferences(ServiceContext serviceContext,
			ViewDescription viewDescription, NodeId nodeId, UaNode node,
			List<UaReference> references) {
	}

	@Override
	public void onAddReference(ServiceContext serviceContext,
			NodeId sourceNodeId, UaNode sourceNode,
			ExpandedNodeId targetNodeId, UaNode targetNode,
			NodeId referenceTypeId, UaReferenceType referenceType,
			boolean isForward) throws StatusException {
		checkUserAccess(serviceContext);
	}

	@Override
	public void onAfterAddReference(ServiceContext serviceContext,
            com.prosysopc.ua.stack.builtintypes.NodeId sourceNodeId,
            UaNode sourceNode,
            com.prosysopc.ua.stack.builtintypes.ExpandedNodeId targetNodeId,
            UaNode targetNode,
            com.prosysopc.ua.stack.builtintypes.NodeId referenceTypeId,
            UaReferenceType referenceType,
            boolean isForward)
            throws StatusException {
	}
	
	@Override
	public void onDeleteReference(ServiceContext serviceContext,
			NodeId sourceNodeId, UaNode sourceNode,
			ExpandedNodeId targetNodeId, UaNode targetNode,
			NodeId referenceTypeId, UaReferenceType referenceType,
			boolean isForward, boolean deleteBidirectional)
			throws StatusException {
		checkUserAccess(serviceContext);
	}

	@Override
	public void onCreateMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, NodeId nodeId, UaNode node,
			UnsignedInteger attributeId, NumericRange indexRange,
			MonitoringParameters params, MonitoringFilter filter,
			AggregateFilterResult filterResult, MonitoringMode monitoringMode) throws StatusException {
		//System.out.println("onCreateMonitoredDataItem");
	}

	@Override
	public void onAfterCreateMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, MonitoredDataItem item) {
		try {
		    int sourceNs = 2; // TODO get this from somewhere?
		    String name = item.getNodeId().getValue().toString();
		    //System.out.println("onCreateMonitoredDataItem: " + name);
		    com.prosysopc.ua.client.MonitoredDataItem clientMonitoredDataItem = new com.prosysopc.ua.client.MonitoredDataItem(new NodeId(sourceNs, name), Attributes.Value, MonitoringMode.Reporting);
		    clientMonitoredDataItem.setDataChangeListener(new AppMonitoredDataItemListener(this.server, this.client));
		    com.prosysopc.ua.client.Subscription clientSubscription = client.getSubscriptions()[0];
		    clientSubscription.addItem(clientMonitoredDataItem);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
	    }
	}
	
	@Override
	public void onModifyMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, MonitoredDataItem item, UaNode node,
			MonitoringParameters params, MonitoringFilter filter,
			AggregateFilterResult filterResult) {
		//System.out.println("onModifyMonitoredDataItem");
	}

	@Override
	public void onAfterModifyMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, MonitoredDataItem item) {
		//System.out.println("onAfterModifyMonitoredDataItem");
	}

	@Override
	public void onDeleteMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, MonitoredDataItem monitoredItem) {
	}

	@Override
	public void onAfterDeleteMonitoredDataItem(ServiceContext serviceContext,
			Subscription subscription, MonitoredDataItem item) {
		try {
	        String name = item.getNodeId().getValue().toString();
		    //System.out.println("onAfterDeleteMonitoredDataItem: " + name);	
		    com.prosysopc.ua.client.Subscription clientSubscription = client.getSubscriptions()[0];	
		    com.prosysopc.ua.MonitoredItemBase[] clientMonitoredItems = clientSubscription.getItems(); 
		    for (com.prosysopc.ua.MonitoredItemBase clientItem : clientMonitoredItems) {
			    String clientItemName = clientItem.getNodeId().getValue().toString();
			    if (name.equals(clientItemName)) clientSubscription.removeItem(clientItem);
		    }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
	    }
	}

	private void checkUserAccess(ServiceContext serviceContext)
			throws StatusException {
		if (serviceContext.getSession().getUserIdentity().getType().equals(UserTokenType.Anonymous))
			throw new StatusException(StatusCodes.Bad_UserAccessDenied);
	}

}
