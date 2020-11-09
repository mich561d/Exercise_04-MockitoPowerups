package unit.datalayer.player;

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
import utils.DatabaseUtils;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetPlayersTest {

    private PlayerStorage playerStorage;
    private Flyway flyway;

    @BeforeAll
    public void beforeAll() throws PlayerStorageException {
        var url = DatabaseUtils.URL;
        var db = DatabaseUtils.DB;
        var user = DatabaseUtils.USER;
        var password = DatabaseUtils.PASSWORD;

        flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("1")
                .dataSource(url, user, password));

        flyway.clean();
        flyway.migrate();

        playerStorage = new PlayerStorageImpl(url + db, user, password);
    }

    private void addFakePlayers(int numPlayers) throws PlayerStorageException {
        Faker faker = new Faker();
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player(faker.name().username(), 0, 0, new Date(System.currentTimeMillis()));
            playerStorage.createPlayer(p);
        }
    }

    @Test
    public void mustRetrieveZeroPlayers() throws PlayerStorageException {
        // Arrange
        flyway.clean();
        flyway.migrate();
        // Act
        var players = playerStorage.getPlayers();
        // Assert
        assertEquals(0, players.size());
    }

    @Test
    public void mustRetrieveTenPlayers() throws PlayerStorageException {
        // Arrange
        var numPlayers = 10;
        addFakePlayers(numPlayers);
        // Act
        var players = playerStorage.getPlayers();
        // Assert
        assertEquals(numPlayers, players.size());
    }


}
