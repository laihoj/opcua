// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.server.ServerCodegenModel;
import com.prosysopc.ua.server.ServerCodegenModelProvider;
import java.lang.Override;
import java.lang.RuntimeException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
public class ServerInformationModel implements ServerCodegenModelProvider {
  public static final ServerCodegenModel MODEL;

  static {
    ServerCodegenModel.Builder b = ServerCodegenModel.builder();
    b.addClass(TopologyElementTypeNode.class);
    b.addClass(DeviceTypeNode.class);
    b.addClass(BlockTypeNode.class);
    b.addClass(ConfigurableObjectTypeNode.class);
    b.addClass(FunctionalGroupTypeNode.class);
    b.addClass(ProtocolTypeNode.class);
    b.addClass(UIElementTypeNode.class);
    b.addClass(NetworkTypeNode.class);
    b.addClass(ConnectionPointTypeNode.class);
    b.addClass(TransferServicesTypeNode.class);
    b.addClass(LockingServicesTypeNode.class);
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
  public ServerCodegenModel get() {
    return MODEL;
  }

  /**
   * Returns URI for the NodeSet XML file 'Opc.Ua.Di.NodeSet2.xml', assuming it is put into classpath next to classfile of this class. You can use the 'server_model' codegen target to do it automatically as part of the code generation. If the file is not found this method will throw RuntimeException.
   */
  public static URI getLocationURI() {
    URL nodeset = ServerInformationModel.class.getResource("Opc.Ua.Di.NodeSet2.xml");
    if (nodeset == null) {
      throw new RuntimeException("Cannot find nodeset 'Opc.Ua.Di.NodeSet2.xml' in classpath next to "+ServerInformationModel.class);
    }
    try {
      return nodeset.toURI();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
