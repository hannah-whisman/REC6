/**
 * This class implements the game we all love to
 * not play.
 *
 * @author (M Rasamny)
 * @version (03 / 13 / 2018)
 */
public class TicTacToe {
    // instance variables
    private GamePiece[][] board;
    private GamePiece[] player;
    private int nextPlayerIndex;
    private int numOfMoves;

    public TicTacToe(GamePiece p1, GamePiece p2) {
        board = new GamePiece[3][3];
        clear();
        player = new GamePiece[2];
        player[0] = new GamePiece(p1);
        player[1] = new GamePiece(p2);
        nextPlayerIndex = 0;
        numOfMoves = 0;
    }


    /**
     * Returns true if the location is an integer that represents one of the squares on the board ; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is an integer that represents one of the squares on the board ; false otherwise
     */
    public boolean isValid(int location) {
        return location >= 0 && location < 9;
        //return false;
    }

    /**
     * Returns true if the location is NOT occupied by a game piece; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is NOT occupied by a game piece; false otherwise
     */
    public boolean isEmpty(int location) {
        int row = location / 3;
        int col = location % 3;
        return board[row][col] == null;
        //return false;
    }

    /**
     * Returns the number of moves remaining on the board
     *
     * @return the number of moves remaining on the board
     */
    public int movesRemaining() {
        return 9 - numOfMoves;
        //return 0;
    }

    /**
     * Returns the game piece at the provided location
     *
     * @param location the integer representation of the square
     * @return the game piece at the provided location
     */
    public GamePiece getPiece(int location) {
        int row = location / 3;
        int col = location % 3;
        return board[row][col];
        //return null;
    }

    /**
     * Returns the winner's GamePiece or null if there is no winner at the time the method is invoked
     *
     * @return the winner's GamePiece or null if there is no winner at the time the method is invoked
     */
    public GamePiece getWinner() {
        GamePiece winner = null;
        int[][] combos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal winning combinations
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical winning combinations
                {0, 4, 8}, {2, 4, 6}             // diagonal winning combinations
        };

        for (int[] combo : combos) {
            int a = combo[0];
            int b = combo[1];
            int c = combo[2];

            if (board[a / 3][a % 3] != null &&
                    board[a / 3][a % 3].equals(board[b / 3][b % 3]) &&
                    board[a / 3][a % 3].equals(board[c / 3][c % 3])) {
                return board[a / 3][a % 3];
            }
        }

        return winner;
    }

    /**
     * Indicates the current player by returning the current player's game piece
     * @return the current player's game piece.
     */
    public GamePiece getCurrentPlayer() {
        return new GamePiece(player[nextPlayerIndex]);
    }

    /**
     * Places a game piece at the provided location if and only if the location is valid and is empty.  Returns
     * true if the operation is successful; false otherwise
     * @param location the integer representation of the square.
     * @return true if it is able to place a game piece at the specified location; false otherwise
     */
    public boolean add(int location) {
        if (isValid(location) && isEmpty(location)) {
            int row = location / 3;
            int col = location % 3;
            board[row][col] = player[nextPlayerIndex];
            nextPlayerIndex = (nextPlayerIndex + 1) % 2;
            numOfMoves++;
            return true;
        }
        return false;

    }

    /**
     * Clears the board of all game pieces
     */
    public void clear() {
        // set all elements of 2d array to null
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
        numOfMoves = 0;
    }

    /**
     * Returns a string representation of the board
     * @return a string representation of the board
     */
    @Override
    public String toString() {
        String s = "";
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) {
                    s += "-";
                } else {
                    s += board[row][col].getPiece();
                }
                if (col < 2) {
                    s += " | ";
                }
            }
            s += "\n";
            if (row < 2) {
                s += "---------\n";
            }
        }
        return s;
    }
}