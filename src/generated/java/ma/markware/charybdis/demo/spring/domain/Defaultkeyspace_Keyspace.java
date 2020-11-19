package ma.markware.charybdis.demo.spring.domain;

import java.lang.String;
import ma.markware.charybdis.model.field.metadata.KeyspaceMetadata;

public class Defaultkeyspace_Keyspace implements KeyspaceMetadata {
  public static final Defaultkeyspace_Keyspace charybdis_demo = new Defaultkeyspace_Keyspace();

  public static final String KEYSPACE_NAME = "charybdis_demo";

  private Defaultkeyspace_Keyspace() {
  }

  public String getKeyspaceName() {
    return KEYSPACE_NAME;
  }
}
