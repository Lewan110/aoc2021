package pl.lewan.day2;

import java.util.List;

import static pl.lewan.day2.Direction.*;

public class Solution {

    public static void main(String[] args) {
        var fileName = "src/pl/lewan/day2/input.txt";
        List<String> inputList = new java.util.ArrayList<>();

        try (java.io.BufferedReader br = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(fileName))) {
            inputList = br.lines()
                    .collect(java.util.stream.Collectors.toList());

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        List<Command> commands = inputList.stream()
                .map(line -> new Command(Direction.fromValue(line.split(" ")[0]), Integer.valueOf(line.split(" ")[1])))
                .collect(java.util.stream.Collectors.toList());
        int depth = 0;
        int horizontalPosition = 0;
        for (Command currentCommand : commands) {
            if (currentCommand.getDirection() == FORWARD) {
                horizontalPosition += currentCommand.getUnits();
            }
            if (currentCommand.getDirection() == DOWN) {
                depth -= currentCommand.getUnits();
            }
            if (currentCommand.getDirection() == UP) {
                depth += currentCommand.getUnits();
            }
        }
        System.out.println("solution: " + depth * horizontalPosition);

        int depth2 = 0;
        int horizontalPosition2 = 0;
        int aim = 0;
        for (Command currentCommand : commands) {
            if (currentCommand.getDirection() == FORWARD) {
                horizontalPosition2 += currentCommand.getUnits();
                depth2 += aim * currentCommand.getUnits();
            }
            if (currentCommand.getDirection() == DOWN) {
                aim += currentCommand.getUnits();
            }
            if (currentCommand.getDirection() == UP) {
                aim -= currentCommand.getUnits();
            }
        }
        System.out.println("solution2: " + depth2 * horizontalPosition2);

    }
}

enum Direction {

    FORWARD("forward"),
    UP("up"),
    DOWN("down");

    final String value;

    Direction(String value) {
        this.value = value;
    }

    static Direction fromValue(String input) {
        return java.util.Arrays.stream(values()).filter(value -> value.value.equals(input)).findFirst().orElse(null);
    }

}

class Command {
    private final Direction direction;
    private final Integer units;

    public Direction getDirection() {
        return direction;
    }

    public Integer getUnits() {
        return units;
    }

    public Command(Direction direction, Integer units) {
        this.direction = direction;
        this.units = units;
    }
}