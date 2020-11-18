package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import ma.markware.charybdis.model.annotation.Keyspace;

@Keyspace(name = DEFAULT_KEYSPACE)
public class Defaultkeyspace {

  public static final String DEFAULT_KEYSPACE = "charybdis_demo";
}
