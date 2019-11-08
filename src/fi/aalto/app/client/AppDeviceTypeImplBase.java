// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package fi.aalto.app.client;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import fi.aalto.app.AppDeviceType;
import opc.ua.di.client.DeviceTypeImpl;

/**
 * Generated on 2019-11-05 12:19:48
 */
@TypeDefinitionId("nsu=http://org.aalto.fi/AppServer;i=1002")
public abstract class AppDeviceTypeImplBase extends DeviceTypeImpl implements AppDeviceType {
  protected AppDeviceTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }
}
