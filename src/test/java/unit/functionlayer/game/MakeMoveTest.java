package unit.functionlayer.game;

import functionlayer.game.GameBoard;
import functionlayer.game.InvalidMoveException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class MakeMoveTest {

    @Test
    public void mustChangeBoardWhenDoingAValidMove() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        gameBoard.createBoard();
        // Act
        gameBoard.makeMove(1, 1, 1);
        int[][] board = gameBoard.getBoard();
        int[][] expected = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        // Assert
        assertArrayEquals(expected, board);
    }

    @Test
    public void mustThrowExceptionWhenDoingAnInvalidMove() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        gameBoard.createBoard();
        // Act
        gameBoard.makeMove(1, 1, 1);
        boolean result = gameBoard.makeMove(1, 1, 1);
        // Assert
        assertFalse(result);
    }

    @Test
    public void mustThrowExceptionWhenDoingAnInvalidMoveAsOpponent() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        gameBoard.createBoard();
        // Act
        gameBoard.makeMove(1, 1, 1);
        boolean result = gameBoard.makeMove(2, 1, 1);
        // Assert
        assertFalse(result);
    }
}
