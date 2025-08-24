package src.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import src.app.ChaikinApp;
import src.model.Line;
import src.model.Point;

public class Canvas extends JPanel {

    private final ChaikinApp app;

    private static final Color BACKGROUND_COLOR = new Color(24, 24, 36);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color POINT_COLOR = new Color(255, 107, 107);
    private static final Color LINE_COLOR = new Color(78, 205, 196);

    public Canvas(ChaikinApp app) {
        this.app = app;
        setupCanvas();
    }

    private void setupCanvas() {
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (!app.getFixedPoints().isEmpty()) {
            drawPoints(graphics);
        }

        if (this.app.isAnimating()) {
            if (this.app.hasLines()) {
                this.drawLines(graphics);
            } else if (this.app.hasTwoPoints()) {

                Point p1 = this.app.getFixedPoints().get(0);
                Point p2 = this.app.getFixedPoints().get(1);

                this.drawLine(graphics, p1, p2);
            }
        }
    }

    private void drawPoints(Graphics graphics) {
        graphics.setColor(POINT_COLOR);
        for (Point point : this.app.getFixedPoints()) {
            int x = point.getX() - 3;
            int y = point.getY() - 5;

            graphics.drawOval(x, y, 7, 7);
        }
    }

    private void drawLines(Graphics graphics) {
        List<Line> currentLines = this.app.getLines().get(0);

        for (Line line : currentLines) {
            this.drawLine(graphics, line.getFirstPoint(), line.getSecondPoint());
        }
    }

    private void drawLine(Graphics graphics, Point p1, Point p2) {
        graphics.setColor(LINE_COLOR);

        graphics.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}
