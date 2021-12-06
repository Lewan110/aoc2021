package pl.lewan.day6;

import pl.lewan.*;

import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) {
        String fileLocation = "src/pl/lewan/day6/test.txt";
        List<String> lines = FileExtractor.extractFile(fileLocation);
        List<Integer> input = Arrays.stream(lines.get(0).split(","))
                .map(Integer::valueOf)
                .toList();

        System.out.println("PART 1: " + calculate(input, 80));
        System.out.println("PART 2: " + calculate(input, 256));
    }

    private static Long calculate(List<Integer> fishList, int days) {
        long[] ages = new long[9];
        for (int fish : fishList) {
            ages[fish] += 1;
        }
        for (int i = 0; i < days; i++) {
            ages[(i + 7) % 9] += ages[i % 9];
        }
        return LongStream.of(ages).sum();
    }


}



