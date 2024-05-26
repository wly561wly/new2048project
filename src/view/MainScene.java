package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainScene {
    private Label label= new Label("欢迎来到2048妙妙屋");
    private Button btn_mode1= new Button("经典模式");
    private Button btn_mode2= new Button("自定义模式");
    private Button btn_mode3= new Button("挑战模式");

    private Button btn_help= new Button("帮助");
    private Button btn_setting=new Button("设置");
    private Button btn_back = new Button("返回标题界面");
    private Button btn_back2 = new Button("返回登录界面");
    VBox root = new VBox(10); // 使用VBox并设置间距
    private Scene scene = new Scene(root, 900, 550); // 增大场景尺寸以适应所有标签
    private double height;
    private double width;

    public MainScene() {
        label.setLayoutX(70);
        label.setLayoutY(70);
        Font font=new Font("Arial", 24);
        label.setFont(font);
        btn_mode1.setLayoutX(140);
        btn_mode1.setLayoutY(140);
        btn_mode2.setLayoutX(210);
        btn_mode2.setLayoutY(210);
        btn_mode3.setLayoutX(0);
        btn_mode3.setLayoutY(0);
        btn_help.setLayoutX(280);
        btn_help.setLayoutY(280);
        btn_setting.setLayoutX(350);
        btn_setting.setLayoutY(350);
        btn_back.setLayoutX(420);
        btn_back.setLayoutY(420);
        btn_back.setLayoutX(500);
        btn_back2.setLayoutY(500);
        root.setAlignment(Pos.CENTER); // VBox中的内容将垂直居中
        root.setPadding(new Insets(10)); // 设置内边距
        // 将Label添加到VBox中
        root.getChildren().addAll(label, btn_mode1, btn_mode2, btn_mode3,btn_help,btn_setting,btn_back,btn_back2);

    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Button getBtn_mode1() {
        return btn_mode1;
    }

    public void setBtn_mode1(Button btn_mode1) {
        this.btn_mode1 = btn_mode1;
    }

    public Button getBtn_mode2() {
        return btn_mode2;
    }

    public void setBtn_mode2(Button btn_mode2) {
        this.btn_mode2 = btn_mode2;
    }

    public Button getBtn_mode3() {
        return btn_mode3;
    }

    public void setBtn_mode3(Button btn_mode3) {
        this.btn_mode3 = btn_mode3;
    }
    public Button getBtn_help() {
        return btn_help;
    }

    public Button getBtn_setting() {
        return btn_setting;
    }

    public Button getBtn_back() {
        return btn_back;
    }

    public void setBtn_back(Button btn_back) {
        this.btn_back = btn_back;
    }

    public Button getBtn_back2() {
        return btn_back2;
    }

    public void setBtn_back2(Button btn_back2) {
        this.btn_back2 = btn_back2;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    public Scene getScene(){return scene;}
}
