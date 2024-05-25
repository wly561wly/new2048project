package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameOver {
    private Scene scene;

    private Button restartButn = new Button("Restart");
    private Button exitButn = new Button("Exit");
    private Label settleLabel;
    private Label levelLabel= new Label();
    public GameOver(int scores)
    {
        settleLabel= new Label("本局游戏得分为："+ Integer.toString(scores));
        settleLabel.setLayoutX(101);
        settleLabel.setLayoutY(50);
        levelLabel.setLayoutX(110);
        levelLabel.setLayoutY(100);

        if(scores>=5000){
            levelLabel.setText("你的评分为 SSS");
        }
        else if(scores>=2548){
            levelLabel.setText("你的评分为 SS");
        }
        else if(scores>=1050){
            levelLabel.setText("你的评分为 S");
        }
        else if(scores>=512){
            levelLabel.setText("你的评分为 A+");
        }
        else if(scores>=256){
            levelLabel.setText("你的评分为 A");
        }
        else if(scores>=100){
            levelLabel.setText("你的评分为 B");
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

        scene=new Scene(overPane,300,300);
    }
    public Scene getScene(){return scene;}
    public Button getRestartButn(){return restartButn;}
    public Button getExitButn(){return exitButn;}
}
