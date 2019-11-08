// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313;

import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import java.lang.String;
import opc.ua.di.ConfigurableObjectType;
import opc.ua.di.DeviceType;
import opc.ua.di.FunctionalGroupType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1002")
public interface CtrlResourceType extends DeviceType {
  String METHOD_SET = "MethodSet";

  String TASKS = "Tasks";

  String PROGRAMS = "Programs";

  String GLOBAL_VARS = "GlobalVars";

  String CONFIGURATION = "Configuration";

  String DIAGNOSTIC = "Diagnostic";

  @Optional
  BaseObjectType getMethodSetNode();

  @Mandatory
  ConfigurableObjectType getTasksNode();

  @Mandatory
  ConfigurableObjectType getProgramsNode();

  @Optional
  FunctionalGroupType getGlobalVarsNode();

  @Optional
  FunctionalGroupType getConfigurationNode();

  @Optional
  FunctionalGroupType getDiagnosticNode();
}
