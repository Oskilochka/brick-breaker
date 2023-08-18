package org.josk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int playerPositionX = 310;
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    private int ballDirectionX = -1;
    private int ballDirectionY = -2;

    private int totalBricks;
    private final int height;
    private final int width;

    private final Timer timer;

    private final MapGenerator mapGenerator;

    public Gameplay(int width, int height, int totalBricks) {
        mapGenerator = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 5;
        timer = new Timer(delay, this);
        timer.start();

        this.height = height;
        this.width = width;
        this.totalBricks = totalBricks;
    }

    public void paint(Graphics graphics) {
        // bg
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, width, height);

        // drawing map
        mapGenerator.draw((Graphics2D) graphics);

        // the paddle
        graphics.setColor(Color.green);
        graphics.fillRect(playerPositionX, 550, 100, 8);

        // the ball
        graphics.setColor(Color.red);
        graphics.fillOval(ballPositionX, ballPositionY, 20, 20);

        graphics.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerPositionX >= width) {
                playerPositionX = width;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerPositionX < 5) {
                playerPositionX = 5;
            } else {
                moveLeft();
            }
        }
    }

    private void moveLeft() {
        play = true;
        playerPositionX -= 20;
    }

    private void moveRight() {
        play = true;
        playerPositionX += 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {
            if (new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerPositionX, 550, 100, 8))) {
                ballDirectionY = -ballDirectionY;
            }

            A:
            for (int i = 0; i < mapGenerator.map.length; i++) {
                for (int j = 0; j < mapGenerator.map[0].length; j++) {
                    if (mapGenerator.map[i][j] > 0) {
                        int brickX = j * mapGenerator.brickWidth + 80;
                        int brickY = j * mapGenerator.brickWidth + 50;
                        int brickWidth = mapGenerator.brickWidth;
                        int brickHeight = mapGenerator.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20, 20);

                        if (ballRect.intersects(brickRect)) {
                            mapGenerator.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 1;

                            if (ballPositionX + 20 <= brickRect.x
                                    || ballPositionX + 1 >= brickRect.x + brickRect.width) {
                                ballDirectionX = -ballDirectionX;
                            } else {
                                ballDirectionY = -ballDirectionY;
                            }
                            break A;
                        }
                    }
                }
            }

            ballPositionX += ballDirectionX;
            ballPositionY += ballDirectionY;

            // left border
            if (ballPositionX < 0) {
                ballDirectionX = -ballDirectionX;
            }
            // top border
            if (ballPositionY < 0) {
                ballDirectionY = -ballDirectionY;
            }
            //  right border
            if (ballPositionX > 670) {
                ballDirectionX = -ballDirectionX;
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
