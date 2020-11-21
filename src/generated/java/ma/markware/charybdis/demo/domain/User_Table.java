package ma.markware.charybdis.demo.domain;

import com.datastax.oss.driver.api.core.cql.Row;
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
    @Override
    public String getName() {
      return "userid";
    }

    @Override
    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    @Override
    public UUID serialize(UUID field) {
      return field;
    }

    @Override
    public UUID deserialize(Row row) {
      if (row == null || row.isNull("userid")) return null;
      return row.get("userid", java.util.UUID.class);
    }

    @Override
    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<String, String> username = new ColumnMetadata<String, String>() {
    @Override
    public String getName() {
      return "username";
    }

    @Override
    public Class getFieldClass() {
      return java.lang.String.class;
    }

    @Override
    public String serialize(String field) {
      return field;
    }

    @Override
    public String deserialize(Row row) {
      if (row == null || row.isNull("username")) return null;
      return row.get("username", java.lang.String.class);
    }

    @Override
    public String getIndexName() {
      return "user_username_idx";
    }
  };

  public static final ColumnMetadata<Instant, Instant> creationDate = new ColumnMetadata<Instant, Instant>() {
    @Override
    public String getName() {
      return "creation_date";
    }

    @Override
    public Class getFieldClass() {
      return java.time.Instant.class;
    }

    @Override
    public Instant serialize(Instant field) {
      return field;
    }

    @Override
    public Instant deserialize(Row row) {
      if (row == null || row.isNull("creation_date")) return null;
      return row.get("creation_date", java.time.Instant.class);
    }
  };

  public static final ColumnMetadata<Instant, Instant> lastUpdatedDate = new ColumnMetadata<Instant, Instant>() {
    @Override
    public String getName() {
      return "last_updated_date";
    }

    @Override
    public Class getFieldClass() {
      return java.time.Instant.class;
    }

    @Override
    public Instant serialize(Instant field) {
      return field;
    }

    @Override
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

  @Override
  public String getKeyspaceName() {
    return KEYSPACE_NAME;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public ConsistencyLevel getDefaultReadConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  @Override
  public ConsistencyLevel getDefaultWriteConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  @Override
  public SerialConsistencyLevel getDefaultSerialConsistency() {
    return SerialConsistencyLevel.NOT_SPECIFIED;
  }

  @Override
  public Map<String, ColumnMetadata> getColumnsMetadata() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userId);
    results.put("username", username);
    results.put("creation_date", creationDate);
    results.put("last_updated_date", lastUpdatedDate);
    return results;
  }

  @Override
  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userId);
    return results;
  }

  @Override
  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    return results;
  }

  @Override
  public Map<String, ColumnMetadata> getPrimaryKeys() {
    Map<String, ColumnMetadata> result = new HashMap<>();
    result.putAll(getPartitionKeyColumns());
    result.putAll(getClusteringKeyColumns());
    return result;
  }

  @Override
  public ColumnMetadata getColumnMetadata(String columnName) {
    return getColumnsMetadata().get(columnName);
  }

  @Override
  public boolean isPrimaryKey(String columnName) {
    return getPartitionKeyColumns().containsKey(columnName) || getClusteringKeyColumns().containsKey(columnName);
  }

  @Override
  public int getPrimaryKeySize() {
    return getPartitionKeyColumns().size() + getClusteringKeyColumns().size();
  }

  @Override
  public int getColumnsSize() {
    return getColumnsMetadata().size();
  }

  @Override
  public void setGeneratedValues(User entity) {
    if (entity != null) {
      entity.setUserId((java.util.UUID) SequenceModel.UUID.getGenerationMethod().get());
    }
  }

  @Override
  public void setCreationDate(User entity, Instant creationDate) {
    if (entity != null) {
      entity.setCreationDate(creationDate);
    }
  }

  @Override
  public void setLastUpdatedDate(User entity, Instant lastUpdatedDate) {
    if (entity != null) {
      entity.setLastUpdatedDate(lastUpdatedDate);
    }
  }

  @Override
  public Map<String, Object> serialize(User entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("userid", userId.serialize(entity.getUserId()));
    columnValueMap.put("username", username.serialize(entity.getUsername()));
    columnValueMap.put("creation_date", creationDate.serialize(entity.getCreationDate()));
    columnValueMap.put("last_updated_date", lastUpdatedDate.serialize(entity.getLastUpdatedDate()));
    return columnValueMap;
  }

  @Override
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
