// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.di.BlockType;

/**
 * Adds the concept of Blocks needed for block-oriented FieldDevices
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1003")
public abstract class BlockTypeImplBase extends TopologyElementTypeImpl implements BlockType {
  protected BlockTypeImplBase(AddressSpace addressSpace, NodeId nodeId, QualifiedName browseName,
      LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Optional
  @Override
  public UaProperty getRevisionCounterNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "RevisionCounter");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public UnsignedInteger getRevisionCounter() {
    UaVariable node = getRevisionCounterNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (UnsignedInteger) value;
  }

  @Optional
  @Override
  public void setRevisionCounter(UnsignedInteger value) throws StatusException {
    UaVariable node = getRevisionCounterNode();
    if (node == null) {
      throw new RuntimeException("Setting RevisionCounter failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getActualModeNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "ActualMode");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public LocalizedText getActualMode() {
    UaVariable node = getActualModeNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText) value;
  }

  @Optional
  @Override
  public void setActualMode(LocalizedText value) throws StatusException {
    UaVariable node = getActualModeNode();
    if (node == null) {
      throw new RuntimeException("Setting ActualMode failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getPermittedModeNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "PermittedMode");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public LocalizedText[] getPermittedMode() {
    UaVariable node = getPermittedModeNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText[]) value;
  }

  @Optional
  @Override
  public void setPermittedMode(LocalizedText[] value) throws StatusException {
    UaVariable node = getPermittedModeNode();
    if (node == null) {
      throw new RuntimeException("Setting PermittedMode failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getNormalModeNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "NormalMode");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public LocalizedText[] getNormalMode() {
    UaVariable node = getNormalModeNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText[]) value;
  }

  @Optional
  @Override
  public void setNormalMode(LocalizedText[] value) throws StatusException {
    UaVariable node = getNormalModeNode();
    if (node == null) {
      throw new RuntimeException("Setting NormalMode failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Optional
  @Override
  public UaProperty getTargetModeNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "TargetMode");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public LocalizedText[] getTargetMode() {
    UaVariable node = getTargetModeNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText[]) value;
  }

  @Optional
  @Override
  public void setTargetMode(LocalizedText[] value) throws StatusException {
    UaVariable node = getTargetModeNode();
    if (node == null) {
      throw new RuntimeException("Setting TargetMode failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}
