// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;

/**
 * Represents the communication means for Devices that are connected to it.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6247")
public class NetworkTypeImpl extends NetworkTypeImplBase {
  protected NetworkTypeImpl(AddressSpace addressSpace, NodeId nodeId, QualifiedName browseName,
      LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }
}
