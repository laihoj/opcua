// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.StructureSerializer;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.encoding.DecodingException;
import com.prosysopc.ua.stack.encoding.EncodingException;
import com.prosysopc.ua.stack.encoding.IDecoder;
import com.prosysopc.ua.stack.encoding.IEncodeable;
import com.prosysopc.ua.stack.encoding.IEncoder;
import com.prosysopc.ua.stack.encoding.binary.IEncodeableSerializer;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated on 2019-11-05 12:19:47
 */
public class Serializers {
  public static final IEncodeableSerializer[] SERIALIZERS;

  static {
    List<IEncodeableSerializer> l = new ArrayList<IEncodeableSerializer>();
    l.add(new FetchResultErrorDataTypeSerializer());
    l.add(new FetchResultDataDataTypeSerializer());
    l.add(new ParameterResultDataTypeSerializer());
    SERIALIZERS = l.toArray(new IEncodeableSerializer[0]);
  }

  public static class FetchResultErrorDataTypeSerializer extends StructureSerializer {
    public FetchResultErrorDataTypeSerializer() {
      super(FetchResultErrorDataType.class, FetchResultErrorDataType.BINARY, FetchResultErrorDataType.XML);
    }

    @Override
    public void calcEncodeable(IEncodeable encodeable, IEncoder calculator) throws
        EncodingException {
      super.calcEncodeable(encodeable, calculator);
      FetchResultErrorDataType obj = (FetchResultErrorDataType) encodeable;
      calculator.put(null, (obj == null) ? null : obj.getStatus(), Integer.class);
      calculator.put(null, (obj == null) ? null : obj.getDiagnostics(), DiagnosticInfo.class);
    }

    @Override
    public void getEncodeable(IDecoder decoder, IEncodeable encodeable) throws DecodingException {
      FetchResultErrorDataType result = (FetchResultErrorDataType) encodeable;
      super.getEncodeable(decoder, result);
      result.setStatus(decoder.get("Status", Integer.class));
      result.setDiagnostics(decoder.get("Diagnostics", DiagnosticInfo.class));
    }

    @Override
    public IEncodeable newEncodeable() {
      return new FetchResultErrorDataType();
    }

    @Override
    public void putEncodeable(IEncodeable encodeable, IEncoder encoder) throws EncodingException {
      super.putEncodeable(encodeable, encoder);
      FetchResultErrorDataType obj = (FetchResultErrorDataType) encodeable;
      encoder.put("Status", (obj == null) ? null : obj.getStatus(), Integer.class);
      encoder.put("Diagnostics", (obj == null) ? null : obj.getDiagnostics(), DiagnosticInfo.class);
    }
  }

  public static class FetchResultDataDataTypeSerializer extends StructureSerializer {
    public FetchResultDataDataTypeSerializer() {
      super(FetchResultDataDataType.class, FetchResultDataDataType.BINARY, FetchResultDataDataType.XML);
    }

    @Override
    public void calcEncodeable(IEncodeable encodeable, IEncoder calculator) throws
        EncodingException {
      super.calcEncodeable(encodeable, calculator);
      FetchResultDataDataType obj = (FetchResultDataDataType) encodeable;
      calculator.put(null, (obj == null) ? null : obj.getSequenceNumber(), Integer.class);
      calculator.put(null, (obj == null) ? null : obj.getEndOfResults(), Boolean.class);
      calculator.put(null, (obj == null) ? null : obj.getParameterDefs(), ParameterResultDataType[].class);
    }

    @Override
    public void getEncodeable(IDecoder decoder, IEncodeable encodeable) throws DecodingException {
      FetchResultDataDataType result = (FetchResultDataDataType) encodeable;
      super.getEncodeable(decoder, result);
      result.setSequenceNumber(decoder.get("SequenceNumber", Integer.class));
      result.setEndOfResults(decoder.get("EndOfResults", Boolean.class));
      result.setParameterDefs(decoder.get("ParameterDefs", ParameterResultDataType[].class));
    }

    @Override
    public IEncodeable newEncodeable() {
      return new FetchResultDataDataType();
    }

    @Override
    public void putEncodeable(IEncodeable encodeable, IEncoder encoder) throws EncodingException {
      super.putEncodeable(encodeable, encoder);
      FetchResultDataDataType obj = (FetchResultDataDataType) encodeable;
      encoder.put("SequenceNumber", (obj == null) ? null : obj.getSequenceNumber(), Integer.class);
      encoder.put("EndOfResults", (obj == null) ? null : obj.getEndOfResults(), Boolean.class);
      encoder.put("ParameterDefs", (obj == null) ? null : obj.getParameterDefs(), ParameterResultDataType[].class);
    }
  }

  public static class ParameterResultDataTypeSerializer extends StructureSerializer {
    public ParameterResultDataTypeSerializer() {
      super(ParameterResultDataType.class, ParameterResultDataType.BINARY, ParameterResultDataType.XML);
    }

    @Override
    public void calcEncodeable(IEncodeable encodeable, IEncoder calculator) throws
        EncodingException {
      super.calcEncodeable(encodeable, calculator);
      ParameterResultDataType obj = (ParameterResultDataType) encodeable;
      calculator.put(null, (obj == null) ? null : obj.getNodePath(), QualifiedName[].class);
      calculator.put(null, (obj == null) ? null : obj.getStatusCode(), StatusCode.class);
      calculator.put(null, (obj == null) ? null : obj.getDiagnostics(), DiagnosticInfo.class);
    }

    @Override
    public void getEncodeable(IDecoder decoder, IEncodeable encodeable) throws DecodingException {
      ParameterResultDataType result = (ParameterResultDataType) encodeable;
      super.getEncodeable(decoder, result);
      result.setNodePath(decoder.get("NodePath", QualifiedName[].class));
      result.setStatusCode(decoder.get("StatusCode", StatusCode.class));
      result.setDiagnostics(decoder.get("Diagnostics", DiagnosticInfo.class));
    }

    @Override
    public IEncodeable newEncodeable() {
      return new ParameterResultDataType();
    }

    @Override
    public void putEncodeable(IEncodeable encodeable, IEncoder encoder) throws EncodingException {
      super.putEncodeable(encodeable, encoder);
      ParameterResultDataType obj = (ParameterResultDataType) encodeable;
      encoder.put("NodePath", (obj == null) ? null : obj.getNodePath(), QualifiedName[].class);
      encoder.put("StatusCode", (obj == null) ? null : obj.getStatusCode(), StatusCode.class);
      encoder.put("Diagnostics", (obj == null) ? null : obj.getDiagnostics(), DiagnosticInfo.class);
    }
  }
}
