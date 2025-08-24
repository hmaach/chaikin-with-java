package src.algorithm;

import java.util.ArrayList;
import java.util.List;
import src.model.Line;
import src.model.Point;

public class Chaikin {

    public static List<List<Line>> refine(List<Point> points) {
        List<List<Line>> lines2D = new ArrayList<>(new ArrayList<>());

        for (int i = 0; i < 1; i++) {
            List<Line> lines = new ArrayList<>();
            for (int j = 0; j < points.size() - 1; j++) {
                Line line = new Line(points.get(j), points.get(j + 1));
                lines.add(line);
            }
            lines2D.add(lines);
        }

        return lines2D;
    }
}
