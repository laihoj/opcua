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
import com.prosysopc.ua.types.opcua.server.BaseObjectTypeNode;
import com.prosysopc.ua.types.opcua.server.FolderTypeNode;
import java.lang.Override;
import opc.ua.di.ConfigurableObjectType;

/**
 * Defines a general pattern to expose and configure modular components
 * <p>
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=1004")
public abstract class ConfigurableObjectTypeNodeBase extends BaseObjectTypeNode implements ConfigurableObjectType {
  private static GeneratedNodeInitializer<ConfigurableObjectTypeNode> configurableObjectTypeNodeInitializer;

  protected ConfigurableObjectTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getSupportedTypesNode());
    GeneratedNodeInitializer<ConfigurableObjectTypeNode> impl = getConfigurableObjectTypeNodeInitializer();
    if(impl != null) {
      impl.init((ConfigurableObjectTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<ConfigurableObjectTypeNode> getConfigurableObjectTypeNodeInitializer() {
    return configurableObjectTypeNodeInitializer;
  }

  public static void setConfigurableObjectTypeNodeInitializer(GeneratedNodeInitializer<ConfigurableObjectTypeNode> configurableObjectTypeNodeInitializerNewValue) {
    configurableObjectTypeNodeInitializer=configurableObjectTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public FolderTypeNode getSupportedTypesNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "SupportedTypes");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}
