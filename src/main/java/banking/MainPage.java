package banking;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import window_config.WindowConfigurations;

//  Bu sadece işlemlere yönlendirmelerin yapılacağı sayfa
public class MainPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainPage.fxml"));
        Parent root = loader.load();

        Scene scene = WindowConfigurations.createScene(root);
        stage.setTitle("Banking App");
        WindowConfigurations.configureState(stage);
        stage.setScene(scene);
        stage.show();
    }
}
