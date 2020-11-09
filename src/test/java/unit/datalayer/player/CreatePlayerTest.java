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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreatePlayerTest {

    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
    }

    @Test
    public void mustCreateStandardPlayerWhenCreatingPlayer() throws PlayerStorageException {
        // Arrange
        var name = "Cipher-X";
        var lastPlayed = new Date(System.currentTimeMillis());
        Player p = new Player(name, 0, 0, lastPlayed);
        // Act
        var id = playerStorage.createPlayer(p);
        var player = playerStorage.getPlayerById(id);
        // Assert
        assertNotNull(player);
    }
}
