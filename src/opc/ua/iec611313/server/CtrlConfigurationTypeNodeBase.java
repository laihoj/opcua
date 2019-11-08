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
import opc.ua.iec611313.CtrlConfigurationType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1001")
public abstract class CtrlConfigurationTypeNodeBase extends DeviceTypeNode implements CtrlConfigurationType {
  private static GeneratedNodeInitializer<CtrlConfigurationTypeNode> ctrlConfigurationTypeNodeInitializer;

  protected CtrlConfigurationTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getMethodSetNode());
    callAfterCreateIfExists(getResourcesNode());
    callAfterCreateIfExists(getGlobalVarsNode());
    callAfterCreateIfExists(getAccessVarsNode());
    callAfterCreateIfExists(getConfigVarsNode());
    callAfterCreateIfExists(getConfigurationNode());
    callAfterCreateIfExists(getDiagnosticNode());
    GeneratedNodeInitializer<CtrlConfigurationTypeNode> impl = getCtrlConfigurationTypeNodeInitializer();
    if(impl != null) {
      impl.init((CtrlConfigurationTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CtrlConfigurationTypeNode> getCtrlConfigurationTypeNodeInitializer() {
    return ctrlConfigurationTypeNodeInitializer;
  }

  public static void setCtrlConfigurationTypeNodeInitializer(GeneratedNodeInitializer<CtrlConfigurationTypeNode> ctrlConfigurationTypeNodeInitializerNewValue) {
    ctrlConfigurationTypeNodeInitializer=ctrlConfigurationTypeNodeInitializerNewValue;
  }

  @Optional
  @Override
  public BaseObjectTypeNode getMethodSetNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "MethodSet");
    return (BaseObjectTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public ConfigurableObjectTypeNode getResourcesNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Resources");
    return (ConfigurableObjectTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public FunctionalGroupTypeNode getGlobalVarsNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "GlobalVars");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public FunctionalGroupTypeNode getAccessVarsNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "AccessVars");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public FunctionalGroupTypeNode getConfigVarsNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "ConfigVars");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public FunctionalGroupTypeNode getConfigurationNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Configuration");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Mandatory
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
