// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.types.opcua.FolderType;
import java.lang.Integer;
import java.lang.String;

/**
 * Defines the basic information components for all configurable elements in a device topology
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1002")
public interface DeviceType extends TopologyElementType {
  String SERIAL_NUMBER = "SerialNumber";

  String REVISION_COUNTER = "RevisionCounter";

  String MANUFACTURER = "Manufacturer";

  String MODEL = "Model";

  String DEVICE_MANUAL = "DeviceManual";

  String DEVICE_REVISION = "DeviceRevision";

  String SOFTWARE_REVISION = "SoftwareRevision";

  String HARDWARE_REVISION = "HardwareRevision";

  String DEVICE_CLASS = "DeviceClass";

  String DEVICE_HEALTH = "DeviceHealth";

  String DEVICE_TYPE_IMAGE = "DeviceTypeImage";

  String DOCUMENTATION = "Documentation";

  String PROTOCOL_SUPPORT = "ProtocolSupport";

  String IMAGE_SET = "ImageSet";

  String C_P_IDENTIFIER = "<CPIdentifier>";

  @Mandatory
  UaProperty getSerialNumberNode();

  @Mandatory
  String getSerialNumber();

  @Mandatory
  void setSerialNumber(String value) throws StatusException;

  @Mandatory
  UaProperty getRevisionCounterNode();

  @Mandatory
  Integer getRevisionCounter();

  @Mandatory
  void setRevisionCounter(Integer value) throws StatusException;

  @Mandatory
  UaProperty getManufacturerNode();

  @Mandatory
  LocalizedText getManufacturer();

  @Mandatory
  void setManufacturer(LocalizedText value) throws StatusException;

  @Mandatory
  UaProperty getModelNode();

  @Mandatory
  LocalizedText getModel();

  @Mandatory
  void setModel(LocalizedText value) throws StatusException;

  @Mandatory
  UaProperty getDeviceManualNode();

  @Mandatory
  String getDeviceManual();

  @Mandatory
  void setDeviceManual(String value) throws StatusException;

  @Mandatory
  UaProperty getDeviceRevisionNode();

  @Mandatory
  String getDeviceRevision();

  @Mandatory
  void setDeviceRevision(String value) throws StatusException;

  @Mandatory
  UaProperty getSoftwareRevisionNode();

  @Mandatory
  String getSoftwareRevision();

  @Mandatory
  void setSoftwareRevision(String value) throws StatusException;

  @Mandatory
  UaProperty getHardwareRevisionNode();

  @Mandatory
  String getHardwareRevision();

  @Mandatory
  void setHardwareRevision(String value) throws StatusException;

  @Optional
  UaProperty getDeviceClassNode();

  @Optional
  String getDeviceClass();

  @Optional
  void setDeviceClass(String value) throws StatusException;

  @Optional
  UaProperty getDeviceHealthNode();

  @Optional
  DeviceHealthEnumeration getDeviceHealth();

  @Optional
  void setDeviceHealth(DeviceHealthEnumeration value) throws StatusException;

  @Optional
  FolderType getDeviceTypeImageNode();

  @Optional
  FolderType getDocumentationNode();

  @Optional
  FolderType getProtocolSupportNode();

  @Optional
  FolderType getImageSetNode();
}
