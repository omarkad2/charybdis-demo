package ma.markware.charybdis.demo.spring.domain;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ClusteringKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ClusteringOrder;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class MessageByChatRoom_Table implements TableMetadata<MessageByChatRoom> {
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

  public static final ClusteringKeyColumnMetadata<UUID, UUID> messageId = new ClusteringKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "messageid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("messageid")) return null;
      return row.get("messageid", java.util.UUID.class);
    }

    public int getClusteringKeyIndex() {
      return 0;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final PartitionKeyColumnMetadata<UUID, UUID> chatRoomId = new PartitionKeyColumnMetadata<UUID, UUID>() {
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

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<UUID, UUID> authorId = new ColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "authorid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("authorid")) return null;
      return row.get("authorid", java.util.UUID.class);
    }
  };

  public static final ColumnMetadata<String, String> authorUsername = new ColumnMetadata<String, String>() {
    public String getName() {
      return "authorusername";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("authorusername")) return null;
      return row.get("authorusername", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<String, String> content = new ColumnMetadata<String, String>() {
    public String getName() {
      return "content";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("content")) return null;
      return row.get("content", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<Instant, Instant> sendingDate = new ColumnMetadata<Instant, Instant>() {
    public String getName() {
      return "sendingdate";
    }

    public Class getFieldClass() {
      return java.time.Instant.class;
    }

    public Instant serialize(Instant field) {
      return field;
    }

    public Instant deserialize(Row row) {
      if (row == null || row.isNull("sendingdate")) return null;
      return row.get("sendingdate", java.time.Instant.class);
    }
  };

  public static final MessageByChatRoom_Table message_by_chatroom = new MessageByChatRoom_Table();

  public static final String KEYSPACE_NAME = "charybdis_demo";

  public static final String TABLE_NAME = "message_by_chatroom";

  private MessageByChatRoom_Table() {
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
    results.put("messageid", messageId);
    results.put("chatroomid", chatRoomId);
    results.put("authorid", authorId);
    results.put("authorusername", authorUsername);
    results.put("content", content);
    results.put("sendingdate", sendingDate);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("chatroomid", chatRoomId);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("messageid", messageId);
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

  public void setGeneratedValues(MessageByChatRoom entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(MessageByChatRoom entity, Instant creationDate) {
    if (entity != null) {
      entity.setCreationDate(creationDate);
    }
  }

  public void setLastUpdatedDate(MessageByChatRoom entity, Instant lastUpdatedDate) {
    if (entity != null) {
      entity.setLastUpdatedDate(lastUpdatedDate);
    }
  }

  public Map<String, Object> serialize(MessageByChatRoom entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("creation_date", creationDate.serialize(entity.getCreationDate()));
    columnValueMap.put("last_updated_date", lastUpdatedDate.serialize(entity.getLastUpdatedDate()));
    columnValueMap.put("messageid", messageId.serialize(entity.getMessageId()));
    columnValueMap.put("chatroomid", chatRoomId.serialize(entity.getChatRoomId()));
    columnValueMap.put("authorid", authorId.serialize(entity.getAuthorId()));
    columnValueMap.put("authorusername", authorUsername.serialize(entity.getAuthorUsername()));
    columnValueMap.put("content", content.serialize(entity.getContent()));
    columnValueMap.put("sendingdate", sendingDate.serialize(entity.getSendingDate()));
    return columnValueMap;
  }

  public MessageByChatRoom deserialize(Row row) {
    if (row == null) return null;
    MessageByChatRoom entity = new MessageByChatRoom();
    entity.setCreationDate(creationDate.deserialize(row));
    entity.setLastUpdatedDate(lastUpdatedDate.deserialize(row));
    entity.setMessageId(messageId.deserialize(row));
    entity.setChatRoomId(chatRoomId.deserialize(row));
    entity.setAuthorId(authorId.deserialize(row));
    entity.setAuthorUsername(authorUsername.deserialize(row));
    entity.setContent(content.deserialize(row));
    entity.setSendingDate(sendingDate.deserialize(row));
    return entity;
  }
}
