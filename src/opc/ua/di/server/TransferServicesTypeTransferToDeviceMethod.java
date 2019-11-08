// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.ServiceContext;
import opc.ua.di.TransferServicesType;

public abstract interface TransferServicesTypeTransferToDeviceMethod {
  TransferServicesType.TransferToDeviceMethodOutputs transferToDevice(ServiceContext serviceContext,
      TransferServicesTypeNode node) throws StatusException;
}
