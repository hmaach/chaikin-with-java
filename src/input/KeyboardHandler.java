package src.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import src.app.ChaikinApp;

public class KeyboardHandler extends KeyAdapter {

    private final ChaikinApp app;

    public KeyboardHandler(ChaikinApp app) {
        this.app = app;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_ENTER -> {
                app.startChaikin();
            }
            case KeyEvent.VK_SPACE -> {
                app.clear();
            }
            case KeyEvent.VK_ESCAPE -> {
                app.exit();
            }
        }
    }
}
