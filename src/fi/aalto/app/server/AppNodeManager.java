package fi.aalto.app.server;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Stack;

import org.xml.sax.SAXException;

import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.common.NamespaceTable;
import com.prosysopc.ua.stack.common.ServiceResultException;
import com.prosysopc.ua.stack.core.Identifiers;
import com.prosysopc.ua.stack.core.BrowseDescription;
import com.prosysopc.ua.stack.core.BrowseDirection;
import com.prosysopc.ua.stack.core.BrowseResult;
import com.prosysopc.ua.stack.core.ReferenceDescription;
import com.prosysopc.ua.stack.utils.StackUtils;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import com.prosysopc.ua.types.opcua.NonExclusiveLimitAlarmType;
import com.prosysopc.ua.types.opcua.OffNormalAlarmType;

import fi.aalto.app.AppDeviceType;
import fi.aalto.app.Ids;
import opc.ua.di.TopologyElementType;
import opc.ua.di.UIElementType;
import opc.ua.di.client.UIElementTypeImpl;

import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.ModelException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UaBrowsePath;
import com.prosysopc.ua.UaQualifiedName;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaNodeFactoryException;
import com.prosysopc.ua.nodes.UaObjectType;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.nodes.UaType;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.UaInstantiationException;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.server.UaServer.NodeManagerUaServer;
import com.prosysopc.ua.server.instantiation.TypeDefinitionBasedNodeBuilderConfiguration;
import com.prosysopc.ua.server.nodes.PlainVariable;
import com.prosysopc.ua.server.nodes.UaObjectNode;
import com.prosysopc.ua.server.nodes.UaObjectTypeNode;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.client.UaClient;

public class AppNodeManager extends NodeManagerUaNode {

	public static final String NAMESPACE = "http://localhost/OPCUA/AppAddressSpace";

	public AppNodeManager(UaServer arg0, String arg1) {
		super(arg0, arg1);
		// Auto-generated constructor stub
		//Code below used to add optionnal components
		TypeDefinitionBasedNodeBuilderConfiguration.Builder conf =TypeDefinitionBasedNodeBuilderConfiguration.builder();
		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.TopologyElementType, UaQualifiedName.from("http://opcfoundation.org/UA/DI/", "ParameterSet")));
		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.TopologyElementType, UaQualifiedName.from("http://opcfoundation.org/UA/DI/", "MethodSet")));
		this.setNodeBuilderConfiguration(conf.build());
	}

	public void createAddressSpace(UaClient client) throws StatusException, UaInstantiationException {
		
		AddressSpace sourceAddressSpace = client.getAddressSpace();
		final UaNode deviceSet;
		String[] deviceNames= {"L300","L301","M200","P300","T300","Y301","Y303","Y501"};	//Devices list

		int ns = getNamespaceIndex();
		final UaNode objectsFolder = getServer().getNodeManagerRoot().getObjectsFolder();
		
		UaNode parent = objectsFolder;

		// Base objects
		//No need for these directories
		/*parent = createFolder(ns, "CoDeSys.OPC.DA", parent);
		parent = createFolder(ns, "PLC_GW3", parent);
		parent = createFolder(ns, "Application", parent);
		parent = createFolder(ns, "GVL_WP_HPP", parent);*/
		
		int sourceNs = 2; // TODO get sourceNs from client?
		try {
		    BrowseDescription browseDesc = new BrowseDescription();
		    browseDesc.setNodeId(new NodeId(sourceNs, "GVL_WP_HPP"));
		    browseDesc.setBrowseDirection(BrowseDirection.Forward);
		    browseDesc.setReferenceTypeId(Identifiers.HasComponent);
		    //browseDesc.setNodeClassMask(NodeClass.Variable.getMask());
	
			BrowseResult[] browseResult = sourceAddressSpace.browse(null, 0, browseDesc);
	    	ReferenceDescription[] refs = browseResult[0].getReferences();

	    	System.out.println("Number of variables: " + refs.length);
			    
	    	registerModels();			//Register and load additional types and configurations
	    	deviceSet=getDeviceSet();	//Get DeviceSet node (must be done AFTER the register step)
	    	
//	    	createDevices(ns,deviceSet,deviceNames);	//Devices instantiation
	    	
//	    	 L300 High level alarm
	    	UaObjectNode l300 = createDevice(ns,deviceSet, "L300");
	    	UaNode parameterSet = l300.getComponent(new QualifiedName("ParameterSet"));
			createVariable(ns, "L300_CurModeVal", "AUTO", Identifiers.String, l300);
			createVariable(ns, "L300_MeasMan", false, Identifiers.Boolean, l300);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "L300_AlrmEvtMsg", "L300: Boiler NOT FULL", Identifiers.String, parent);
//			createVariable(ns, "L300_AlrmEvtOn", false, Identifiers.Boolean, parent);
//			createVariable(ns, "L300_SetModeAuto", true, Identifiers.Boolean, l300);
//			createVariable(ns, "L300_SetModeMan", false, Identifiers.Boolean, l300);	
			
	    	// L301 Low level alarm	    	
			UaObjectNode l301 = createDevice(ns,deviceSet, "L301");
			createVariable(ns, "L301_CurModeVal", "AUTO", Identifiers.String, l301);
			createVariable(ns, "L301_MeasMan", false, Identifiers.Boolean, l301);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "L301_AlrmEvtMsg", "L301: Boiler EMPTY", Identifiers.String, parent);
//			createVariable(ns, "L301_AlrmEvtOn", true, Identifiers.Boolean, parent);
//			createVariable(ns, "L301_SetModeAuto", true, Identifiers.Boolean, parent);
//			createVariable(ns, "L301_SetModeMan", false, Identifiers.Boolean, parent);
			
			
			// P300 Pressure sensor
			UaObjectNode p300 = createDevice(ns,deviceSet, "P300");
			createVariable(ns, "P300_CurModeVal", "AUTO", Identifiers.String, p300);
			createVariable(ns, "P300_MeasMan", new Float(0), Identifiers.Float, p300);
			UaVariable p300_measurement = 	createVariable(ns, "P300_MeasVal", new Float(0), Identifiers.Float, p300);
			createVariable(ns, "P300_ZeroMeas", false, Identifiers.Boolean, p300);
			createNonExclusiveLimitAlarmNode(p300_measurement , p300, parameterSet).setMessage(new LocalizedText("P300: (L) Boiler pressure at 0.0bar(g)"));
			//TODO: methods
//			createVariable(ns, "P300_SetModeAuto", true, Identifiers.Boolean, p300);
//			createVariable(ns, "P300_SetModeMan", false, Identifiers.Boolean, p300);
			
			
	    	// M200 Pump			w
			UaObjectNode m200 = createDevice(ns,deviceSet, "M200");
			createVariable(ns, "M200_CtrlOut", new Integer(0), Identifiers.Integer, m200);
			createVariable(ns, "M200_CurCtrlVal", new Float(0), Identifiers.Float, m200);
			createVariable(ns, "M200_CurModeVal", "MANUAL", Identifiers.String, m200);
			createVariable(ns, "M200_IntActive", false, Identifiers.Boolean, m200);
			createVariable(ns, "M200_IntMsg", "M200: NORMAL", Identifiers.String, m200);
			createVariable(ns, "M200_ManCtrlVal", new Float(0), Identifiers.Float, m200);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "M200_AlrmEvtMsq", "M200: NORMAL", Identifiers.String, parent);
//			createVariable(ns, "M200_AlrmEvtOn", false, Identifiers.Boolean, parent);
//			createVariable(ns, "M200_SetModeAuto", false, Identifiers.Boolean, m200);
//			createVariable(ns, "M200_SetModeMan", true, Identifiers.Boolean, m200);
	    	
	    	
	    	// T300 Temperature sensor	    	
			UaObjectNode t300 = createDevice(ns,deviceSet, "T300");
	    	UaVariable t300_measurement = 	createVariable(ns, "T300_MeasVal", new Float(0), Identifiers.Float, t300);
			createNonExclusiveLimitAlarmNode(t300_measurement , t300, parameterSet).setMessage(new LocalizedText("T300: (L) Boiler temperature at 3.104C"));
			createVariable(ns, "T300_CurModeVal", "AUTO", Identifiers.String, t300);
			createVariable(ns, "T300_MeasMan", new Float(0), Identifiers.Float, t300);
			createVariable(ns, "T300_ZeroMeas", false, Identifiers.Boolean, t300);
			//TODO: methods
//			createVariable(ns, "T300_SetModeAuto", true, Identifiers.Boolean, t300);
//			createVariable(ns, "T300_SetModeMan", false, Identifiers.Boolean, t300);
			
	    	
	    	
	    	
	    	// Y301 and Y303 Cut-off valves
			UaObjectNode y301 = createDevice(ns,deviceSet, "Y301");
			
			createVariable(ns, "Y301_CtrlOut", false, Identifiers.Boolean, y301);
			createVariable(ns, "Y301_CurOutINT", new Integer(0), Identifiers.Integer, y301);
			createVariable(ns, "Y301_CtrlVal", false, Identifiers.Boolean, y301);
			createVariable(ns, "Y301_CurModeVal", "MANUAL", Identifiers.String, y301);
			createVariable(ns, "Y301_IntActive", false, Identifiers.Boolean, y301);
			createVariable(ns, "Y301_IntMsg", "Y301: NORMAL", Identifiers.String, y301);
			createVariable(ns, "Y301_ManCtrlVal", false, Identifiers.Boolean, y301);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "Y301_AlrmEvtMsq", "Y301: NORMAL", Identifiers.String, y301);
//			createVariable(ns, "Y301_AlrmEvtOn", false, Identifiers.Boolean, y301);
//			createVariable(ns, "Y301_SetModeAuto", false, Identifiers.Boolean, y301);
//			createVariable(ns, "Y301_SetModeMan", true, Identifiers.Boolean, y301);

			
			UaObjectNode y303 = createDevice(ns,deviceSet, "Y303");
			
			createVariable(ns, "Y303_CtrlOut", false, Identifiers.Boolean, y303);
			createVariable(ns, "Y303_CurOutINT", new Integer(0), Identifiers.Integer, y303);
			createVariable(ns, "Y303_CtrlVal", false, Identifiers.Boolean, y303);
			createVariable(ns, "Y303_CurModeVal", "MANUAL", Identifiers.String, y303);
			createVariable(ns, "Y303_IntActive", false, Identifiers.Boolean, y303);
			createVariable(ns, "Y303_IntMsg", "Y303: NORMAL", Identifiers.String, y303);
			createVariable(ns, "Y303_ManCtrlVal", false, Identifiers.Boolean, y303);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "Y303_AlrmEvtMsq", "Y303: NORMAL", Identifiers.String, y303);
//			createVariable(ns, "Y303_AlrmEvtOn", false, Identifiers.Boolean, y303);
//			createVariable(ns, "Y303_SetModeAuto", false, Identifiers.Boolean, y303);
//			createVariable(ns, "Y303_SetModeMan", true, Identifiers.Boolean, y303);

			
			
			// Y501 Control valve
			UaObjectNode y501 = createDevice(ns,deviceSet, "Y501");
			
			createVariable(ns, "Y501_CtrlOut", new Integer(0), Identifiers.Integer, y501);
			createVariable(ns, "Y501_CtrlVal", new Float(0), Identifiers.Float, y501);
			createVariable(ns, "Y501_CurCtrlVal", new Float(0), Identifiers.Float, y501);
			createVariable(ns, "Y501_CurModeVal", "MANUAL", Identifiers.String, y501);
			createVariable(ns, "Y501_IntActive", true, Identifiers.Boolean, y501);
			createVariable(ns, "Y501_IntMsg", "Y501: FORCED OFF", Identifiers.String, y501);
			createVariable(ns, "Y501_ManCtrlVal", new Float(0), Identifiers.Float, y501);
			//TODO: OffNormalAlarmType
			//TODO: methods
//			createVariable(ns, "Y501_AlrmEvtMsq", "Y501: NORMAL", Identifiers.String, y501);
//			createVariable(ns, "Y501_AlrmEvtOn", false, Identifiers.Boolean, y501);
//			createVariable(ns, "Y501_SetModeAuto", false, Identifiers.Boolean, y501);
//			createVariable(ns, "Y501_SetModeMan", false, Identifiers.Boolean, y501);
			
	    	
			// PIC300 Pressure PID control
			//TODO: CTRLCONFTYPE
//			createVariable(ns, "PIC300_AlrmEvtMsq", "PIC300: NORMAL", Identifiers.String, parent);
//			createVariable(ns, "PIC300_AlrmEvtOn", false, Identifiers.Boolean, parent);
//			createVariable(ns, "PIC300_CtrlOff", true, Identifiers.Boolean, parent);
//			createVariable(ns, "PIC300_CtrlOn", false, Identifiers.Boolean, parent);
//			createVariable(ns, "PIC300_CtrlVal", 0, Identifiers.Float, parent);
//			createVariable(ns, "PIC300_CurModeVal", "MANUAL", Identifiers.String, parent);		
//			createVariable(ns, "PIC300_CurSPVal", new Float(0), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_Enable", false, Identifiers.Boolean, parent);
//			createVariable(ns, "PIC300_Kp", new Float(10), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_Td", new Float(1), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_Ti", new Float(0.1), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_ManCtrlVal", new Float(0), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_PIDReset", false, Identifiers.Boolean, parent);
//			// SP2, SP2Active, SP3, SP3Active, SPSeq
//			createVariable(ns, "PIC300_SPVal", new Float(0), Identifiers.Float, parent);
//			createVariable(ns, "PIC300_SetModeAuto", false, Identifiers.Boolean, parent);
//			createVariable(ns, "PIC300_SetModeMan", false, Identifiers.Boolean, parent);
//			//createVariable(ns, "PIC300_SetModeSeq", false, Identifiers.Boolean, parent);

	    	
	    	
//			
//			for (ReferenceDescription ref : refs) {
//		    	UaNode var = sourceAddressSpace.getNode(ref.getNodeId(), UaNode.class);	//Get node from DemoServer
//		        String name = var.getBrowseName().getName();	//Get node's browse name
//		        
//		        if(name.startsWith("PIC300_")) {
//		        	//The data about the tag PIC300 should follow the OPC UA Information Model for IEC 61131.3,
//		        	//release 1.00. You can utilize the CtrlConfigurationType type.
//		        	
//		        }
//		        else if(name.contains("SetMode")) {
//		        	//The variables whose name contain SetModeAuto or SetModeMan should be provided as 
//		        	//OPC UA methods according to the OPC UA Specification, 
//		        	//Part 3: Address Space Model, release 1.04.
//		        }
//		        else if(name.contains("AlrmEvt")) {
//		        	//The variables who have the nature of alarms should be provided only as OPC UA alarms (and not as variables) 
//		        	//according to the OPC UA Specification Part 9: Alarms & Conditions, release 1.04.
//		        }
//		        else {
//		        	//The data about all other tags should follow the OPC UA for Devices Companion Specification, 
//		        	//release 1.01. You have to utilize the DeviceType type.
////		        	assignOtherVar(ns, (UaVariable)var);
//		        }
//		        
//		        //Following lines were here to create variable
//		        //NodeId dataTypeId = var.getDataTypeId();
//		        //createVariable(ns, name, dataTypeId, parent);
//		    }
			
	    	
		    /*for (ReferenceDescription ref : refs) {
		    	UaVariable var = sourceAddressSpace.getNode(ref.getNodeId(), UaVariable.class);
		        String name = var.getBrowseName().getName();
		        NodeId dataTypeId = var.getDataTypeId();
		        createVariable(ns, name, dataTypeId, parent);
		    }*/
//			deviceSet.getAddressSpace();
			
			
			
			
			
//			
//			UaVariable var = sourceAddressSpace.getNode(refs[0].getNodeId(), UaVariable.class);
//	        String name = var.getBrowseName().getName();
//	        NodeId dataTypeId = var.getDataTypeId();
//	        UaVariable variable = createVariable(ns, name, dataTypeId, parent);
////			
//	        UaObjectNode deviceNode;
//			
//			//Devices nodes creation
//			NodeId deviceId = new NodeId(ns, "Test device");
//			deviceNode = new UaObjectNode(this,deviceId,"Test device",Locale.ENGLISH);	//Instantiate new node
//			deviceNode.setTypeDefinitionId(Ids.AppDeviceType);	//Set device node's type
//			deviceNode.setDescription(new LocalizedText("Test device"));
//			deviceSet.addComponent(deviceNode);	//Attach the device to the DeviceSet
//	        
//	        
//			createAlarmNode(variable, deviceNode, deviceSet);
			
		} catch (Exception e) {
			System.out.println("\n/!\\ EXCEPTION /!\\");
		    System.out.println("-->MESSAGE: "+e.getMessage());
		    System.out.println("\n-->STACK: ");
		    int stackSize=e.getStackTrace().length;
		    for(int i=0;i<stackSize;i++) {
		    	System.out.println("Stack["+i+"]: '"+e.getStackTrace()[i].getMethodName()+"' at line '"+e.getStackTrace()[i].getLineNumber()+"'");
		    }
		    
	    }
	}
	
	@Override
	protected void init() throws StatusException, UaNodeFactoryException {
		super.init();
		
	}

	private UaObjectNode createFolder(int ns, String name, UaNode parent)
	{
	    final NodeId id = new NodeId(ns, name);
	    UaObjectNode node = new UaObjectNode(this, id, name, Locale.ENGLISH);
	    node.setTypeDefinitionId(Identifiers.FolderType);
		try {
		    this.addNodeAndReference(parent, node, Identifiers.Organizes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		node.setDescription(new LocalizedText(name)); 
		
		return node;
	}

	private <T> PlainVariable<T> createVariable(int ns, String name, T value, NodeId dataTypeId, UaNode parent)
	{
		NodeId id = new NodeId(ns, name);
		PlainVariable<T> variable = new PlainVariable<T>(this, id, name, LocalizedText.NO_LOCALE);
		variable.setDataTypeId(dataTypeId);
		variable.setTypeDefinitionId(Identifiers.BaseDataVariableType);
		parent.addComponent(variable);
		variable.setCurrentValue(value);
		variable.setDescription(new LocalizedText(name)); 

		return variable;
	}
	
	private <T>PlainVariable<T> createVariable(int ns, String name, NodeId varNodeId, T value, NodeId dataTypeId, UaNode parent)
	{
		PlainVariable<T> variable = new PlainVariable<T>(this, varNodeId, name, LocalizedText.NO_LOCALE);
		variable.setDataTypeId(dataTypeId);
		variable.setTypeDefinitionId(Identifiers.BaseDataVariableType);
		parent.addComponent(variable);
		variable.setCurrentValue(value);
		variable.setDescription(new LocalizedText(name)); 

		return variable;
	}
	
	private UaObjectNode createDevice(int ns, UaNode deviceSetNode, String deviceName) throws StatusException, ServiceResultException
	{
		if(deviceSetNode==null) {	//DeviceSet is compulsory to continue
    		System.out.println("The 'DeviceSet' was not found, the address space won't be set up.");
    		return null;
    	}
		UaObjectNode deviceNode, componentNode;	//Nodes that will be used to instantiate all devices and their components
		
		NodeId deviceId = new NodeId(ns, deviceName);	//Generate device's nodeId
		NodeId paramSetId= new NodeId(ns,deviceName+TopologyElementType.PARAMETER_SET); //Generate device ParameterSet's nodeId
		NodeId methodSetId= new NodeId(ns,deviceName+TopologyElementType.METHOD_SET); //Generate device MethodSet's nodeId
		
		//Device node
		deviceNode = new UaObjectNode(this,deviceId,deviceName,Locale.ENGLISH);	//Instantiate node
		deviceNode.setTypeDefinitionId(Ids.AppDeviceType);							//Set node's type
		deviceNode.setDescription(new LocalizedText(deviceName)); 				//Set node's description
		deviceNode.setBrowseName(new QualifiedName(deviceName));
		deviceSetNode.addComponent(deviceNode);										//Attach the device to the DeviceSet
		
		//ParameterSet node
		componentNode = new UaObjectNode(this,paramSetId,TopologyElementType.PARAMETER_SET,Locale.ENGLISH);	//Instantiate node
		componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
		componentNode.setDescription(new LocalizedText("ParameterSet of "+deviceName)); 				//Set node's description
		deviceNode.addComponent(componentNode);																//Attach the component to its device

		//MethodSet node
		componentNode = new UaObjectNode(this,methodSetId,TopologyElementType.METHOD_SET,Locale.ENGLISH);	//Instantiate node
		componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
		componentNode.setDescription(new LocalizedText("MethodSet of "+deviceName)); 					//Set node's description
		deviceNode.addComponent(componentNode);
		return deviceNode;
	}
	
	
	
	private void createDevices(int ns, UaNode deviceSetNode, String[] deviceNames) throws StatusException, ServiceResultException
	{
		UaObjectNode deviceNode, componentNode;	//Nodes that will be used to instantiate all devices and their components
		
		if(deviceSetNode==null) {	//DeviceSet is compulsory to continue
    		System.out.println("The 'DeviceSet' was not found, the address space won't be set up.");
    		return;
    	}
		
		//We create all devices and assign the needed components to each device
		int deviceNumber=deviceNames.length;
		for(int i=0;i<deviceNumber;i++) {
			NodeId deviceId = new NodeId(ns, deviceNames[i]);	//Generate device's nodeId
			NodeId paramSetId= new NodeId(ns,deviceNames[i]+TopologyElementType.PARAMETER_SET); //Generate device ParameterSet's nodeId
			NodeId methodSetId= new NodeId(ns,deviceNames[i]+TopologyElementType.METHOD_SET); //Generate device MethodSet's nodeId
			
			//Device node
			deviceNode = new UaObjectNode(this,deviceId,deviceNames[i],Locale.ENGLISH);	//Instantiate node
			deviceNode.setTypeDefinitionId(Ids.AppDeviceType);							//Set node's type
			deviceNode.setDescription(new LocalizedText(deviceNames[i])); 				//Set node's description
			deviceSetNode.addComponent(deviceNode);										//Attach the device to the DeviceSet
			
			//ParameterSet node
			componentNode = new UaObjectNode(this,paramSetId,TopologyElementType.PARAMETER_SET,Locale.ENGLISH);	//Instantiate node
			componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
			componentNode.setDescription(new LocalizedText("ParameterSet of "+deviceNames[i])); 				//Set node's description
			deviceNode.addComponent(componentNode);																//Attach the component to its device

			//MethodSet node
			componentNode = new UaObjectNode(this,methodSetId,TopologyElementType.METHOD_SET,Locale.ENGLISH);	//Instantiate node
			componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
			componentNode.setDescription(new LocalizedText("MethodSet of "+deviceNames[i])); 					//Set node's description
			deviceNode.addComponent(componentNode);																//Attach the component to its device
		}
	}
	
	private void assignOtherVar(int ns, UaVariable nodeToAssign) throws StatusException {
		//names[0]= device's name ; names[1]= node's name
		String[] names = nodeToAssign.getBrowseName().getName().split("_");
		//System.out.println("Device '"+names[0]+"' ; Var '"+names[1]+"'");
		
		//Get the ParameterSet of the current device
		UaNode paramSetNode = getParameterSet(ns, names[0]);
		
		//Create and assign the variable to ParameterSet
		NodeId varId = new NodeId(ns,names[0]+"|"+names[1]);	//Unique NodeId for each var
		createVariable(ns,names[1],varId,nodeToAssign.getValue().getValue(),nodeToAssign.getDataTypeId(),paramSetNode);
	}	
	
	private UaNode getDeviceSet() throws StatusException
	{	
		UaNode deviceSet=null;
		
		//Try to find DeviceSet in all NameSpaces
		String[] namespaceTable = getNamespaceTable().toArray();	//Get namespaces table
		for(int i=0;i<namespaceTable.length;i++) {
	    	//System.out.println("Namespace "+i+" : '"+namespaceTable[i]+"'");
	    	NodeId nodeIdTest = NodeId.parseNodeId("ns="+i+";i=5001");	//Form the nodeId according to current namespace and xml defined index
	    	
	    	if(getServer().getAddressSpace().hasNode(nodeIdTest)) {	//Test its existence in whole address space
	    		//System.out.println("DeviceSet found ("+nodeIdTest.toString()+")");
	    		deviceSet=getServer().getAddressSpace().getNode(nodeIdTest);	//Fetch it
	    		return deviceSet;
	    	}
	    	//System.out.println("DeviceSet not found ("+nodeIdTest.toString()+")");
	    }
		//System.out.println("DeviceSet not found in any namespace");
		
		return deviceSet;
	}
	
	private void registerModels() throws SAXException, IOException, ModelException
	{
		UaServer srv = getServer();	//Get this node manager's server
		
		// 1. Register the generated classes in your UaServer object by
		// using the ServerInformationModel class that is generated in the server package.
		// 2. Load the type nodes from the .xml file.
		// THE ORDER IS IMPORTANT
		
		srv.registerModel(opc.ua.iec611313.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("Opc.Ua.Di.NodeSet2.xml").toURI());
		
		srv.registerModel(opc.ua.di.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("Opc.Ua.Plc.NodeSet2.xml").toURI());
		
		srv.registerModel(fi.aalto.app.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("appserver.xml").toURI());

		// 3. Now you can create and use instances of generated types
	}
	
	
	
	/*probably doesnt work, not tested
	 * Which parameters does offnormalalarmtype need to be set?
	 * */
	private OffNormalAlarmType createOffNormalAlarmNode(UaVariable source, UaObjectNode device, UaNode deviceSet) throws StatusException, UaInstantiationException {
		int ns = this.getNamespaceIndex();
		final NodeId myAlarmId = new NodeId(ns, device.getNodeId().getValue() + "_Alrm");
		String name = device.getBrowseName().getName() + "_Alrm";
		
		TypeDefinitionBasedNodeBuilderConfiguration.Builder conf = TypeDefinitionBasedNodeBuilderConfiguration.builder();
		   this.setNodeBuilderConfiguration(conf.build());

		   OffNormalAlarmType myAlarm = createInstance(OffNormalAlarmType.class, name, myAlarmId);
		   myAlarm.setSourceNode(source.getNodeId());
		   myAlarm.setInputNode(source.getNodeId());

		   myAlarm.setMessage(new LocalizedText("Level exceeded"));
		   device.addComponent(myAlarm); // addReference(...Identifiers.HasComponent...)
		   source.addReference(myAlarm, Identifiers.HasCondition, false);
		   device.addReference(source, Identifiers.HasEventSource, false);

		   return myAlarm;
	}
	
	
	private NonExclusiveLimitAlarmType createNonExclusiveLimitAlarmNode(UaVariable source, UaObjectNode device, UaNode deviceSet) throws StatusException, UaInstantiationException {
		int ns = this.getNamespaceIndex();
		final NodeId myAlarmId = new NodeId(ns, device.getNodeId().getValue() + "_Alrm");
		String name = device.getBrowseName().getName() + "_Alrm";
		
		TypeDefinitionBasedNodeBuilderConfiguration.Builder conf = TypeDefinitionBasedNodeBuilderConfiguration.builder();
		//these need to be added, but I dont know how to find LimitAlarmType
//		conf.addOptional(UaBrowsePath.from(opc.ua.iec611313.Ids.LimitAlarmType, UaQualifiedName.standard("HighHighLimit")));
////		conf.addOptional(UaBrowsePath.from(opc.ua.iec611313.Ids., UaQualifiedName.standard("HighHighLimit")));
//		conf.addOptional(UaBrowsePath.from(Ids.LimitAlarmType, UaQualifiedName.standard("HighLimit")));
//		conf.addOptional(UaBrowsePath.from(Ids.LimitAlarmType, UaQualifiedName.standard("LowLimit")));
//		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.LimitAlarmType, UaQualifiedName.standard("LowLowLimit")));
////		conf.addOptional(UaBrowsePath.from(Ids.LimitAlarmType, UaQualifiedName.standard("LowLowLimit")));
//		,
		this.setNodeBuilderConfiguration(conf.build());

		NonExclusiveLimitAlarmType myAlarm = createInstance(NonExclusiveLimitAlarmType.class, name, myAlarmId);
		myAlarm.setSourceNode(source.getNodeId());
//		   myAlarm.setSourceName(source.);
		   // Input is the node which has the measurement that generates the alarm
//		   myAlarm.setInput(source);
		myAlarm.setInputNode(source.getNodeId());

		myAlarm.setMessage(new LocalizedText("Level exceeded"));
//		   myAlarm.setSeverity(500); // Medium level warning
//		   myAlarm.setHighHighLimit(90.0);
//		   myAlarm.setHighLimit(70.0);
//		   myAlarm.setLowLimit(30.0);
//		   myAlarm.setLowLowLimit(10.0);
//		   myAlarm.setEnabled(true);
		device.addComponent(myAlarm); // addReference(...Identifiers.HasComponent...)

		   // + HasCondition, the SourceNode of the reference should normally
		   // correspond to the Source set above
		source.addReference(myAlarm, Identifiers.HasCondition, false);
		device.addReference(source, Identifiers.HasEventSource, false);

//		   deviceSet.addReference(device, Identifiers.HasNotifier, false);
		   return myAlarm;
	}
	
	
	private UaVariable getVariable(int ns,String deviceName, String variableName) throws StatusException {
		UaVariable var=null;	//The variable to return
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode parameterSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.PARAMETER_SET));	//The parameterSet node
		
		//Browse name composed of namespace index + var name
		var=(UaVariable) parameterSetNode.getComponent(new QualifiedName(ns, variableName));
		
		return var;
	}
	
	private UaNode getParameterSet(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode parameterSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.PARAMETER_SET));	//The parameterSet node
		
		return parameterSetNode;
	}
	
	private UaNode getMethodSet(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode methodSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.METHOD_SET));	//The methodSet node
		
		return methodSetNode;
	}
	
	private UaNode getDevice(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		
		return deviceNode;
	}
}
