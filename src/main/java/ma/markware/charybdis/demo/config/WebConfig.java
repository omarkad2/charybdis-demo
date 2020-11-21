package ma.markware.charybdis.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // Can just allow `methods` that you need.
    registry.addMapping("/**")
            .allowedMethods("PUT", "GET", "DELETE", "OPTIONS", "PATCH", "POST");
  }
}
