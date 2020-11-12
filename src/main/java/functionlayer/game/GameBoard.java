package functionlayer.game;

public class GameBoard {
    private int[][] board;

    public void createBoard() {
        board = new int[3][3];
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean makeMove(int player, int xInput, int yInput) {
        int x = --xInput;
        int y = --yInput;
        if (board[x][y] != 0) return false;
        board[x][y] = player;
        return true;
    }

    public boolean checkForFullBoard() {
        for (int[] x : board) {
            for (int y : x) {
                if (y == 0) return false;
            }
        }
        return true;
    }

    public boolean checkWin(int player) {
        return checkStraightsForWin(player) || checkDiagonalsForWin(player);
    }

    private boolean checkStraightsForWin(int player) {
        for (int i = 0; i < 3; i++) {
            if (checkForThreeIdentical(player, board[0][i], board[1][i], board[2][i])) return true;
            if (checkForThreeIdentical(player, board[i][0], board[i][1], board[i][2])) return true;
        }
        return false;
    }

    private boolean checkDiagonalsForWin(int player) {
        boolean leftToRight = checkForThreeIdentical(player, board[0][0], board[1][1], board[2][2]);
        boolean rightToLeft = checkForThreeIdentical(player, board[0][2], board[1][1], board[2][0]);
        return leftToRight || rightToLeft;
    }

    private boolean checkForThreeIdentical(int player, int i1, int i2, int i3) {
        return ((i1 == player) && (i1 == i2) && (i2 == i3));
    }

    public String printBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------\n");
        for (int[] x : board) {
            for (int y : x) {
                char c = ' ';
                if (y == 2) {
                    c = 'o';
                } else if (y == 1){
                    c = 'x';
                }
                stringBuilder.append("| "+ c + " ");
            }
            stringBuilder.append("|\n-------------\n");
        }
        return stringBuilder.toString();
    }
}
