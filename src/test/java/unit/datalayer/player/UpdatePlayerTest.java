package unit.datalayer.player;

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

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class UpdatePlayerTest {

    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() {
        var url = DatabaseUtils.URL;
        var db = DatabaseUtils.DB;
        var user = DatabaseUtils.USER;
        var password = DatabaseUtils.PASSWORD;

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("1")
                .dataSource(url, user, password));

        flyway.clean();
        flyway.migrate();

        playerStorage = new PlayerStorageImpl(url + db, user, password);
    }

    @Test
    public void mustUpdatePlayerWithWins() throws PlayerStorageException {
        // Arrange
        var id = playerStorage.createPlayer(new Player("Le Mouns", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerWithId(id);
        // Act
        player.addWin();
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerWithId(player.getId());
        // Assert
        assertEquals(player.getWins(), updatePlayer.getWins());
    }

    @Test
    public void mustUpdatePlayerWithLoses() throws PlayerStorageException {
        // Arrange
        var id = playerStorage.createPlayer(new Player("Ci-ung", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerWithId(id);
        // Act
        player.addLoses();
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerWithId(player.getId());
        // Assert
        assertEquals(player.getLoses(), updatePlayer.getLoses());
    }

    @Test
    public void mustUpdatePlayerWithLastPlayed() throws PlayerStorageException {
        // Arrange
        var id = playerStorage.createPlayer(new Player("Slippers Dippers", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerWithId(id);
        // Act
        player.setLastPlayed(new Date(System.currentTimeMillis()));
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerWithId(player.getId());
        // Assert
        assertEquals(updatePlayer.getLastPlayed().toString(), player.getLastPlayed().toString());
    }
}
