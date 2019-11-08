// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package fi.aalto.app.client;

import com.prosysopc.ua.client.ClientCodegenModel;
import com.prosysopc.ua.client.ClientCodegenModelProvider;
import fi.aalto.app.DataTypeDictionaryHelper;
import fi.aalto.app.Serializers;
import java.lang.Override;

/**
 * Generated on 2019-11-05 12:19:48
 */
public class ClientInformationModel implements ClientCodegenModelProvider {
  public static final ClientCodegenModel MODEL;

  static {
    ClientCodegenModel.Builder b = ClientCodegenModel.builder();
    b.addClass(AppDeviceTypeImpl.class);
    b.addSerializers(Serializers.SERIALIZERS);
    b.setDataTypeDictionary(DataTypeDictionaryHelper.createDataTypeDictionary());
    MODEL = b.build();
  }

  @Override
  public ClientCodegenModel get() {
    return MODEL;
  }
}
