package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import src.app.ChaikinApp;
import src.model.Line;
import src.model.Point;

public class Canvas extends JPanel {

    private final ChaikinApp app;

    private static final Color BACKGROUND_COLOR = new Color(15, 32, 39);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color POINT_COLOR = new Color(255, 193, 7);
    private static final Color LINE_COLOR = new Color(32, 201, 151);

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
        if (this.app.isAnimating()) {
            if (this.app.hasLines()) {
                this.drawLines(graphics);
            } else if (this.app.hasTwoPoints()) {

                Point p1 = this.app.getFixedPoints().get(0);
                Point p2 = this.app.getFixedPoints().get(1);

                this.drawLine(graphics, p1, p2);
            }
        }

        if (!app.getFixedPoints().isEmpty()) {
            drawPoints(graphics);
        }
        drawInfo(graphics);
    }

    private void drawPoints(Graphics graphics) {
        graphics.setColor(POINT_COLOR);
        for (Point point : this.app.getFixedPoints()) {
            int x = point.getX() - 4;
            int y = point.getY() - 4;

            graphics.fillOval(x, y, 8, 8);
        }
    }

    private void drawLines(Graphics graphics) {
        List<Line> currentLines = app.getCurrentStepLines();
        for (Line line : currentLines) {
            drawLine(graphics, line.getFirstPoint(), line.getSecondPoint());
        }
    }

    private void drawLine(Graphics graphics, Point p1, Point p2) {
        graphics.setColor(LINE_COLOR);

        graphics.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private void drawInfo(Graphics graphics) {
        graphics.setColor(TEXT_COLOR);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));

        if (app.isAnimating()) {
            String stepText = String.format("Step: %d / 7", app.getCurrentStep() + 1);
            graphics.drawString(stepText, 10, 25);
        } else {
            if (app.getFixedPoints().isEmpty()) {
                graphics.drawString("Left click to add points", 10, 25);
                graphics.drawString("Press 'Enter' after adding points", 10, 45);
            } else {
                graphics.drawString("Press 'Enter' to start Chaikin", 10, 25);
            }
        }

        // 🚨 draw warning
        if (app.getWarningMessage() != null) {
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Arial", Font.BOLD, 14));
            graphics.drawString(app.getWarningMessage(), 10, 65);
            // app.setWarningMessage(null);
        }

        graphics.setColor(TEXT_COLOR);
        graphics.setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.drawString("Press 'Space' to clear", 10, getHeight() - 35);
        graphics.drawString("Press 'Escape' to exit", 10, getHeight() - 15);
    }

}
