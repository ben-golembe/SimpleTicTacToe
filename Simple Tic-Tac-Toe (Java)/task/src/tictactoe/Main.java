package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] gameStateArr = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        showGameState(gameStateArr);
        while (true) {
            gameStateArr = makeMove(scanner, gameStateArr, 'X');
            showGameState(gameStateArr);
            if (evaluateGameState(gameStateArr)) {
                break;
            }
            gameStateArr = makeMove(scanner, gameStateArr, 'O');
            showGameState(gameStateArr);
            if (evaluateGameState(gameStateArr)) {
                break;
            }
        }
    }

    public static void showGameState(char[][] gameStateArr) {
        System.out.println("---------");
        for (int i = 0; i < gameStateArr.length; i++) {
            System.out.printf("| %c %c %c |%n", gameStateArr[i][0], gameStateArr[i][1], gameStateArr[i][2]);
        }
        System.out.println("---------");
    }

    public static boolean evaluateGameState(char[][] gameStateArr) {
        boolean finished = false;

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < gameStateArr.length; i++) {
            for (int j = 0; j < gameStateArr[i].length; j++) {
                if (gameStateArr[i][j] == 'X') {
                    xCount++;
                } else if (gameStateArr[i][j] == 'O') {
                    oCount++;
                }
            }
        }

        if (xCount - oCount > 1 || xCount - oCount < -1) {
            System.out.println("Impossible");
            return true;
        }

        boolean xWins = checkWin(gameStateArr, 'X');
        boolean oWins = checkWin(gameStateArr, 'O');

        if (xWins && oWins) {
            System.out.println("Impossible");
        } else if (xWins) {
            System.out.println("X wins");
            finished = true;
        } else if (oWins) {
            System.out.println("O wins");
            finished = true;
        } else if (xCount + oCount == 9) {
            System.out.println("Draw");
            finished = true;
        }

        return finished;
    }

    public static boolean checkWin(char[][] gameStateArr, char player) {
        for (int i = 0; i < 3; i++) {
            if (gameStateArr[i][0] == player && gameStateArr[i][1] == player && gameStateArr[i][2] == player) {
                return true;
            } else if (gameStateArr[0][i] == player && gameStateArr[1][i] == player && gameStateArr[2][i] == player) {
                return true;
            }
        }
        if (gameStateArr[0][0] == player && gameStateArr[1][1] == player && gameStateArr[2][2] == player) {
            return true;
        } else if (gameStateArr[2][0] == player && gameStateArr[1][1] == player && gameStateArr[0][2] == player) {
            return true;
        }
        return false;
    }

    public static char[][] makeMove(Scanner scanner, char[][] gameStateArr, char player) {
        String strRow;
        String strColumn;
        int row;
        int column;

        while (true) {
            strRow = scanner.next();
            strColumn = scanner.next();
            try {
                row = Integer.parseInt(strRow);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            try {
                column = Integer.parseInt(strColumn);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (row < 1 || row > 3 || column < 1 || column > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (gameStateArr[row - 1][column - 1] == 'X' || gameStateArr[row - 1][column - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            gameStateArr[row - 1][column - 1] = player;
            break;
        }

        return gameStateArr;
    }
}
