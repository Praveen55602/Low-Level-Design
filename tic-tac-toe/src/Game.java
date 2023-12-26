import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    int playerCount;
    Deque<Player> playerQueue;
    Board gameBoard;
    Player currentPlayer;
    Scanner inputScanner = new Scanner(System.in);
    int uniqueId;

    public Game() {
        playerQueue = new LinkedList<Player>();
        uniqueId = 1;
        SetupGame();
        SetupPlayer();
    }

    void SetupPlayer() {
        System.out.println("Enter number of players ");
        this.playerCount = Integer.parseInt(inputScanner.nextLine());
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name ");
            String playerName = inputScanner.nextLine();
            System.out.print("Enter player symbol ");
            Character playerSymbol = inputScanner.nextLine().charAt(0);
            Player newPlayer = new Player(playerName, playerSymbol, uniqueId++);
            playerQueue.push(newPlayer);
        }
    }

    void SetupGame() {
        System.out.println("Board size ");
        int size = Integer.parseInt(inputScanner.nextLine());
        this.gameBoard = new Board(size);
    }

    public void StartGame() {
        GameLoop();
    }

    private void GameLoop() {
        boolean winnerFound = false;
        while (!winnerFound) {
            if (gameBoard.isBoardFull())
                break;
            gameBoard.displayBoard();
            currentPlayer = playerQueue.removeFirst();
            System.out.println(String.format("%s's turn", currentPlayer.getPlayerName()));
            System.out.println("write position to play at(x y)");
            String s = inputScanner.nextLine();
            String[] values = s.split(" ");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);
            boolean moveSuccess = gameBoard.addMoveToBoard(inputRow, inputColumn, currentPlayer.getPlayerCharacter());
            if (moveSuccess) {
                winnerFound = gameBoard.isThereWinner(inputRow, inputColumn, currentPlayer.getPlayerCharacter());
                playerQueue.add(currentPlayer);
            } else {
                System.out.println("wrong input please try again");
                playerQueue.push(currentPlayer);
            }
        }

        gameBoard.displayBoard();
        if (winnerFound) {
            System.out.println("Game over! winner is " + currentPlayer.getPlayerName());
            return;
        }
        System.out.println("Game Over! draw");
        inputScanner.close();
    }
}
