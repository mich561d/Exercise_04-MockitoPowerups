package unit.dto.player;

import dto.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class AddLosesTest {

    @Test
    public void mustAddLosesToPlayer() {
        // Arrange
        Player player = new Player("BigDawg", 0, 0, new Date(System.currentTimeMillis()));
        var oldLoses = player.getLoses();
        // Act
        player.addLoses();
        var newLoses = player.getLoses();
        // Assert
        assertEquals(oldLoses + 1, newLoses);
    }
}
