public class Point implements Cloneable {
    public float radius;
    public float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 5.0f;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkCollision(float areaX, float areaY) {
        float dx = areaX - x;
        float dy = areaY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance <= radius;
    }

    public Point make25Point(Point other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        Point p = new Point(this.x + dx * 0.25f, this.y + dy * 0.25f);
        p.radius = 0.0f;
        return p;
    }

    public Point make75Point(Point other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        Point p = new Point(this.x + dx * 0.75f, this.y + dy * 0.75f);
        p.radius = 0.0f;
        return p;
    }

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}