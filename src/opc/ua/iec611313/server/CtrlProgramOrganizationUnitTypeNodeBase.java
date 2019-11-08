// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.stack.builtintypes.XmlElement;
import com.prosysopc.ua.types.opcua.server.BaseDataVariableTypeNode;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.di.server.BlockTypeNode;
import opc.ua.iec611313.CtrlProgramOrganizationUnitType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1003")
public abstract class CtrlProgramOrganizationUnitTypeNodeBase extends BlockTypeNode implements CtrlProgramOrganizationUnitType {
  private static GeneratedNodeInitializer<CtrlProgramOrganizationUnitTypeNode> ctrlProgramOrganizationUnitTypeNodeInitializer;

  protected CtrlProgramOrganizationUnitTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getBodyNode());
    GeneratedNodeInitializer<CtrlProgramOrganizationUnitTypeNode> impl = getCtrlProgramOrganizationUnitTypeNodeInitializer();
    if(impl != null) {
      impl.init((CtrlProgramOrganizationUnitTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CtrlProgramOrganizationUnitTypeNode> getCtrlProgramOrganizationUnitTypeNodeInitializer() {
    return ctrlProgramOrganizationUnitTypeNodeInitializer;
  }

  public static void setCtrlProgramOrganizationUnitTypeNodeInitializer(GeneratedNodeInitializer<CtrlProgramOrganizationUnitTypeNode> ctrlProgramOrganizationUnitTypeNodeInitializerNewValue) {
    ctrlProgramOrganizationUnitTypeNodeInitializer=ctrlProgramOrganizationUnitTypeNodeInitializerNewValue;
  }

  @Optional
  @Override
  public BaseDataVariableTypeNode getBodyNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Body");
    return (BaseDataVariableTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public XmlElement getBody() {
    UaVariable node = getBodyNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (XmlElement) value;
  }

  @Optional
  @Override
  public void setBody(XmlElement value) {
    UaVariable node = getBodyNode();
    if (node == null) {
      throw new RuntimeException("Setting Body failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Body failed unexpectedly", e);
    }
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
