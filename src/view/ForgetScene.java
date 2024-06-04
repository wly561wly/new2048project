package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileWriter;
import java.io.IOException;

//这个类用于处理忘记密码后的逻辑
public class ForgetScene {
    VBox mainVBox = new VBox(10); // 垂直间距为10
    Label accountLabel = new Label("账号");
    Label passwordLabel = new Label("请输入新密码");
    Button confirmBtn = new Button("确认");
    Button backBtn = new Button("返回");
    TextField account = new TextField();
    PasswordField password = new PasswordField();
    VBox forgetRoot = new VBox(30); // 竖直间距为30
    Scene scene;
    boolean confirm = false;
    private Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\4.jpg");
    public ForgetScene() {
        mainVBox.setMaxWidth(400);
        forgetRoot.setMaxWidth(400);
        mainVBox.setAlignment(Pos.TOP_CENTER); // 内容顶部居中对齐
        mainVBox.setPadding(new Insets(20, 20, 20, 20)); // 设置内边距
        forgetRoot.setAlignment(Pos.CENTER_LEFT); // 内容左对齐
        forgetRoot.getChildren().addAll(accountLabel, account, passwordLabel, password, confirmBtn, backBtn);
        mainVBox.getChildren().add(forgetRoot);
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        mainVBox.setBackground(background);
        scene = new Scene(mainVBox,900,550);
    }

    public Scene getScene(){
        return scene;
    }

    public VBox getMainVBox() {
        return mainVBox;
    }

    public void setMainVBox(VBox mainVBox) {
        this.mainVBox = mainVBox;
    }

    public Label getAccountLabel() {
        return accountLabel;
    }

    public void setAccountLabel(Label accountLabel) {
        this.accountLabel = accountLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(Label passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public Button getConfirmBtn() {
        return confirmBtn;
    }

    public void setConfirmBtn(Button confirmBtn) {
        this.confirmBtn = confirmBtn;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(Button backBtn) {
        this.backBtn = backBtn;
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

    public VBox getForgetRoot() {
        return forgetRoot;
    }

    public void setForgetRoot(VBox forgetRoot) {
        this.forgetRoot = forgetRoot;
    }
    public boolean getConfirm(){
        return confirm;
    }
}
