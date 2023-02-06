package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private double ballX = 500;
    private double ballY = 300;
    private double ballSpeedX = 3;
    private double ballSpeedY = 3;
    private Circle ball;

    private double paddle1Y = HEIGHT / 2;
    private Rectangle paddle1;

    private double paddle2Y = HEIGHT / 2;
    private Rectangle paddle2;

    private boolean upPressed;
    private boolean downPressed;
    private boolean wPressed;
    private boolean sPressed;

    int score1 = 0;
    int score2 = 0;


    @Override
    public void start(Stage primaryStage) {
        ball = new Circle(ballX, ballY, 10, Color.WHITE);

        paddle1 = new Rectangle(10, paddle1Y, 10, 120);
        paddle1.setFill(Color.WHITE);

        paddle2 = new Rectangle(WIDTH - 20, paddle2Y, 10, 120);
        paddle2.setFill(Color.WHITE);

        Text score = new Text();
        score.setStyle("-fx-text-inner-color: red;");
        score.setFont(Font.font(30));
        score.setX(300);
        score.setY(90);

        Pane root = new Pane();
        root.getChildren().addAll(ball, paddle1, paddle2);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.GREEN);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W) {
                upPressed = true;
            } else if (event.getCode() == KeyCode.S) {
                downPressed = true;
            } else if (event.getCode() == KeyCode.UP) {
                wPressed = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                sPressed = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.W) {
                upPressed = false;
            } else if (event.getCode() == KeyCode.S) {
                downPressed = false;
            } else if (event.getCode() == KeyCode.UP) {
                wPressed = false;
            } else if (event.getCode() == KeyCode.DOWN) {
                sPressed = false;
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if (upPressed) {
                paddle1Y -= 8;
            }
            if (downPressed) {
                paddle1Y += 8;
            }
            if (wPressed) {
                paddle2Y -= 8;
            }
            if (sPressed) {
                paddle2Y += 8;
            }
            ballX += ballSpeedX;
            ballY += ballSpeedY;

            if (ballX <= 20) {
                if (ballY > paddle1Y && ballY < paddle1Y + 50) {
                    ballSpeedX = -ballSpeedX;
                } else {
                    score1++;
                    score.setText("Player 1: " + score2 + "  Player 2: " + score1);
                    ballX = 500;
                    ballY = 300;
                    ballSpeedX = 3;
                    ballSpeedY = 3;
                }
            }
            if (ballX >= 975) {
                if (ballY > paddle2Y && ballY < paddle2Y + 50) {
                    ballSpeedX = -ballSpeedX;
                } else {
                    score2++;
                    score.setText("Player 1: " + score2 + "  Player 2: " + score1);
                    ballX = 500;
                    ballY = 300;
                    ballSpeedX = 3;
                    ballSpeedY = 3;
                }
            }
            if (ballY <= 10 || ballY >= 400) {
                ballSpeedY = -ballSpeedY;
            }
            if (paddle1Y <= 0) {
                paddle1Y = 0;
            } else if (paddle1Y >= 390) {
                paddle1Y = 390;
            }

            if (paddle2Y <= 0) {
                paddle2Y = 0;
            } else if (paddle2Y >= 600) {
                paddle2Y = 600;
            }
            paddle1.setY(paddle1Y);
            paddle2.setY(paddle2Y);
            ball.setCenterX(ballX);
            ball.setCenterY(ballY);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        score.setText("Player 1: " + score2 + "  Player 2: " + score1);
        root.getChildren().add(score);


        primaryStage.setTitle("PONG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

