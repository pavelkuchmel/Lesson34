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
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public static final String TITLE = "Pong";
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    public static final int PADDLE_WIDTH = 25;
    public static final int PADDLE_HEIGHT = 100;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(17), e -> run(gc))); //new KeyFrame() - время между кадрами в миллисекундах
        tl.setCycleCount(Timeline.INDEFINITE); //через сколкьо циклов повторяется анимация. индефините - не будет повторяться

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
    }
    public static void main(String[] args) {
        launch(args);
    }
}
