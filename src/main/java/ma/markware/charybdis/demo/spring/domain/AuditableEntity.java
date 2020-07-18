package ma.markware.charybdis.demo.spring.domain;

import java.time.Instant;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.CreationDate;
import ma.markware.charybdis.model.annotation.LastUpdatedDate;

public class AuditableEntity {

  @Column(name = "creation_date")
  @CreationDate
  private Instant creationDate;

  @Column(name = "last_updated_date")
  @LastUpdatedDate
  private Instant lastUpdatedDate;

  public Instant getCreationDate() {
    return creationDate;
  }

  public Instant getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setCreationDate(final Instant creationDate) {
    this.creationDate = creationDate;
  }

  public void setLastUpdatedDate(final Instant lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }
}
