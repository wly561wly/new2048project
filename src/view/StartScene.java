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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URI;
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
        /*String source = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\music\\scores.mp3"; // 替换为你的MP3文件路径
        Path path = Paths.get(source);
        URI uri = path.toUri();
        Media media = new Media(uri.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/

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


        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root.setBackground(background);

        root.setAlignment(Pos.CENTER); // VBox中的内容将垂直居中
        root.setPadding(new Insets(10)); // 设置内边距
        root.getChildren().addAll(label, Welcome);// 将Label添加到VBox中
        scene= new Scene(root, 900, 550); // 增大场景尺寸以适应所有标签
    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Scene getScene(){return scene;}
}
