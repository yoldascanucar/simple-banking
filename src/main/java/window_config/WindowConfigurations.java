package window_config;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowConfigurations {

    public static final double WINDOW_WIDTH = 450;
    public static final double WINDOW_HEIGHT = 450;

    public static void configureState(Stage stage) {
        stage.setResizable(false);
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
    }

    public static Scene createScene(Parent root) {
        return new Scene(root, WINDOW_WIDTH,WINDOW_HEIGHT);
    }
}
