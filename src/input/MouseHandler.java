package src.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import src.app.ChaikinApp;
import src.model.Point;

public class MouseHandler extends MouseAdapter {

    private final ChaikinApp app;

    public MouseHandler(ChaikinApp app) {
        this.app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // left click
            app.setWarningMessage(null); // remove the warning message
            app.addPoint(new Point(e.getX(), e.getY()));
            this.app.repaint();
        }
    }
}
