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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetPlayerByIdTest {

    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() throws PlayerStorageException {
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
    public void mustRetrievePlayerWhenCreatingPlayer() throws PlayerStorageException {
        // Arrange
        Player p1 = new Player("JigSaw", 0, 0, new Date(System.currentTimeMillis()));
        var id = playerStorage.createPlayer(p1);
        // Act
        Player p2 = playerStorage.getPlayerWithId(id);
        // Assert
        assertEquals(p1.getName(), p2.getName());
    }

    @Test
    public void mustNotRetrievePlayerWhenGivingInvalidId() {
        // Arrange
        var invalidId = 9001;
        // Assert
        assertThrows(PlayerStorageException.class, () -> {
            // Act
            Player p2 = playerStorage.getPlayerWithId(invalidId);
        });
    }
}
