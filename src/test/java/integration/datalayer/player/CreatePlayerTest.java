package integration.datalayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import datalayer.player.PlayerStorageImpl;
import dto.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreatePlayerTest {
    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
    }

    @Test
    public void mustSavePlayerInDatabaseWhenCallingCreatePlayer() throws PlayerStorageException {
        // Arrange
        Player p = DatabaseUtils.createFakePlayer();
        // Act
        playerStorage.createPlayer(p);
        // Assert
        var players = playerStorage.getPlayers();
        assertTrue(
                players.stream().anyMatch(x ->
                        x.getName().equals(p.getName()) &&
                                x.getWins() == 0 &&
                                x.getLoses() == 0));
    }

    @Test
    public void mustReturnLatestId() throws PlayerStorageException {
        // Arrange
        Player p1 = DatabaseUtils.createFakePlayer();
        Player p2 = DatabaseUtils.createFakePlayer();
        // Act
        var id1 = playerStorage.createPlayer(p1);
        var id2 = playerStorage.createPlayer(p2);
        // Assert
        assertEquals(1, id2 - id1);
    }
}
