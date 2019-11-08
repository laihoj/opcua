// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import java.lang.String;

/**
 * Adds the concept of Blocks needed for block-oriented FieldDevices
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1003")
public interface BlockType extends TopologyElementType {
  String REVISION_COUNTER = "RevisionCounter";

  String ACTUAL_MODE = "ActualMode";

  String PERMITTED_MODE = "PermittedMode";

  String NORMAL_MODE = "NormalMode";

  String TARGET_MODE = "TargetMode";

  @Optional
  UaProperty getRevisionCounterNode();

  @Optional
  UnsignedInteger getRevisionCounter();

  @Optional
  void setRevisionCounter(UnsignedInteger value) throws StatusException;

  @Optional
  UaProperty getActualModeNode();

  @Optional
  LocalizedText getActualMode();

  @Optional
  void setActualMode(LocalizedText value) throws StatusException;

  @Optional
  UaProperty getPermittedModeNode();

  @Optional
  LocalizedText[] getPermittedMode();

  @Optional
  void setPermittedMode(LocalizedText[] value) throws StatusException;

  @Optional
  UaProperty getNormalModeNode();

  @Optional
  LocalizedText[] getNormalMode();

  @Optional
  void setNormalMode(LocalizedText[] value) throws StatusException;

  @Optional
  UaProperty getTargetModeNode();

  @Optional
  LocalizedText[] getTargetMode();

  @Optional
  void setTargetMode(LocalizedText[] value) throws StatusException;
}
