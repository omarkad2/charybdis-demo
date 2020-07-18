package ma.markware.charybdis.demo.spring.domain;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.Frozen;
import ma.markware.charybdis.model.annotation.Index;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = "keyspace_demo", name = "user")
public class User extends AuditableEntity {

  @Column
  @PartitionKey
  private String name;

  @Column
  private List<@Frozen Address> addresses;

  @Column
  @Index(name = "access_role") // Generates a secondary index on this column
  private RoleEnum role;

  @Column(name = "access_logs")
  private Map<Instant, String> accessLogs;

  public User() {
  }

  public User(final String name, final List<Address> addresses, final RoleEnum role, final Map<Instant, String> accessLogs) {
    this.name = name;
    this.addresses = addresses;
    this.role = role;
    this.accessLogs = accessLogs;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(final List<Address> addresses) {
    this.addresses = addresses;
  }

  public RoleEnum getRole() {
    return role;
  }

  public void setRole(final RoleEnum role) {
    this.role = role;
  }

  public Map<Instant, String> getAccessLogs() {
    return accessLogs;
  }

  public void setAccessLogs(final Map<Instant, String> accessLogs) {
    this.accessLogs = accessLogs;
  }
}
