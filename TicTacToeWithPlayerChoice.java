import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeWithPlayerChoice {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameEnded = false;
    private static boolean vsComputer = false;

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        System.out.print("Do you want to play against the computer? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();

        if (choice.equals("Y")) {
            vsComputer = true;
        } else if (choice.equals("N")) {
            vsComputer = false;
        } else {
            System.out.println("Invalid choice. Playing against the computer by default.");
            vsComputer = true;
        }

        while (!gameEnded) {
            if (vsComputer && currentPlayer == 'O') {
                makeComputerMove();
            } else {
                makePlayerMove(scanner);
            }

            displayBoard();

            if (checkGameEnd()) {
                gameEnded = true;
                continue;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void makePlayerMove(Scanner scanner) {
        System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
        int row = -1, col = -1;
        try {
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numbers.");
            scanner.nextLine(); // Clear the invalid input from scanner
            makePlayerMove(scanner);
            return;
        }

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move! Please try again.");
            makePlayerMove(scanner);
        }
    }

    private static void makeComputerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        System.out.println("Computer (O) chooses row: " + (row + 1) + " column: " + (col + 1));
        board[row][col] = 'O';
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean checkGameEnd() {
        if (checkWin() || checkDraw()) {
            return true;
        }
        return false;
    }

    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            System.out.println("Player " + currentPlayer + " wins!");
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            System.out.println("Player " + currentPlayer + " wins!");
            return true;
        }

        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        System.out.println("It's a draw!");
        return true;
    }
}
