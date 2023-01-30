package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private Pane board;

    @FXML
    private Rectangle player1;

    @FXML
    private Rectangle player2;

    @FXML
    private Circle ball;

    @FXML
    private Label score1;

    @FXML
    private Label score2;

    private AnimationTimer timer;
    private double playerVelY = 0;
    private double player2VelY = 0;

    public HelloController() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                Pong.handLePlayer(player1, playerVelY);
                Pong.handLePlayer(player2, player2VelY);
                Pong.updateGame(player1, player2, ball);
                checkEndGame();
            }
        };
    }

    @FXML
    public void initialize() {

    }

    @FXML
    void play() {
        board.requestFocus();
        timer.start();
    }

    @FXML
    void reset(ActionEvent event) {
        timer.stop();
        ball.setCenterX(400);
        ball.setCenterY(230);
        player1.setX(10);
        player1.setY(170);
        player2.setX(780);
        player1.setY(170);
        Pong.resetGame();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if (player1.getY() >= 10) {
                    playerVelY = -10;
                    player1.setY(player1.getY() + playerVelY);
                }
                break;
            case S:
                if (player1.getY() <= 600) {
                    playerVelY = 10;
                    player1.setY(player1.getY() + playerVelY);
                }
                break;
            case J:
                if (player2.getY() >= 600) {
                    player2VelY = 10;
                    player2.setY(player2.getY() + player2VelY);
                }
                break;
            case N:
                if (player2.getY() <= 600) {
                    player2VelY = 10;
                    player2.setY(player2.getY() + player2VelY);
                }
                break;
            default:
                break;

        }
    }

    @FXML
    void onKeyReleased(KeyEvent event) {

    }

    public void checkEndGame() {
        if (ball.getCenterX() < 15) {
            timer.stop();
        }
    }

}