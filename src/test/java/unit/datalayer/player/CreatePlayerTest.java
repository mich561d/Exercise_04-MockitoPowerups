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
public class CreatePlayerTest {

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
    public void mustCreateStandardPlayerWhenCreatingPlayer() throws PlayerStorageException {
        // Arrange
        var name = "Cipher-X";
        var lastPlayed = new Date(System.currentTimeMillis());
        Player p = new Player(name, 0, 0, lastPlayed);
        // Act
        var id = playerStorage.createPlayer(p);
        var player = playerStorage.getPlayerWithId(id);
        // Assert
        assertNotNull(player);
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
