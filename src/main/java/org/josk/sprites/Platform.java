package org.josk.sprites;

import org.josk.config.Config;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Platform extends Sprite {
    private int directionX;

    public Platform() {
        loadImage();
        getImageDimensions();
        resetPosition();
    }

//    public int getDirectionX() {
//        return directionX;
//    }
//
//    public void setDirectionX(int directionX) {
//        this.directionX = directionX;
//    }

    private void loadImage() {
        var imageIcon = new ImageIcon("srs/resources/platrform.png");
        image = imageIcon.getImage();
    }

    private void resetPosition() {
        x = Config.INIT_PLATFORM_X;
        y = Config.INIT_PLATFORM_Y;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            directionX = 1;
        }

        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            directionX = -1;
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            directionX = 0;
        }

        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            directionX = 0;
        }
    }

    public void move() {
        x += directionX;

        if (x <= 0) {
            x = 0;
        }

        if (x >= Config.WIDTH - imageWidth) {
            x = Config.WIDTH - imageWidth;
        }
    }
}
