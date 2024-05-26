package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.*;

public class RegisterScene {
    VBox mainVBox = new VBox(10); // 垂直间距为10
    Label accountLabel = new Label("账号");
    Label passwordLabel = new Label("请输入密码");
    Label emailLabel = new Label("请输入邮箱");
    Button confirmBtn = new Button("确认");
    Button backBtn = new Button("返回");
    TextField account = new TextField();
    PasswordField password = new PasswordField();
    TextField email = new TextField();
    VBox forgetRoot = new VBox(30); // 竖直间距为30
    Scene scene = new Scene(mainVBox,900,550);
    public RegisterScene(){
        mainVBox.setAlignment(Pos.TOP_CENTER); // 内容顶部居中对齐
        mainVBox.setPadding(new Insets(20,20,20,20)); // 设置内边距
        forgetRoot.setAlignment(Pos.CENTER_LEFT); // 内容左对齐
        forgetRoot.getChildren().addAll(accountLabel, account,passwordLabel,password,emailLabel,email,confirmBtn,backBtn);
        mainVBox.getChildren().add(forgetRoot);
    }
    //读取
    public boolean authenticate(TextField account, PasswordField password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\86189\\IdeaProjects\\javafx\\UserInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) == null) {
                    return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
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

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public VBox getForgetRoot() {
        return forgetRoot;
    }

    public void setForgetRoot(VBox forgetRoot) {
        this.forgetRoot = forgetRoot;
    }

    public Scene getScene() {
        return scene;
    }

    public void setRegisterScene(Scene registerScene) {
        this.scene = scene;
    }
}
