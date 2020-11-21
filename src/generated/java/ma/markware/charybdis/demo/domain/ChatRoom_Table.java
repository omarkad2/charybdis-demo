package ma.markware.charybdis.demo.domain;

import com.datastax.oss.driver.api.core.cql.Row;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ClusteringKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.SetColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ClusteringOrder;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class ChatRoom_Table implements TableMetadata<ChatRoom> {
  public static final ClusteringKeyColumnMetadata<UUID, UUID> chatRoomId = new ClusteringKeyColumnMetadata<UUID, UUID>() {
    @Override
    public String getName() {
      return "chatroomid";
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
      if (row == null || row.isNull("chatroomid")) return null;
      return row.get("chatroomid", java.util.UUID.class);
    }

    @Override
    public int getClusteringKeyIndex() {
      return 0;
    }

    @Override
    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final ColumnMetadata<Integer, Integer> chatRoomCode = new ColumnMetadata<Integer, Integer>() {
    @Override
    public String getName() {
      return "chatroomcode";
    }

    @Override
    public Class getFieldClass() {
      return java.lang.Integer.class;
    }

    @Override
    public Integer serialize(Integer field) {
      return field;
    }

    @Override
    public Integer deserialize(Row row) {
      if (row == null || row.isNull("chatroomcode")) return null;
      return row.get("chatroomcode", java.lang.Integer.class);
    }
  };

  public static final ColumnMetadata<UUID, UUID> creatorId = new ColumnMetadata<UUID, UUID>() {
    @Override
    public String getName() {
      return "creatorid";
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
      if (row == null || row.isNull("creatorid")) return null;
      return row.get("creatorid", java.util.UUID.class);
    }
  };

  public static final SetColumnMetadata<UUID, UUID> userIds = new SetColumnMetadata<UUID, UUID>() {
    @Override
    public String getName() {
      return "userids";
    }

    @Override
    public Class getFieldClass() {
      return java.util.Set.class;
    }

    @Override
    public Set<UUID> serialize(Set<UUID> field) {
      return field;
    }

    @Override
    public Set<UUID> deserialize(Row row) {
      if (row == null || row.isNull("userids")) return null;
      return row.getSet("userids", java.util.UUID.class);
    }
  };

  public static final ColumnMetadata<Boolean, Boolean> single = new ColumnMetadata<Boolean, Boolean>() {
    @Override
    public String getName() {
      return "single";
    }

    @Override
    public Class getFieldClass() {
      return java.lang.Boolean.class;
    }

    @Override
    public Boolean serialize(Boolean field) {
      return field;
    }

    @Override
    public Boolean deserialize(Row row) {
      if (row == null || row.isNull("single")) return null;
      return row.get("single", java.lang.Boolean.class);
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

  public static final ChatRoom_Table chatroom = new ChatRoom_Table();

  public static final String KEYSPACE_NAME = "charybdis_demo";

  public static final String TABLE_NAME = "chatroom";

  private ChatRoom_Table() {
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
    results.put("chatroomid", chatRoomId);
    results.put("chatroomcode", chatRoomCode);
    results.put("creatorid", creatorId);
    results.put("userids", userIds);
    results.put("single", single);
    results.put("creation_date", creationDate);
    results.put("last_updated_date", lastUpdatedDate);
    return results;
  }

  @Override
  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("chatroomid", chatRoomId);
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
  public void setGeneratedValues(ChatRoom entity) {
    if (entity != null) {
    }
  }

  @Override
  public void setCreationDate(ChatRoom entity, Instant creationDate) {
    if (entity != null) {
      entity.setCreationDate(creationDate);
    }
  }

  @Override
  public void setLastUpdatedDate(ChatRoom entity, Instant lastUpdatedDate) {
    if (entity != null) {
      entity.setLastUpdatedDate(lastUpdatedDate);
    }
  }

  @Override
  public Map<String, Object> serialize(ChatRoom entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("chatroomid", chatRoomId.serialize(entity.getChatRoomId()));
    columnValueMap.put("chatroomcode", chatRoomCode.serialize(entity.getChatRoomCode()));
    columnValueMap.put("creatorid", creatorId.serialize(entity.getCreatorId()));
    columnValueMap.put("userids", userIds.serialize(entity.getUserIds()));
    columnValueMap.put("single", single.serialize(entity.isSingle()));
    columnValueMap.put("creation_date", creationDate.serialize(entity.getCreationDate()));
    columnValueMap.put("last_updated_date", lastUpdatedDate.serialize(entity.getLastUpdatedDate()));
    return columnValueMap;
  }

  @Override
  public ChatRoom deserialize(Row row) {
    if (row == null) return null;
    ChatRoom entity = new ChatRoom();
    entity.setChatRoomId(chatRoomId.deserialize(row));
    entity.setChatRoomCode(chatRoomCode.deserialize(row));
    entity.setCreatorId(creatorId.deserialize(row));
    entity.setUserIds(userIds.deserialize(row));
    entity.setSingle(single.deserialize(row));
    entity.setCreationDate(creationDate.deserialize(row));
    entity.setLastUpdatedDate(lastUpdatedDate.deserialize(row));
    return entity;
  }
}
