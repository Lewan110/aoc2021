package pl.lewan.day10;

import pl.lewan.*;

import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) {
        String fileLocation = "src/pl/lewan/day10/input.txt";
        List<String> lines = FileExtractor.extractFile(fileLocation);

        System.out.println("PART 1: " + calculateSyntaxErrorScore(lines));
        System.out.println("PART 2: " + calculateCompletionScore(lines));
    }

    private static long calculateCompletionScore(List<String> lines) {
        ArrayList<Long> localScores = new ArrayList<>();
        for (String line : lines) {
            Stack<Character> characters = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                var openingChar = Bracket.fromOpeningCharacter(line.charAt(i));
                openingChar.ifPresent(v -> characters.push(v.getOpening()));

                var closingChar = Bracket.fromClosingCharacter(line.charAt(i));
                if (closingChar.isPresent()) {
                    if (characters.peek() == closingChar.get().getOpening()) {
                        characters.pop();
                    } else {
                        characters.clear();
                        break;
                    }
                }
            }

            var missingCharacters = characters.stream()
                    .map(Bracket::fromOpeningCharacter)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            Collections.reverse(missingCharacters);
            long localScore = 0;
            for (Bracket missingCharacter : missingCharacters) {
                localScore = (localScore * 5) + missingCharacter.getCompletionScore();
            }
            localScores.add(localScore);
        }
        long[] sorted = localScores.stream()
                .filter(score -> score != 0)
                .mapToLong(Long::longValue)
                .sorted()
                .toArray();
        return sorted[sorted.length / 2];
    }

    private static int calculateSyntaxErrorScore(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                var openingCharacter = Bracket.fromOpeningCharacter(line.charAt(i));
                openingCharacter.ifPresent(value -> stack.push(value.getOpening()));

                var closingCharacter = Bracket.fromClosingCharacter(line.charAt(i));
                if (closingCharacter.isPresent()) {
                    if (stack.peek() == closingCharacter.get().getOpening()) {
                        stack.pop();
                    } else {
                        sum += closingCharacter.get().getErrorScore();
                        break;
                    }
                }
            }
        }
        return sum;
    }

    private static int calculateScore(String line) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{' || line.charAt(i) == '<') {
                stack.push(line.charAt(i));
            }
            if (line.charAt(i) == ')') {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    return 3;
                }
            }
            if (line.charAt(i) == ']') {
                if (stack.peek() == '[') {
                    stack.pop();
                } else {
                    return 57;
                }
            }
            if (line.charAt(i) == '}') {
                if (stack.peek() == '{') {
                    stack.pop();
                } else {
                    return 1197;
                }
            }
            if (line.charAt(i) == '>') {
                if (stack.peek() == '<') {
                    stack.pop();
                } else {
                    return 25137;
                }
            }
        }
        return 0;
    }

}

enum Bracket {
    ROUND('(', ')', 3, 1),
    SQUARE('[', ']', 57, 2),
    CURLY('{', '}', 1197, 3),
    ANGLE('<', '>', 25137, 4);


    final Character opening;
    final Character closing;
    final int errorScore;
    final int completionScore;

    Bracket(Character opening, Character closing, int errorScore, int completionScore) {
        this.opening = opening;
        this.closing = closing;
        this.errorScore = errorScore;
        this.completionScore = completionScore;
    }

    static Optional<Bracket> fromOpeningCharacter(Character input) {
        return Arrays.stream(Bracket.values())
                .filter(v -> v.opening == input)
                .findFirst();
    }

    public int getCompletionScore() {
        return completionScore;
    }

    static Optional<Bracket> fromClosingCharacter(Character input) {
        return Arrays.stream(Bracket.values())
                .filter(v -> v.closing == input)
                .findFirst();
    }

    public Character getOpening() {
        return opening;
    }

    public Character getClosing() {
        return closing;
    }

    public int getErrorScore() {
        return errorScore;
    }
}

