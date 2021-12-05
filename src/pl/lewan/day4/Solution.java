package pl.lewan.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static pl.lewan.FileExtractor.getFirstLineFromFile;

public class Solution {

    private static final int ROW_SIZE = 5;
    private static final int COLUMN_SIZE = 5;

    public static void main(String[] args) throws IOException {

        String fileLocation = "src/pl/lewan/day4/input.txt";
        String firstLine = getFirstLineFromFile(fileLocation);

        List<Integer> numbers = Arrays.stream(firstLine.split(","))
                .map(Integer::valueOf)
                .toList();

        List<BingoBoard> matrixList = getBingoBoards(fileLocation);
        var board = getWinningBoard(numbers, matrixList);
        if (board.isPresent()) {
            BingoBoard winningBoard = board.get();
            System.out.println("PART 1");
            System.out.println("last marked number: " + winningBoard.getLastMarked());
            System.out.println("unmarked sum: " + winningBoard.getUnmarkedSum());
            System.out.println("score: " + winningBoard.getUnmarkedSum() * winningBoard.getLastMarked());
        }

        matrixList = getBingoBoards(fileLocation);
        var lastWinningBoard = getLastWinningBoard(numbers, matrixList);
        if (lastWinningBoard.isPresent()) {
            BingoBoard winningBoard = lastWinningBoard.get();
            System.out.println("PART 2");
            System.out.println("last marked number: " + winningBoard.getLastMarked());
            System.out.println("unmarked sum: " + winningBoard.getUnmarkedSum());
            System.out.println("score: " + winningBoard.getUnmarkedSum() * winningBoard.getLastMarked());
        }
    }

    private static Optional<BingoBoard> getLastWinningBoard(List<Integer> numbers, List<BingoBoard> matrixList) {
        int winningBoardsCount = 0;
        for (Integer number : numbers) {
            for (BingoBoard bingoBoard : matrixList) {
                if (bingoBoard.isWinningBoard()) {
                    continue;
                }
                bingoBoard.markNumber(number);
                if (bingoBoard.isWinningBoard()) {
                    winningBoardsCount++;
                    if (winningBoardsCount == matrixList.size()) {
                        return Optional.of(bingoBoard);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<BingoBoard> getWinningBoard(List<Integer> numbers, List<BingoBoard> matrixList) {
        for (Integer number : numbers) {
            for (BingoBoard bingoBoard : matrixList) {
                bingoBoard.markNumber(number);
                if (bingoBoard.isWinningBoard()) {
                    return Optional.of(bingoBoard);
                }
            }
        }
        return Optional.empty();
    }

    private static List<BingoBoard> getBingoBoards(String fileLocation) throws FileNotFoundException {
        List<BingoBoard> matrixList = new ArrayList<>();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileLocation)));
        sc.nextLine();
        sc.nextLine();
        while (sc.hasNextLine()) {
            int[][] matrix = readSingleMatrix(sc);
            matrixList.add(new BingoBoard(matrix));
        }
        return matrixList;
    }

    private static int[][] readSingleMatrix(Scanner sc) {
        int[][] matrix = new int[ROW_SIZE][COLUMN_SIZE];
        for (int i = 0; i < ROW_SIZE; i++) {
            String nextLine = sc.nextLine();
            if ("".equals(nextLine)) {
                nextLine = sc.nextLine();
            }
            String[] readLine = nextLine.trim().split(" ");
            List<Integer> lineNumbers = Arrays.stream(readLine)
                    .filter(number -> !" ".equals(number))
                    .filter(number -> !"".equals(number))
                    .map(Integer::valueOf)
                    .toList();
            for (int j = 0; j < COLUMN_SIZE; j++) {
                matrix[i][j] = lineNumbers.get(j);
            }
        }
        return matrix;
    }
}

class BingoBoard {
    private final int[][] matrix;
    private final boolean[][] marked;
    private int lastMarked;

    BingoBoard(int[][] matrix) {
        this.matrix = matrix;
        this.marked = new boolean[matrix.length][matrix.length];
    }

    int getLastMarked() {
        return lastMarked;
    }

    void markNumber(int number) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == number) {
                    marked[i][j] = true;
                    this.lastMarked = number;
                }
            }
        }
    }

    boolean isWinningBoard() {
        for (int i = 0; i < matrix.length; i++) {
            int markedCounter = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (marked[i][j]) {
                    markedCounter++;
                }
            }
            if (markedCounter == matrix.length) {
                return true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            int markedCounter = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (marked[j][i]) {
                    markedCounter++;
                }
            }
            if (markedCounter == matrix.length) {
                return true;
            }
        }
        return false;
    }

    int getUnmarkedSum() {
        int unMarkedSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!marked[i][j]) {
                    unMarkedSum += matrix[i][j];
                }
            }
        }
        return unMarkedSum;
    }
}
