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
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1001")
public interface CtrlConfigurationType extends DeviceType {
  String METHOD_SET = "MethodSet";

  String RESOURCES = "Resources";

  String GLOBAL_VARS = "GlobalVars";

  String ACCESS_VARS = "AccessVars";

  String CONFIG_VARS = "ConfigVars";

  String CONFIGURATION = "Configuration";

  String DIAGNOSTIC = "Diagnostic";

  @Optional
  BaseObjectType getMethodSetNode();

  @Mandatory
  ConfigurableObjectType getResourcesNode();

  @Mandatory
  FunctionalGroupType getGlobalVarsNode();

  @Mandatory
  FunctionalGroupType getAccessVarsNode();

  @Mandatory
  FunctionalGroupType getConfigVarsNode();

  @Mandatory
  FunctionalGroupType getConfigurationNode();

  @Mandatory
  FunctionalGroupType getDiagnosticNode();
}
