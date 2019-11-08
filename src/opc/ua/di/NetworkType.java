// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import java.lang.String;

/**
 * Represents the communication means for Devices that are connected to it.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6247")
public interface NetworkType extends BaseObjectType {
  String PROFILE_IDENTIFIER = "<ProfileIdentifier>";

  String LOCK = "Lock";

  @Optional
  LockingServicesType getLockNode();
}
