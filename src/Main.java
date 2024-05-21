import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GameScene;
import view.LoginScene;
import view.MainScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MainScene mainScene=new MainScene();
        LoginScene loginScene=new LoginScene();
        GameScene gameScene=new GameScene(4,4);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(gameScene.getScene());
    //    primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
