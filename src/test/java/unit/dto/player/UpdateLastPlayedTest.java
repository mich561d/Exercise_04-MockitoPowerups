package unit.dto.player;

import dto.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class UpdateLastPlayedTest {

    @Test
    public void mustUpdateLastPlayedToPlayer() {
        // Arrange
        Player player = new Player("Nutcracker", 0, 0, new Date(System.currentTimeMillis()));
        var newDate = new Date(System.currentTimeMillis());
        // Act
        player.setLastPlayed(newDate);
        // Assert
        assertEquals(newDate, player.getLastPlayed());
    }
}
