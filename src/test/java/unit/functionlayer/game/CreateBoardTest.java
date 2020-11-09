package unit.functionlayer.game;

import functionlayer.game.GameBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBoardTest {

    @Test
    public void mustCreateEmptyBoard() {
        // Arrange
        GameBoard gameBoard = new GameBoard();
        // Act
        gameBoard.createBoard();
        int[][] board = gameBoard.getBoard();
        int[][] expected = new int[3][3];
        // Assert
        assertArrayEquals(expected, board);
    }
}
