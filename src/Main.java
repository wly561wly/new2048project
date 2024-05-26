import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.*;

import java.util.Optional;

import javafx.scene.Scene;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class Main extends Application {
    private int X_COUNT;
    private int Y_COUNT;
    private int pattern;
    private String userName;
    private boolean AutoSave;
    MainScene mainScene=new MainScene();
    LoginScene loginScene=new LoginScene();
    ForgetScene forgetScene = new ForgetScene();
    RegisterScene registerScene =new RegisterScene();
    ClassicChooseScene classicChooseScene;
    ChallengeChooseScene challengeChooseScene;
    GameScene gameScene;
    StartScene startScene = new StartScene();
    @Override
    public void start(Stage primaryStage) throws Exception{

        prepare(primaryStage);

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
            userName="Visitor_1231";
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
        //以下是模式按钮是监听
        mainScene.getBtn_mode1().setOnAction(event ->{
            primaryStage.setScene(classicChooseScene.getChoiceScene());
        });
        mainScene.getBtn_mode2().setOnAction(event ->{

        });
        mainScene.getBtn_mode3().setOnAction(event ->{

        });
        mainScene.getBtn_help().setOnAction(event ->{

        });
        mainScene.getBtn_setting().setOnAction(event ->{

            Stage stage = new Stage();
            ToggleGroup toggleGroup = new ToggleGroup();
            RadioButton radioButton1 = new RadioButton("Classic");
            radioButton1.setToggleGroup(toggleGroup);
            radioButton1.setSelected(true); // 默认选中第一个
            RadioButton radioButton2 = new RadioButton("Pattern ");
//            radioButton2.setToggleGroup(toggleGroup);
//            RadioButton radioButton3 = new RadioButton("Pattern " + i);
//            radioButton3.setToggleGroup(toggleGroup);
//            RadioButton radioButton4 = new RadioButton("Pattern " + i);
//            radioButton4.setToggleGroup(toggleGroup);
//            RadioButton radioButton5 = new RadioButton("Pattern " + i);
//            radioButton5.setToggleGroup(toggleGroup);

            // 创建自动保存CheckBox
            CheckBox autoSaveCheckBox = new CheckBox("自动保存");
            autoSaveCheckBox.setSelected(false); // 默认不选中

            // 布局设置
            VBox vbox = new VBox(10); // 垂直布局，元素间隔10像素
            vbox.setPadding(new Insets(10)); // 设置内边距
            vbox.setAlignment(Pos.CENTER); // 设置对齐方式为居中

            HBox radioBox = new HBox(10); // 水平布局，存放RadioButton
            radioBox.getChildren().addAll(radioButton1 /* 其他RadioButton可以添加到这里 */);

            vbox.getChildren().addAll(radioBox, autoSaveCheckBox); // 将RadioButton和CheckBox添加到VBox中

            // 创建场景并设置给Stage
            Scene scene = new Scene(vbox, 300, 200); // 场景大小为300x200像素
            stage.setTitle("设置场景");
            stage.setScene(scene);
            stage.show();

        });
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

    public void prepare(Stage stage)
    {
        X_COUNT=4;
        Y_COUNT=4;
        pattern=0;
        AutoSave=true;
        classicChooseScene=new ClassicChooseScene();
        challengeChooseScene=new ChallengeChooseScene();

        classicChooseScene.getBtn_mode1().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",512,AutoSave);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode2().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",1024,AutoSave);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode3().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",2048,AutoSave);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode4().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",4096,AutoSave);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void doExitHintStage(Stage stage)
    {
        int scores=gameScene.getChessNumber().getScores();
        Label settleLabel= new Label("本局游戏得分为："+ Integer.toString(scores));
        Label hintLabel = new Label("你确定要退出本局游戏吗");
        settleLabel.setLayoutX(101);
        settleLabel.setLayoutY(50);
        hintLabel.setLayoutX(90);
        hintLabel.setLayoutY(100);

        Button saveBtn = new Button("Save and Exit");
        Button exitBtn = new Button("Exit directly");
        Button cancelBtn = new Button("Cancel");
        saveBtn.setLayoutX(25);
        saveBtn.setLayoutY(200);
        exitBtn.setLayoutX(132);
        exitBtn.setLayoutY(200);
        cancelBtn.setLayoutX(227);
        cancelBtn.setLayoutY(200);

        Pane settlePane=new Pane();
        settlePane.getChildren().addAll(settleLabel,hintLabel,saveBtn,exitBtn,cancelBtn);

        Scene settleScene=new Scene(settlePane,300,300);
        Stage stageGameSettle= new Stage();

        saveBtn.setOnAction(event ->{
            gameScene.getChessNumber().saveNumber(gameScene.getSteps(),1);
            stageGameSettle.close();
            stage.setScene(classicChooseScene.getChoiceScene());
        });
        exitBtn.setOnAction(event->{
            stageGameSettle.close();
            stage.setScene(classicChooseScene.getChoiceScene());
        });
        cancelBtn.setOnAction(event->{
            stageGameSettle.close();
        });
        stageGameSettle.setTitle("Exiting");
        stageGameSettle.setScene(settleScene);
        stageGameSettle.initModality(APPLICATION_MODAL);
        stageGameSettle.show();
    }
}
