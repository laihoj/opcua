// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.stack.builtintypes.Structure;
import com.prosysopc.ua.types.opcua.BaseDataVariableType;
import java.lang.String;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1004")
public interface CtrlProgramType extends CtrlProgramOrganizationUnitType {
  String PROGRAM = "Program";

  @Optional
  BaseDataVariableType getProgramNode();

  @Optional
  Structure getProgram();

  @Optional
  void setProgram(Structure value) throws StatusException;
}
