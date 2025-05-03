
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLScenes/mainScene.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();

        primaryStage.setTitle("Clock");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        mainController.showStartView();
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
