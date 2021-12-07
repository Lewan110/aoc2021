package pl.lewan.day7;

import pl.lewan.*;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String fileLocation = "src/pl/lewan/day7/input.txt";
        List<String> lines = FileExtractor.extractFile(fileLocation);
        List<Integer> input = Arrays.stream(lines.get(0).split(","))
                .map(Integer::valueOf)
                .toList();

        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));


    }

    private static int part1(List<Integer> positions) {
        int min = positions.stream().min(Integer::compareTo).get();
        int max = positions.stream().max(Integer::compareTo).get();
        int minFuel = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int currentMinFuel = 0;
            for (Integer position : positions) {
                currentMinFuel += Math.abs(position - i);
                if (currentMinFuel >= minFuel) {
                    break;
                }
            }
            if (currentMinFuel < minFuel) {
                minFuel = currentMinFuel;
            }
        }

        return minFuel;
    }

    private static int part2(List<Integer> positions) {
        int min = positions.stream().min(Integer::compareTo).get();
        int max = positions.stream().max(Integer::compareTo).get();
        int minFuel = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int currentMinFuel = 0;
            for (Integer position : positions) {
                int distance = Math.abs(position - i);
                currentMinFuel += ((distance * (distance + 1)) / 2);
                if (currentMinFuel >= minFuel) {
                    break;
                }
            }
            if (currentMinFuel < minFuel) {
                minFuel = currentMinFuel;
            }
        }
        return minFuel;
    }

}

