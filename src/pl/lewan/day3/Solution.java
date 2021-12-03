package pl.lewan.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        var fileName = "src/pl/lewan/day3/test.txt";
        List<String> inputList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            inputList = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] zerosCount = new int[12];
        int[] onesCount = new int[12];

        for (String s : inputList) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    zerosCount[j] += 1;
                } else {
                    onesCount[j] += 1;
                }
            }
        }
        int[] gamma = new int[12];
        int[] epsilon = new int[12];
        for (int i = 0; i < zerosCount.length; i++) {
            if (onesCount[i] > zerosCount[i]) {
                gamma[i] = 1;
                epsilon[i] = 0;
            } else {
                gamma[i] = 0;
                epsilon[i] = 1;
            }
        }

        System.out.println("gamma: " + Arrays.toString(gamma));
        System.out.println("gamma: " + toDecimal(gamma));
        System.out.println("epsilon: " + Arrays.toString(epsilon));
        System.out.println("epsilon: " + toDecimal(epsilon));
        System.out.println("power: " + toDecimal(epsilon) * toDecimal(gamma));


        int length = inputList.get(0).length();
        ArrayList<String> calcs = new ArrayList<>(inputList);
        for (int i = 0; i < length; i++) {
            int currentCommonNumber = mostCommonOnPosition(i, calcs);
            for (int j = 0; j < calcs.size(); j++) {
                int charAt = (int) calcs.get(j).charAt(i) - '0';
                if (charAt != currentCommonNumber) {
                    calcs.remove(j);
                }
            }
        }
        int test = 2;
    }

    private static int mostCommonOnPosition(int position, List<String> input) {
        int amountOfZero = 0;
        int amountOfOne = 0;

        for (String s : input) {
            if (s.charAt(position) == '0') {
                amountOfZero++;
            } else {
                amountOfOne++;
            }
        }
        return amountOfOne >= amountOfZero ? 1 : 0;

    }

    private static int toDecimal(int[] binaryNumber) {
        int result = 0;
        int placesToSkip = 0;
        for (int i = 0; i < binaryNumber.length; i++) {
            if (binaryNumber[i] == 1) {
                result += Math.pow(2, binaryNumber.length - i - placesToSkip);
            } else {
                if (result == 0) {
                    placesToSkip++;
                }
            }
        }
        return result;
    }
}