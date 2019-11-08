// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import java.lang.String;

/**
 * Represents the interface (interface card) of a Device to a Network.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6308")
public interface ConnectionPointType extends TopologyElementType {
  String NETWORK_ADDRESS = "NetworkAddress";

  String PROFILE_ID = "<ProfileId>";

  @Mandatory
  FunctionalGroupType getNetworkAddressNode();
}
