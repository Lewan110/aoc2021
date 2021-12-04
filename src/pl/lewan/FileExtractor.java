package pl.lewan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
}
