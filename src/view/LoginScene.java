package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginScene {
    /*
    private Label label= new Label("登录");
    private Label label1= new Label("账号");
    private Label label2= new Label("密码");
    private Label Label3= new Label("忘记密码");
    private Label label4=new Label("注册");
    */
    private Label titleLabel = new Label("登录");
    private Label accountLabel = new Label("账号");
    private Label passwordLabel = new Label("密码");
    private Button forgetPasswordButn = new Button("忘记密码");
    private Button registerButn = new Button("注册");
    Button loginButton = new Button("登录");
    private TextField account = new TextField();
    private PasswordField password = new PasswordField();
    private Scene scene;
    public LoginScene()
    {
        titleLabel.setFont(new Font(20));
        accountLabel.setFont(new Font(16));
        passwordLabel.setFont(new Font(16));
        forgetPasswordButn.setFont(new Font(14));
        registerButn.setFont(new Font(14));
        loginButton.setOnAction(e -> {
            // 在这里处理登录逻辑，比如验证账号和密码
            // 假设验证通过，保存账户数据（这里只是模拟）
           // saveAccountData(accountTextField.getText(), passwordTextField.getText());
        });
        forgetPasswordButn.setOnAction(e -> {
            // 忘记密码
            // saveAccountData(accountTextField.getText(), passwordTextField.getText());
        });
        registerButn.setOnAction(e -> {
            // 注册账号
            // saveAccountData(accountTextField.getText(), passwordTextField.getText());
        });

        // 使用VBox来垂直排列主要的组件
        VBox mainVBox = new VBox(10); // 垂直间距为10
        mainVBox.setAlignment(Pos.TOP_CENTER); // 内容顶部居中对齐
        mainVBox.setPadding(new Insets(20, 20, 20, 20)); // 设置内边距

        // 用于标题的HBox
        HBox titleHBox = new HBox(10); // 水平间距为10
        titleHBox.setAlignment(Pos.CENTER); // 内容居中对齐
        titleHBox.getChildren().add(titleLabel);

        // 用于账号和密码的VBox
        VBox inputHBox = new VBox(30); // 竖直间距为30
        inputHBox.setAlignment(Pos.CENTER_LEFT); // 内容左对齐
        inputHBox.getChildren().addAll(accountLabel, account,passwordLabel,password); // 这里只是放置Label，实际还需要添加输入框

        // 用于“忘记密码”和“注册”的HBox
        HBox otherHBox = new HBox(20); // 水平间距为20
        otherHBox.setAlignment(Pos.CENTER_RIGHT); // 内容右对齐
        otherHBox.getChildren().addAll(forgetPasswordButn, registerButn);

        // 将HBox添加到VBox中
        mainVBox.getChildren().addAll(titleHBox, inputHBox, otherHBox,loginButton);

        // 假设这里有一个StackPane或其他容器来承载VBox
        StackPane root = new StackPane();
        root.getChildren().add(mainVBox);

        scene=new Scene(root,300,400);
    }
    public Scene getScene(){return this.scene;}
    public void saveAccountData()
    {

    }
}
