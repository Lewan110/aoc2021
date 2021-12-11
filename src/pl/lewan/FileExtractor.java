package pl.lewan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileExtractor {

    public static List<String> extractFile(String location) {
        List<String> inputList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(location))) {
            inputList = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public static String getFirstLineFromFile(String location) {
        String firstLine = "";
        BufferedReader brTest;
        try {
            brTest = new BufferedReader(new FileReader(location));
            firstLine = brTest.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }

    public static int[][] readMap(String location, int rowSize, int columnSize) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(location)));
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
