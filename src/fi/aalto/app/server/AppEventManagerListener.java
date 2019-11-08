package fi.aalto.app.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.EventManagerListener;
import com.prosysopc.ua.server.MonitoredEventItem;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.server.Subscription;
import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.core.EventFilter;
import com.prosysopc.ua.stack.core.EventFilterResult;
import com.prosysopc.ua.types.opcua.server.AcknowledgeableConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.AlarmConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.ConditionTypeNode;
import com.prosysopc.ua.types.opcua.server.ShelvedStateMachineTypeNode;

public class AppEventManagerListener implements EventManagerListener {

	public AppEventManagerListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onAcknowledge(ServiceContext arg0, AcknowledgeableConditionTypeNode arg1, ByteString arg2,
			LocalizedText arg3) throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onAddComment(ServiceContext arg0, ConditionTypeNode arg1, ByteString arg2, LocalizedText arg3)
			throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAfterCreateMonitoredEventItem(ServiceContext arg0, Subscription arg1, MonitoredEventItem arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAfterDeleteMonitoredEventItem(ServiceContext arg0, Subscription arg1, MonitoredEventItem arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAfterModifyMonitoredEventItem(ServiceContext arg0, Subscription arg1, MonitoredEventItem arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConditionRefresh(ServiceContext arg0, Subscription arg1) throws StatusException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onConfirm(ServiceContext arg0, AcknowledgeableConditionTypeNode arg1, ByteString arg2,
			LocalizedText arg3) throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreateMonitoredEventItem(ServiceContext arg0, NodeId arg1, EventFilter arg2, EventFilterResult arg3)
			throws StatusException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeleteMonitoredEventItem(ServiceContext arg0, Subscription arg1, MonitoredEventItem arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onDisable(ServiceContext arg0, ConditionTypeNode arg1) throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onEnable(ServiceContext arg0, ConditionTypeNode arg1) throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onModifyMonitoredEventItem(ServiceContext arg0, Subscription arg1, MonitoredEventItem arg2,
			EventFilter arg3, EventFilterResult arg4) throws StatusException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onOneshotShelve(ServiceContext arg0, AlarmConditionTypeNode arg1, ShelvedStateMachineTypeNode arg2)
			throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTimedShelve(ServiceContext arg0, AlarmConditionTypeNode arg1, ShelvedStateMachineTypeNode arg2,
			double arg3) throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onUnshelve(ServiceContext arg0, AlarmConditionTypeNode arg1, ShelvedStateMachineTypeNode arg2)
			throws StatusException {
		// TODO Auto-generated method stub
		return false;
	}

}
