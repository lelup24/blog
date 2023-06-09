package de.blog.config;

import java.time.Clock;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

  public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Europe/Berlin");
  public static final Clock DEFAULT_CLOCK = Clock.system(DEFAULT_ZONE_ID);

  @Bean
  public Clock clock() {
    return DEFAULT_CLOCK;
  }
}
