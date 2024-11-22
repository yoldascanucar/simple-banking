package util;
import controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import window_config.WindowConfigurations;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StageUtil  {
    public void loadAndDisplayStage(Button pageButton, List<String> clientIbanNumbers, URL fxmlSource) throws IOException {
        FXMLLoader loader = new FXMLLoader(fxmlSource);
        Parent root = loader.load();
        BaseController controller = loader.getController();
        ComboBox<String> ibanDropdown = controller.getDropdownIbans();

        if (ibanDropdown != null) {
            ibanDropdown.getItems().addAll(clientIbanNumbers);
        }

        Stage stage = (Stage) pageButton.getScene().getWindow();
        Scene scene = WindowConfigurations.createScene(root);
        stage.setScene(scene);
        stage.show();
    }
}
