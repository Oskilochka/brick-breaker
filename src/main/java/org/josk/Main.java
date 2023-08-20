package org.josk;

import org.josk.Board.Board;
import org.josk.config.Config;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        System.out.println(frame.toString());

        frame.add(new Board());

        frame.setSize(Config.WIDTH, Config.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setTitle(Config.TITLE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
