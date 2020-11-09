package unit.datalayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import datalayer.player.PlayerStorageImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.DatabaseUtils;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class UpdatePlayerTest {

    private PlayerStorage playerStorage;
    private int playerId;

    @BeforeAll
    public void beforeAll() throws PlayerStorageException {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
        playerId = playerStorage.createPlayer(DatabaseUtils.createFakePlayer());
    }

    @Test
    public void mustUpdatePlayerWithWins() throws PlayerStorageException {
        // Arrange
        var player = playerStorage.getPlayerById(playerId);
        // Act
        player.addWin();
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerById(player.getId());
        // Assert
        assertEquals(player.getWins(), updatePlayer.getWins());
    }

    @Test
    public void mustUpdatePlayerWithLoses() throws PlayerStorageException {
        // Arrange
        var player = playerStorage.getPlayerById(playerId);
        // Act
        player.addLoses();
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerById(player.getId());
        // Assert
        assertEquals(player.getLoses(), updatePlayer.getLoses());
    }

    @Test
    public void mustUpdatePlayerWithLastPlayed() throws PlayerStorageException {
        // Arrange
        var player = playerStorage.getPlayerById(playerId);
        // Act
        player.setLastPlayed(new Date(System.currentTimeMillis()));
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerById(player.getId());
        // Assert
        assertEquals(updatePlayer.getLastPlayed().toString(), player.getLastPlayed().toString());
    }
}
