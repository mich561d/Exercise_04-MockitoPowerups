package unit.servicelayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceException;
import servicelayer.player.PlayerServiceImpl;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GetPlayerByIdTest {

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
        var id = 1;
        // Act
        playerService.getPlayerById(id);
        // Assert
        verify(storageMock, times(1))
                .getPlayerById(id);
    }
}
