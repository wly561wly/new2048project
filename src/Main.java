import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.ChessNumber;
import model.UserImform;
import view.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.scene.Scene;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class Main extends Application {
    private int X_COUNT;
    private int Y_COUNT;
    private int pattern;
    private String userName;
    private boolean AutoSave;
    private Map<String, UserImform>userMap=new HashMap<>();
    private MainScene mainScene=new MainScene();
    private LoginScene loginScene=new LoginScene();
    private ForgetScene forgetScene = new ForgetScene();
    private RegisterScene registerScene =new RegisterScene();
    private ClassicChooseScene classicChooseScene;
    private ChallengeChooseScene challengeChooseScene;
    private GameScene gameScene;
    StartScene startScene = new StartScene();
    Setting setting = new Setting(0);
    Help help =new Help();
    @Override
    public void start(Stage primaryStage) throws Exception{

        prepare(primaryStage);

//这里有些问题，可能是相对路径有问题
        //可以使用
        Image mainImage = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\2.png");
        ImageView imageView = new ImageView();
        mainScene.getRoot().getChildren().add(imageView);
        primaryStage.getIcons().add(new Image(String.valueOf(new File("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\2.png"))));
        BorderPane root = new BorderPane();

        Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\3.png");


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
            String accountTxt=loginScene.getAccount().getText();
            String passwordTxt=loginScene.getPassword().getText();
            UserImform found=userMap.get(accountTxt);
            if (found!=null&&CheckPasswordKey(passwordTxt,found)){
                userName=accountTxt;
                if(gameScene!=null)gameScene.setUserName(userName);
                System.out.println(userName+" 已进入游戏！");
                //清空账号密码
                loginScene.getAccount().setText("");
                loginScene.getPassword().setText("");
                primaryStage.setScene(mainScene.getScene());
            }else{
                // 登录失败
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("登录失败");
                alert.setHeaderText("登陆失败，请重试！");
                if(found==null)alert.setContentText("没有账号？注册一个吧！");
                else alert.setContentText("密码错误，请重新输入！");
                loginScene.getPassword().setText("");
                alert.showAndWait();
            }
        });

        //游客登录
        loginScene.getVisitorBtn().setOnAction(event -> {
            userName="Visitor_1231";
            if(gameScene!=null)gameScene.setUserName(userName);
            System.out.println("游客 已进入游戏！");
            //清空输入框
            loginScene.getAccount().setText("");
            loginScene.getPassword().setText("");
            primaryStage.setScene(mainScene.getScene());
        });

        // 忘记密码
        loginScene.getForgetPasswordBtn().setOnAction(e -> {
            primaryStage.setScene(forgetScene.getScene());
            forgetScene.getBackBtn().setOnAction(event -> {
                //清空输入框
                forgetScene.getAccount().setText("");
                forgetScene.getPassword().setText("");
                primaryStage.setScene(loginScene.getScene());
            });
            //todo:确认逻辑
            forgetScene.getConfirmBtn().setOnAction(event ->{
                String accountTxt=forgetScene.getAccount().getText();
                String passwordTxt=forgetScene.getPassword().getText();
                UserImform found=userMap.get(accountTxt);
                if(found!=null){
                    UserImform user=new UserImform(accountTxt,found.getUserEmail(),getPasswordKey(passwordTxt),getPasswordKey1(passwordTxt));
                    userMap.replace(accountTxt,user);
                    WriteUserFile();
                    System.out.println("修改密码成功");
                    //清空输入框
                    forgetScene.getAccount().setText("");
                    forgetScene.getPassword().setText("");
                    primaryStage.setScene(loginScene.getScene());
                }
                else {
                    // 修改密码失败
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("账号出错");
                    alert.setHeaderText("不存在该账号，请重试！");
                    alert.setContentText("没有账号？注册一个吧！");
                    //清空输入框
                    forgetScene.getAccount().setText("");
                    forgetScene.getPassword().setText("");
                    alert.showAndWait();
                }
            });
            });

        // 注册账号
        loginScene.getRegisterBtn().setOnAction(e -> {

            primaryStage.setScene(registerScene.getScene());
            registerScene.getBackBtn().setOnAction(event->{
                //清空输入框
                registerScene.getAccount().setText("");
                registerScene.getPassword().setText("");
                registerScene.getEmail().setText("");
                primaryStage.setScene(loginScene.getScene());
            });

            registerScene.getConfirmBtn().setOnAction(Aevent->{
                String accountTxt=registerScene.getAccount().getText();
                String passwordTxt=registerScene.getPassword().getText();
                String emailTxt=registerScene.getEmail().getText();
                System.out.println("input Account:"+accountTxt);
                System.out.println("input Email:"+emailTxt);
                System.out.println("input Password:");
                // saveAccountData(accountTextField.getText(), passwordTextField.getText());
                int flag=Check_Account(accountTxt);
                if(flag==0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("账号重复");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的账号已存在，请重新输入");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        alert.close();
                    }
                }
                else if(flag==-1){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("账号非法");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的账号包含非法字符或不符合规范(如:?,/,\\,:等)，请重新输入");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        alert.close();
                    }
                }
                else if(flag==-2){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("账号过长");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的账号长度应在18以内，请重新输入");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        alert.close();
                    }
                }
                else if(emailTxt.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("邮箱");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的邮箱不应为空，请重新输入");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        alert.close();
                    }
                }
                else if(!registerScene.check_password()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("密码不合规");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的密码长度应在8-20之间，且同时包含数字和字母，请重新输入");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        alert.close();
                    }
                }
                else {
                    DoRegister(accountTxt,emailTxt,passwordTxt);
                    //清空输入框
                    registerScene.getAccount().setText("");
                    registerScene.getPassword().setText("");
                    registerScene.getEmail().setText("");
                    System.out.println("Register successfully, come to log in!!!");
                    primaryStage.setScene(loginScene.getScene());
                }
            });
            //todo：确认逻辑
        });


//以下为主界面
        //以下是模式按钮是监听
        mainScene.getBtn_mode1().setOnAction(event ->{
            primaryStage.setScene(classicChooseScene.getChoiceScene());
        });
        mainScene.getBtn_mode2().setOnAction(event ->{
            primaryStage.setScene(challengeChooseScene.getChoiceScene());
        });
        mainScene.getBtn_mode3().setOnAction(event ->{
            gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"scores",6,AutoSave);
            setMenubar(primaryStage);
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(primaryStage);
            });
            primaryStage.setScene(gameScene.getScene());
        });

        mainScene.getBtn_help().setOnAction(event ->{

        });
        //设置的监听器
        mainScene.getBtn_setting().setOnAction(event ->{
            setting=new Setting(1);
            dotheSetting();
        })  ;

        //帮助界面
        mainScene.getBtn_help().setOnAction(event -> {
            help.getStage().show();
        });

        //返回键的监听器
        mainScene.getBtn_back().setOnAction(event -> {
            primaryStage.setScene(startScene.getScene());
        });
        mainScene.getBtn_back2().setOnAction(event -> {
            primaryStage.setScene(loginScene.getScene());
        });
        //选择界面的返回监听
        classicChooseScene.getExitButton().setOnAction(event->{
            if(gameScene!=null)if(gameScene.getX_Count()!=X_COUNT){
                X_COUNT=gameScene.getX_Count();
                Y_COUNT=gameScene.getX_Count();
            }
            primaryStage.setScene(mainScene.getScene());
        });
        challengeChooseScene.getExitButton().setOnAction(event->{
            if(gameScene!=null)if(gameScene.getX_Count()!=X_COUNT){
                X_COUNT=gameScene.getX_Count();
                Y_COUNT=gameScene.getX_Count();
            }
            primaryStage.setScene(mainScene.getScene());
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
        AutoSave=false;
        classicChooseScene=new ClassicChooseScene();
        challengeChooseScene=new ChallengeChooseScene();
        //经典模式选择界面监听
        classicChooseScene.getBtn_mode1().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",512,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode2().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",1024,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode3().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",2048,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        classicChooseScene.getBtn_mode4().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"classic",4096,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        //挑战模式选择界面监听
        challengeChooseScene.getBtn_mode1().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"challenge",30,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        challengeChooseScene.getBtn_mode2().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"challenge",60,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        challengeChooseScene.getBtn_mode3().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"challenge",90,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });
        challengeChooseScene.getBtn_mode4().setOnAction(event ->{
            gameScene=gameScene=new GameScene(X_COUNT,Y_COUNT,userName,pattern,"challenge",120,AutoSave);
            setMenubar(stage);
            stage.setScene(gameScene.getScene());
            gameScene.getExitButton().setOnAction(actionEvent ->{
                doExitHintStage(stage);
            });
        });

        //以下为登录注册初始化
        ReadUserFile();//读取文件并存储
    }
    public static void main(String[] args) {
        launch(args);
    }

    //退出单局游戏说明
    public void doExitHintStage(Stage stage)
    {
        gameScene.setRunning(0);
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
            if(gameScene.getX_Count()!=X_COUNT){
                X_COUNT=gameScene.getX_Count();
                Y_COUNT=gameScene.getX_Count();
            }
            if(!userName.equals("Visitor_1231"))gameScene.getChessNumber().saveNumber(gameScene.getSteps(),1,gameScene.getTime());
            else System.out.println("Visitor can't save!");
            stageGameSettle.close();
            if(gameScene.getMode().equals("classic"))stage.setScene(classicChooseScene.getChoiceScene());
            else if(gameScene.getMode().equals("challenge"))stage.setScene(challengeChooseScene.getChoiceScene());
            else if(gameScene.getMode().equals("scores"))stage.setScene(mainScene.getScene());
        });
        exitBtn.setOnAction(event->{
            if(gameScene.getX_Count()!=X_COUNT){
                X_COUNT=gameScene.getX_Count();
                Y_COUNT=gameScene.getX_Count();
            }
            gameScene.setRunning(1);
            stageGameSettle.close();
            if(gameScene.getMode().equals("classic"))stage.setScene(classicChooseScene.getChoiceScene());
            else if(gameScene.getMode().equals("challenge"))stage.setScene(challengeChooseScene.getChoiceScene());
            else if(gameScene.getMode().equals("scores"))stage.setScene(mainScene.getScene());
        });
        cancelBtn.setOnAction(event->{
            gameScene.setRunning(1);
            stageGameSettle.close();
        });
        stageGameSettle.setOnCloseRequest(e -> {
            gameScene.setRunning(1);
        });
        stageGameSettle.setTitle("Exiting");
        stageGameSettle.setScene(settleScene);
        stageGameSettle.initModality(APPLICATION_MODAL);
        stageGameSettle.show();
    }
    public void ReadUserFile()
    {
        String path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\userImform.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设每行都是 "username,email,password" 格式，且没有额外的空格或特殊字符
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0].trim();
                    String userEmail = parts[1].trim();
                    long password = Long.parseLong(parts[2].trim()); // 注意：通常密码应该是加密的字符串，而不是long类型
                    long password1 = Long.parseLong(parts[3].trim());
                    userMap.put(username,new UserImform(username, userEmail, password,password1));
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("读取用户文件出错");
            throw new RuntimeException(e);
        }
    }
    public void WriteUserFile()
    {
        String path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\userImform.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<String, UserImform> entry : userMap.entrySet()) {
                UserImform user = entry.getValue();
                // 注意：这里假设password是一个加密后的字符串，但示例中仍然使用long类型
                // 在实际应用中，你应该将密码转换为一个加密后的字符串
                writer.write(user.getUsername() + "," + user.getUserEmail() + "," + user.getPasswordAsString()+","+String.valueOf(user.getPassword1()));
                writer.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("写入用户文件出错");
            throw new RuntimeException(e);
        }
    }
    public int Check_Account(String accountTest)//检测账号中非法字符
    {
        UserImform found=userMap.get(accountTest);
        System.out.println("Check Acount:"+accountTest);
        if(found!=null|| accountTest=="Visitor_1231")return 0;
        if(accountTest.length()>18)return -2;
        for(int i=0;i<accountTest.length();i++)
        {
            if(Check_char(accountTest.charAt(i)))return -1;
        }
        return 1;
    }
    public boolean Check_char(char c)//检测账号中非法字符
    {
        if(c=='/'||c=='\\'||c==':'||c=='*'||c=='?'||c=='%'||c=='&'||c=='|'||c==' ')return true;
        return false;
    }
    public void DoRegister(String userName,String Email,String passWord)//注册的具体操作
    {
        //新建文件夹，加入map
        String u_path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\"+userName;
        Path userfilepath= Paths.get(u_path);
        try {
            // 使用Files.createDirectories方法创建目录
            // 如果目录已经存在，则此方法不会抛出异常
            // 如果目录不存在，则创建目录及其所有必要的父目录
            Files.createDirectories(userfilepath);
            System.out.println("Directory created successfully: " + u_path);
            //接下来新建子文件夹
            userfilepath=Paths.get(u_path+"\\"+"challenge");
            Files.createDirectories(userfilepath);
            userfilepath=Paths.get(u_path+"\\"+"scores");
            Files.createDirectories(userfilepath);
            userfilepath=Paths.get(u_path+"\\"+"classic");
            Files.createDirectories(userfilepath);
            long passwordKey=getPasswordKey(passWord);
            long passwordKey1=getPasswordKey1(passWord);
            userMap.put(userName,new UserImform(userName,Email,passwordKey,passwordKey1));
            WriteUserFile();
        } catch (IOException e) {
            // 处理任何可能发生的I/O异常
            e.printStackTrace();
        }
    }
    public long getPasswordKey(String s)//生成对应的密钥
    {
        long key=0;
        long t=1997,p=1000000931;
        for(int i=0;i<s.length();i++)
        {
            key=((long)(key*t)+(int)s.charAt(i))%p;
        }
        return key;
    }
    public long getPasswordKey1(String s)//生成密钥
    {
        long key=0;
        long t=1999,p=1000000933;
        for(int i=0;i<s.length();i++)
        {
            key=((long)(key*t)+(int)s.charAt(i))%p;
        }
        return key;
    }
    public boolean CheckPasswordKey(String s,UserImform user)//验证密钥
    {
        long key1=0,key2=0;
        long t1=1997,p1=1000000931;
        long t2=1999,p2=1000000933;
        for(int i=0;i<s.length();i++)
        {
            key1=((long)(key1*t1)+(int)s.charAt(i))%p1;
            key2=((long)(key2*t2)+(int)s.charAt(i))%p2;
        }
        System.out.println("Check Password: "+String.valueOf(key1)+" "+String.valueOf(user.getPassword()));
        System.out.println("Check Password: "+String.valueOf(key2)+" "+String.valueOf(user.getPassword1()));
        if((long)key1!=user.getPassword())return false;
        if((long)key2!=user.getPassword1())return false;
        return true;
    }
    public void setAuto(boolean x)
    {
        AutoSave=x;
        if(gameScene!=null)gameScene.setAutosave(x);
    }
    public void setPattern(int x){
        pattern=x;
        if(gameScene!=null)gameScene.setPattern(x);
    }
    public void setCount(int x)
    {
        X_COUNT=x;
        Y_COUNT=x;
        if(gameScene!=null)gameScene.setX_Count(x);
    }
    public void setMenubar(Stage stage)
    {
        //菜单栏
        if (gameScene!=null) {
            gameScene.getMenubar().getBack().setOnAction(event -> {
                doExitHintStage(stage);
            });
            gameScene.getMenubar().getSettingItem().setOnAction(event -> {
                setting=new Setting(0);
                dotheSetting();
                setting.getStage().show();
            });
            gameScene.getMenubar().getHelpItem().setOnAction(event -> {
                gameScene.setRunning(0);
                help.getStage().show();
                help.getStage().setOnCloseRequest(e -> {
                    gameScene.setRunning(1);
                    help.getStage().close();
                });
            });
        }
    }
    public void dotheSetting(){
        if(gameScene!=null){
            gameScene.setRunning(0);
            if(gameScene.getX_Count()!=gameScene.getX_Count())setCount(gameScene.getX_Count());
        }
        setting.getStage().show();
        if (X_COUNT == 3) setting.getChoiceBox().getSelectionModel().select("3*3"); //设置默认选项
        else if (X_COUNT == 4) setting.getChoiceBox().getSelectionModel().select("4*4");
        else if (X_COUNT == 5) setting.getChoiceBox().getSelectionModel().select("5*5");
        else if (X_COUNT == 6) setting.getChoiceBox().getSelectionModel().select("6*6");
        else if (X_COUNT == 7) setting.getChoiceBox().getSelectionModel().select("7*7");
        else if (X_COUNT == 8) setting.getChoiceBox().getSelectionModel().select("8*8");
        else if (X_COUNT == 9) setting.getChoiceBox().getSelectionModel().select("9*9");
        else if (X_COUNT == 10) setting.getChoiceBox().getSelectionModel().select("10*10");

        if (pattern == 0) setting.getRadioButton1().setSelected(true);
        else if (pattern == 1) setting.getRadioButton2().setSelected(true);
        else if (pattern == 2) setting.getRadioButton3().setSelected(true);
        else if (pattern == 3) setting.getRadioButton4().setSelected(true);
        else if (pattern == 4) setting.getRadioButton5().setSelected(true);

        // 自动保存CheckBox
        if (AutoSave == false) setting.getAutoSaveCheckBox().setSelected(false);
        else setting.getAutoSaveCheckBox().setSelected(true);

        //对其中的按钮 监听
        setting.getStage().setOnCloseRequest(e -> {
            if(gameScene!=null)gameScene.setRunning(1);
        });
        setting.getChoiceBox().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("ChoiceBox selected value changed to: " + newValue);
                if (newValue.charAt(0) == '3') setCount(3);
                else if (newValue.charAt(0) == '4') setCount(4);
                else if (newValue.charAt(0) == '5') setCount(5);
                else if (newValue.charAt(0) == '6') setCount(6);
                else if (newValue.charAt(0) == '7') setCount(7);
                else if (newValue.charAt(0) == '8') setCount(8);
                else if (newValue.charAt(0) == '9') setCount(9);
                else if (newValue.charAt(0) == '1') setCount(10);
            }
        });
        setting.getAutoSaveCheckBox().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    // 如果被选中了
                    setAuto(true);
                    System.out.println("自动保存已启用");
                } else {
                    // 如果未被选中
                    setAuto(false);
                    System.out.println("自动保存已禁用");
                }
            }
        });
        // 监听 ToggleGroup 的 selectedToggle 属性
        setting.getToggleGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // newValue 是新选中的 RadioButton
                RadioButton selectedRadioButton = (RadioButton) newValue;
                System.out.println("Selected RadioButton: " + selectedRadioButton.getText());
                if (selectedRadioButton.getText().equals("Classic")) setPattern(0);
                else if (selectedRadioButton.getText().equals("Green and Blue")) setPattern(1);
                else if (selectedRadioButton.getText().equals("Pink")) setPattern(2);
                else if (selectedRadioButton.getText().equals("Blue")) setPattern(3);
                else if (selectedRadioButton.getText().equals("Yellow and Red")) setPattern(4);
            } else {
                // 如果没有选中任何 RadioButton，则 newValue 是 null
                System.out.println("No RadioButton selected");
            }
        });
    }
}
