package org.josk;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int width = 700;
        int height = 700;
        int totalBricks = 21;
        String title = "Brick Breaker";

        JFrame frame = new JFrame();

        Gameplay gameplay = new Gameplay(width, height, totalBricks);

        frame.add(gameplay);
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
