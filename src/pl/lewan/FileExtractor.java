package pl.lewan;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileExtractor {

    public static List<String> extractFile(String location){
        List<String> inputList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(location))) {
            inputList = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
