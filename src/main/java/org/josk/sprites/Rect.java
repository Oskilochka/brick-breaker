package org.josk.sprites;

import javax.swing.*;

public class Rect extends Sprite {
    private boolean isBroken;

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;

        isBroken = false;

        loadImage();
        getImageDimensions();
    }

    public boolean getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(boolean isBrokenVValue) {
        isBroken = isBrokenVValue;
    }

    private void loadImage() {
        var imageIcon = new ImageIcon("srs/resources/brick.png");
        image = imageIcon.getImage();
    }
}
