// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.ServiceContext;
import java.lang.Integer;

public abstract interface LockingServicesTypeRenewLockMethod {
  Integer renewLock(ServiceContext serviceContext, LockingServicesTypeNode node) throws
      StatusException;
}
