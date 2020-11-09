package integration.servicelayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceException;
import servicelayer.player.PlayerServiceImpl;
import utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreatePlayerTest {

    private PlayerService playerService;

    @BeforeAll
    public void setup() {
        DatabaseUtils.setupFlyway("1");
        PlayerStorage playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
        playerService = new PlayerServiceImpl(playerStorage);
    }

    @Test
    public void mustSavePlayerToDatabaseWhenCallingCreatePlayer() throws PlayerServiceException {
        // Arrange
        var name = DatabaseUtils.getFakerUsername();
        int id = playerService.createPlayer(name);
        // Act
        var player = playerService.getPlayerById(id);
        // Assert
        assertEquals(name, player.getName());
    }
}
