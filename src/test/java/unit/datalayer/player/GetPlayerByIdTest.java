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
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetPlayerByIdTest {

    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
    }

    @Test
    public void mustRetrievePlayerWhenCreatingPlayer() throws PlayerStorageException {
        // Arrange
        Player p1 = new Player("JigSaw", 0, 0, new Date(System.currentTimeMillis()));
        var id = playerStorage.createPlayer(p1);
        // Act
        Player p2 = playerStorage.getPlayerById(id);
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
            Player p2 = playerStorage.getPlayerById(invalidId);
        });
    }
}
