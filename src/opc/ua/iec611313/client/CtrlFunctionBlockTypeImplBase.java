// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.client;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.types.opcua.BaseDataVariableType;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.iec611313.CtrlFunctionBlockType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1005")
public abstract class CtrlFunctionBlockTypeImplBase extends CtrlProgramOrganizationUnitTypeImpl implements CtrlFunctionBlockType {
  protected CtrlFunctionBlockTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Optional
  @Override
  public BaseDataVariableType getFunctionBlockNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "FunctionBlock");
    return (BaseDataVariableType) getComponent(browseName);
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
  public void setFunctionBlock(Object value) throws StatusException {
    UaVariable node = getFunctionBlockNode();
    if (node == null) {
      throw new RuntimeException("Setting FunctionBlock failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}
