package src.app;

import java.util.ArrayList;
import java.util.List;
import src.model.Point;
import src.ui.Window;

public class ChaikinApp {

    private List<Point> fixedPoints = new ArrayList<>();

    public void run() {
        Window window = new Window();
    }

    public List<Point> getFixedPoints() {
        return fixedPoints;
    }

    public void insertPoint(Point point) {
        this.fixedPoints.add(point);
    }

}
