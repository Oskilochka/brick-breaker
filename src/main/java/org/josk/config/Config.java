package org.josk.config;

public interface Config {
    String TITLE = "Arkanoid game";
    int WIDTH = 500;
    int HEIGHT = 800;
    int RECT_AMOUNT = 30;
    int PERIOD = 10;
    int BOTTOM_EDGE = HEIGHT - 10;
    int INIT_PLATFORM_X = WIDTH / 2;
    int INIT_PLATFORM_Y = HEIGHT - 40;
    int INIT_BALL_X = WIDTH / 2;
    int INIT_BALL_Y = HEIGHT / 2;

    int DELAY = 5;

    String FINISH_SUCCESS_MESSAGE = "You win. Congratulations !";
    String DEFEAT_MESSAGE = "Game over. You lost !";

}
