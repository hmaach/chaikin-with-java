package src;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {

    private final int width = 800;
    private final int height = 600;
    private final String title = "Chaikin's algorithm with Java";
    private final Color backgrouColor = Color.BLACK;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    public Window() {
        JFrame frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(this.title));
        frame.getContentPane().setBackground(this.backgrouColor);
        frame.setSize(this.width, this.height);
        frame.setAlwaysOnTop(true);
        frame.setCursor(this.cursor);
        frame.setVisible(true);
        frame.setLocation(1000, 50);
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}
