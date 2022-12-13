package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class Main extends Application {

    public static final String TITLE = "Pong";
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    public static final int PADDLE_WIDTH = 25;
    public static final int PADDLE_HEIGHT = 100;
    public static final int BALL_RADIUS = 15;

    private static Random rdm;

    private boolean gameStarted;

    private double leftPlayerYPos = WINDOW_HEIGHT / 2;
    private double rightPlayerYPos = WINDOW_HEIGHT / 2;
    private double leftPlayerXPos = 10;
    private double rightPlayerXPos = WINDOW_WIDTH - PADDLE_WIDTH - 10;

    private int ballSpeedX = 1;
    private int ballSpeedY = 1;
    private double ballXpos = WINDOW_WIDTH / 2;
    private double ballYpos = WINDOW_HEIGHT / 2;

    private int scoreLeftPlayer = 0;
    private int scoreRightPlayer = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(17), e -> run(gc))); //new KeyFrame() - время между кадрами в миллисекундах
        tl.setCycleCount(Timeline.INDEFINITE); //через сколкьо циклов повторяется анимация. индефините - не будет повторяться

        canvas.setOnMouseClicked(e -> gameStarted = true);
        canvas.setOnMouseMoved(e -> leftPlayerYPos = e.getY());
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
        tl.play();

        /*Line line = new Line(WINDOW_WIDTH/2, 0, WINDOW_WIDTH/2,WINDOW_HEIGHT);
        line.setStroke(Color.GRAY);
        line.setStrokeWidth(10);

        Circle ball = new Circle(WINDOW_WIDTH/2, WINDOW_WIDTH/2, 15, Color.WHITE);

        Rectangle leftPaddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT);
        leftPaddle.setX(PADDLE_WIDTH * 1.5);
        leftPaddle.setY((WINDOW_HEIGHT - PADDLE_HEIGHT) / 2);
        leftPaddle.setFill(Color.WHITE);

        Rectangle rightPaddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle.setX(WINDOW_WIDTH - PADDLE_WIDTH * 1.5);
        rightPaddle.setY((WINDOW_HEIGHT - PADDLE_HEIGHT) / 2);
        rightPaddle.setFill(Color.WHITE);


        Group group = new Group(line, ball, leftPaddle, rightPaddle);
        Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.DIMGRAY);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    private void run(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);

        gc.setFill(Color.WHITE);
        gc.fillRect(WINDOW_WIDTH/2-2, 0, 4, WINDOW_HEIGHT);

        if (gameStarted){
            //движение мяча
            ballXpos += ballSpeedX;
            ballYpos += ballSpeedY;
            //проверка вышел ли мяч за границы поля
            if (ballXpos < leftPlayerXPos){
                scoreRightPlayer++;
                gameStarted = false;
            }
            if (ballYpos > rightPlayerXPos){
                scoreLeftPlayer++;
                gameStarted = false;
            }
            if (ballYpos < 0 || ballYpos > 0){
                ballSpeedY = -ballSpeedY;

            }
            //отбиваение от ракетки

            if((ballXpos + BALL_RADIUS > rightPlayerXPos &&
                    ballYpos >= rightPlayerYPos && ballYpos <= rightPlayerYPos + PADDLE_HEIGHT)){
                if (ballSpeedX > 0) ballSpeedX++;
                else ballSpeedX--;
                if (ballSpeedY > 0) ballSpeedY++;
                else ballSpeedY--;
                ballSpeedX = -ballSpeedX;
                //ballSpeedY = -ballSpeedY;
            }
            if((ballXpos > leftPlayerXPos + BALL_RADIUS &&
                    ballYpos >= leftPlayerYPos && ballYpos <= leftPlayerYPos + PADDLE_HEIGHT)){
                if (ballSpeedX > 0) ballSpeedX++;
                else ballSpeedX--;
                if (ballSpeedY > 0) ballSpeedY++;
                else ballSpeedY--;
                ballSpeedX = -ballSpeedX;
                //ballSpeedY = -ballSpeedY;
            }
            //типа ИИ правого игрока
            if (ballXpos < 3 * WINDOW_WIDTH / 4){
                rightPlayerYPos = ballYpos - PADDLE_HEIGHT / 2;
            }
            else {
                rightPlayerYPos = ballYpos > rightPlayerYPos + PADDLE_HEIGHT / 2 ? rightPlayerYPos + 1 : rightPlayerYPos - 1;
            }
            gc.setFill(Color.RED);
            gc.fillOval(ballXpos, ballYpos, BALL_RADIUS, BALL_RADIUS);
        }
        else {
            gc.setStroke(Color.YELLOW);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to Start", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
            ballXpos = WINDOW_WIDTH / 2;
            ballYpos = WINDOW_HEIGHT / 2;
            if (rdm.nextInt(2) == 0){
                ballSpeedX = 1;
            }
            else {
                ballSpeedX = -1;
            }
            if (rdm.nextInt(2) == 0){
                ballSpeedY = 1;
            }
            else {
                ballSpeedY = -1;
            }
        }
        gc.fillText(scoreLeftPlayer + "\t\t\t\t\t\t\t" + scoreRightPlayer, WINDOW_WIDTH / 2, 100);
        gc.fillRect(rightPlayerXPos, rightPlayerYPos, PADDLE_WIDTH, PADDLE_HEIGHT);
        gc.fillRect(leftPlayerXPos, leftPlayerYPos, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
