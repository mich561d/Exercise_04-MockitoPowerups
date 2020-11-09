package integration.datalayer.player;

import com.github.javafaker.Faker;
import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import datalayer.player.PlayerStorageImpl;
import dto.Player;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreatePlayerTest {
    private PlayerStorage playerStorage;

    @BeforeAll
    public void Setup() throws PlayerStorageException {
        var url = "jdbc:mysql://localhost:3307/";
        var db = "DemoApplicationTest";
        var user = "root";
        var password = "testuser123";

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("1")
                .dataSource(url, user, password));

        flyway.migrate();

        playerStorage = new PlayerStorageImpl(url + db, user, password);

        var numPlayers = this.playerStorage.getPlayers().size();
        if (numPlayers < 100) {
            addFakePlayers(100 - numPlayers);
        }
    }

    private void addFakePlayers(int numPlayers) throws PlayerStorageException {
        Faker faker = new Faker();
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player(faker.name().username(), 0, 0, new Date(System.currentTimeMillis()));
            playerStorage.createPlayer(p);
        }
    }

    @Test
    public void mustSavePlayerInDatabaseWhenCallingCreatePlayer() throws PlayerStorageException {
        // Arrange
        var name = "Cipher-X";
        Player p = new Player(name, 0, 0, new Date(System.currentTimeMillis()));
        // Act
        playerStorage.createPlayer(p);

        // Assert
        var players = playerStorage.getPlayers();
        assertTrue(
                players.stream().anyMatch(x ->
                        x.getName().equals(name) &&
                                x.getWins() == 0 &&
                                x.getLoses() == 0));
    }

    @Test
    public void mustReturnLatestId() throws PlayerStorageException {
        // Arrange
        var name1 = "Riptide";
        var name2 = "GoJo-juice";
        Player p1 = new Player(name1, 0, 0, new Date(System.currentTimeMillis()));
        Player p2 = new Player(name2, 0, 0, new Date(System.currentTimeMillis()));
        // Act
        var id1 = playerStorage.createPlayer(p1);
        var id2 = playerStorage.createPlayer(p2);

        // Assert
        assertEquals(1, id2 - id1);
    }
}
