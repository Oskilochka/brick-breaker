package org.josk.sprites;

import java.awt.*;

public class Sprite {

    int x;
    int y;
    int imageWidth;
    int imageHeight;
    Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Sprite() {
    }

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
}
