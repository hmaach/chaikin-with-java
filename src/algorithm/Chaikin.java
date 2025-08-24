package src.algorithm;

import java.util.ArrayList;
import java.util.List;
import src.model.Line;
import src.model.Point;

public class Chaikin {

    public static List<List<Line>> refine(List<Point> points) {
        List<List<Line>> lines2D = new ArrayList<>(new ArrayList<>());

        for (int i = 0; i < 7; i++) {
            List<Line> lines = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < points.size() - 1; j++) {
                    Line line = new Line(points.get(j), points.get(j + 1));
                    lines.add(line);
                }
            } else {
                List<Line> lastPath = lines2D.get(lines2D.size() - 1);
                for (int j = 0; j < lastPath.size() - 1; j++) {

                    Line current = lastPath.get(j);
                    Point currentP1 = current.getFirstPoint();
                    Point currentP2 = current.getSecondPoint();

                    Line next = lastPath.get(j + 1);
                    Point nextP1 = next.getFirstPoint();
                    Point nextP2 = next.getSecondPoint();

                    int deltaCurrentX = currentP2.getX() - currentP1.getX();
                    int deltaCurrentY = currentP2.getY() - currentP1.getY();

                    int deltaNextX = nextP2.getX() - nextP1.getX();
                    int deltaNextY = nextP2.getY() - nextP1.getY();

                    lines.add(current);
                    Line newLine = scale_points(currentP2, nextP1, deltaCurrentX, deltaCurrentY, deltaNextX, deltaNextY);
                    lines.add(newLine);
                }
            }
            lines2D.add(lines);
        }
        print2DList(lines2D);
        return lines2D;
    }

    private static Line scale_points(Point currentP2, Point nextP1, int deltaCurrentX, int deltaCurrentY, int deltaNextX, int deltaNextY) {

        currentP2.setX(((int) ((double) currentP2.getX() - (double) deltaCurrentX * 0.25)));
        currentP2.setY(((int) ((double) currentP2.getY() - (double) deltaCurrentY * 0.25)));

        nextP1.setX(((int) ((double) nextP1.getX() + (double) deltaNextX * 0.25)));
        nextP1.setY(((int) ((double) nextP1.getY() + (double) deltaNextY * 0.25)));
        return new Line(new Point(currentP2.getX(), currentP2.getY()), new Point(nextP1.getX(), nextP1.getY()));
    }

    private static void print2DList(List<List<Line>> list2D) {
        System.err.println("------------start-------------");
        for (List<Line> list : list2D) {
            for (Line line : list) {
                if (line != null) {
                    System.err.println(line.toString());
                }
            }
        }
        System.err.println("------------end-------------");
    }
}
