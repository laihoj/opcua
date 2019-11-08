// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.types.opcua.FolderType;
import com.prosysopc.ua.types.opcua.client.BaseObjectTypeImpl;
import java.lang.Override;
import opc.ua.di.ConfigurableObjectType;

/**
 * Defines a general pattern to expose and configure modular components
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1004")
public abstract class ConfigurableObjectTypeImplBase extends BaseObjectTypeImpl implements ConfigurableObjectType {
  protected ConfigurableObjectTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Mandatory
  @Override
  public FolderType getSupportedTypesNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "SupportedTypes");
    return (FolderType) getComponent(browseName);
  }
}
