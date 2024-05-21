import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main class of the 2D Aim Trainer application.
 */
public class Main extends Application {

    private static boolean startTextRemoved = false;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        double sceneWidth = 750;
        double sceneHeight = 450;
        int countdownSeconds = 30;

        pane.setStyle("-fx-background-color: #0b1325;");

        Text startText = new Text("CLICK THE SCREEN TO START THE GAME");
        startText.setFont(Font.font("Consolas", 30));
        startText.setFill(Color.WHITE);
        startText.setX(sceneWidth / 8.5);
        startText.setY(sceneHeight / 2);
        pane.getChildren().add(startText);

        pane.setOnMouseClicked(e -> {
            if (!startTextRemoved) {
                pane.getChildren().remove(startText);
                startTextRemoved = true;
                CountDownTimer.createTimer(pane, sceneWidth, sceneHeight, countdownSeconds);

                for (int i = 0; i < 5; i++) {
                    Circle target = Target.createTarget(pane, sceneWidth, sceneHeight);
                    pane.getChildren().add(target);
                }
            }
        });

        Scene scene = new Scene(pane, sceneWidth, sceneHeight);
        primaryStage.setResizable(false);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2D Aim Trainer");
        primaryStage.show();
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}