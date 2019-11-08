// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Optional;
import com.prosysopc.ua.stack.builtintypes.XmlElement;
import com.prosysopc.ua.types.opcua.BaseDataVariableType;
import java.lang.String;
import opc.ua.di.BlockType;

/**
 * Generated on 2019-11-05 12:19:46
 */
@TypeDefinitionId("nsu=http://PLCopen.org/OpcUa/IEC61131-3/;i=1003")
public interface CtrlProgramOrganizationUnitType extends BlockType {
  String BODY = "Body";

  @Optional
  BaseDataVariableType getBodyNode();

  @Optional
  XmlElement getBody();

  @Optional
  void setBody(XmlElement value) throws StatusException;
}
