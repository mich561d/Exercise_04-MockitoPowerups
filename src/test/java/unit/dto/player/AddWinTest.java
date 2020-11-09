package unit.dto.player;

import dto.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class AddWinTest {

    @Test
    public void mustAddWinToPlayer() {
        // Arrange
        Player player = DatabaseUtils.createFakePlayer();
        var oldWins = player.getWins();
        // Act
        player.addWin();
        var newWins = player.getWins();
        // Assert
        assertEquals(oldWins + 1, newWins);
    }
}
