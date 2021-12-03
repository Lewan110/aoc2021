package pl.lewan.day3;

import pl.lewan.FileExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        List<String> input = FileExtractor.extractFile("src/pl/lewan/day3/input.txt");
        int numberLength = input.get(0).length();

        int[] zerosCount = new int[numberLength];
        int[] onesCount = new int[numberLength];

        for (String s : input) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    zerosCount[j] += 1;
                } else {
                    onesCount[j] += 1;
                }
            }
        }

        int[] gamma = new int[numberLength];
        int[] epsilon = new int[numberLength];
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


        ArrayList<String> calculateList = new ArrayList<>(input);
        for (int i = 0; i < numberLength; i++) {
            if (calculateList.size() == 1) {
                continue;
            }
            int currentCommonNumber = mostCommonOnPosition(i, calculateList);
            ArrayList<String> toRemove = getNumbersToRemove(calculateList, i, currentCommonNumber);
            calculateList.removeAll(toRemove);
        }

        int oxygenGeneratorRating = toDecimal(calculateList.get(0));
        System.out.println("oxygen generator rating: " + oxygenGeneratorRating);

        calculateList = new ArrayList<>(input);
        for (int i = 0; i < numberLength; i++) {
            if (calculateList.size() == 1) {
                continue;
            }
            int currentCommonNumber = leastCommonOnPosition(i, calculateList);
            ArrayList<String> toRemove = getNumbersToRemove(calculateList, i, currentCommonNumber);
            calculateList.removeAll(toRemove);
        }
        int scrubberRating = toDecimal(calculateList.get(0));
        System.out.println("CO2 scrubber rating: " + scrubberRating);

        System.out.println("life support rating: " + scrubberRating * oxygenGeneratorRating);
    }

    private static ArrayList<String> getNumbersToRemove(
            ArrayList<String> calculateList, int i, int currentCommonNumber) {
        ArrayList<String> toRemove = new ArrayList<>();
        for (String value : calculateList) {
            int charAt = (int) value.charAt(i) - '0';
            if (charAt != currentCommonNumber) {
                toRemove.add(value);
            }
        }
        return toRemove;
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

    private static int leastCommonOnPosition(int position, List<String> input) {
        return mostCommonOnPosition(position, input) ^ 1;
    }

    private static int toDecimal(String value) {
        return Integer.parseInt(value, 2);
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
