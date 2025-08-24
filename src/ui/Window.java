package src.ui;

import java.awt.Cursor;
import javax.swing.JFrame;
import src.app.ChaikinApp;
import src.input.KeyboardHandler;
import src.input.MouseHandler;

public class Window {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String WINDOW_TITLE = "Chaikin's Algorithm with Java";
    private static final int WINDOW_X_POSITION = 1000;
    private static final int WINDOW_Y_POSITION = 50;

    private final ChaikinApp app;
    private final JFrame frame;
    private final Canvas canvas;

    public Window(ChaikinApp app) {
        this.app = app;
        this.frame = createFrame();
        this.canvas = createCanvas();

        setupFrame();
        setupEventHandlers();
    }

    private JFrame createFrame() {
        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setLocation(WINDOW_X_POSITION, WINDOW_Y_POSITION);
        jFrame.setAlwaysOnTop(true);
        jFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return jFrame;
    }

    private Canvas createCanvas() {
        return new Canvas(this.app);
    }

    private void setupFrame() {
        frame.add(canvas);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
    }

    private void setupEventHandlers() {
        canvas.addMouseListener(new MouseHandler(this.app));
        canvas.addKeyListener(new KeyboardHandler(this.app));
    }

    public void repaint() {
        if (canvas != null) {
            canvas.repaint();
        }
    }

    public void exit() {
        if (frame != null) {
            frame.dispose();
        }
    }

    public void setCursor(int cursor) {
        frame.setCursor(new Cursor(cursor));
    }
}
