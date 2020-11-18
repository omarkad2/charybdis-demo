package ma.markware.charybdis.demo.spring.config;

import com.datastax.oss.driver.api.core.CqlSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import ma.markware.charybdis.CqlTemplate;
import ma.markware.charybdis.session.DefaultSessionFactory;
import ma.markware.charybdis.session.SessionFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CharybdisConfig {

  @Bean(destroyMethod = "shutdown")
  public SessionFactory sessionFactory() {
    return new DefaultSessionFactory();
  }

  @Bean
  public CqlTemplate cqlTemplate() {
    return new CqlTemplate(sessionFactory());
  }

  @PostConstruct
  public void createDb() throws IOException {
    SessionFactory sessionFactory = sessionFactory();
    CqlSession session = sessionFactory.getSession();
    executeCqlFile(session, "ddl_create.cql");
  }

  private void executeCqlFile(CqlSession session, String cqlFilename) throws IOException {
    try (InputStream resourceAsStream = new ClassPathResource(cqlFilename).getInputStream()) {
      StringWriter writer = new StringWriter();
      IOUtils.copy(resourceAsStream, writer, StandardCharsets.UTF_8);
      String[] statements = StringUtils.split(writer.toString(), ";\n");
      Arrays.stream(statements)
            .filter(StringUtils::isNotBlank)
            .map(statement -> StringUtils.normalizeSpace(statement) + ";\n")
            .forEach(session::execute);
    }
  }
}
