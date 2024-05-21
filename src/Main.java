import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.LoginScene;
import view.MainScene;

import javax.swing.text.html.ImageView;

public class Main extends Application {
    private boolean enter;
    @Override
    public void start(Stage primaryStage) throws Exception{
        enter = false;

        AnchorPane main = new AnchorPane();
        AnchorPane log = new AnchorPane();

        MainScene mainScene=new MainScene(main,700,500);
        LoginScene loginScene=new LoginScene(log, 700.0, 500.0);



        primaryStage.setTitle("2048妙妙屋");
        primaryStage.setScene(loginScene.getScene());
//        primaryStage.setScene(mainScene.getScene());

    //    primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
