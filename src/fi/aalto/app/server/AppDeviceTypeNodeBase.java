// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package fi.aalto.app.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.Variant;
import fi.aalto.app.AppDeviceType;
import java.lang.Override;
import opc.ua.di.server.DeviceTypeNode;

/**
 * Generated on 2019-11-05 12:19:48
 */
@TypeDefinitionId("nsu=http://org.aalto.fi/AppServer;i=1002")
public abstract class AppDeviceTypeNodeBase extends DeviceTypeNode implements AppDeviceType {
  private static GeneratedNodeInitializer<AppDeviceTypeNode> appDeviceTypeNodeInitializer;

  protected AppDeviceTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    GeneratedNodeInitializer<AppDeviceTypeNode> impl = getAppDeviceTypeNodeInitializer();
    if(impl != null) {
      impl.init((AppDeviceTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<AppDeviceTypeNode> getAppDeviceTypeNodeInitializer() {
    return appDeviceTypeNodeInitializer;
  }

  public static void setAppDeviceTypeNodeInitializer(GeneratedNodeInitializer<AppDeviceTypeNode> appDeviceTypeNodeInitializerNewValue) {
    appDeviceTypeNodeInitializer=appDeviceTypeNodeInitializerNewValue;
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
