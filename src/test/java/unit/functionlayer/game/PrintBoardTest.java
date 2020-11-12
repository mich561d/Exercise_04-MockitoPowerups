package unit.functionlayer.game;

import functionlayer.game.GameBoard;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class PrintBoardTest {

    @Test
    public void printEmptyBoardMustNotBeNull() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        // Assert
        assertNotNull(gameBoard.printBoard());
    }

    @Test
    public void printBoardMustContainsMarkers() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1,1,1);
        gameBoard.makeMove(2,2,2);
        String result = gameBoard.printBoard();
        // Assert
        assertTrue(result.contains("x") && result.contains("o"));
    }
}
