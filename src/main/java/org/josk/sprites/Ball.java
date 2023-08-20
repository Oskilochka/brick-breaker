package org.josk.sprites;

import org.josk.config.Config;

import javax.swing.*;

public class Ball extends Sprite {
    private int directionX;
    private int directionY;

    public Ball() {
        directionX = 1;
        directionY = -1;

        loadImage();
        getImageDimensions();
        resetPosition();
    }

    public int getDirectionX() {
        return directionX;
    }

    //    when the ball hits the paddle or a brick
    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    //    when the ball hits the paddle or a brick
    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    private void loadImage() {
        var imageIcon = new ImageIcon("srs/resources/ball.png");
        image = imageIcon.getImage();
    }

    private void resetPosition() {
        x = Config.INIT_BALL_X;
        y = Config.INIT_BALL_Y;
    }


    public void move() {
        x += directionX;
        y += directionY;

        if (x == 0) {
            setDirectionX(1);
        }

        if (x == Config.WIDTH - imageWidth) {
            setDirectionX(-1);
        }

        if (y == 0) {
            setDirectionY(1);
        }
    }
}
