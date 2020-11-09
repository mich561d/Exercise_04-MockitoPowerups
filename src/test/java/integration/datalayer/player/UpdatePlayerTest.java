package integration.datalayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageImpl;
import org.junit.jupiter.api.*;
import utils.DatabaseUtils;

@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class UpdatePlayerTest {
    private PlayerStorage playerStorage;

    @BeforeAll
    public void beforeAll() {
        DatabaseUtils.setupFlyway("1");
        playerStorage = new PlayerStorageImpl(DatabaseUtils.CON_STR, DatabaseUtils.USER, DatabaseUtils.PASSWORD);
    }

    @Test
    public void test() {
        // Arrange
        // Act
        // Assert
    }
}
