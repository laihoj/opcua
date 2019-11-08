// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

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
import com.prosysopc.ua.stack.builtintypes.Variant;
import com.prosysopc.ua.types.opcua.server.FolderTypeNode;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import opc.ua.di.DeviceHealthEnumeration;
import opc.ua.di.DeviceType;

/**
 * Defines the basic information components for all configurable elements in a device topology
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1002")
public abstract class DeviceTypeNodeBase extends TopologyElementTypeNode implements DeviceType {
  private static GeneratedNodeInitializer<DeviceTypeNode> deviceTypeNodeInitializer;

  protected DeviceTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getDeviceTypeImageNode());
    callAfterCreateIfExists(getDocumentationNode());
    callAfterCreateIfExists(getProtocolSupportNode());
    callAfterCreateIfExists(getImageSetNode());
    GeneratedNodeInitializer<DeviceTypeNode> impl = getDeviceTypeNodeInitializer();
    if(impl != null) {
      impl.init((DeviceTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<DeviceTypeNode> getDeviceTypeNodeInitializer() {
    return deviceTypeNodeInitializer;
  }

  public static void setDeviceTypeNodeInitializer(GeneratedNodeInitializer<DeviceTypeNode> deviceTypeNodeInitializerNewValue) {
    deviceTypeNodeInitializer=deviceTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public UaProperty getSerialNumberNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "SerialNumber");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getSerialNumber() {
    UaVariable node = getSerialNumberNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node SerialNumber does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setSerialNumber(String value) {
    UaVariable node = getSerialNumberNode();
    if (node == null) {
      throw new RuntimeException("Setting SerialNumber failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting SerialNumber failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getRevisionCounterNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "RevisionCounter");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Integer getRevisionCounter() {
    UaVariable node = getRevisionCounterNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node RevisionCounter does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (Integer) value;
  }

  @Mandatory
  @Override
  public void setRevisionCounter(Integer value) {
    UaVariable node = getRevisionCounterNode();
    if (node == null) {
      throw new RuntimeException("Setting RevisionCounter failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting RevisionCounter failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getManufacturerNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "Manufacturer");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public LocalizedText getManufacturer() {
    UaVariable node = getManufacturerNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Manufacturer does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText) value;
  }

  @Mandatory
  @Override
  public void setManufacturer(LocalizedText value) {
    UaVariable node = getManufacturerNode();
    if (node == null) {
      throw new RuntimeException("Setting Manufacturer failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Manufacturer failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getModelNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "Model");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public LocalizedText getModel() {
    UaVariable node = getModelNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Model does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (LocalizedText) value;
  }

  @Mandatory
  @Override
  public void setModel(LocalizedText value) {
    UaVariable node = getModelNode();
    if (node == null) {
      throw new RuntimeException("Setting Model failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Model failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getDeviceManualNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "DeviceManual");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getDeviceManual() {
    UaVariable node = getDeviceManualNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node DeviceManual does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setDeviceManual(String value) {
    UaVariable node = getDeviceManualNode();
    if (node == null) {
      throw new RuntimeException("Setting DeviceManual failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting DeviceManual failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getDeviceRevisionNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "DeviceRevision");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getDeviceRevision() {
    UaVariable node = getDeviceRevisionNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node DeviceRevision does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setDeviceRevision(String value) {
    UaVariable node = getDeviceRevisionNode();
    if (node == null) {
      throw new RuntimeException("Setting DeviceRevision failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting DeviceRevision failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getSoftwareRevisionNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "SoftwareRevision");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getSoftwareRevision() {
    UaVariable node = getSoftwareRevisionNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node SoftwareRevision does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setSoftwareRevision(String value) {
    UaVariable node = getSoftwareRevisionNode();
    if (node == null) {
      throw new RuntimeException("Setting SoftwareRevision failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting SoftwareRevision failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getHardwareRevisionNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "HardwareRevision");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getHardwareRevision() {
    UaVariable node = getHardwareRevisionNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node HardwareRevision does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setHardwareRevision(String value) {
    UaVariable node = getHardwareRevisionNode();
    if (node == null) {
      throw new RuntimeException("Setting HardwareRevision failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting HardwareRevision failed unexpectedly", e);
    }
  }

  @Optional
  @Override
  public UaProperty getDeviceClassNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "DeviceClass");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public String getDeviceClass() {
    UaVariable node = getDeviceClassNode();
    if (node == null) {
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Optional
  @Override
  public void setDeviceClass(String value) {
    UaVariable node = getDeviceClassNode();
    if (node == null) {
      throw new RuntimeException("Setting DeviceClass failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting DeviceClass failed unexpectedly", e);
    }
  }

  @Optional
  @Override
  public UaProperty getDeviceHealthNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "DeviceHealth");
    return getProperty(browseName);
  }

  @Optional
  @Override
  public DeviceHealthEnumeration getDeviceHealth() {
    UaVariable node = getDeviceHealthNode();
    if (node == null) {
      return null;
    }
    Variant value = node.getValue().getValue();
    return (DeviceHealthEnumeration) value.asEnum(DeviceHealthEnumeration.class);
  }

  @Optional
  @Override
  public void setDeviceHealth(DeviceHealthEnumeration value) {
    UaVariable node = getDeviceHealthNode();
    if (node == null) {
      throw new RuntimeException("Setting DeviceHealth failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting DeviceHealth failed unexpectedly", e);
    }
  }

  @Optional
  @Override
  public FolderTypeNode getDeviceTypeImageNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "DeviceTypeImage");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FolderTypeNode getDocumentationNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "Documentation");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FolderTypeNode getProtocolSupportNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "ProtocolSupport");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Optional
  @Override
  public FolderTypeNode getImageSetNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "ImageSet");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
