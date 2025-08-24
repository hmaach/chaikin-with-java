package src.model;

public class Line {

    private Point firstPoint;
    private Point secondPoint;

    public Line(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    @Override
    public String toString() {
        return String.format("{P1: (%d, %d)} {P2: (%d, %d)}",
                firstPoint.getX(), firstPoint.getY(),
                secondPoint.getX(), secondPoint.getY());
    }
}
