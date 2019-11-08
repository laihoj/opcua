// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.types.opcua.FolderType;
import java.lang.Object;
import java.lang.String;

/**
 * FolderType is used to organize the Parameters and Methods from the complete set (ParameterSet, MethodSet) with regard to their application
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1005")
public interface FunctionalGroupType extends FolderType {
  String GROUP_IDENTIFIER = "<GroupIdentifier>";

  String U_I_ELEMENT = "UIElement";

  @Optional
  UIElementType getUIElementNode();

  @Optional
  Object getUIElement();

  @Optional
  void setUIElement(Object value) throws StatusException;
}
