package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ChallengeChooseScene {
    private Label titlelabel=new Label("挑战模式：在固定的时间内获取更高的分数");
    private Button btn_mode1= new Button("30s");
    private Button btn_mode2= new Button("60s");
    private Button btn_mode3= new Button("90s");
    private Button btn_mode4= new Button("120s");

    private Button exitButton = new Button("Exit");
    private Scene choiceScene;
    private Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\5.jpg");
    public ChallengeChooseScene(){
        titlelabel.setLayoutX(250);
        titlelabel.setLayoutY(100);
        Font font=new Font("Arial", 24);
        titlelabel.setFont(font);
        btn_mode1.setLayoutX(400);
        btn_mode1.setLayoutY(250);
        btn_mode2.setLayoutX(400);
        btn_mode2.setLayoutY(300);
        btn_mode3.setLayoutX(400);
        btn_mode3.setLayoutY(350);
        btn_mode4.setLayoutX(400);
        btn_mode4.setLayoutY(400);
        exitButton.setLayoutX(400);
        exitButton.setLayoutY(450);
        Pane pane=new Pane();
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        pane.setBackground(background);
        pane.getChildren().addAll(btn_mode1,btn_mode2,btn_mode3,btn_mode4,titlelabel,exitButton);
        choiceScene=new Scene(pane,900,550);
    }
    public Scene getChoiceScene(){return choiceScene;}
    public Button getBtn_mode1() {
        return btn_mode1;
    }
    public Button getBtn_mode2() {
        return btn_mode2;
    }
    public Button getBtn_mode3() {
        return btn_mode3;
    }
    public Button getBtn_mode4() {
        return btn_mode4;
    }
    public Button getExitButton() {
        return exitButton;
    }
}
