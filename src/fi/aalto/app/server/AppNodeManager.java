package fi.aalto.app.server;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Stack;

import org.xml.sax.SAXException;

import com.prosysopc.ua.stack.builtintypes.DataValue;
import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.UnsignedShort;
import com.prosysopc.ua.stack.common.NamespaceTable;
import com.prosysopc.ua.stack.common.ServiceResultException;
import com.prosysopc.ua.stack.core.Identifiers;
import com.prosysopc.ua.stack.core.NodeAttributes;
import com.prosysopc.ua.stack.core.BrowseDescription;
import com.prosysopc.ua.stack.core.BrowseDirection;
import com.prosysopc.ua.stack.core.BrowseResult;
import com.prosysopc.ua.stack.core.ReferenceDescription;
import com.prosysopc.ua.stack.utils.StackUtils;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import com.prosysopc.ua.types.opcua.NonExclusiveLimitAlarmType;
import com.prosysopc.ua.types.opcua.OffNormalAlarmType;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.types.opcua.server.OffNormalAlarmTypeNode;

import fi.aalto.app.AppDeviceType;
import fi.aalto.app.Ids;
import opc.ua.di.FunctionalGroupType;
//import jdk.internal.org.objectweb.asm.tree.MethodNode;
import opc.ua.di.TopologyElementType;
import opc.ua.di.UIElementType;
import opc.ua.di.client.UIElementTypeImpl;
import opc.ua.di.server.FunctionalGroupTypeNode;
import opc.ua.iec611313.CtrlConfigurationType;
import opc.ua.iec611313.server.CtrlConfigurationTypeNode;
import opc.ua.iec611313.server.CtrlConfigurationTypeNodeBase;

import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.ModelException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UaBrowsePath;
import com.prosysopc.ua.UaQualifiedName;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaNodeFactoryException;
import com.prosysopc.ua.nodes.UaObjectType;
import com.prosysopc.ua.nodes.UaReference;
import com.prosysopc.ua.nodes.UaType;
import com.prosysopc.ua.server.EventManagerListener;
import com.prosysopc.ua.server.MethodManagerUaNode;
import com.prosysopc.ua.server.NodeManager;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.UaInstantiationException;
import com.prosysopc.ua.server.UaNodeUtils;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.server.UaServer.NodeManagerUaServer;
import com.prosysopc.ua.server.instantiation.TypeDefinitionBasedNodeBuilderConfiguration;
import com.prosysopc.ua.server.nodes.PlainVariable;
import com.prosysopc.ua.server.nodes.UaMethodNode;
import com.prosysopc.ua.server.nodes.UaObjectNode;
import com.prosysopc.ua.server.nodes.UaObjectTypeNode;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.client.AddressSpaceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.client.nodes.UaMethodImpl;

public class AppNodeManager extends NodeManagerUaNode {

	public static final String NAMESPACE = "http://localhost/OPCUA/AppAddressSpace";

	public AppNodeManager(UaServer arg0, String arg1) {
		super(arg0, arg1);
		// Auto-generated constructor stub
		//Code below used to add optionnal components
		TypeDefinitionBasedNodeBuilderConfiguration.Builder conf =TypeDefinitionBasedNodeBuilderConfiguration.builder();
		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.TopologyElementType, UaQualifiedName.from("http://opcfoundation.org/UA/DI/", "ParameterSet")));
		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.TopologyElementType, UaQualifiedName.from("http://opcfoundation.org/UA/DI/", "MethodSet")));
//		conf.addOptional(UaBrowsePath.from(opc.ua.di.Ids.TopologyElementType, UaQualifiedName.from("http://opcfoundation.org/UA/DI/", "TriggerAlarm")));
		this.setNodeBuilderConfiguration(conf.build());
		
		
	}

	public void createAddressSpace(UaClient client) throws StatusException, UaInstantiationException {
		
		AddressSpace sourceAddressSpace = client.getAddressSpace();
		final UaNode deviceSet;
		String[] deviceNames= {"L300","L301","M200","P300","PIC300","T300","Y301","Y303","Y501"};	//Devices list

		int ns = getNamespaceIndex();
		final UaNode objectsFolder = getServer().getNodeManagerRoot().getObjectsFolder();
		
		UaNode parent = objectsFolder;

		// Base objects
		//No need for these directories
		/*parent = createFolder(ns, "CoDeSys.OPC.DA", parent);
		parent = createFolder(ns, "PLC_GW3", parent);
		parent = createFolder(ns, "Application", parent);
		parent = createFolder(ns, "GVL_WP_HPP", parent);*/
		
		int sourceNs = 2;
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
			createDevices(ns,deviceSet,deviceNames);	//Devices instantiation	
			
			//Iterate through all references retrieved from DemoServer
			//Sort each node according to its name
		    for (ReferenceDescription ref : refs) {
		    	UaNode var = sourceAddressSpace.getNode(ref.getNodeId(), UaNode.class);	//Get node from DemoServer
		        String name = var.getBrowseName().getName();	//Get node's browse name
		        
		        if(name.contains("SetMode")) {
		        	//The variables whose name contain SetModeAuto or SetModeMan should be provided as 
		        	//OPC UA methods according to the OPC UA Specification, 
		        	//Part 3: Address Space Model, release 1.04.
		        	//PIC300 methods are also managed here
		        	assignMethod(ns,var);
		        }
		        else if(!name.contains("AlrmEvt")){	//Condition to match other vars but Alarm related ones
		        	//The data about all other tags should follow the OPC UA for Devices Companion Specification, 
		        	//release 1.01. You have to utilize the DeviceType type.
		        	//PIC300 variables are also processed here (first step)
		        	assignOtherVar(ns, (UaVariable)var);
		        }
		    }
		    
		    /**MethodManager init**/
		    MethodManagerUaNode m = (MethodManagerUaNode) this.getMethodManager();	//Get method manager of this node manager
		    m.addCallListener(new AppMethodManagerListener(client, this));				//Assign the listener to the method manager
		    
			/**PIC300 components organisation (2nd step)**/
		    //The data about the tag PIC300 should follow the OPC UA Information Model for IEC 61131.3, release 1.00.
		    //You can utilize the CtrlConfigurationType type.
		    UaObjectNode deviceNode=(UaObjectNode) getDevice(ns,"PIC300");	//Get device node
		    deviceNode.setTypeDefinitionId(NodeId.parseNodeId("ns=4;i=1001"));	//Set CtrlConfigurationType from generated model
		    //GlobalVars functional group
		    NodeId FuncGrpId = new NodeId(ns,"PIC300_"+CtrlConfigurationType.GLOBAL_VARS);	//Id to identify the functional group
		    FunctionalGroupTypeNode FuncGrpNode = new FunctionalGroupTypeNode(this, FuncGrpId,
		    		new QualifiedName(CtrlConfigurationType.GLOBAL_VARS),
		    		new LocalizedText(CtrlConfigurationType.GLOBAL_VARS));	//Functional group node
		    FuncGrpNode.setDescription(new LocalizedText("Global components involved in PID process",Locale.ENGLISH));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","CtrlOff"));	//Assign components to the group they belong to
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","CtrlOn"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","CtrlVal"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","CurModeVal"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","CurSPVal"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","Enable"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","Kp"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","ManCtrlVal"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","PIDReset"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","SPVal"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","Td"));
		    FuncGrpNode.addComponent(getVariable(ns,"PIC300","Ti"));
		    deviceNode.addComponent(FuncGrpNode);	//Attach the group to the device
		    
		    //Configuration functional group
		    FuncGrpId = new NodeId(ns,"PIC300_"+CtrlConfigurationType.CONFIGURATION);	//Id to identify the functional group
		    FuncGrpNode = new FunctionalGroupTypeNode(this, FuncGrpId,
		    		new QualifiedName(CtrlConfigurationType.CONFIGURATION),
		    		new LocalizedText(CtrlConfigurationType.CONFIGURATION));	//Functional group node
		    FuncGrpNode.setDescription(new LocalizedText("Configuration components involved in PID process",Locale.ENGLISH));
		    FuncGrpNode.addComponent(getMethod(ns,"PIC300","SetModeAuto"));	//Assign components to the group they belong to
		    FuncGrpNode.addComponent(getMethod(ns,"PIC300","SetModeMan"));
		    deviceNode.addComponent(FuncGrpNode);	//Attach the group to the device
		    
		    /**Alarms configuration**/
		    this.getEventManager().setListener(new AppEventManagerListener(getServer(),client,this));	//Alarm events manager
			//L300 High level alarm
			assignOffNormalAlarm(ns,"L300","MeasVal","AlrmEvtMsg",client);
			
			//L301 High level alarm
			assignOffNormalAlarm(ns,"L301","MeasVal","AlrmEvtMsg",client);

			//M200 Pump
			assignOffNormalAlarm(ns,"M200","IntActive","AlrmEvtMsq",client);	//The 'q' of AlrmEvtMsq is not an error...
			
			//P300 Pressure sensor
			assignNonExclusiveLimitAlarm(ns, "P300", "MeasVal", "AlrmEvtMsg", client);
			
			//PIC300 PID
			assignOffNormalAlarm(ns,"PIC300","Enable","AlrmEvtMsq",client);
			
			//T300 Temperature sensor
			assignNonExclusiveLimitAlarm(ns, "T300", "MeasVal", "AlrmEvtMsg", client);
			
			//Y301 Cut-off valve
			assignOffNormalAlarm(ns,"Y301","CtrlVal","AlrmEvtMsq",client);
			
			//Y303 Cut-off valve
			assignOffNormalAlarm(ns,"Y303","CtrlVal","AlrmEvtMsq",client);
			
			//Y501 Control valve
			assignOffNormalAlarm(ns,"Y501","IntActive","AlrmEvtMsq",client);

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
			NodeId paramSetId= new NodeId(ns,deviceNames[i]+"_"+TopologyElementType.PARAMETER_SET); //Generate device ParameterSet's nodeId
			NodeId methodSetId= new NodeId(ns,deviceNames[i]+"_"+TopologyElementType.METHOD_SET); //Generate device MethodSet's nodeId
			
			//Device node
			deviceNode = new UaObjectNode(this,deviceId,deviceNames[i],Locale.ENGLISH);	//Instantiate node
			deviceNode.setTypeDefinitionId(Ids.AppDeviceType);							//Set node's type
			deviceNode.setDescription(new LocalizedText(deviceNames[i])); 				//Set node's description
			deviceSetNode.addComponent(deviceNode);										//Attach the device to the DeviceSet
			
			//ParameterSet node
			componentNode = new UaObjectNode(this,paramSetId,TopologyElementType.PARAMETER_SET,Locale.ENGLISH);	//Instantiate node
			componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
			componentNode.setDescription(new LocalizedText("ParameterSet of "+deviceNames[i],Locale.ENGLISH)); 	//Set node's description
			deviceNode.addComponent(componentNode);																//Attach the component to its device

			//MethodSet node
			componentNode = new UaObjectNode(this,methodSetId,TopologyElementType.METHOD_SET,Locale.ENGLISH);	//Instantiate node
			componentNode.setTypeDefinitionId(Identifiers.BaseObjectType);										//Set node's type
			componentNode.setDescription(new LocalizedText("MethodSet of "+deviceNames[i],Locale.ENGLISH)); 	//Set node's description
			deviceNode.addComponent(componentNode);																//Attach the component to its device
		}
	}

	
	private void assignNonExclusiveLimitAlarm(int ns, String deviceName, String measurementVarName, String messageVarName, UaClient client) throws StatusException, ServiceException {
		UaNode deviceNode = getDevice(ns,deviceName);	//Get device's node

		UaVariable measurementVar=getVariable(ns,deviceName,measurementVarName);	//Get measurement node from AppServer
		
		NodeId messageVarId=new NodeId(ns,deviceName+"_"+messageVarName);
		DataValue messageVal=client.readValue(messageVarId);	//Get msg value from DemoServer bc not copied in AppServer
		createNonExclusiveLimitAlarmNode(ns, measurementVar,(UaObjectNode) deviceNode,/*(double) 0,(double)1,(double)2,(double)3, */messageVarName);
	}
	
	private void assignOffNormalAlarm(int ns, String deviceName, String measurementVarName, String messageVarName, UaClient client) throws StatusException, ServiceException {
		UaNode deviceNode = getDevice(ns,deviceName);	//Get device's node

		UaVariable measurementVar=getVariable(ns,deviceName,measurementVarName);	//Get measurement node from AppServer
		
		NodeId messageVarId=new NodeId(ns,deviceName+"_"+messageVarName);
		DataValue messageVal=client.readValue(messageVarId);	//Get msg value from DemoServer bc not copied in AppServer
		
		//Establish NormalSate Node in AppServer from trigger value in DemoServer
		NodeId normalStateNodeId=new NodeId(ns,deviceName+"_normal");	//NormalStateNode in AppServer
		NodeId triggerStateNodeId=new NodeId(ns,deviceName+"_AlrmEvtOn");//trigger value in DemoServer
		
		UaVariable normalStateNode=new PlainVariable<UaVariable>(this, normalStateNodeId, deviceName+"_normal", LocalizedText.NO_LOCALE);
		normalStateNode.setDataTypeId(measurementVar.getDataTypeId());
		normalStateNode.setTypeDefinitionId(Identifiers.BaseDataVariableType);
		normalStateNode.setValue(!client.readValue(triggerStateNodeId).getValue().booleanValue());	//Get the inverse value (normal= !trigger)
		normalStateNode.setDescription(new LocalizedText(deviceName+" normal state", Locale.ENGLISH));
		normalStateNode.setBrowseName(new QualifiedName(deviceName+"_normal"));
		deviceNode.addComponent(normalStateNode);

		createOffNormalAlarmNode(ns, measurementVar, deviceNode, normalStateNodeId,messageVal.getValue().toString(), client);
	}
	
	private void assignOtherVar(int ns, UaVariable nodeToAssign) throws StatusException {
		//names[0]= device's name ; names[1]= node's name
		String[] names = nodeToAssign.getBrowseName().getName().split("_");
		//System.out.println("Device '"+names[0]+"' ; Var '"+names[1]+"'");
		
		//Get the ParameterSet of the current device
		UaNode paramSetNode = getParameterSet(ns, names[0]);
		
		//Create and assign the variable to ParameterSet
		NodeId varId = new NodeId(ns,names[0]+"_"+names[1]);	//Unique NodeId for each var
		createVariable(ns,names[1],varId,nodeToAssign.getValue().getValue(),nodeToAssign.getDataTypeId(),paramSetNode);
	}
	
	private void assignMethod(int ns, UaNode nodeToAssign) throws StatusException {
		//names[0]= device's name ; names[1]= node's name
		String[] names = nodeToAssign.getBrowseName().getName().split("_");
		
		//Get the ParameterSet of the current device
		UaNode methodSetNode = getMethodSet(ns, names[0]);
		
		//Create and assign the method to MethodSet
		NodeId methId = new NodeId(ns,names[0]+"_"+names[1]);	//Unique NodeId for each method
		UaMethodNode methNode= new UaMethodNode(this,methId,names[1],Locale.ENGLISH);//Instantiate node
		methNode.setDescription(new LocalizedText("Method '"+names[1]+"'",Locale.ENGLISH));
		
		methodSetNode.addComponent(methNode);	//Attach the method to ddevice's MethodSet
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
		
		// 1. Register the generated classes in the UaServer object by
		// using the ServerInformationModel class that is generated in the server package.
		// 2. Load the type nodes from the .xml file.
		// THE ORDER IS IMPORTANT
		
		srv.registerModel(opc.ua.iec611313.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("Opc.Ua.Di.NodeSet2.xml").toURI());
		
		srv.registerModel(opc.ua.di.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("Opc.Ua.Plc.NodeSet2.xml").toURI());
		
		srv.registerModel(fi.aalto.app.server.ServerInformationModel.MODEL);
		srv.getAddressSpace().loadModel(new File("appserver.xml").toURI());
	}
	
	private OffNormalAlarmTypeNode createOffNormalAlarmNode(int ns, UaVariable source, UaNode device, NodeId normalState, String message, UaClient client) throws StatusException, UaInstantiationException {
//		int ns = this.getNamespaceIndex();
		final NodeId myAlarmId = new NodeId(ns, device.getNodeId().getValue() + "_Alrm");
		String name = device.getBrowseName().getName() + "_Alrm";

		OffNormalAlarmTypeNode myAlarm = createInstance(OffNormalAlarmTypeNode.class, name, myAlarmId);
		myAlarm.setSource(source);
		myAlarm.setInput(source);
		myAlarm.setSeverity(500);
		myAlarm.setEnabled(true);
		myAlarm.setAcked(false);
		myAlarm.setConfirmed(false);
		myAlarm.setSuppressed(false);
		myAlarm.setNormalState(normalState);
		myAlarm.setSuppressedOrShelved(false);
		myAlarm.setActiveState(new LocalizedText(message));

		myAlarm.setMessage(new LocalizedText(message));
		device.addComponent(myAlarm); // addReference(...Identifiers.HasComponent...)
		myAlarm.addReference(source, Identifiers.HasEventSource, false);
		source.addReference(myAlarm, Identifiers.HasCondition, false);
		getDeviceSet().addReference(device, Identifiers.HasEventSource, false);
		
		
		String alarmOnName = myAlarm.getBrowseName().getName() + "EvtOn";
    	NodeId demoserveralarmId = new NodeId(ns, alarmOnName);
		AddressSpace sourceAddressSpace = client.getAddressSpace();
		try {
			UaVariable nodeToAssign = (UaVariable) sourceAddressSpace.getNode(demoserveralarmId, UaNode.class);
			
			String[] names = nodeToAssign.getBrowseName().getName().split("_");
			//Create and assign the variable to alarm
			NodeId varId = new NodeId(ns,names[0]+"_"+names[1]);	//Unique NodeId for each var
			createVariable(ns,names[1],varId,nodeToAssign.getValue().getValue(),nodeToAssign.getDataTypeId(),myAlarm);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (AddressSpaceException e) {
			e.printStackTrace();
		}
		
		return myAlarm;
	}
	
	private NonExclusiveLimitAlarmType createNonExclusiveLimitAlarmNode(int ns,
																		UaVariable source, 
																		UaObjectNode device,
																		/*double llLimit,
																		double lLimit,
																		double hLimit,
																		double hhLimit,*/
																		String message) throws StatusException, UaInstantiationException {
		
		String llLimitNodeName = device.getBrowseName().getName() + "_AlrmEvtLL";
//		System.out.println("createNonExclusiveLimitAlarmNode ns: " + ns);
		NodeId llLimitNodeId=new NodeId(ns,llLimitNodeName);	//NormalStateNode in AppServer
		UaVariable llLimitNode=new PlainVariable<UaVariable>(this, llLimitNodeId, llLimitNodeName, LocalizedText.NO_LOCALE);
		llLimitNode.setTypeDefinitionId(Identifiers.BaseDataVariableType);
//		llLimitNode.setValue(llLimit);
		llLimitNode.setBrowseName(new QualifiedName("AlrmEvtLL"));
		llLimitNode.setDisplayName(new LocalizedText("AlrmEvtLL"));
		
		String lLimitNodeName = device.getBrowseName().getName() + "_AlrmEvtL";
		NodeId lLimitNodeId=new NodeId(ns,lLimitNodeName);	//NormalStateNode in AppServer
		UaVariable lLimitNode=new PlainVariable<UaVariable>(this, lLimitNodeId, lLimitNodeName, LocalizedText.NO_LOCALE);
		lLimitNode.setTypeDefinitionId(Identifiers.BaseDataVariableType);
//		lLimitNode.setValue(lLimit);
		lLimitNode.setBrowseName(new QualifiedName("AlrmEvtL"));
		
		String hLimitNodeName = device.getBrowseName().getName() + "_AlrmEvtH";
		NodeId hLimitNodeId=new NodeId(ns,hLimitNodeName);	//NormalStateNode in AppServer
		UaVariable hLimitNode=new PlainVariable<UaVariable>(this, hLimitNodeId, hLimitNodeName, LocalizedText.NO_LOCALE);
		hLimitNode.setTypeDefinitionId(Identifiers.BaseDataVariableType);
//		hLimitNode.setValue(hLimit);
		hLimitNode.setBrowseName(new QualifiedName("AlrmEvtH"));
		
		String hhLimitNodeName = device.getBrowseName().getName() + "_AlrmEvtHH";
		NodeId hhLimitNodeId=new NodeId(ns,hhLimitNodeName);	//NormalStateNode in AppServer
		UaVariable hhLimitNode=new PlainVariable<UaVariable>(this, hhLimitNodeId, hhLimitNodeName, LocalizedText.NO_LOCALE);
		hhLimitNode.setTypeDefinitionId(Identifiers.BaseDataVariableType);
//		hhLimitNode.setValue(hhLimit);
		hhLimitNode.setBrowseName(new QualifiedName("AlrmEvtHH"));
		
		
		final NodeId myAlarmId = new NodeId(ns, device.getNodeId().getValue() + "_Alrm");
		String name = device.getBrowseName().getName() + "_Alrm";
		
		NonExclusiveLimitAlarmType myAlarm = createInstance(NonExclusiveLimitAlarmType.class, name, myAlarmId);
		myAlarm.setSourceNode(source.getNodeId());
		myAlarm.setInputNode(source.getNodeId());

		myAlarm.setMessage(new LocalizedText(message));
		myAlarm.addComponent(llLimitNode);
		myAlarm.addComponent(lLimitNode);
		myAlarm.addComponent(hLimitNode);
		myAlarm.addComponent(hhLimitNode);
		myAlarm.setSeverity(new UnsignedShort(500));
		myAlarm.setActiveState(new LocalizedText(message));
   
		device.addComponent(myAlarm); // addReference(...Identifiers.HasComponent...)

		// + HasCondition, the SourceNode of the reference should normally
		// correspond to the Source set above
		source.addReference(myAlarm, Identifiers.HasCondition, false);
		device.addReference(source, Identifiers.HasEventSource, false);
		return myAlarm;
	}

	public UaVariable getVariable(int ns,String deviceName, String variableName) throws StatusException {
		UaVariable var=null;	//The variable to return
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode parameterSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.PARAMETER_SET));	//The parameterSet node
		
		//Browse name composed of namespace index + var name
		var=(UaVariable) parameterSetNode.getComponent(new QualifiedName(ns, variableName));
		
		return var;
	}
	
	public UaMethod getMethod(int ns,String deviceName, String methodName) throws StatusException {
		UaMethod meth=null;	//The method to return
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the method
		UaNode methodSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.METHOD_SET));	//The methodSet node
		
		//Browse name composed of namespace index + method name
		meth= (UaMethod) methodSetNode.getComponent(new QualifiedName(ns, methodName));
		
		return meth;
	}																							 
	
	public UaNode getParameterSet(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode parameterSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.PARAMETER_SET));	//The parameterSet node
		
		return parameterSetNode;
	}
	
	public UaNode getMethodSet(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		UaNode methodSetNode = deviceNode.getComponent(new QualifiedName(ns,TopologyElementType.METHOD_SET));	//The methodSet node
		
		return methodSetNode;
	}
	
	public UaNode getDevice(int ns,String deviceName) throws StatusException {
		NodeId deviceNodeId = new NodeId(ns,deviceName);	//Node Id formed only by the device's name
		UaNode deviceNode = this.getNode(deviceNodeId);	//The device node that ownes the variable
		
		return deviceNode;
	}

}
