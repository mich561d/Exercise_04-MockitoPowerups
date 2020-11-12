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
public class CheckWinTest {

    @Test
    public void threeInARowMustGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(1, 1, 2);
        gameBoard.makeMove(1, 1, 3);
        // Assert
        assertTrue(gameBoard.checkWin(1));
    }

    @Test
    public void threeInAColumnMustGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(1, 2, 1);
        gameBoard.makeMove(1, 3, 1);
        // Assert
        assertTrue(gameBoard.checkWin(1));
    }

    @Test
    public void threeDiagonalLeftToRightMustGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(1, 2, 2);
        gameBoard.makeMove(1, 3, 3);
        // Assert
        assertTrue(gameBoard.checkWin(1));
    }

    @Test
    public void threeDiagonalRightToLeftMustGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 3);
        gameBoard.makeMove(1, 2, 2);
        gameBoard.makeMove(1, 3, 1);
        // Assert
        assertTrue(gameBoard.checkWin(1));
    }

    @Test
    public void mustNotGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(1, 1, 2);
        gameBoard.makeMove(1, 1, 3);
        // Assert
        assertFalse(gameBoard.checkWin(2));
    }

    @Test
    public void noWinConditionMustNotGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        // Assert
        assertFalse(gameBoard.checkWin(1));
    }

    @Test
    public void OnePinMustNotGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        // Assert
        assertFalse(gameBoard.checkWin(1));
    }

    @Test
    public void TwoPinMustNotGiveWin() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        gameBoard.makeMove(1, 1, 1);
        gameBoard.makeMove(1, 1, 2);
        // Assert
        assertFalse(gameBoard.checkWin(1));
    }
}
