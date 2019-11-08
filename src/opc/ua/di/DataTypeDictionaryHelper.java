// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;
import com.prosysopc.ua.typedictionary.GeneratedDataTypeDictionary;
import java.lang.String;

/**
 * Generated on 2019-11-05 12:19:47
 */
public class DataTypeDictionaryHelper {
  public static GeneratedDataTypeDictionary createDataTypeDictionary() {
    GeneratedDataTypeDictionary r = new GeneratedDataTypeDictionary("http://opcfoundation.org/UA/DI/");
    r.addTypeInformation(eni("nsu=http://opcfoundation.org/UA/DI/;i=6244"), "DeviceHealthEnumeration", DeviceHealthEnumeration.class);
    r.addTypeInformation(eni("nsu=http://opcfoundation.org/UA/DI/;i=6522"), "FetchResultDataType", FetchResultDataType.class);
    r.addTypeInformation(eni("nsu=http://opcfoundation.org/UA/DI/;i=6523"), "FetchResultErrorDataType", FetchResultErrorDataType.class);
    r.addTypeInformation(eni("nsu=http://opcfoundation.org/UA/DI/;i=6524"), "FetchResultDataDataType", FetchResultDataDataType.class);
    r.addTypeInformation(eni("nsu=http://opcfoundation.org/UA/DI/;i=6525"), "ParameterResultDataType", ParameterResultDataType.class);
    return r;
  }

  private static ExpandedNodeId eni(String id) {
    return ExpandedNodeId.parseExpandedNodeId(id);
  }
}
