// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaMethod;
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
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import opc.ua.di.FetchResultDataType;
import opc.ua.di.TransferServicesType;

/**
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6526")
public abstract class TransferServicesTypeNodeBase extends BaseObjectTypeNode implements TransferServicesType {
  private static GeneratedNodeInitializer<TransferServicesTypeNode> transferServicesTypeNodeInitializer;

  private static TransferServicesTypeTransferToDeviceMethod transferToDeviceMethodImplementation;

  private static TransferServicesTypeTransferFromDeviceMethod transferFromDeviceMethodImplementation;

  private static TransferServicesTypeFetchTransferResultDataMethod fetchTransferResultDataMethodImplementation;

  protected TransferServicesTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    GeneratedNodeInitializer<TransferServicesTypeNode> impl = getTransferServicesTypeNodeInitializer();
    if(impl != null) {
      impl.init((TransferServicesTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<TransferServicesTypeNode> getTransferServicesTypeNodeInitializer() {
    return transferServicesTypeNodeInitializer;
  }

  public static void setTransferServicesTypeNodeInitializer(GeneratedNodeInitializer<TransferServicesTypeNode> transferServicesTypeNodeInitializerNewValue) {
    transferServicesTypeNodeInitializer=transferServicesTypeNodeInitializerNewValue;
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    if (isComponentMatch(getQualifiedName("http://opcfoundation.org/UA/DI/", "TransferToDevice"), methodId)) {
      return doTransferToDevice(serviceContext).asVariantArray();
    }
    if (isComponentMatch(getQualifiedName("http://opcfoundation.org/UA/DI/", "TransferFromDevice"), methodId)) {
      return doTransferFromDevice(serviceContext).asVariantArray();
    }
    if (isComponentMatch(getQualifiedName("http://opcfoundation.org/UA/DI/", "FetchTransferResultData"), methodId)) {
      return new Variant[]{new Variant(doFetchTransferResultData(serviceContext, (Integer) inputArguments[0].getValue(), (Integer) inputArguments[1].getValue(), (Integer) inputArguments[2].getValue(), (Boolean) inputArguments[3].getValue()))};
    }
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }

  @Mandatory
  @Override
  public UaMethod getTransferToDeviceNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "TransferToDevice");
    return (UaMethod) getComponent(browseName);
  }

  protected abstract TransferServicesType.TransferToDeviceMethodOutputs onTransferToDevice(ServiceContext serviceContext)
      throws StatusException;

  @Override
  public TransferServicesType.TransferToDeviceMethodOutputs transferToDevice() throws
      StatusException {
    return doTransferToDevice(ServiceContext.INTERNAL_OPERATION_CONTEXT);
  }

  private TransferServicesType.TransferToDeviceMethodOutputs doTransferToDevice(ServiceContext serviceContext)
      throws StatusException {
    TransferServicesTypeTransferToDeviceMethod impl = getTransferToDeviceMethodImplementation();
    if(impl != null) {
      return impl.transferToDevice(serviceContext, (TransferServicesTypeNode)this);
    } else {
      return onTransferToDevice(serviceContext);
    }
  }

  public static TransferServicesTypeTransferToDeviceMethod getTransferToDeviceMethodImplementation() {
    return transferToDeviceMethodImplementation;
  }

  public static void setTransferToDeviceMethodImplementation(TransferServicesTypeTransferToDeviceMethod transferToDeviceMethodImplementationNewValue) {
    transferToDeviceMethodImplementation=transferToDeviceMethodImplementationNewValue;
  }

  @Mandatory
  @Override
  public UaMethod getTransferFromDeviceNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "TransferFromDevice");
    return (UaMethod) getComponent(browseName);
  }

  protected abstract TransferServicesType.TransferFromDeviceMethodOutputs onTransferFromDevice(ServiceContext serviceContext)
      throws StatusException;

  @Override
  public TransferServicesType.TransferFromDeviceMethodOutputs transferFromDevice() throws
      StatusException {
    return doTransferFromDevice(ServiceContext.INTERNAL_OPERATION_CONTEXT);
  }

  private TransferServicesType.TransferFromDeviceMethodOutputs doTransferFromDevice(ServiceContext serviceContext)
      throws StatusException {
    TransferServicesTypeTransferFromDeviceMethod impl = getTransferFromDeviceMethodImplementation();
    if(impl != null) {
      return impl.transferFromDevice(serviceContext, (TransferServicesTypeNode)this);
    } else {
      return onTransferFromDevice(serviceContext);
    }
  }

  public static TransferServicesTypeTransferFromDeviceMethod getTransferFromDeviceMethodImplementation() {
    return transferFromDeviceMethodImplementation;
  }

  public static void setTransferFromDeviceMethodImplementation(TransferServicesTypeTransferFromDeviceMethod transferFromDeviceMethodImplementationNewValue) {
    transferFromDeviceMethodImplementation=transferFromDeviceMethodImplementationNewValue;
  }

  @Mandatory
  @Override
  public UaMethod getFetchTransferResultDataNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/DI/", "FetchTransferResultData");
    return (UaMethod) getComponent(browseName);
  }

  protected abstract FetchResultDataType onFetchTransferResultData(ServiceContext serviceContext,
      Integer transferID, Integer sequenceNumber, Integer maxParameterResultsToReturn,
      Boolean omitGoodResults) throws StatusException;

  @Override
  public FetchResultDataType fetchTransferResultData(Integer transferID, Integer sequenceNumber,
      Integer maxParameterResultsToReturn, Boolean omitGoodResults) throws StatusException {
    return doFetchTransferResultData(ServiceContext.INTERNAL_OPERATION_CONTEXT, transferID, sequenceNumber, maxParameterResultsToReturn, omitGoodResults);
  }

  private FetchResultDataType doFetchTransferResultData(ServiceContext serviceContext,
      Integer transferID, Integer sequenceNumber, Integer maxParameterResultsToReturn,
      Boolean omitGoodResults) throws StatusException {
    TransferServicesTypeFetchTransferResultDataMethod impl = getFetchTransferResultDataMethodImplementation();
    if(impl != null) {
      return impl.fetchTransferResultData(serviceContext, (TransferServicesTypeNode)this, transferID, sequenceNumber, maxParameterResultsToReturn, omitGoodResults);
    } else {
      return onFetchTransferResultData(serviceContext, transferID, sequenceNumber, maxParameterResultsToReturn, omitGoodResults);
    }
  }

  public static TransferServicesTypeFetchTransferResultDataMethod getFetchTransferResultDataMethodImplementation() {
    return fetchTransferResultDataMethodImplementation;
  }

  public static void setFetchTransferResultDataMethodImplementation(TransferServicesTypeFetchTransferResultDataMethod fetchTransferResultDataMethodImplementationNewValue) {
    fetchTransferResultDataMethodImplementation=fetchTransferResultDataMethodImplementationNewValue;
  }
}
