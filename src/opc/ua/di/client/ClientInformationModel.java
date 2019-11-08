// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.client;

import com.prosysopc.ua.client.ClientCodegenModel;
import com.prosysopc.ua.client.ClientCodegenModelProvider;
import java.lang.Override;
import opc.ua.di.DataTypeDictionaryHelper;
import opc.ua.di.DeviceHealthEnumeration;
import opc.ua.di.FetchResultDataDataType;
import opc.ua.di.FetchResultDataType;
import opc.ua.di.FetchResultErrorDataType;
import opc.ua.di.ParameterResultDataType;
import opc.ua.di.Serializers;

/**
 * Generated on 2019-11-05 12:19:47
 */
public class ClientInformationModel implements ClientCodegenModelProvider {
  public static final ClientCodegenModel MODEL;

  static {
    ClientCodegenModel.Builder b = ClientCodegenModel.builder();
    b.addClass(TopologyElementTypeImpl.class);
    b.addClass(DeviceTypeImpl.class);
    b.addClass(BlockTypeImpl.class);
    b.addClass(ConfigurableObjectTypeImpl.class);
    b.addClass(FunctionalGroupTypeImpl.class);
    b.addClass(ProtocolTypeImpl.class);
    b.addClass(UIElementTypeImpl.class);
    b.addClass(NetworkTypeImpl.class);
    b.addClass(ConnectionPointTypeImpl.class);
    b.addClass(TransferServicesTypeImpl.class);
    b.addClass(LockingServicesTypeImpl.class);
    b.addSerializers(Serializers.SERIALIZERS);
    b.setDataTypeDictionary(DataTypeDictionaryHelper.createDataTypeDictionary());
    b.addStructureSpecification(FetchResultDataType.SPECIFICATION);
    b.addStructureSpecification(FetchResultErrorDataType.SPECIFICATION);
    b.addStructureSpecification(FetchResultDataDataType.SPECIFICATION);
    b.addStructureSpecification(ParameterResultDataType.SPECIFICATION);
    b.addEnumerationSpecification(DeviceHealthEnumeration.SPECIFICATION);
    MODEL = b.build();
  }

  @Override
  public ClientCodegenModel get() {
    return MODEL;
  }
}
