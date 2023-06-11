package config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class PostgresInitializer {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(final ConfigurableApplicationContext applicationContext) {
            String hostPort = applicationContext.getEnvironment().getProperty("testcontainers.hostPort");

            if (hostPort == null) {
                hostPort = "5432";
            }

            PostgresTestContainer testContainer = PostgresTestContainer.getInstance(hostPort);

            testContainer.start();

            TestPropertyValues.of(
                    "spring.datasource.url=" + testContainer.getJdbcUrl(),
                    "spring.datasource.username=" + testContainer.getUsername(),
                    "spring.datasource.password=" + testContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());

        }
    }
}
