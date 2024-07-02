import java.util.Random;
import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_SLOT = '-';
    private static final char PLAYER_ONE_TOKEN = 'R';
    private static final char PLAYER_TWO_TOKEN = 'Y';
    private static final char COMPUTER_TOKEN = 'C';
    private char[][] board = new char[ROWS][COLUMNS];
    private char currentPlayer;

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.initializeBoard();
        game.playGame();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect Four!");
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Computer");
        int gameMode = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean playAgainstComputer = (gameMode == 2);
        boolean gameRunning = true;
        currentPlayer = PLAYER_ONE_TOKEN;

        while (gameRunning) {
            displayBoard();
            int col;
            if (currentPlayer == COMPUTER_TOKEN && playAgainstComputer) {
                col = getComputerMove();
            } else {
                System.out.println("Player " + currentPlayer + ", enter your move (column[1-7]): ");
                col = scanner.nextInt() - 1;
                if (col < 0 || col >= COLUMNS) {
                    System.out.println("Invalid input! Please enter a number between 1 and 7.");
                    continue;
                }
            }

            if (isValidMove(col)) {
                makeMove(col);
                if (isWinningMove()) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameRunning = false;
                } else if (isBoardFull()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    gameRunning = false;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_ONE_TOKEN) ? (playAgainstComputer ? COMPUTER_TOKEN : PLAYER_TWO_TOKEN) : PLAYER_ONE_TOKEN;
                }
            } else {
                System.out.println("Column is full! Please try another column.");
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private void displayBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private boolean isValidMove(int col) {
        return board[0][col] == EMPTY_SLOT;
    }

    private void makeMove(int col) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY_SLOT) {
                board[i][col] = currentPlayer;
                break;
            }
        }
    }

    private boolean isWinningMove() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i][j + 1] == currentPlayer &&
                    board[i][j + 2] == currentPlayer &&
                    board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i + 1][j] == currentPlayer &&
                    board[i + 2][j] == currentPlayer &&
                    board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        // Check bottom-left to top-right diagonals
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i - 1][j + 1] == currentPlayer &&
                    board[i - 2][j + 2] == currentPlayer &&
                    board[i - 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        // Check top-left to bottom-right diagonals
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i + 1][j + 1] == currentPlayer &&
                    board[i + 2][j + 2] == currentPlayer &&
                    board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int j = 0; j < COLUMNS; j++) {
            if (board[0][j] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }

    private int getComputerMove() {
        Random random = new Random();
        int col;
        do {
            col = random.nextInt(COLUMNS);
        } while (!isValidMove(col));
        return col;
    }
}








































































































































































































































































































































































































































// import java.util.InputMismatchException;
// import java.util.Random;
// import java.util.Scanner;

// public class ConnectFour {
//     private static final int ROWS = 6;
//     private static final int COLUMNS = 7;
//     private static final char EMPTY_SLOT = '-';
//     private static char[][] board = new char[ROWS][COLUMNS];
//     private static char currentPlayer = 'R';
//     private static boolean playingAgainstComputer = false;

//     public static void main(String[] args) {
//         initializeBoard();
//         displayBoard();

//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Do you want to play against the computer? (yes/no): ");
//         String playAgainstComputerResponse = scanner.nextLine().trim().toLowerCase();
//         if (playAgainstComputerResponse.equals("yes")) {
//             playingAgainstComputer = true;
//         }

//         boolean gameRunning = true;

//         while (gameRunning) {
//             int col;
//             if (currentPlayer == 'Y' && playingAgainstComputer) {
//                 col = getComputerMove();
//             } else {
//                 System.out.println("Player " + currentPlayer + ", enter your move (column[1-7]): ");
//                 try {
//                     col = scanner.nextInt() - 1;
//                     if (col < 0 || col >= COLUMNS) {
//                         throw new InputMismatchException();
//                     }
//                 } catch (InputMismatchException e) {
//                     System.out.println("Invalid input! Please enter a number between 1 and 7.");
//                     scanner.nextLine(); // Clear the invalid input
//                     continue;
//                 }
//             }

//             if (isValidMove(col)) {
//                 makeMove(col);
//                 displayBoard();

//                 if (isWinningMove()) {
//                     System.out.println("Player " + currentPlayer + " wins!");
//                     gameRunning = false;
//                 } else if (isBoardFull()) {
//                     System.out.println("It's a draw!");
//                     gameRunning = false;
//                 } else {
//                     currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
//                 }
//             } else {
//                 System.out.println("Column is full! Please try another column.");
//             }
//         }

//         System.out.println("Thank you for playing!");
//         scanner.close();
//     }

//     private static void initializeBoard() {
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLUMNS; j++) {
//                 board[i][j] = EMPTY_SLOT;
//             }
//         }
//     }

//     private static void displayBoard() {
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLUMNS; j++) {
//                 System.out.print(board[i][j] + " ");
//             }
//             System.out.println();
//         }
//         System.out.println("1 2 3 4 5 6 7");
//     }

//     private static boolean isValidMove(int col) {
//         return board[0][col] == EMPTY_SLOT;
//     }

//     private static void makeMove(int col) {
//         for (int i = ROWS - 1; i >= 0; i--) {
//             if (board[i][col] == EMPTY_SLOT) {
//                 board[i][col] = currentPlayer;
//                 break;
//             }
//         }
//     }

//     private static boolean isWinningMove() {
//         return checkHorizontal() || checkVertical() || checkDiagonal();
//     }

//     private static boolean checkHorizontal() {
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLUMNS - 3; j++) {
//                 if (board[i][j] == currentPlayer &&
//                     board[i][j + 1] == currentPlayer &&
//                     board[i][j + 2] == currentPlayer &&
//                     board[i][j + 3] == currentPlayer) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     private static boolean checkVertical() {
//         for (int i = 0; i < ROWS - 3; i++) {
//             for (int j = 0; j < COLUMNS; j++) {
//                 if (board[i][j] == currentPlayer &&
//                     board[i + 1][j] == currentPlayer &&
//                     board[i + 2][j] == currentPlayer &&
//                     board[i + 3][j] == currentPlayer) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     private static boolean checkDiagonal() {
//         // Check bottom-left to top-right diagonals
//         for (int i = 3; i < ROWS; i++) {
//             for (int j = 0; j < COLUMNS - 3; j++) {
//                 if (board[i][j] == currentPlayer &&
//                     board[i - 1][j + 1] == currentPlayer &&
//                     board[i - 2][j + 2] == currentPlayer &&
//                     board[i - 3][j + 3] == currentPlayer) {
//                     return true;
//                 }
//             }
//         }
//         // Check top-left to bottom-right diagonals
//         for (int i = 0; i < ROWS - 3; i++) {
//             for (int j = 0; j < COLUMNS - 3; j++) {
//                 if (board[i][j] == currentPlayer &&
//                     board[i + 1][j + 1] == currentPlayer &&
//                     board[i + 2][j + 2] == currentPlayer &&
//                     board[i + 3][j + 3] == currentPlayer) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     private static boolean isBoardFull() {
//         for (int j = 0; j < COLUMNS; j++) {
//             if (board[0][j] == EMPTY_SLOT) {
//                 return false;
//             }
//         }
//         return true;
//     }

//     private static int getComputerMove() {
//         Random random = new Random();
//         int col;
//         do {
//             col = random.nextInt(COLUMNS);
//         } while (!isValidMove(col));
//         return col;
//     }
// }






