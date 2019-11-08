// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.ServiceContext;
import java.lang.Boolean;
import java.lang.Integer;
import opc.ua.di.FetchResultDataType;

public abstract interface TransferServicesTypeFetchTransferResultDataMethod {
  FetchResultDataType fetchTransferResultData(ServiceContext serviceContext,
      TransferServicesTypeNode node, Integer transferID, Integer sequenceNumber,
      Integer maxParameterResultsToReturn, Boolean omitGoodResults) throws StatusException;
}
