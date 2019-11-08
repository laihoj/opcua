// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.client;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.types.opcua.client.BaseObjectTypeImpl;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import opc.ua.iec611313.CtrlTaskType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1006")
public abstract class CtrlTaskTypeImplBase extends BaseObjectTypeImpl implements CtrlTaskType {
  protected CtrlTaskTypeImplBase(AddressSpace addressSpace, NodeId nodeId, QualifiedName browseName,
      LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Mandatory
  @Override
  public UaProperty getPriorityNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Priority");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public UnsignedInteger getPriority() {
    UaVariable node = getPriorityNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (UnsignedInteger) value;
  }

  @Mandatory
  @Override
  public void setPriority(UnsignedInteger value) throws StatusException {
    UaVariable node = getPriorityNode();
    if (node == null) {
      throw new RuntimeException("Setting Priority failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getIntervalNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Interval");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public String getInterval() {
    UaVariable node = getIntervalNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Optional
  @Override
  public void setInterval(String value) throws StatusException {
    UaVariable node = getIntervalNode();
    if (node == null) {
      throw new RuntimeException("Setting Interval failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getSingleNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Single");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public String getSingle() {
    UaVariable node = getSingleNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Optional
  @Override
  public void setSingle(String value) throws StatusException {
    UaVariable node = getSingleNode();
    if (node == null) {
      throw new RuntimeException("Setting Single failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}
