import javafx.animation.ScaleTransition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.io.File;
import java.util.Random;

/**
 * Represents a target in the game.
 */
public class Target {
    private static int totalTargetsHit = 0;

    /**
     * Creates a target and adds it to the specified pane.
     *
     * @param pane        The pane to which the target will be added.
     * @param sceneWidth  The width of the scene.
     * @param sceneHeight The height of the scene.
     * @return The created target circle.
     */
    public static Circle createTarget(Pane pane, double sceneWidth, double sceneHeight) {
        Random random = new Random();
        double radius = 25;

        double x = random.nextDouble() * (sceneWidth - 2 * radius) + radius;
        double y = random.nextDouble() * (sceneHeight - 2 * radius) + radius;

        String colorHex = "#4D45E5";
        Circle target = new Circle(x, y, radius, Color.WHITE);
        target.setStroke(Color.web(colorHex));
        target.setStrokeWidth(3);

        ScaleTransition scaleTransition = getScaleTransition(target);
        scaleTransition.play();

        target.setOnMouseClicked(e -> {
            if (!CountDownTimer.isCountdownDone()) {
                System.out.print("Target hit\n");
                pane.getChildren().remove(target);
                hitSound();
                totalTargetsHit += 1;
                Circle newTarget = createTarget(pane, sceneWidth, sceneHeight);
                pane.getChildren().add(newTarget);
            }
        });

        return target;
    }

    private static ScaleTransition getScaleTransition(Circle target) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), target);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        return scaleTransition;
    }

    /**
     * Plays the sound effect when a target is hit.
     */
    public static void hitSound() {
        String musicFile = "soft-hitnormal.mp3";
        Media sound = new Media(new File("src/resources/" + musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Gets the total number of targets hit.
     *
     * @return The total number of targets hit.
     */
    public static Integer getNumHits() {
        return totalTargetsHit;
    }
}
