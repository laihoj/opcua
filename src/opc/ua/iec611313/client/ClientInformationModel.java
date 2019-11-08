// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.iec611313.client;

import com.prosysopc.ua.client.ClientCodegenModel;
import com.prosysopc.ua.client.ClientCodegenModelProvider;
import java.lang.Override;
import opc.ua.iec611313.DataTypeDictionaryHelper;
import opc.ua.iec611313.Serializers;

/**
 * Generated on 2019-11-05 12:19:46
 */
public class ClientInformationModel implements ClientCodegenModelProvider {
  public static final ClientCodegenModel MODEL;

  static {
    ClientCodegenModel.Builder b = ClientCodegenModel.builder();
    b.addClass(CtrlConfigurationTypeImpl.class);
    b.addClass(CtrlResourceTypeImpl.class);
    b.addClass(CtrlProgramOrganizationUnitTypeImpl.class);
    b.addClass(CtrlProgramTypeImpl.class);
    b.addClass(CtrlFunctionBlockTypeImpl.class);
    b.addClass(CtrlTaskTypeImpl.class);
    b.addClass(SFCTypeImpl.class);
    b.addSerializers(Serializers.SERIALIZERS);
    b.setDataTypeDictionary(DataTypeDictionaryHelper.createDataTypeDictionary());
    MODEL = b.build();
  }

  @Override
  public ClientCodegenModel get() {
    return MODEL;
  }
}
