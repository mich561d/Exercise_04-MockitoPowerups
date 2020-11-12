package unit.functionlayer.game;

import functionlayer.game.GameBoard;
import functionlayer.game.InvalidMoveException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CheckForFullBoardTest {

    @Test
    public void fullBoardMustGiveTrue() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(2, 1, 2);
        gameBoard.makeMove(1, 1, 3);
        gameBoard.makeMove(2, 2, 1);
        gameBoard.makeMove(1, 2, 2);
        gameBoard.makeMove(2, 2, 3);
        gameBoard.makeMove(1, 3, 1);
        gameBoard.makeMove(2, 3, 2);
        gameBoard.makeMove(1, 3, 3);
        // Assert
        assertTrue(gameBoard.checkForFullBoard());
    }

    @Test
    public void almostFullBoardMustGiveFalse() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(2, 1, 2);
        gameBoard.makeMove(1, 1, 3);
        gameBoard.makeMove(2, 2, 1);
        gameBoard.makeMove(1, 2, 2);
        gameBoard.makeMove(2, 2, 3);
        gameBoard.makeMove(1, 3, 1);
        gameBoard.makeMove(2, 3, 2);
        // Assert
        assertFalse(gameBoard.checkForFullBoard());
    }
}
