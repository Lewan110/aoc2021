package pl.lewan.day2;

import pl.lewan.FileExtractor;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static pl.lewan.day2.Direction.*;

public class Solution {

    public static void main(String[] args) {
        List<String> inputList = FileExtractor.extractFile("src/pl/lewan/day2/input.txt");
        List<Command> commands = inputList.stream()
                .map(line -> new Command(Direction.fromValue(line.split(" ")[0]),
                        Integer.valueOf(line.split(" ")[1])))
                .collect(toList());
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
        return stream(values()).filter(value -> value.value.equals(input)).findFirst().orElse(null);
    }

}

class Command {
    private final Direction direction;
    private final Integer units;

    Direction getDirection() {
        return direction;
    }

    Integer getUnits() {
        return units;
    }

    Command(Direction direction, Integer units) {
        this.direction = direction;
        this.units = units;
    }
}
