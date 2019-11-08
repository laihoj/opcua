// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.MethodArgumentTransformer;
import com.prosysopc.ua.MethodCallStatusException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.stack.transport.AsyncResult;
import com.prosysopc.ua.types.opcua.client.BaseObjectTypeImpl;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import opc.ua.di.LockingServicesType;

/**
 * An interface for Locking.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6388")
public abstract class LockingServicesTypeImplBase extends BaseObjectTypeImpl implements LockingServicesType {
  protected LockingServicesTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Mandatory
  @Override
  public UaProperty getLockedNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "Locked");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Boolean isLocked() {
    UaVariable node = getLockedNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Boolean) value;
  }

  @Mandatory
  @Override
  public void setLocked(Boolean value) throws StatusException {
    UaVariable node = getLockedNode();
    if (node == null) {
      throw new RuntimeException("Setting Locked failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Mandatory
  @Override
  public UaProperty getLockingClientNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "LockingClient");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getLockingClient() {
    UaVariable node = getLockingClientNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setLockingClient(String value) throws StatusException {
    UaVariable node = getLockingClientNode();
    if (node == null) {
      throw new RuntimeException("Setting LockingClient failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Mandatory
  @Override
  public UaProperty getLockingUserNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "LockingUser");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getLockingUser() {
    UaVariable node = getLockingUserNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setLockingUser(String value) throws StatusException {
    UaVariable node = getLockingUserNode();
    if (node == null) {
      throw new RuntimeException("Setting LockingUser failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Mandatory
  @Override
  public UaProperty getRemainingLockTimeNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "RemainingLockTime");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Double getRemainingLockTime() {
    UaVariable node = getRemainingLockTimeNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setRemainingLockTime(Double value) throws StatusException {
    UaVariable node = getRemainingLockTimeNode();
    if (node == null) {
      throw new RuntimeException("Setting RemainingLockTime failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Mandatory
  @Override
  public UaMethod getInitLockNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "InitLock");
    return (UaMethod) getComponent(browseName);
  }

  @Override
  public Integer initLock(String currentContext) throws MethodCallStatusException,
      ServiceException {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "InitLock"));
    return call(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    }, (Object)currentContext);
  }

  public AsyncResult<? extends Integer> initLockAsync(String currentContext) {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "InitLock"));
    return callAsync(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    }, (Object)currentContext);
  }

  @Mandatory
  @Override
  public UaMethod getRenewLockNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "RenewLock");
    return (UaMethod) getComponent(browseName);
  }

  @Override
  public Integer renewLock() throws MethodCallStatusException, ServiceException {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "RenewLock"));
    return call(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }

  public AsyncResult<? extends Integer> renewLockAsync() {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "RenewLock"));
    return callAsync(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }

  @Mandatory
  @Override
  public UaMethod getExitLockNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "ExitLock");
    return (UaMethod) getComponent(browseName);
  }

  @Override
  public Integer exitLock() throws MethodCallStatusException, ServiceException {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "ExitLock"));
    return call(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }

  public AsyncResult<? extends Integer> exitLockAsync() {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "ExitLock"));
    return callAsync(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }

  @Mandatory
  @Override
  public UaMethod getBreakLockNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "BreakLock");
    return (UaMethod) getComponent(browseName);
  }

  @Override
  public Integer breakLock() throws MethodCallStatusException, ServiceException {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "BreakLock"));
    return call(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }

  public AsyncResult<? extends Integer> breakLockAsync() {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/DI/", "BreakLock"));
    return callAsync(methodId, new MethodArgumentTransformer<Integer>() {
      @Override
      public Integer fromVariantArray(Variant[] values) {
        return (Integer) values[0].getValue();
      }
    });
  }
}
