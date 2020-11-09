package functionlayer.game;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Invalid move! Please try another..");
    }
}
