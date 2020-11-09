package unit.servicelayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import dto.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceException;
import servicelayer.player.PlayerServiceImpl;

import java.sql.Date;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class UpdatePlayerTest {

    private PlayerService playerService;
    private PlayerStorage storageMock;

    @BeforeAll
    public void beforeAll() {
        storageMock = mock(PlayerStorage.class);
        playerService = new PlayerServiceImpl(storageMock);
    }

    @Test
    public void mustCallStorageWhenRetrievingACustomer() throws PlayerServiceException, PlayerStorageException {
        // Arrange
        Player player = new Player(1, "Test-Player", 1, 0, new Date(System.currentTimeMillis()));
        // Act
        playerService.updatePlayer(player);
        // Assert
        verify(storageMock, times(1))
                .updatePlayer(player);
    }
}
