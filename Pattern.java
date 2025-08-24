import java.awt.*;
import java.util.List;
import java.util.*;

public class Pattern {
    public List<Point> path;
    public List<Point> fixedPoints;
    public List<List<Point>> finalPath;

    public boolean animationStart = false;
    public int frame = 0;

    public boolean dragging = false;
    public int[] draggablePoint = null; // [fixedIndex, pathIndex]

    public Pattern() {
        this.path = new ArrayList<>();
        this.fixedPoints = new ArrayList<>();
        this.finalPath = new ArrayList<>();
        this.draggablePoint = null;
    }

    public void appendPoint(float x, float y) {
        Point p = new Point(x, y);
        path.add(p);
        fixedPoints.add(p.clone());
    }

    public void drawPath(Graphics2D g, int width, int height) {
        g.setStroke(new BasicStroke(2.2f));

        // Draw animated path
        if (animationStart && !path.isEmpty()) {
            Point prev = path.get(0);
            for (int i = 1; i < path.size(); i++) {
                Point curr = path.get(i);
                g.setColor(Color.RED);
                g.drawLine((int) prev.x, (int) prev.y, (int) curr.x, (int) curr.y);
                prev = curr;
            }
        }

        // Draw control points
        for (Point p : fixedPoints) {
            g.setColor(Color.BLUE);
            g.fillOval((int) (p.x - p.radius), (int) (p.y - p.radius),
                       (int) (2 * p.radius), (int) (2 * p.radius));
        }
    }

    public void checkPointCollision(float areaX, float areaY) {
        for (int i = 0; i < fixedPoints.size(); i++) {
            Point p = fixedPoints.get(i);
            if (p.checkCollision(areaX, areaY)) {
                dragging = true;
                // Find corresponding point in path
                for (int j = 0; j < path.size(); j++) {
                    Point pathPoint = path.get(j);
                    if (Math.abs(p.x - pathPoint.x) < 0.01 && Math.abs(p.y - pathPoint.y) < 0.01) {
                        draggablePoint = new int[]{i, j};
                        return;
                    }
                }
                return;
            }
        }
    }

    public void chaikin() {
        if (path.size() < 2) return;

        List<Point> original = new ArrayList<>();
        for (Point p : path) original.add(p.clone());

        finalPath.clear();
        finalPath.add(new ArrayList<>(original)); // initial state

        for (int iteration = 0; iteration < 7; iteration++) {
            List<Point> newPath = new ArrayList<>();

            for (int i = 0; i < original.size() - 1; i++) {
                Point p0 = original.get(i);
                Point p1 = original.get(i + 1);

                Point p25 = p0.make25Point(p1);
                Point p75 = p0.make75Point(p1);

                if (i == 0) {
                    newPath.add(p0.clone());
                    newPath.add(p75);
                } else {
                    newPath.add(p25);
                    if (i == original.size() - 2) {
                        newPath.add(p1.clone());
                    } else {
                        newPath.add(p75);
                    }
                }
            }

            original = newPath;
            finalPath.add(new ArrayList<>(original));
        }

        animationStart = true;
        frame = 0;
    }
}