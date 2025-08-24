package src.algorithm;

import java.util.ArrayList;
import java.util.List;
import src.app.ChaikinApp;
import src.model.Line;
import src.model.Point;

public class Chaikin {

    public static List<List<Line>> refine(ChaikinApp app) {
        int maxIterations = ChaikinApp.MAX_STEPS;
        List<Point> points = app.getFixedPoints();
        List<List<Line>> lines2D = new ArrayList<>();

        if (points.size() < 2) {
            return lines2D;
        }

        List<Point> originalPath = new ArrayList<>();
        for (Point p : points) {
            originalPath.add(new Point(p.getX(), p.getY()));
        }

        List<Line> initialLines = new ArrayList<>();
        for (int i = 0; i < originalPath.size() - 1; i++) {
            initialLines.add(new Line(originalPath.get(i), originalPath.get(i + 1)));
        }
        lines2D.add(initialLines);

        List<Point> currentPath = new ArrayList<>(originalPath);

        for (int iter = 1; iter < maxIterations; iter++) {
            List<Point> newPath = new ArrayList<>();

            for (int i = 0; i < currentPath.size() - 1; i++) {
                Point p0 = currentPath.get(i);
                Point p1 = currentPath.get(i + 1);

                Point p25 = make25Point(p0, p1);
                Point p75 = make75Point(p0, p1);

                if (i == 0) {
                    newPath.add(new Point(p0.getX(), p0.getY()));
                    newPath.add(p75);
                } else {
                    newPath.add(p25);
                    if (i == currentPath.size() - 2) {
                        newPath.add(new Point(p1.getX(), p1.getY()));
                    } else {
                        newPath.add(p75);
                    }
                }
            }

            currentPath = newPath;

            // Convert points back to lines for storage
            List<Line> currentLines = new ArrayList<>();
            for (int i = 0; i < currentPath.size() - 1; i++) {
                currentLines.add(new Line(currentPath.get(i), currentPath.get(i + 1)));
            }
            lines2D.add(currentLines);
        }

        return lines2D;
    }

    private static Point make25Point(Point p0, Point p1) {
        int deltaX = p1.getX() - p0.getX();
        int deltaY = p1.getY() - p0.getY();

        int newX = (int) ((double) p0.getX() + (double) deltaX * 0.25);
        int newY = (int) ((double) p0.getY() + (double) deltaY * 0.25);

        return new Point(newX, newY);
    }

    private static Point make75Point(Point p0, Point p1) {
        int deltaX = p1.getX() - p0.getX();
        int deltaY = p1.getY() - p0.getY();

        int newX = (int) ((double) p0.getX() + (double) deltaX * 0.75);
        int newY = (int) ((double) p0.getY() + (double) deltaY * 0.75);

        return new Point(newX, newY);
    }

}
