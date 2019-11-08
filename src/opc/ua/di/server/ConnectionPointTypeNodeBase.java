// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.builtintypes.Variant;
import java.lang.Override;
import opc.ua.di.ConnectionPointType;

/**
 * Represents the interface (interface card) of a Device to a Network.
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6308")
public abstract class ConnectionPointTypeNodeBase extends TopologyElementTypeNode implements ConnectionPointType {
  private static GeneratedNodeInitializer<ConnectionPointTypeNode> connectionPointTypeNodeInitializer;

  protected ConnectionPointTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getNetworkAddressNode());
    GeneratedNodeInitializer<ConnectionPointTypeNode> impl = getConnectionPointTypeNodeInitializer();
    if(impl != null) {
      impl.init((ConnectionPointTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<ConnectionPointTypeNode> getConnectionPointTypeNodeInitializer() {
    return connectionPointTypeNodeInitializer;
  }

  public static void setConnectionPointTypeNodeInitializer(GeneratedNodeInitializer<ConnectionPointTypeNode> connectionPointTypeNodeInitializerNewValue) {
    connectionPointTypeNodeInitializer=connectionPointTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public FunctionalGroupTypeNode getNetworkAddressNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "NetworkAddress");
    return (FunctionalGroupTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
