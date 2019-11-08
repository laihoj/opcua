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
import java.lang.Override;
import opc.ua.di.ConnectionPointType;
import opc.ua.di.FunctionalGroupType;

/**
 * Represents the interface (interface card) of a Device to a Network.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6308")
public abstract class ConnectionPointTypeImplBase extends TopologyElementTypeImpl implements ConnectionPointType {
  protected ConnectionPointTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Mandatory
  @Override
  public FunctionalGroupType getNetworkAddressNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "NetworkAddress");
    return (FunctionalGroupType) getComponent(browseName);
  }
}
