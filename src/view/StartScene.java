package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;



public class StartScene {
    private Label label= new Label("欢迎来到2048妙妙屋");
    private Label Welcome = new Label("点击屏幕任意位置开始游戏");
    VBox root = new VBox(10); // 使用VBox并设置间距
    private Scene scene = new Scene(root, 900, 550); // 增大场景尺寸以适应所有标签

    LoginScene loginScene = new LoginScene();


    public StartScene() {
        label.setLayoutX(100);
        label.setLayoutY(100);
        Font font=new Font("Arial", 24);//设置字体
        label.setFont(font);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Welcome.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(Welcome.opacityProperty(), 0.2))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // 设置无限循环
        timeline.setAutoReverse(true); // 设置自动反向播放
        // 启动动画
        timeline.play();

        root.setAlignment(Pos.CENTER); // VBox中的内容将垂直居中
        root.setPadding(new Insets(10)); // 设置内边距
        root.getChildren().addAll(label, Welcome);// 将Label添加到VBox中

    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Scene getScene(){return scene;}
}
