import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
    MainScene mainScene=new MainScene();
    LoginScene loginScene=new LoginScene();
    ForgetScene forgetScene = new ForgetScene();
    RegisterScene registerScene =new RegisterScene();
    StartScene startScene = new StartScene();
    @Override
    public void start(Stage primaryStage) throws Exception{
//这里有些问题，可能是相对路径有问题
//        Image mainImage = new Image("C:\\Users\\86189\\IdeaProjects\\javafx\\src\\img.png");
//        ImageView imageView = new ImageView();
//        mainScene.getRoot().getChildren().add(imageView);
//        primaryStage.getIcons().add(new Image(String.valueOf(new File("image/img.png"))));
        primaryStage.setTitle("2048妙妙屋");
        primaryStage.setScene(startScene.getScene());
        startScene.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(loginScene.getScene());
            }
        });


//以下为login界面
        // 登录
        loginScene.getLoginButton().setOnAction(actionEvent -> {
            if (loginScene.getAuthenticate()){
                primaryStage.setScene(mainScene.getScene());
            }else{
                // 登录失败
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("登录失败");
                alert.setHeaderText("登陆失败，请重试！");
                alert.setContentText("没有账号？注册一个吧！");
                alert.showAndWait();
            }
        });

        //游客登录
        loginScene.getVisitorBtn().setOnAction(event -> {
            primaryStage.setScene(mainScene.getScene());
        });

        // 忘记密码
        loginScene.getForgetPasswordBtn().setOnAction(e -> {
            primaryStage.setScene(forgetScene.getScene());
            forgetScene.getBackBtn().setOnAction(event -> {
                primaryStage.setScene(loginScene.getScene());
            });
            //todo:确认逻辑
            forgetScene.getConfirmBtn().setOnAction(event ->{
                if (forgetScene.getConfirm()){
                    primaryStage.setScene(mainScene.getScene());
                }
            });
            });

        // 注册账号
        loginScene.getRegisterBtn().setOnAction(e -> {
            // saveAccountData(accountTextField.getText(), passwordTextField.getText());
            primaryStage.setScene(registerScene.getScene());
            registerScene.getBackBtn().setOnAction(event->{
                primaryStage.setScene(loginScene.getScene());
            });
            //todo：确认逻辑
        });


//以下为主界面
        //返回键的监听器
        mainScene.getBtn_back().setOnAction(event -> {
            primaryStage.setScene(startScene.getScene());
        });
        mainScene.getBtn_back2().setOnAction(event -> {
            primaryStage.setScene(loginScene.getScene());
        });



        //设置退出提示
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出游戏");
            alert.setHeaderText(null);
            alert.setContentText("您是否要退出游戏");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                primaryStage.close();
            }
        });

    //    primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
