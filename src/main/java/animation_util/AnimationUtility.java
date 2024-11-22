package animation_util;

import controllers.BaseController;
import controllers.DepositionController;
import controllers.SuccessPageController;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationUtility {

    public static void showSuccessMessage(Stage currentStage, Scene previousScene, String message, String balanceMessage, BaseController baseController) {
        try {
            FXMLLoader loader = new FXMLLoader(AnimationUtility.class.getResource("/fxml/successPage.fxml"));
            Parent root = loader.load();
            StackPane successPage = (StackPane) root;
            successPage.setOpacity(0);

            SuccessPageController controller = loader.getController();
            controller.setTransactionMessage(message);

            Scene currentScene = new Scene(successPage);
            currentStage.setScene(currentScene);
            currentStage.show();

            // Görünür Olma animasyonu
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8), successPage);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            // Kaybolma animasyonu
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.8), successPage);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                baseController.getClientBalanceField().setText(balanceMessage);
                currentStage.setScene(previousScene);
            });

            // Pause animasyonu
            PauseTransition pause = new PauseTransition(Duration.seconds(1.4));
            pause.setOnFinished(e -> {
                fadeOut.play();
            });

            fadeIn.play();
            fadeIn.setOnFinished(e -> {
                pause.play();
            });

        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}

