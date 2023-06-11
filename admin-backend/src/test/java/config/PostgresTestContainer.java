package config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {

    private static final String IMAGE = "postgres:15";
    private static PostgresTestContainer container;

    private PostgresTestContainer() {
        super(IMAGE);
    }

    public static PostgresTestContainer getInstance(String hostPort) {
        if (container == null) {
            container = new PostgresTestContainer()
                    .withDatabaseName("blog_test_db")
                    .withExposedPorts(5432)
                    .withUsername("postgres")
                    .withPassword("postgres");
        }
        container.addFixedExposedPort(Integer.parseInt(hostPort), 5432);
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
