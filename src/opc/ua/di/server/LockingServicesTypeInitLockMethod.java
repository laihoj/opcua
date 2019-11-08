// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.ServiceContext;
import java.lang.Integer;
import java.lang.String;

public abstract interface LockingServicesTypeInitLockMethod {
  Integer initLock(ServiceContext serviceContext, LockingServicesTypeNode node,
      String currentContext) throws StatusException;
}
