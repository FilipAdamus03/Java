import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the board size: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        boolean[][] board = generateBoard(n);
        printBoard(board);

        while (true) {
            System.out.println("Press Enter to go to the next game state...");
            scanner.nextLine();
            board = updateBoard(board);
            printBoard(board);
        }
    }

    public static boolean[][] generateBoard(int n) {
        boolean[][] board = new boolean[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = random.nextDouble() < 0.3;
            }
        }
        return board;
    }

    public static void printBoard(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean cell : row) {
                System.out.print(cell ? "*" : ".");
            }
            System.out.println();
        }
    }

    public static boolean[][] updateBoard(boolean[][] board) {
        int n = board.length;
        boolean[][] newBoard = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int neighbors = getNeighbors(board, i, j);
                if (!board[i][j] && neighbors == 3) {
                    newBoard[i][j] = true;
                } else if (board[i][j] && (neighbors == 2 || neighbors == 3)) {
                    newBoard[i][j] = true;
                }
            }
        }
        return newBoard;
    }

    public static int getNeighbors(boolean[][] board, int i, int j) {
        int n = board.length;
        int count = 0;
        for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, n - 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, n - 1); y++) {
                if ((x != i || y != j) && board[x][y]) {
                    count++;
                }
            }
        }
        return count;
    }
}
