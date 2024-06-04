package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StartScene {
    private Label label= new Label("欢迎来到2048妙妙屋");
    private Label Welcome = new Label("点击屏幕任意位置开始游戏");
    VBox root = new VBox(10); // 使用VBox并设置间距
    private Scene scene;

    LoginScene loginScene = new LoginScene();
    private Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\6.jpg");

    public StartScene() {

/*        URL uri = this.getClass().getClassLoader().getResource("music/win.mp3");
        AudioClip m1=new AudioClip(uri.toString());
        m1.play();*/

        label.setLayoutX(100);
        label.setLayoutY(100);
        Font font=new Font("Mini Pixel-7", 30);//设置字体
        label.setFont(font);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Welcome.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(Welcome.opacityProperty(), 0.2))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // 设置无限循环
        timeline.setAutoReverse(true); // 设置自动反向播放
        // 启动动画
        timeline.play();


        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root.setBackground(background);

        root.setAlignment(Pos.CENTER); // VBox中的内容将垂直居中
        root.setPadding(new Insets(10)); // 设置内边距
        root.getChildren().addAll(label, Welcome);// 将Label添加到VBox中
        scene= new Scene(root, 900, 550); // 增大场景尺寸以适应所有标签

        label.setStyle("-fx-text-fill: #003964;");
        Welcome.setStyle("-fx-background-color: #00eaff ");
    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Scene getScene(){return scene;}
}
