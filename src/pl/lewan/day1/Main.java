package pl.lewan.day1;

import pl.lewan.FileExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = FileExtractor.extractFile("src/pl/lewan/day1/input.txt")
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int largerMeasurementCount = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) < list.get(i)) {
                largerMeasurementCount++;
            }
        }
        System.out.println(largerMeasurementCount);


        int largerSumCount = 0;
        int previousSum = Integer.MIN_VALUE;
        for (int i = 2; i < list.size() - 2; i++) {
            int currentSum = list.get(i - 2) + list.get(i - 1) + list.get(i);
            if (currentSum > previousSum) {
                largerSumCount++;
            }
            previousSum = currentSum;
        }
        System.out.println(largerSumCount);
    }
}
