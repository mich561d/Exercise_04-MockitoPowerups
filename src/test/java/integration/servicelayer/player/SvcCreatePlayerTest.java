package integration.servicelayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageImpl;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceException;
import servicelayer.player.PlayerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
class SvcCreatePlayerTest {

    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "testuser1234";
    @Container
    public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql"))
            .withPassword(PASSWORD)
            .withExposedPorts(PORT);
    private PlayerService svc;
    private PlayerStorage storage;

    // A generic container could be used as well:
//    public static GenericContainer mysql = new GenericContainer(DockerImageName.parse("mysql"))
//            .withExposedPorts(PORT)
//            .withEnv("MYSQL_ROOT_PASSWORD", PASSWORD);

    @BeforeAll
    public void setup() {
        System.err.println("mysql created: " + mysql.isCreated());
        System.err.println("mysql running: " + mysql.isRunning());
        System.err.println("mysql host: " + mysql.getHost());
        String url = "jdbc:mysql://" + mysql.getHost() + ":" + mysql.getFirstMappedPort() + "/";
        String db = "DemoApplicationTest";
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .schemas(db)
                        .defaultSchema(db)
                        .createSchemas(true)
                        .target("4")
                        .dataSource(url, USER, PASSWORD)
        );
        flyway.migrate();

        storage = new PlayerStorageImpl(url + db, USER, PASSWORD);
        svc = new PlayerServiceImpl(storage);
    }

    @Test
    public void mustSavePlayerToDatabaseWhenCallingCreatePlayer() throws PlayerServiceException {
        // Arrange
        var name = "John";
        int id = svc.createPlayer(name);

        // Act
        var player = svc.getPlayerById(id);

        // Assert
        assertEquals(name, player.getName());
    }
}
