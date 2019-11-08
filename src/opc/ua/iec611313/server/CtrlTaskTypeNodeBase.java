// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.types.opcua.server.BaseObjectTypeNode;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import opc.ua.iec611313.CtrlTaskType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1006")
public abstract class CtrlTaskTypeNodeBase extends BaseObjectTypeNode implements CtrlTaskType {
  private static GeneratedNodeInitializer<CtrlTaskTypeNode> ctrlTaskTypeNodeInitializer;

  protected CtrlTaskTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    GeneratedNodeInitializer<CtrlTaskTypeNode> impl = getCtrlTaskTypeNodeInitializer();
    if(impl != null) {
      impl.init((CtrlTaskTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CtrlTaskTypeNode> getCtrlTaskTypeNodeInitializer() {
    return ctrlTaskTypeNodeInitializer;
  }

  public static void setCtrlTaskTypeNodeInitializer(GeneratedNodeInitializer<CtrlTaskTypeNode> ctrlTaskTypeNodeInitializerNewValue) {
    ctrlTaskTypeNodeInitializer=ctrlTaskTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public UaProperty getPriorityNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Priority");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public UnsignedInteger getPriority() {
    UaVariable node = getPriorityNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Priority does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (UnsignedInteger) value;
  }

  @Mandatory
  @Override
  public void setPriority(UnsignedInteger value) {
    UaVariable node = getPriorityNode();
    if (node == null) {
      throw new RuntimeException("Setting Priority failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Priority failed unexpectedly", e);
    }
  }

  public void setPriority(long value) {
    setPriority(UnsignedInteger.valueOf(value));
  }

  @Optional
  @Override
  public UaProperty getIntervalNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Interval");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public String getInterval() {
    UaVariable node = getIntervalNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Optional
  @Override
  public void setInterval(String value) {
    UaVariable node = getIntervalNode();
    if (node == null) {
      throw new RuntimeException("Setting Interval failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Interval failed unexpectedly", e);
    }
  }

  @Optional
  @Override
  public UaProperty getSingleNode() {
    QualifiedName browseName = getQualifiedName("http://PLCopen.org/OpcUa/IEC61131-3/", "Single");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public String getSingle() {
    UaVariable node = getSingleNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Optional
  @Override
  public void setSingle(String value) {
    UaVariable node = getSingleNode();
    if (node == null) {
      throw new RuntimeException("Setting Single failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Single failed unexpectedly", e);
    }
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
