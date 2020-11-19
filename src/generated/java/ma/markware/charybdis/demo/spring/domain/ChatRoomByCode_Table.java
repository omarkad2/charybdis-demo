package ma.markware.charybdis.demo.spring.domain;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ClusteringKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.SetColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ClusteringOrder;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class ChatRoomByCode_Table implements TableMetadata<ChatRoomByCode> {
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

  public static final ClusteringKeyColumnMetadata<UUID, UUID> chatRoomId = new ClusteringKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "chatroomid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("chatroomid")) return null;
      return row.get("chatroomid", java.util.UUID.class);
    }

    public int getClusteringKeyIndex() {
      return 0;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final PartitionKeyColumnMetadata<Integer, Integer> chatRoomCode = new PartitionKeyColumnMetadata<Integer, Integer>() {
    public String getName() {
      return "chatroomcode";
    }

    public Class getFieldClass() {
      return java.lang.Integer.class;
    }

    public Integer serialize(Integer field) {
      return field;
    }

    public Integer deserialize(Row row) {
      if (row == null || row.isNull("chatroomcode")) return null;
      return row.get("chatroomcode", java.lang.Integer.class);
    }

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<UUID, UUID> creatorId = new ColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "creatorid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("creatorid")) return null;
      return row.get("creatorid", java.util.UUID.class);
    }
  };

  public static final SetColumnMetadata<UUID, UUID> userIds = new SetColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "userids";
    }

    public Class getFieldClass() {
      return java.util.Set.class;
    }

    public Set<UUID> serialize(Set<UUID> field) {
      return field;
    }

    public Set<UUID> deserialize(Row row) {
      if (row == null || row.isNull("userids")) return null;
      return row.getSet("userids", java.util.UUID.class);
    }
  };

  public static final ColumnMetadata<Boolean, Boolean> single = new ColumnMetadata<Boolean, Boolean>() {
    public String getName() {
      return "single";
    }

    public Class getFieldClass() {
      return java.lang.Boolean.class;
    }

    public Boolean serialize(Boolean field) {
      return field;
    }

    public Boolean deserialize(Row row) {
      if (row == null || row.isNull("single")) return null;
      return row.get("single", java.lang.Boolean.class);
    }
  };

  public static final ChatRoomByCode_Table chatroom_by_code = new ChatRoomByCode_Table();

  public static final String KEYSPACE_NAME = "charybdis_demo";

  public static final String TABLE_NAME = "chatroom_by_code";

  private ChatRoomByCode_Table() {
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
    results.put("creation_date", creationDate);
    results.put("last_updated_date", lastUpdatedDate);
    results.put("chatroomid", chatRoomId);
    results.put("chatroomcode", chatRoomCode);
    results.put("creatorid", creatorId);
    results.put("userids", userIds);
    results.put("single", single);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("chatroomcode", chatRoomCode);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("chatroomid", chatRoomId);
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

  public void setGeneratedValues(ChatRoomByCode entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(ChatRoomByCode entity, Instant creationDate) {
    if (entity != null) {
      entity.setCreationDate(creationDate);
    }
  }

  public void setLastUpdatedDate(ChatRoomByCode entity, Instant lastUpdatedDate) {
    if (entity != null) {
      entity.setLastUpdatedDate(lastUpdatedDate);
    }
  }

  public Map<String, Object> serialize(ChatRoomByCode entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("creation_date", creationDate.serialize(entity.getCreationDate()));
    columnValueMap.put("last_updated_date", lastUpdatedDate.serialize(entity.getLastUpdatedDate()));
    columnValueMap.put("chatroomid", chatRoomId.serialize(entity.getChatRoomId()));
    columnValueMap.put("chatroomcode", chatRoomCode.serialize(entity.getChatRoomCode()));
    columnValueMap.put("creatorid", creatorId.serialize(entity.getCreatorId()));
    columnValueMap.put("userids", userIds.serialize(entity.getUserIds()));
    columnValueMap.put("single", single.serialize(entity.isSingle()));
    return columnValueMap;
  }

  public ChatRoomByCode deserialize(Row row) {
    if (row == null) return null;
    ChatRoomByCode entity = new ChatRoomByCode();
    entity.setCreationDate(creationDate.deserialize(row));
    entity.setLastUpdatedDate(lastUpdatedDate.deserialize(row));
    entity.setChatRoomId(chatRoomId.deserialize(row));
    entity.setChatRoomCode(chatRoomCode.deserialize(row));
    entity.setCreatorId(creatorId.deserialize(row));
    entity.setUserIds(userIds.deserialize(row));
    entity.setSingle(single.deserialize(row));
    return entity;
  }
}
