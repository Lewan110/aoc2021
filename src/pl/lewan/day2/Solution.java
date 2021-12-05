package pl.lewan.day2;

import pl.lewan.FileExtractor;

import java.util.List;

import static java.util.Arrays.stream;
import static pl.lewan.day2.Direction.*;

public class Solution {

    public static void main(String[] args) {
        List<String> inputList = FileExtractor.extractFile("src/pl/lewan/day2/input.txt");
        List<Command> commands = inputList.stream()
                .map(line -> new Command(Direction.fromValue(line.split(" ")[0]),
                        Integer.valueOf(line.split(" ")[1])))
                .toList();
        int depth = 0;
        int horizontalPosition = 0;
        for (Command currentCommand : commands) {
            switch (currentCommand.getDirection()) {
                case FORWARD -> horizontalPosition += currentCommand.getUnits();
                case DOWN -> depth -= currentCommand.getUnits();
                case UP -> depth += currentCommand.getUnits();
            }
        }
        System.out.println("PART 1: " + Math.abs(depth * horizontalPosition));

        int depth2 = 0;
        int horizontalPosition2 = 0;
        int aim = 0;
        for (Command currentCommand : commands) {
            switch (currentCommand.getDirection()) {
                case FORWARD -> {
                    horizontalPosition2 += currentCommand.getUnits();
                    depth2 += aim * currentCommand.getUnits();
                }
                case DOWN -> aim += currentCommand.getUnits();
                case UP -> aim -= currentCommand.getUnits();
            }
        }
        System.out.println("PART 2: " + depth2 * horizontalPosition2);

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
        return stream(values()).filter(value -> value.value.equals(input)).findFirst().orElse(null);
    }

}

record Command(Direction direction, Integer units) {
    Direction getDirection() {
        return direction;
    }

    Integer getUnits() {
        return units;
    }

}
