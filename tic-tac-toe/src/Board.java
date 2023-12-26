public class Board {
    private int boardSize;
    private Character[][] board;
    private int movesLeft;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        movesLeft = boardSize * boardSize;
        setupBoard();
    }

    private void setupBoard() {
        resetBoard();
    }

    private void resetBoard() {
        board = new Character[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void displayBoard() {
        for (Character[] characters : board) {
            for (Character character : characters) {
                System.out.print(character + " ");
            }
            System.out.println(' ');
        }
    }

    public boolean isBoardFull() {
        if (movesLeft == 0)
            return true;

        return false;
    }

    public boolean addMoveToBoard(int x, int y, Character playerSymbol) {
        if (x >= boardSize || y >= boardSize || board[x][y] != '-')
            return false;
        board[x][y] = playerSymbol;
        movesLeft--;
        return true;
    }

    public boolean isThereWinner(int row, int column, Character pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // need to check in row
        for (int i = 0; i < board.length; i++) {

            if (board[row][i] == '-' || board[row][i] != pieceType) {
                rowMatch = false;
            }
        }

        // need to check in column
        for (int i = 0; i < board.length; i++) {

            if (board[i][column] == '-' || board[i][column] != pieceType) {
                columnMatch = false;
            }
        }

        // need to check diagonals
        for (int i = 0, j = 0; i < board.length; i++, j++) {
            if (board[i][j] == '-' || board[i][j] != pieceType) {
                diagonalMatch = false;
            }
        }

        // need to check anti-diagonals
        for (int i = 0, j = board.length - 1; i < board.length; i++, j--) {
            if (board[i][j] == '-' || board[i][j] != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
