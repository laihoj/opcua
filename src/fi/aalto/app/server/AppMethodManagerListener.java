package fi.aalto.app.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UaException;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.server.CallableListener;
import com.prosysopc.ua.server.NodeManager;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.server.UaServer.NodeManagerUaServer;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.Variant;

public class AppMethodManagerListener implements CallableListener {	

    public AppMethodManagerListener() {
            super();
    }

    /**
     * Handle method calls
     * 
     * @param serviceContext
     * @param objectId - the ID of the node whose method is being called
     * @param object  - the object node whose method is being called, if available (device's MethodSet node)
     * @param methodId - the ID of the method being called
     * @param method - the method node being called, if available
     * @param inputArguments - input argument values
     * @param inputArgumentResults - room for argument errors. Fill in the array if you encounter errors in the values.
     * @param inputArgumentDiagnosticInfos - room for diagnostic info, in case of errors.
     * @param outputs - room for output values. The array is pre-created, so just fill in the values.
 	 * 
 	 * @return true
     */
	@Override
	public boolean onCall(ServiceContext serviceContext, NodeId objectId, UaNode object, NodeId methodId, UaMethod method, Variant[] inputArguments,
			StatusCode[] inputArgumentResults, DiagnosticInfo[] inputArgumentDiagnosticInfos, Variant[] outputs) throws StatusException {
		
		//No input argument needed, therefore no verification needed
		
		/* Goal from teamwork pdf:
		 * Calling any method with simple application logic.
		 * It is possible to call all methods with names SetModeAuto and SetModeMan.
		 * The value of the variable CurModeVal is set to either AUTO or MANUAL according these calls.
		 */
		
		String[] names = methodId.getValue().toString().split("_");	//Method id = {DeviceName}_{MethodName} ; names[0]= device's name ; names[1]= method's name
		//System.out.println("names[0]='"+names[0]+"' ; names[1]='"+names[1]+"'");
		final String varName = "CurModeVal";	//name of the variable to change
		final NodeId varId = new NodeId(objectId.getNamespaceIndex(),names[0]+"_"+varName); //variable ID = {DeviceName}_{VariableName}
		//System.out.println("varId='"+varId+"'");
		
		try {
			UaVariable varNode = (UaVariable)object.getAddressSpace().getNode(varId);
			//System.out.println("varNode BrowseName='"+varNode.getBrowseName()+"'");
			if(names[1].contains("SetModeAuto")) {
				//Set the variable to AUTO
				varNode.setValue("AUTO");
				//System.out.println("varNode set to 'AUTO'");
			}
			else if(names[1].contains("SetModeMan")){
				//Set the variable to MAN
				varNode.setValue("MAN");
				//System.out.println("varNode set to 'MAN'");
			}else {
				//Invalid method name
				//System.out.println("invalid method called");
				return false;
			}
		} catch (UaException e) {
			return false;
		}
		return true;
	}
}
