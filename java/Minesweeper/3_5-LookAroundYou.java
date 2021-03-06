package minesweeper;

import java.util.Scanner;
import java.util.Random;

public class Main {

    final static String mine = "X";
    final static String notMine = ".";
    static int maxRows = 9;
    static int maxColumns = 9;

    public static void main(String[] args) {

        // Input for receiving number of mines
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int userInput = scanner.nextInt();

        // Creating minefield with numbers
        String[][] minefield = addMines(userInput, generateEmptyMineField());

        // Printing minefield
        printMineField(minefield);
    }

    // Function for generating an empty minefield
    public static String[][] generateEmptyMineField() {
        String[][] minefield = new String[maxRows][maxColumns];
        for (int rowPos = 0; rowPos < maxRows; rowPos++) {
            for (int columnPos = 0; columnPos < maxColumns; columnPos++) {
                minefield[rowPos][columnPos] = notMine;
            }
        }
        return minefield;
    }

    // Function for randomly placing mines in the minefield
    public static String[][] addMines(int numberOfMines, String[][] minefield) {

        // Variable for keeping track of how many mines are placed.
        int mineCounter = 0;

        // Creating random number generator object
        Random random = new Random();

        // Variables for placing a mine at the specific row and column (i.e. cell)
        int mineColumnPos = 0;
        int mineRowPos = 0;

        // Generating a random number for mineColumnPos and mineRowPos within the bounds of maxRows and maxColumns
        while (mineCounter < numberOfMines) {
            mineRowPos = random.nextInt(maxRows);
            mineColumnPos = random.nextInt(maxColumns);

            // Checking if cell is already mined. If not, it is mined.
            if (minefield[mineRowPos][mineColumnPos].equals(notMine)) {
                minefield[mineRowPos][mineColumnPos] = mine;
                mineCounter++;
            }
        }

        // Returning minefield with states of nearby cells
        return checkMineField(minefield);
    }

    // Function for printing minefield
    public static void printMineField(String[][] minefield) {
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < maxColumns; columnIndex++) {
                System.out.printf(minefield[rowIndex][columnIndex]);
            }
            System.out.print('\n');
        }
    }

    // Function for checking nearby cells if they have mines
    public static String[][] checkMineField(String[][] minefield) {

        // Nested for loop for checking through each cell
        for (int rowPos = 0; rowPos < maxRows; rowPos++) {
            for (int columnPos = 0; columnPos < maxColumns; columnPos++) {

                // if statement for checking all around the cell if it is mined.
                if (minefield[rowPos][columnPos].equals(mine)) {
                    for (int row = - 1; row < 2; row ++) {
                        for (int column = -1; column < 2; column++) {

                            // if cell is out of bounds, ignore and continue with loop
                            try {
                                minefield[rowPos + row][columnPos + column] = changeCellState(minefield[rowPos + row][columnPos + column]);
                            } catch (ArrayIndexOutOfBoundsException ignored) { }
                        }
                    }
                }
            }
        }
        return minefield;
    }

    // Function for changing state of cells
    public static String changeCellState(String cell) {
        switch (cell) {
            case ".":
                return "1";
            case "1":
                return "2";
            case "2":
                return "3";
            case "3":
                return "4";
            case "4":
                return "5";
            case "5":
                return "6";
            case "6":
                return "7";
            case "7":
                return "8";
            default:
                return cell;
        }
    }
}
