package src.app;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import src.algorithm.Chaikin;
import src.model.Line;
import src.model.Point;
import src.ui.Window;

public class ChaikinApp {

    private Window window;
    private final List<Point> fixedPoints;
    private List<List<Line>> lines;
    private boolean isAnimating = false;
    private int currentStep = 0;
    private Timer animationTimer;

    private static final int ANIMATION_DELAY = 800;
    private static final int MAX_STEPS = 7;

    public ChaikinApp() {
        this.fixedPoints = new ArrayList<>();
        this.lines = new ArrayList<>();
        setupAnimationTimer();
    }

    public void run() {
        this.window = new Window(this);
    }

    private void setupAnimationTimer() {
        this.animationTimer = new Timer(ANIMATION_DELAY, e -> {
            nextStep();
            repaint();
        });
        this.animationTimer.setRepeats(true);
    }

    public void startChaikin() {
        if (this.fixedPoints.size() > 1 && !isAnimating) {
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

    public List<Line> getCurrentStepLines() {
        if (hasLines() && currentStep < lines.size()) {
            return lines.get(currentStep);
        }
        return new ArrayList<>();
    }

    public boolean hasTwoPoints() {
        return fixedPoints.size() == 2;
    }

    public void addLines(List<Line> lines) {
        this.lines.add(lines);
    }

    public void startAnimation() {
        if (!lines.isEmpty()) {
            this.isAnimating = true;
            this.currentStep = 0;
            this.animationTimer.start();
            repaint();
        }
    }

    public void stopAnimation() {
        this.isAnimating = false;
        this.currentStep = 0;
        this.animationTimer.stop();
        repaint();
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void nextStep() {
        if (isAnimating && !lines.isEmpty()) {
            currentStep++;

            if (currentStep >= MAX_STEPS) {
                currentStep = 0;
            }
        }
    }

    public void repaint() {
        if (window != null) {
            window.repaint();
        }
    }

    public void clear() {
        stopAnimation();
        this.fixedPoints.clear();
        this.lines.clear();
        repaint();
    }

    public void exit() {
        stopAnimation();
        if (window != null) {
            window.exit();
        }
        System.exit(0);
    }
}
