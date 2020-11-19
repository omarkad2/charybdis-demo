package ma.markware.charybdis.demo.spring.domain;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SequenceModel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class User_Table implements TableMetadata<User> {
  public static final PartitionKeyColumnMetadata<UUID, UUID> userId = new PartitionKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "userid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("userid")) return null;
      return row.get("userid", java.util.UUID.class);
    }

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<String, String> username = new ColumnMetadata<String, String>() {
    public String getName() {
      return "username";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("username")) return null;
      return row.get("username", java.lang.String.class);
    }

    public String getIndexName() {
      return "user_username_idx";
    }
  };

  public static final ColumnMetadata<Instant, Instant> creationDate = new ColumnMetadata<Instant, Instant>() {
    public String getName() {
      return "creation_date";
    }

    public Class getFieldClass() {
      return java.time.Instant.class;
    }

    public Instant serialize(Instant field) {
      return field;
    }

    public Instant deserialize(Row row) {
      if (row == null || row.isNull("creation_date")) return null;
      return row.get("creation_date", java.time.Instant.class);
    }
  };

  public static final ColumnMetadata<Instant, Instant> lastUpdatedDate = new ColumnMetadata<Instant, Instant>() {
    public String getName() {
      return "last_updated_date";
    }

    public Class getFieldClass() {
      return java.time.Instant.class;
    }

    public Instant serialize(Instant field) {
      return field;
    }

    public Instant deserialize(Row row) {
      if (row == null || row.isNull("last_updated_date")) return null;
      return row.get("last_updated_date", java.time.Instant.class);
    }
  };

  public static final User_Table user = new User_Table();

  public static final String KEYSPACE_NAME = "charybdis_demo";

  public static final String TABLE_NAME = "user";

  private User_Table() {
  }

  public String getKeyspaceName() {
    return KEYSPACE_NAME;
  }

  public String getTableName() {
    return TABLE_NAME;
  }

  public ConsistencyLevel getDefaultReadConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  public ConsistencyLevel getDefaultWriteConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  public SerialConsistencyLevel getDefaultSerialConsistency() {
    return SerialConsistencyLevel.NOT_SPECIFIED;
  }

  public Map<String, ColumnMetadata> getColumnsMetadata() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userId);
    results.put("username", username);
    results.put("creation_date", creationDate);
    results.put("last_updated_date", lastUpdatedDate);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userId);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    return results;
  }

  public Map<String, ColumnMetadata> getPrimaryKeys() {
    Map<String, ColumnMetadata> result = new HashMap<>();
    result.putAll(getPartitionKeyColumns());
    result.putAll(getClusteringKeyColumns());
    return result;
  }

  public ColumnMetadata getColumnMetadata(String columnName) {
    return getColumnsMetadata().get(columnName);
  }

  public boolean isPrimaryKey(String columnName) {
    return getPartitionKeyColumns().containsKey(columnName) || getClusteringKeyColumns().containsKey(columnName);
  }

  public int getPrimaryKeySize() {
    return getPartitionKeyColumns().size() + getClusteringKeyColumns().size();
  }

  public int getColumnsSize() {
    return getColumnsMetadata().size();
  }

  public void setGeneratedValues(User entity) {
    if (entity != null) {
      entity.setUserId((java.util.UUID) SequenceModel.UUID.getGenerationMethod().get());
    }
  }

  public void setCreationDate(User entity, Instant creationDate) {
    if (entity != null) {
      entity.setCreationDate(creationDate);
    }
  }

  public void setLastUpdatedDate(User entity, Instant lastUpdatedDate) {
    if (entity != null) {
      entity.setLastUpdatedDate(lastUpdatedDate);
    }
  }

  public Map<String, Object> serialize(User entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("userid", userId.serialize(entity.getUserId()));
    columnValueMap.put("username", username.serialize(entity.getUsername()));
    columnValueMap.put("creation_date", creationDate.serialize(entity.getCreationDate()));
    columnValueMap.put("last_updated_date", lastUpdatedDate.serialize(entity.getLastUpdatedDate()));
    return columnValueMap;
  }

  public User deserialize(Row row) {
    if (row == null) return null;
    User entity = new User();
    entity.setUserId(userId.deserialize(row));
    entity.setUsername(username.deserialize(row));
    entity.setCreationDate(creationDate.deserialize(row));
    entity.setLastUpdatedDate(lastUpdatedDate.deserialize(row));
    return entity;
  }
}
