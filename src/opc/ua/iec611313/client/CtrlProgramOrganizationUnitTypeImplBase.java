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
import com.prosysopc.ua.stack.builtintypes.XmlElement;
import com.prosysopc.ua.types.opcua.BaseDataVariableType;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.di.client.BlockTypeImpl;
import opc.ua.iec611313.CtrlProgramOrganizationUnitType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1003")
public abstract class CtrlProgramOrganizationUnitTypeImplBase extends BlockTypeImpl implements CtrlProgramOrganizationUnitType {
  protected CtrlProgramOrganizationUnitTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Optional
  @Override
  public BaseDataVariableType getBodyNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Body");
    return (BaseDataVariableType) getComponent(browseName);
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
  public void setBody(XmlElement value) throws StatusException {
    UaVariable node = getBodyNode();
    if (node == null) {
      throw new RuntimeException("Setting Body failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}
