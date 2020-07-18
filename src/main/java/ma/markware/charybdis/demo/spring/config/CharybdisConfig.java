package ma.markware.charybdis.demo.spring.config;

import com.datastax.oss.driver.api.core.CqlSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import ma.markware.charybdis.crud.DefaultEntityManager;
import ma.markware.charybdis.crud.EntityManager;
import ma.markware.charybdis.dsl.DefaultDslQuery;
import ma.markware.charybdis.dsl.DslQuery;
import ma.markware.charybdis.session.DefaultSessionFactory;
import ma.markware.charybdis.session.SessionFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharybdisConfig {

  @Bean(destroyMethod = "shutdown")
  SessionFactory sessionFactory() {
    return new DefaultSessionFactory();
  }

  @Bean
  public DslQuery dslQuery() {
    return new DefaultDslQuery(sessionFactory());
  }

  @Bean
  public EntityManager entityManager() {
    return new DefaultEntityManager(sessionFactory());
  }

  @PostConstruct
  public void createDb() throws IOException {
    SessionFactory sessionFactory = sessionFactory();
    CqlSession session = sessionFactory.getSession();
    executeCqlFile(session, "ddl_create.cql");
  }

  private void executeCqlFile(CqlSession session, String cqlFilename) throws IOException {
    InputStream resourceAsStream = getClass().getClassLoader()
                                             .getResourceAsStream(cqlFilename);
    assert resourceAsStream != null;
    StringWriter writer = new StringWriter();
    IOUtils.copy(resourceAsStream, writer, StandardCharsets.UTF_8);
    String[] statements = StringUtils.split(writer.toString(), ";\n");
    Arrays.stream(statements)
          .filter(StringUtils::isNotBlank)
          .map(statement -> StringUtils.normalizeSpace(statement) + ";\n")
          .forEach(session::execute);
  }
}
