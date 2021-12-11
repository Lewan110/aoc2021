package pl.lewan.day11;

import pl.lewan.*;

import java.io.*;

public class Solution {

    private static final int ROW_SIZE = 10;
    private static final int COLUMN_SIZE = 10;
    private static int totalFlashes = 0;

    public static void main(String[] args) throws FileNotFoundException {
        String fileLocation = "src/pl/lewan/day11/test.txt";
        int[][] map = FileExtractor.readMap(fileLocation, ROW_SIZE, COLUMN_SIZE);
        printMatrix(map);
        int steps = 100;
        for (int step = 1; step <= steps; step++) {
            increaseBy1(map);
            boolean[][] alreadyExplodedMap = new boolean[ROW_SIZE][COLUMN_SIZE];
            while (is10inMap(map)) {
                for (int row = 0; row < ROW_SIZE; row++) {
                    for (int column = 0; column < COLUMN_SIZE; column++) {
                        if (map[row][column] > 9 && !alreadyExplodedMap[row][column]) {
                            explode10(map, row, column, alreadyExplodedMap);
                            totalFlashes++;
                            alreadyExplodedMap[row][column] = true;
                        }
                    }
                }
                turnTo0(map, alreadyExplodedMap);
            }
            System.out.println();
            System.out.println("after step: " + step);
            printMatrix(map);
        }
        System.out.println("Total flashes: " + totalFlashes);
    }

    private static void turnTo0(int[][] matrix, boolean[][] alreadyExploded) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (alreadyExploded[row][col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void explode10(int[][] map, int row, int col, boolean[][] alreadyExplodedMap) {
        // (ROW_INDEX, COLUMN_INDEX)
        // (0,0) (0,1) (0,2)
        // (1,0) (1,1) (1,2)
        // (2,0) (2,1) (2,2)
        int[] rows = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] cols = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < rows.length; i++) {
            int tempRow = row + rows[i];
            int tempCol = col + cols[i];
            if (isValidPoint(tempRow, tempCol)) {
                if (!alreadyExplodedMap[tempRow][tempCol]) {
                    map[tempRow][tempCol] += 1;
                }

            }
        }
    }

    private static boolean isValidPoint(int row, int column) {
        return row > -1 && column > -1 && row < ROW_SIZE && column < COLUMN_SIZE;
    }

    private static void turnTo0IfAbove9(int[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] > 9) {
                    map[row][col] = 0;
                }
            }
        }

    }

    private static void increaseBy1(int[][] map) {
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                ints[j] += 1;
            }
        }
    }

    private static boolean is10inMap(int[][] map) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COLUMN_SIZE; col++) {
                if (map[row][col] == 10) {
                    return true;
                }
            }
        }
        return false;
    }

}

