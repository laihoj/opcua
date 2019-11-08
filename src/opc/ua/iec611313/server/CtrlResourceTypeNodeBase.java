// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.types.opcua.server.BaseObjectTypeNode;
import java.lang.Override;
import opc.ua.di.server.ConfigurableObjectTypeNode;
import opc.ua.di.server.DeviceTypeNode;
import opc.ua.di.server.FunctionalGroupTypeNode;
import opc.ua.iec611313.CtrlResourceType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1002")
public abstract class CtrlResourceTypeNodeBase extends DeviceTypeNode implements CtrlResourceType {
  private static GeneratedNodeInitializer<CtrlResourceTypeNode> ctrlResourceTypeNodeInitializer;

  protected CtrlResourceTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getMethodSetNode());
    callAfterCreateIfExists(getTasksNode());
    callAfterCreateIfExists(getProgramsNode());
    callAfterCreateIfExists(getGlobalVarsNode());
    callAfterCreateIfExists(getConfigurationNode());
    callAfterCreateIfExists(getDiagnosticNode());
    GeneratedNodeInitializer<CtrlResourceTypeNode> impl = getCtrlResourceTypeNodeInitializer();
    if(impl != null) {
      impl.init((CtrlResourceTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CtrlResourceTypeNode> getCtrlResourceTypeNodeInitializer() {
    return ctrlResourceTypeNodeInitializer;
  }

  public static void setCtrlResourceTypeNodeInitializer(GeneratedNodeInitializer<CtrlResourceTypeNode> ctrlResourceTypeNodeInitializerNewValue) {
    ctrlResourceTypeNodeInitializer=ctrlResourceTypeNodeInitializerNewValue;
  }

  @Optional
  @Override
  public BaseObjectTypeNode getMethodSetNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "MethodSet");
    return (BaseObjectTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public ConfigurableObjectTypeNode getTasksNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Tasks");
    return (ConfigurableObjectTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public ConfigurableObjectTypeNode getProgramsNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Programs");
    return (ConfigurableObjectTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FunctionalGroupTypeNode getGlobalVarsNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "GlobalVars");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FunctionalGroupTypeNode getConfigurationNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Configuration");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FunctionalGroupTypeNode getDiagnosticNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Diagnostic");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
