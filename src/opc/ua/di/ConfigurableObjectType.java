// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import com.prosysopc.ua.types.opcua.FolderType;
import java.lang.String;

/**
 * Defines a general pattern to expose and configure modular components
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1004")
public interface ConfigurableObjectType extends BaseObjectType {
  String SUPPORTED_TYPES = "SupportedTypes";

  String OBJECT_IDENTIFIER = "<ObjectIdentifier>";

  @Mandatory
  FolderType getSupportedTypesNode();
}
