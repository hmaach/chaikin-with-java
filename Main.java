import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.util.ArrayList;

public class Main extends JPanel implements Runnable {
    private Thread gameThread;
    private volatile boolean running = true;

    private Pattern pattern = new Pattern();
    private String infoMsg = "";
    private Instant clocker = Instant.now();
    private boolean showInfo = false;

    public Main() {
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Left click
                    if (!pattern.animationStart && !pattern.dragging) {
                        pattern.appendPoint(e.getX(), e.getY());
                        infoMsg = "";
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) { // Right click
                    if (!pattern.animationStart && !pattern.dragging) {
                        pattern.checkPointCollision(e.getX(), e.getY());
                    } else {
                        pattern.dragging = false;
                        pattern.draggablePoint = null;
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (pattern.dragging && pattern.draggablePoint != null) {
                    int fixIdx = pattern.draggablePoint[0];
                    int pathIdx = pattern.draggablePoint[1];
                    float x = e.getX(), y = e.getY();
                    pattern.fixedPoints.get(fixIdx).setPosition(x, y);
                    pattern.path.get(pathIdx).setPosition(x, y);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!pattern.animationStart && pattern.path.size() > 2) {
                        pattern.chaikin();
                        showInfo = false;
                    } else if (!pattern.animationStart && pattern.path.size() <= 2) {
                        infoMsg = "Animation must have 2 points to start!";
                        showInfo = true;
                        Timer timer = new Timer(3000, evt -> showInfo = false);
                        timer.setRepeats(false);
                        timer.start();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pattern = new Pattern();
                    infoMsg = "";
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    running = false;
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        while (running) {
            long start = System.nanoTime();

            update();
            repaint();

            long elapsed = System.nanoTime() - start;
            long delay = Math.max(16_666_666 - elapsed, 0); // ~60 FPS
            try {
                Thread.sleep(delay / 1_000_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void update() {
        if (pattern.animationStart) {
            double elapsed = java.time.Duration.between(clocker, Instant.now()).toMillis() / 1000.0;
            if (elapsed >= 0.5) {
                pattern.path = new ArrayList<>(pattern.finalPath.get(pattern.frame));
                pattern.frame = (pattern.frame + 1) % pattern.finalPath.size();
                clocker = Instant.now();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw FPS
        int fps = (int) (1000 / (System.currentTimeMillis() % 1000 != 0 ? 16.6 : 16.6));
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        g2d.drawString(fps + " FPS", getWidth() - 80, 30);

        // Draw info message
        if (showInfo || !infoMsg.isEmpty()) {
            g2d.setColor(Color.RED);
            FontMetrics fm = g2d.getFontMetrics();
            int msgWidth = fm.stringWidth(infoMsg);
            g2d.drawString(infoMsg, (getWidth() - msgWidth) / 2, getHeight() / 2);
        }

        // Draw instructions
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        if (!pattern.animationStart) {
            g2d.setColor(Color.WHITE);
            g2d.drawString("Left click to create points", 20, 30);
            g2d.drawString("Right click to drag points", 20, 50);
        } else {
            g2d.setColor(Color.ORANGE);
            g2d.drawString("Press SPACE to clear the window.", 20, 30);
            // // Draw animation step
            // if (pattern.animationStart) {
            //     g2d.setColor(Color.ORANGE);
            //     int currentStep = pattern.frame-1 ;
            //     if (currentStep == -1) {
            //         currentStep = 7;
            //     }
            //     String stepText = String.format("Step: %d / %d", currentStep, 7);
            //     g2d.drawString(stepText, 20, 60);
            // }
        }

        // Draw path
        pattern.drawPath(g2d, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main panel = new Main();
            JFrame frame = new JFrame("Chaikin");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setResizable(true);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            panel.requestFocusInWindow();
        });
    }
}