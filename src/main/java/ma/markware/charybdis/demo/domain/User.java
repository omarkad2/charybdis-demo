package ma.markware.charybdis.demo.domain;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.GeneratedValue;
import ma.markware.charybdis.model.annotation.Index;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = Defaultkeyspace.DEFAULT_KEYSPACE, name = "user")
public class User extends AuditableEntity {

  @Column
  @PartitionKey
  @GeneratedValue
  private UUID userId;

  @Column
  @Index // Secondary index (less performant than a true denormalization)
  private String username;

  // Used by charybdis
  public User() {
  }

  public User(final String username) {
    this.username = username;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(final UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }
}
