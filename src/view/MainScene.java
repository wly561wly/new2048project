package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainScene {
    private Label label= new Label("欢迎来到2048小游戏");
    private Label label_mode1= new Label("经典模式");
    private Label label_mode2= new Label("自定义模式");
    private Label label_mode3= new Label("挑战模式");
    private Scene scene;
    public MainScene()
    {
        label.setLayoutX(100);
        label.setLayoutY(100);
        Font font=new Font("Arial", 24);
        label.setFont(font);
        label_mode1.setLayoutX(200);
        label_mode1.setLayoutY(200);
        label_mode2.setLayoutX(300);
        label_mode2.setLayoutY(300);
        label_mode3.setLayoutX(0);
        label_mode3.setLayoutY(0);
        VBox root = new VBox(10); // 使用VBox并设置间距
        root.setAlignment(Pos.CENTER); // VBox中的内容将垂直居中
        root.setPadding(new Insets(10)); // 设置内边距

        // 将Label添加到VBox中
        root.getChildren().addAll(label, label_mode1, label_mode2, label_mode3);

        // 创建一个Scene，并将root设置为根容器
        this.scene = new Scene(root, 900, 550); // 增大场景尺寸以适应所有标签
    }
    public Scene getScene(){return scene;}
}
