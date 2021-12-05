package pl.lewan.day5;

import pl.lewan.FileExtractor;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String fileLocation = "src/pl/lewan/day5/input.txt";

        List<String> lines = FileExtractor.extractFile(fileLocation);
        int[][] ventsMap = new int[1000][1000];
        lines.forEach(line -> {
            String[] lineSegment = line.split(" -> ");
            int x1 = Integer.parseInt(lineSegment[0].split(",")[0]);
            int y1 = Integer.parseInt(lineSegment[0].split(",")[1]);
            int x2 = Integer.parseInt(lineSegment[1].split(",")[0]);
            int y2 = Integer.parseInt(lineSegment[1].split(",")[1]);

            if (x1 == x2) {
                for (int i = Math.min(y1, y2); i <= Math.min(y1, y2) + Math.abs(y1 - y2); i++) {
                    ventsMap[i][x1] += 1;
                }
            } else {
                int limit = Math.min(x1, x2) + Math.abs(x1 - x2);
                if (y1 == y2) {
                    for (int i = Math.min(x1, x2); i <= limit; i++) {
                        ventsMap[y1][i] += 1;
                    }
                } else {
                    if (x1 > x2 && y1 < y2) {
                        int tempY = y2;
                        for (int i = x2; i <= limit; i++) {
                            ventsMap[tempY][i] += 1;
                            tempY--;
                        }
                    } else if (x1 < x2 && y1 > y2) {
                        int tempY = y1;
                        for (int i = x1; i <= limit; i++) {
                            ventsMap[tempY][i] += 1;
                            tempY--;
                        }
                    } else {
                        int tempY = x1 < x2 ? y1 : y2;
                        for (int i = Math.min(x1, x2); i <= limit; i++) {
                            ventsMap[tempY][i] += 1;
                            tempY++;
                        }
                    }
                }
            }
        });
        System.out.println(getOverlappingVents(ventsMap));

    }

    private static void printMap(int[][] ventsMap) {
        for (int[] ints : ventsMap) {
            for (int v = 0; v < ventsMap.length; v++) {
                System.out.print(ints[v] + ", ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    private static int getOverlappingVents(int[][] map) {
        int counter = 0;
        for (int[] ints : map) {
            for (int j = 0; j < map.length; j++) {
                if (ints[j] > 1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

