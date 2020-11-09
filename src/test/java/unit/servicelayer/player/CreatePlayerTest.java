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

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreatePlayerTest {

    private PlayerService playerService;
    private PlayerStorage storageMock;

    @BeforeAll
    public void beforeAll() {
        storageMock = mock(PlayerStorage.class);
        playerService = new PlayerServiceImpl(storageMock);
    }

    @Test
    public void mustCallStorageWhenCreatingCustomer() throws PlayerServiceException, PlayerStorageException {
        // Arrange
        var name = "Jixter";
        // Act
        playerService.createPlayer(name);
        // Assert
        verify(storageMock, times(1))
                .createPlayer(
                        argThat(x -> x.getName().equals(name) &&
                                x.getWins() == 0 &&
                                x.getLoses() == 0));
    }
}
