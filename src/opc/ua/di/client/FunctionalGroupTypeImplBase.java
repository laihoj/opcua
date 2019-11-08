// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.types.opcua.client.FolderTypeImpl;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import opc.ua.di.FunctionalGroupType;
import opc.ua.di.UIElementType;

/**
 * FolderType is used to organize the Parameters and Methods from the complete set (ParameterSet, MethodSet) with regard to their application
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1005")
public abstract class FunctionalGroupTypeImplBase extends FolderTypeImpl implements FunctionalGroupType {
  protected FunctionalGroupTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Optional
  @Override
  public UIElementType getUIElementNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "UIElement");
    return (UIElementType) getComponent(browseName);
  }

  @Optional
  @Override
  public Object getUIElement() {
    UaVariable node = getUIElementNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Object) value;
  }

  @Optional
  @Override
  public void setUIElement(Object value) throws StatusException {
    UaVariable node = getUIElementNode();
    if (node == null) {
      throw new RuntimeException("Setting UIElement failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}
