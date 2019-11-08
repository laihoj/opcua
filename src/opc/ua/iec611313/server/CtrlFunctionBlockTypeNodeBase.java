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
import com.prosysopc.ua.types.opcua.server.BaseDataVariableTypeNode;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.iec611313.CtrlFunctionBlockType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1005")
public abstract class CtrlFunctionBlockTypeNodeBase extends CtrlProgramOrganizationUnitTypeNode implements CtrlFunctionBlockType {
  private static GeneratedNodeInitializer<CtrlFunctionBlockTypeNode> ctrlFunctionBlockTypeNodeInitializer;

  protected CtrlFunctionBlockTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getFunctionBlockNode());
    GeneratedNodeInitializer<CtrlFunctionBlockTypeNode> impl = getCtrlFunctionBlockTypeNodeInitializer();
    if(impl != null) {
      impl.init((CtrlFunctionBlockTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CtrlFunctionBlockTypeNode> getCtrlFunctionBlockTypeNodeInitializer() {
    return ctrlFunctionBlockTypeNodeInitializer;
  }

  public static void setCtrlFunctionBlockTypeNodeInitializer(GeneratedNodeInitializer<CtrlFunctionBlockTypeNode> ctrlFunctionBlockTypeNodeInitializerNewValue) {
    ctrlFunctionBlockTypeNodeInitializer=ctrlFunctionBlockTypeNodeInitializerNewValue;
  }

  @Optional
  @Override
  public BaseDataVariableTypeNode getFunctionBlockNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "FunctionBlock");
    return (BaseDataVariableTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public Object getFunctionBlock() {
    UaVariable node = getFunctionBlockNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Object) value;
  }

  @Optional
  @Override
  public void setFunctionBlock(Object value) {
    UaVariable node = getFunctionBlockNode();
    if (node == null) {
      throw new RuntimeException("Setting FunctionBlock failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting FunctionBlock failed unexpectedly", e);
    }
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
