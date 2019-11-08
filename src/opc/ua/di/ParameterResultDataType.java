// Generated from SampleTypes
// by Prosys OPC UA Java SDK Codegen
//
package opc.ua.di;

import com.prosysopc.ua.StructureSerializer;
import com.prosysopc.ua.StructureUtils;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.UaNodeId;
import com.prosysopc.ua.stack.builtintypes.DiagnosticInfo;
import com.prosysopc.ua.stack.builtintypes.ExpandedNodeId;
import com.prosysopc.ua.stack.builtintypes.QualifiedName;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.encoding.EncoderContext;
import com.prosysopc.ua.stack.utils.AbstractStructure;
import com.prosysopc.ua.stack.utils.ObjectUtils;
import com.prosysopc.ua.typedictionary.FieldSpecification;
import com.prosysopc.ua.typedictionary.StructureSpecification;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

/**
 * Generated on 2019-11-05 12:19:47
 */
@TypeDefinitionId("nsu=http://opcfoundation.org/UA/DI/;i=6525")
public class ParameterResultDataType extends AbstractStructure {
  @Deprecated
  public static final ExpandedNodeId BINARY = Ids.ParameterResultDataType_DefaultBinary;

  @Deprecated
  public static final ExpandedNodeId XML = Ids.ParameterResultDataType_DefaultXml;

  @Deprecated
  public static final ExpandedNodeId ID = Ids.ParameterResultDataType;

  public static final StructureSpecification SPECIFICATION;

  static {
    StructureSpecification.Builder b = StructureSpecification.builder();
    b.addField(Fields.NodePath.getSpecification());
    b.addField(Fields.StatusCode.getSpecification());
    b.addField(Fields.Diagnostics.getSpecification());
    b.setBinaryEncodeId(UaNodeId.fromLocal(BINARY));
    b.setXmlEncodeId(UaNodeId.fromLocal(XML));
    b.setTypeId(UaNodeId.fromLocal(ID));
    b.setName("ParameterResultDataType");
    b.setStructureType(StructureSpecification.StructureType.NORMAL);
    b.setSerializerSupplier(new StructureSpecification.StructureSerializerSupplier() {
      @Override
      public StructureSerializer get(StructureSpecification specification, EncoderContext ctx) {
        return new Serializers.ParameterResultDataTypeSerializer();
      }
    });
    SPECIFICATION = b.build();
  }

  private QualifiedName[] nodePath;

  private StatusCode statusCode;

  private DiagnosticInfo diagnostics;

  public ParameterResultDataType() {
  }

  public ParameterResultDataType(QualifiedName[] nodePath, StatusCode statusCode,
      DiagnosticInfo diagnostics) {
    this.nodePath = nodePath;
    this.statusCode = statusCode;
    this.diagnostics = diagnostics;
  }

  public QualifiedName[] getNodePath() {
    return this.nodePath;
  }

  public void setNodePath(QualifiedName[] nodePath) {
    this.nodePath=nodePath;
  }

  public StatusCode getStatusCode() {
    return this.statusCode;
  }

  public void setStatusCode(StatusCode statusCode) {
    this.statusCode=statusCode;
  }

  public DiagnosticInfo getDiagnostics() {
    return this.diagnostics;
  }

  public void setDiagnostics(DiagnosticInfo diagnostics) {
    this.diagnostics=diagnostics;
  }

  @Override
  public ParameterResultDataType clone() {
    ParameterResultDataType result = (ParameterResultDataType) super.clone();
    result.nodePath = StructureUtils.clone(this.nodePath);
    result.statusCode = StructureUtils.clone(this.statusCode);
    result.diagnostics = StructureUtils.clone(this.diagnostics);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    ParameterResultDataType other = (ParameterResultDataType) obj;
    if (!StructureUtils.scalarOrArrayEquals(getNodePath(), other.getNodePath())) {
      return false;
    }
    if (!StructureUtils.scalarOrArrayEquals(getStatusCode(), other.getStatusCode())) {
      return false;
    }
    if (!StructureUtils.scalarOrArrayEquals(getDiagnostics(), other.getDiagnostics())) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return StructureUtils.hashCode(this.getNodePath(), this.getStatusCode(), this.getDiagnostics());
  }

  @Override
  public String toString() {
    return "ParameterResultDataType:" + ObjectUtils.printFieldsDeep(this);
  }

  @Override
  @Deprecated
  public ExpandedNodeId getBinaryEncodeId() {
    return BINARY;
  }

  @Override
  @Deprecated
  public ExpandedNodeId getXmlEncodeId() {
    return XML;
  }

  @Override
  @Deprecated
  public ExpandedNodeId getTypeId() {
    return ID;
  }

  @Override
  public StructureSpecification specification() {
    return SPECIFICATION;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public Object get(FieldSpecification field) {
    if (Fields.NodePath.getSpecification().equals(field)) {
      return getNodePath();
    }
    if (Fields.StatusCode.getSpecification().equals(field)) {
      return getStatusCode();
    }
    if (Fields.Diagnostics.getSpecification().equals(field)) {
      return getDiagnostics();
    }
    throw new IllegalArgumentException("Unknown field: " + field);
  }

  @Override
  public void set(FieldSpecification field, Object value) {
    if (Fields.NodePath.getSpecification().equals(field)) {
      setNodePath((QualifiedName[]) value);
      return;
    }
    if (Fields.StatusCode.getSpecification().equals(field)) {
      setStatusCode((StatusCode) value);
      return;
    }
    if (Fields.Diagnostics.getSpecification().equals(field)) {
      setDiagnostics((DiagnosticInfo) value);
      return;
    }
    throw new IllegalArgumentException("Unknown field: " + field);
  }

  @Override
  public Builder toBuilder() {
    Builder b = ParameterResultDataType.builder();
    b.setNodePath(getNodePath());
    b.setStatusCode(getStatusCode());
    b.setDiagnostics(getDiagnostics());
    return b;
  }

  public enum Fields {
    NodePath("NodePath", QualifiedName[].class, false, UaNodeId.fromLocal(ExpandedNodeId.parseExpandedNodeId("nsu=http://opcfoundation.org/UA/;i=20")), 1),

    StatusCode("StatusCode", StatusCode.class, false, UaNodeId.fromLocal(ExpandedNodeId.parseExpandedNodeId("nsu=http://opcfoundation.org/UA/;i=19")), -1),

    Diagnostics("Diagnostics", DiagnosticInfo.class, false, UaNodeId.fromLocal(ExpandedNodeId.parseExpandedNodeId("nsu=http://opcfoundation.org/UA/;i=25")), -1);

    private final FieldSpecification value;

    Fields(String name, Class<?> javaClass, boolean isOptional, UaNodeId dataTypeId,
        int valueRank) {
      FieldSpecification.Builder b = FieldSpecification.builder();
      b.setName(name);
      b.setJavaClass(javaClass);
      b.setIsOptional(isOptional);
      b.setDataTypeId(dataTypeId);
      b.setValueRank(valueRank);
      this.value = b.build();
    }

    public FieldSpecification getSpecification() {
      return value;
    }
  }

  public static class Builder extends AbstractStructure.Builder {
    private QualifiedName[] nodePath;

    private StatusCode statusCode;

    private DiagnosticInfo diagnostics;

    protected Builder() {
    }

    public Builder setNodePath(QualifiedName[] nodePath) {
      this.nodePath=nodePath;
      return this;
    }

    public Builder setStatusCode(StatusCode statusCode) {
      this.statusCode=statusCode;
      return this;
    }

    public Builder setDiagnostics(DiagnosticInfo diagnostics) {
      this.diagnostics=diagnostics;
      return this;
    }

    @Override
    public Builder set(FieldSpecification field, Object value) {
      if (Fields.NodePath.getSpecification().equals(field)) {
        setNodePath((QualifiedName[]) value);
        return this;
      }
      if (Fields.StatusCode.getSpecification().equals(field)) {
        setStatusCode((StatusCode) value);
        return this;
      }
      if (Fields.Diagnostics.getSpecification().equals(field)) {
        setDiagnostics((DiagnosticInfo) value);
        return this;
      }
      throw new IllegalArgumentException("Unknown field: " + field);
    }

    @Override
    public ParameterResultDataType build() {
      return new ParameterResultDataType(nodePath, statusCode, diagnostics);
    }
  }
}
