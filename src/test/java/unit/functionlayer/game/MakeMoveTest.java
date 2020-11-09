package unit.functionlayer.game;

import functionlayer.game.GameBoard;
import functionlayer.game.InvalidMoveException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class MakeMoveTest {

    @Test
    public void mustChangeBoardWhenDoingAValidMove() throws InvalidMoveException {
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
        // Assert
        assertThrows(InvalidMoveException.class, () -> {
            // Act
            gameBoard.makeMove(1, 1, 1);
            gameBoard.makeMove(1, 1, 1);
        });
    }

    @Test
    public void mustThrowExceptionWhenDoingAnInvalidMoveAsOpponent() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        gameBoard.createBoard();
        // Assert
        assertThrows(InvalidMoveException.class, () -> {
            // Act
            gameBoard.makeMove(1, 1, 1);
            gameBoard.makeMove(2, 1, 1);
        });
    }
}
