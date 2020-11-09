package unit.datalayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import datalayer.player.PlayerStorageImpl;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetPlayersTest {

    private PlayerStorage playerStorage;
    private Flyway flyway;

    @BeforeAll
    public void beforeAll() {
        flyway = DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
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
        DatabaseUtils.addFakePlayers(numPlayers, playerStorage);
        // Act
        var players = playerStorage.getPlayers();
        // Assert
        assertEquals(numPlayers, players.size());
    }


}
