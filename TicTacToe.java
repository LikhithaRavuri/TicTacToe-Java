import java.util.Scanner;

public class TicTacToe {

    // 3x3 game board
    private static char[][] board = new char[3][3];

    // Current player symbol ('X' or 'O')
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;

        // Show instructions
        printInstructions();

        do {
            initializeBoard();
            playGame(sc);

            // Ask if players want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");

        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
        sc.close();
    }

    // Prints instructions for players
    private static void printInstructions() {
        System.out.println("================ TIC TAC TOE ================");
        System.out.println("Two players game: Player 1 = X, Player 2 = O");
        System.out.println("Enter your move as: row column");
        System.out.println("Rows and columns are 0, 1, or 2");
        System.out.println("Example: 0 1 means row 0, column 1");
        System.out.println("===========================================\n");
    }

    // Initializes the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    // Main game loop
    private static void playGame(Scanner sc) {
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();

            // Get valid move from player
            int row, col;
            while (true) {
                System.out.print("Player " + currentPlayer + ", enter your move (row col): ");
                row = sc.nextInt();
                col = sc.nextInt();

                if (isValidMove(row, col)) {
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            // Place the move
            board[row][col] = currentPlayer;

            // Check for win
            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            }
            // Check for draw
            else if (checkDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            }
            // Switch player
            else {
                switchPlayer();
            }
        }
    }

    // Prints the current board state
    private static void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    // Checks if the move is valid
    private static boolean isValidMove(int row, int col) {
        // Check bounds
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        // Check if cell is empty
        return board[row][col] == ' ';
    }

    // Switches the current player
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Checks if the current player has won
    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // Checks if the game is a draw
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Empty cell found, not a draw
                }
            }
        }
        return true; // No empty cells
    }
}
