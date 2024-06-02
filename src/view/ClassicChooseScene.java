package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ClassicChooseScene {
    private Label titlelabel=new Label("经典模式：得到对应的数字即获得胜利");

    private Button btn_mode1= new Button("512");
    private Button btn_mode2= new Button("1024");
    private Button btn_mode3= new Button("2048");
    private Button btn_mode4= new Button("4096");

    private Button exitButton = new Button("Exit");
    private Scene choiceScene;
    private Image image = new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\4.jpg");
    public ClassicChooseScene(){
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
