package unit.datalayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import datalayer.player.PlayerStorageImpl;
import dto.Player;
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

    @BeforeAll
    public void beforeAll() {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
    }

    @Test
    public void mustUpdatePlayerWithWins() throws PlayerStorageException {
        // Arrange
        var id = playerStorage.createPlayer(new Player("Le Mouns", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerById(id);
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
        var id = playerStorage.createPlayer(new Player("Ci-ung", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerById(id);
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
        var id = playerStorage.createPlayer(new Player("Slippers Dippers", 0, 0, new Date(System.currentTimeMillis())));
        var player = playerStorage.getPlayerById(id);
        // Act
        player.setLastPlayed(new Date(System.currentTimeMillis()));
        playerStorage.updatePlayer(player);
        var updatePlayer = playerStorage.getPlayerById(player.getId());
        // Assert
        assertEquals(updatePlayer.getLastPlayed().toString(), player.getLastPlayed().toString());
    }
}
