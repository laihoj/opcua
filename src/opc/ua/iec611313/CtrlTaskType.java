// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import java.lang.String;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1006")
public interface CtrlTaskType extends BaseObjectType {
  String PRIORITY = "Priority";

  String INTERVAL = "Interval";

  String SINGLE = "Single";

  @Mandatory
  UaProperty getPriorityNode();

  @Mandatory
  UnsignedInteger getPriority();

  @Mandatory
  void setPriority(UnsignedInteger value) throws StatusException;

  @Optional
  UaProperty getIntervalNode();

  @Optional
  String getInterval();

  @Optional
  void setInterval(String value) throws StatusException;

  @Optional
  UaProperty getSingleNode();

  @Optional
  String getSingle();

  @Optional
  void setSingle(String value) throws StatusException;
}
