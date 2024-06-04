package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class LoginScene{
    private Label titleLabel = new Label("登录");
    private Label accountLabel = new Label("账号");
    private Label passwordLabel = new Label("密码");
    private Button forgetPasswordBtn = new Button("忘记密码？");
    private Button registerBtn = new Button("注册");
    Button loginButton = new Button("登录");
    Button visitorBtn = new Button("游客");
    private TextField account = new TextField();
    private PasswordField password = new PasswordField();
    // 使用VBox来垂直排列主要的组件
    VBox mainVBox = new VBox(10); // 垂直间距为10
    // 用于标题的HBox
    HBox titleHBox = new HBox(10); // 水平间距为10
    // 用于账号和密码的VBox
    VBox inputVBox = new VBox(30); // 竖直间距为30
    // 用于“忘记密码”和“注册”的HBox
    HBox otherHBox = new HBox(20); // 水平间距为20
    // 假设这里有一个StackPane或其他容器来承载VBox
    StackPane root = new StackPane();
    private double height;
    private double width;
    private Scene scene;
    private Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\4.jpg");
    public LoginScene()
    {
        titleLabel.setFont(new Font(20));
        accountLabel.setFont(new Font(16));
        passwordLabel.setFont(new Font(16));
        forgetPasswordBtn.setFont(new Font(14));
        loginButton.setFont(new Font(14));
        registerBtn.setFont(new Font(14));
        visitorBtn.setFont(new Font(14));

        account.setMaxWidth(400);
        password.setMaxWidth(400);
        mainVBox.setMaxWidth(500);
        otherHBox.setMaxWidth(400);

        mainVBox.setAlignment(Pos.TOP_CENTER); // 内容顶部居中对齐
        mainVBox.setPadding(new Insets(20, 20, 30, 30)); // 设置内边距
        titleHBox.setAlignment(Pos.CENTER); // 内容居中对齐
        titleHBox.getChildren().add(titleLabel);
        inputVBox.setAlignment(Pos.CENTER_LEFT); // 内容左对齐
        inputVBox.getChildren().addAll(accountLabel, account,passwordLabel,password);
        otherHBox.setAlignment(Pos.CENTER_RIGHT); // 内容右对齐
        otherHBox.getChildren().addAll(forgetPasswordBtn, registerBtn);
        // 将HBox添加到VBox中
        mainVBox.getChildren().addAll(titleHBox, inputVBox, otherHBox,loginButton,visitorBtn);

        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root.setBackground(background);

        root.getChildren().add(mainVBox);
        scene=new Scene(root,900,550);;
    }
    /*
    public boolean authenticate(TextField account, PasswordField password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\86189\\IdeaProjects\\javafx\\UserInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(account) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean getAuthenticate(){return authenticate(account, password);}
*/
    //getter and setter
    public Scene getScene(){return this.scene;}

    public Button getForgetPasswordBtn() {
        return forgetPasswordBtn;
    }

    public void setForgetPasswordBtn(Button forgetPasswordButn) {
        this.forgetPasswordBtn = forgetPasswordButn;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(Button registerButn) {
        this.registerBtn = registerButn;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public TextField getAccount() {
        return account;
    }

    public void setAccount(TextField account) {
        this.account = account;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Button getVisitorBtn() {
        return visitorBtn;
    }

    public void setVisitorBtn(Button visitorBtn) {
        this.visitorBtn = visitorBtn;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }



}