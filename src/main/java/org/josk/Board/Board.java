package org.josk.Board;

import org.josk.config.Config;
import org.josk.sprites.Ball;
import org.josk.sprites.Platform;
import org.josk.sprites.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener, ActionListener {
    private boolean play = true;
    private Ball ball;
    private Rect[] rects;
    private MapGenerator mapGenerator;
    private Platform platform;
    private String message = Config.DEFEAT_MESSAGE;

    private Timer timer;

    public Board() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initGame();
    }

    private void initGame() {
        ball = new Ball();
        platform = new Platform();
        mapGenerator = new MapGenerator(Config.RECT_AMOUNT);
        rects = mapGenerator.getRects();

        System.out.println(rects.length);

        timer = new Timer(Config.DELAY, this);
        timer.start();

//        System.out.println(timer.isRunning());

    }

    @Override
    public void paintComponent(Graphics graphics) {
//        super.paintComponent(graphics);

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);

        graphics.dispose();

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        if (play) {
            graphics2D.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                    ball.getImageWidth(), ball.getImageHeight(), this);
            graphics2D.drawImage(platform.getImage(), platform.getX(), platform.getY(),
                    platform.getImageWidth(), platform.getImageHeight(), this);

            mapGenerator.draw(graphics2D, rects, this);

//            System.out.println(rects.length);

        } else {
            finishedGame(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync(); // synchronizes the graphics state
    }

    private void finishedGame(Graphics2D graphics2D) {
        message = Config.FINISH_SUCCESS_MESSAGE;

        Font font = new Font("Helvetica", Font.ITALIC, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        graphics2D.setFont(font);
        graphics2D.setColor(Color.green);
        graphics2D.drawString(message, Config.WIDTH - fontMetrics.stringWidth(message) / 2, Config.WIDTH / 2);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        platform.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        platform.keyPressed(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
        platform.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
        play = false;
        timer.stop();
    }

    private void checkCollision() {
        // if out of platform
        if (ball.getRect().getMaxY() > Config.BOTTOM_EDGE) {
            stopGame();
        }

        // platform intersection
        if (ball.getRect().intersects(platform.getRect())) {
            int platformPosition = (int) platform.getRect().getMinX();
            int ballPosition = (int) ball.getRect().getMinX();

            if (ballPosition < platformPosition + 8) {
                ball.setDirectionY(1);
                ball.setDirectionX(-1);
            } else if (ballPosition < platformPosition + 16) {
                ball.setDirectionY(-1 * ball.getDirectionY());
                ball.setDirectionX(-1);
            } else if (ballPosition < platformPosition + 24) {
                ball.setDirectionY(-1);
                ball.setDirectionX(0);
            } else if (ballPosition < platformPosition + 32) {
                ball.setDirectionY(-1 * ball.getDirectionY());
                ball.setDirectionX(1);
            } else {
                ball.setDirectionY(-1);
                ball.setDirectionX(1);
            }
        }

        // If it's the last rect
        for (int i = 0, j = 0; i < Config.RECT_AMOUNT; i++) {
            if (rects[i].getIsBroken()) {
                j++;
            }
            if (j == Config.RECT_AMOUNT) {
                message = Config.FINISH_SUCCESS_MESSAGE;
                stopGame();
            }
        }

        // obstacle intersection
        for (int i = 0; i < Config.RECT_AMOUNT; i++) {

            if (ball.getRect().intersects(rects[i].getRect())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point topPoint = new Point(ballLeft, ballTop - 1);
                Point bottomPoint = new Point(ballLeft + 1, ballTop + ballHeight + 1);
                Point rightPoint = new Point(ballLeft + ballWidth + 1, ballTop);
                Point leftPoint = new Point(ballLeft - 1, ballTop);

                if (!rects[i].getIsBroken()) {
                    if (rects[i].getRect().contains(topPoint)) {
                        ball.setDirectionY(1);
                    }
                    if (rects[i].getRect().contains(bottomPoint)) {
                        ball.setDirectionY(-1);
                    }
                    if (rects[i].getRect().contains(rightPoint)) {
                        ball.setDirectionX(-1);
                    }
                    if (rects[i].getRect().contains(leftPoint)) {
                        ball.setDirectionX(1);
                    }

                    rects[i].setIsBroken(true);
                }
            }
        }
    }
}
