package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameOver {
    private Scene scene;

    private Button restartButn = new Button("重新开始");
    private Button exitButn = new Button("退出");
    private Label settleLabel;
    private Label levelLabel= new Label();
    public GameOver(int p,int scores)
    {
        settleLabel= new Label("本局游戏得分为："+ Integer.toString(scores));
        settleLabel.setLayoutX(101);
        settleLabel.setLayoutY(50);
        levelLabel.setLayoutX(110);
        levelLabel.setLayoutY(100);

        if(scores>=35000){
            levelLabel.setText("你的评分为 SSS");
        }
        else if(scores>=21048){
            levelLabel.setText("你的评分为 SS");
        }
        else if(scores>=12020){
            levelLabel.setText("你的评分为 S");
        }
        else if(scores>=7250){
            levelLabel.setText("你的评分为 S-");
        }
        else if(scores>=5000){
            levelLabel.setText("你的评分为 A+");
        }
        else if(scores>=2050){
            levelLabel.setText("你的评分为 A");
        }
        else if(scores>=1050){
            levelLabel.setText("你的评分为 B+");
        }
        else if(scores>=550){
            levelLabel.setText("你的评分为 B");
        }
        else if(scores>=250){
            levelLabel.setText("你的评分为 C");
        }
        else {
            levelLabel.setText("废物没有评分");
        }
        restartButn.setLayoutX(50);
        restartButn.setLayoutY(200);
        exitButn.setLayoutX(170);
        exitButn.setLayoutY(200);

        Pane overPane=new Pane();
        overPane.getChildren().addAll(settleLabel,levelLabel,restartButn,exitButn);

        if(p==1){
            Label winLabel=new Label("You Win!!!");
            winLabel.setLayoutX(120);
            winLabel.setLayoutY(40);
            settleLabel.setLayoutX(101);
            settleLabel.setLayoutY(70);
            levelLabel.setLayoutX(110);
            levelLabel.setLayoutY(120);
            overPane.getChildren().add(winLabel);
        }


        scene=new Scene(overPane,300,300);
    }
    public Scene getScene(){return scene;}
    public Button getRestartButn(){return restartButn;}
    public Button getExitButn(){return exitButn;}
}
