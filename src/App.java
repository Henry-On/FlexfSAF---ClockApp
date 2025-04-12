
import controllers.WatchSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import watch.Watch;

public class App extends Application {

    private Stage stage;
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // load the Watch Scene on start
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLScenes/watchScene.fxml"));
        Parent root = fxmlLoader.load();
        WatchSceneController controller = fxmlLoader.getController();
        primaryStage.setTitle("Clock APP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        try {

            // create the thread
            Thread watchThread = new Thread(controller);
            watchThread.start();
            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println(e);
        }

    }    

}
