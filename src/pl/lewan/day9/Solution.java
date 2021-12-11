package pl.lewan.day9;


import java.io.*;
import java.util.*;

public class Solution {
    private static final int ROW_SIZE = 5;
    private static final int COLUMN_SIZE = 10;

    public static void main(String[] args) throws FileNotFoundException {
        String fileLocation = "src/pl/lewan/day9/test.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileLocation)));
        int[][] map = readMap(sc, ROW_SIZE, COLUMN_SIZE);
        System.out.println(Arrays.deepToString(map));

        System.out.println("PART 1: " + part1(map));
        
    }
    
    private static int part2(int[][] map){
        return 0;
    }

    private static int part1(int[][] map) {
        // (ROW_INDEX, COLUMN_INDEX)
        // (0,0) (0,1) (0,2)
        // (1,0) (1,1) (1,2)
        // (2,0) (2,1) (2,2)
        ArrayList<Integer> lowestPoints = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                int tempRow = i - 1;
                int tempCol = j;
                if (isValidPoint(tempRow, tempCol)) {
                    if (map[tempRow][tempCol] <= map[i][j]) {
                        continue;
                    }
                }
                tempRow = i;
                tempCol = j - 1;
                if (isValidPoint(tempRow, tempCol)) {
                    if (map[tempRow][tempCol] <= map[i][j]) {
                        continue;
                    }
                }
                tempRow = i;
                tempCol = j + 1;
                if (isValidPoint(tempRow, tempCol)) {
                    if (map[tempRow][tempCol] <= map[i][j]) {
                        continue;
                    }
                }
                tempRow = i + 1;
                tempCol = j;
                if (isValidPoint(tempRow, tempCol)) {
                    if (map[tempRow][tempCol] <= map[i][j]) {
                        continue;
                    }
                }
                lowestPoints.add(map[i][j]);

            }
        }
        return lowestPoints.stream().mapToInt(Integer::intValue).sum() + lowestPoints.size();

    }

    private static boolean isValidPoint(int row, int column) {
        return row > -1 && column > -1 && row < ROW_SIZE && column < COLUMN_SIZE;
    }


    private static int[][] readMap(Scanner sc, int rowSize, int columnSize) {
        int[][] matrix = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            String nextLine = sc.nextLine();
            String[] readLine = nextLine.trim().split("");
            List<Integer> lineNumbers = Arrays.stream(readLine)
                    .map(Integer::valueOf)
                    .toList();
            for (int j = 0; j < columnSize; j++) {
                matrix[i][j] = lineNumbers.get(j);
            }
        }
        return matrix;
    }

}

