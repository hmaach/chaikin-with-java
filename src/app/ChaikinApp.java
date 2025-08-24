package src.app;

import java.util.ArrayList;
import java.util.List;
import src.algorithm.Chaikin;
import src.model.Line;
import src.model.Point;
import src.ui.Window;

public class ChaikinApp {

    private Window window;
    private final List<Point> fixedPoints;
    private List<List<Line>> lines;
    private boolean isAnimating = false;

    public ChaikinApp() {
        this.fixedPoints = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public void run() {
        this.window = new Window(this);
    }

    public void startChaikin() {
        if (this.fixedPoints.size() > 1) {
            this.lines = Chaikin.refine(this.fixedPoints);

            this.startAnimation();
        }
    }

    public List<Point> getFixedPoints() {
        return fixedPoints;
    }

    public void addPoint(Point point) {
        if (!isAnimating()) {
            this.fixedPoints.add(point);
            repaint();
        }
    }

    public boolean hasLines() {
        return !lines.isEmpty() && !lines.get(0).isEmpty();
    }

    public List<List<Line>> getLines() {
        return lines;
    }

    public boolean hasTwoPoints() {
        return fixedPoints.size() == 2;
    }

    public void addLines(List<Line> lines) {
        this.lines.add(lines);
    }

    public void repaint() {
        if (window != null) {
            window.repaint();
        }
    }

    public void startAnimation() {
        this.isAnimating = true;
        repaint();
    }

    public void stopAnimation() {
        this.isAnimating = false;
        repaint();
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void clear() {
        this.fixedPoints.clear();
        this.lines.clear();
        this.stopAnimation();
        this.repaint();
    }

    public void exit() {
        clear();
        if (window != null) {
            window.exit();
        }
        System.exit(0);
    }
}
