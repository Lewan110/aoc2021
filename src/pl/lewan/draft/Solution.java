package pl.lewan.draft;

import pl.lewan.FileExtractor;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String fileLocation = "src/pl/lewan/dayX/test.txt";
        List<String> lines = FileExtractor.extractFile(fileLocation);
    }

}

