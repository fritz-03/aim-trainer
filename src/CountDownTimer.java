import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A countdown timer for the game.
 */
public class CountDownTimer {
    private static boolean isDone = false;

    /**
     * Creates a countdown timer and displays it on the pane.
     *
     * @param pane            The pane to which the countdown timer will be added.
     * @param sceneWidth      The width of the scene.
     * @param sceneHeight     The height of the scene.
     * @param countdownSeconds The duration of the countdown timer in seconds.
     */
    public static void createTimer(Pane pane, double sceneWidth, double sceneHeight, double countdownSeconds) {
        Text countdownText = new Text();
        countdownText.setFont(Font.font("Arial", 20));
        countdownText.setFill(Color.WHITE);
        countdownText.setX(sceneWidth / 45);
        countdownText.setY(sceneHeight - 420);
        pane.getChildren().add(countdownText);

        new AnimationTimer() {
            final long startTime = System.nanoTime();
            int elapsedSeconds;
            @Override
            public void handle(long now) {
                long elapsedNanos = now - startTime;
                elapsedSeconds = (int) (elapsedNanos / 1_000_000_000);

                int remainingSeconds = (int) (countdownSeconds - elapsedSeconds);
                countdownText.setText("Time Remaining: " + remainingSeconds);
                countdownText.setFont(Font.font("Consolas", 30));

                if (remainingSeconds <= 0) {
                    System.out.println(Target.getNumHits());
                    isDone = true;
                    pane.getChildren().clear();
                    Text targetHits = new Text("Great Job! Targets Hit: " + Target.getNumHits());
                    targetHits.setFont(Font.font("Consolas", 30));
                    targetHits.setFill(Color.WHITE);
                    targetHits.setX(sceneWidth / 5);
                    targetHits.setY(sceneHeight / 2);
                    pane.getChildren().add(targetHits);
                    stop();
                }
            }
        }.start();
    }

    /**
     * Checks if the countdown timer has finished.
     *
     * @return true if the countdown timer has finished, otherwise false.
     */
    public static boolean isCountdownDone() {
        return isDone;
    }
}
