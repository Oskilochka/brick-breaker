package org.josk.Board;

import org.josk.config.Config;
import org.josk.sprites.Rect;

import java.awt.*;
import java.util.Arrays;

public class MapGenerator {

    private final Rect[] rects;

    public MapGenerator(int rectAmount) {
        rects = new Rect[Config.RECT_AMOUNT];

        int k = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                rects[k] = new Rect(j * 30 + 40, i * 10 + 40);
                k++;
            }
        }
    }

    public Rect[] getRects() {
        return rects;
    }

    public void draw(Graphics2D graphics2D, Rect[] rects, Board board) {
        for (int i = 0; i < Config.RECT_AMOUNT; i++) {

            if (!rects[i].getIsBroken()) {
                graphics2D.drawImage(rects[i].getImage(), rects[i].getX(),
                        rects[i].getY(), rects[i].getImageWidth(),
                        rects[i].getImageHeight(), board);
            }
        }
    }
}
