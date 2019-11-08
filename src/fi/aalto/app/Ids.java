// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package fi.aalto.app;

import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;

/**
 * Generated on 2019-11-05 12:19:48
 */
public class Ids {
  public static final ExpandedNodeId AppDeviceType = init("nsu=http://org.aalto.fi/AppServer;i=1002");

  private static ExpandedNodeId init(String id) {
    return ExpandedNodeId.parseExpandedNodeId(id);
  }
}
