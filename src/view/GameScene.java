package view;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.ChessNumber;

import java.util.Objects;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class GameScene {
    private int steps;
    private int scores;
    private final int x_Count;
    private final int y_Count;
    private ChessNumber chessNumber;
    private ChessPane chessPane;
    private Scene scene;

    private String userName;

    private Label labelStep=new Label("Step: 0");
    private Label labelscores=new Label("Scores: 0");

    // 创建方向按键
    private Button upButton = new Button("↑");
    private Button downButton = new Button("↓");
    private Button leftButton = new Button("←");
    private Button rightButton = new Button("→");

    // 创建功能按钮
    private Button loadButton = new Button("Load");
    private Button saveButton = new Button("Save");
    private Button restartButton = new Button("Restart");
    private Button menuButton = new Button("Exit");
    public GameScene(int X_COUNT,int Y_COUNT,String usersName,int pattern,String mode,int choice){
        x_Count=X_COUNT;y_Count=Y_COUNT;
        this.userName=usersName;

        chessNumber=new ChessNumber(X_COUNT,Y_COUNT,userName);

        chessPane=new ChessPane(X_COUNT,Y_COUNT,chessNumber.getNumber(),500);

        labelStep.setLayoutX(600);
        labelStep.setLayoutY(50);
        labelscores.setLayoutX(600);
        labelscores.setLayoutY(120);

        // 将方向按键放入BorderPane中
        BorderPane directionButtons= new BorderPane();
        directionButtons.setBottom(downButton);
        directionButtons.setTop(upButton);
        directionButtons.setLeft(leftButton);
        directionButtons.setRight(rightButton);

//        loadButton.getStyleClass().add("my-button");//  尝试使用Css对按钮改良

        // 将功能按钮放入VBox中
        VBox functionButtons = new VBox(10, loadButton, saveButton, restartButton, menuButton);
        functionButtons.setAlignment(Pos.TOP_RIGHT); // 顶部右对齐

        // 整体位置布局
        Pane root = new Pane();
        Node ChesPane=chessPane.getGridPane();
        ChesPane.setLayoutX(50);
        ChesPane.setLayoutY(30);
        directionButtons.setLayoutX(650);
        directionButtons.setLayoutY(200);
        functionButtons.setLayoutX(650);
        functionButtons.setLayoutY(300);
        root.getChildren().addAll(ChesPane,directionButtons,functionButtons,labelStep,labelscores);

//        String cssUrl = Objects.requireNonNull(this.getClass().getResource("MyStyle.css")).toExternalForm(); // 假设CSS文件位于项目的根资源文件夹中
//        root.getStylesheets().add(cssUrl); // 将CSS添加到根节点

        // 创建一个场景并设置到舞台上
        scene = new Scene(root, 900, 550);

        //使这些按钮失去键盘导航 的焦点
        upButton.setFocusTraversable(false);
        leftButton.setFocusTraversable(false);
        rightButton.setFocusTraversable(false);
        downButton.setFocusTraversable(false);
        loadButton.setFocusTraversable(false);
        restartButton.setFocusTraversable(false);
        menuButton.setFocusTraversable(false);
        saveButton.setFocusTraversable(false);

        upButton.setOnAction(event -> {
            MoveUp();
            this.updateGridsNumber();
        });

        downButton.setOnAction(event -> {
            MoveDown();
            this.updateGridsNumber();
        });

        leftButton.setOnAction(event -> {
            MoveLeft();
            this.updateGridsNumber();
        });

        rightButton.setOnAction(event -> {
            MoveRight();
            this.updateGridsNumber();
        });

        restartButton.setOnAction(event -> {
            restartGame(steps,chessNumber);
            System.out.println("Do RestartGame here!");
        });

        saveButton.setOnAction(event ->{
            System.out.println("Do SaveGame here!");
            chessNumber.saveNumber(steps);
            this.updateGridsNumber();
        });

        loadButton.setOnAction(event ->{
            LoadGame();
            System.out.println("Do LoadGame here!");
        });

        //键盘操作
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                case UP:
                    MoveUp();
                    this.updateGridsNumber();
                    break;
                case S:
                case DOWN:
                    MoveDown();
                    this.updateGridsNumber();
                    break;
                case A:
                case LEFT:
                    MoveLeft();
                    this.updateGridsNumber();
                    break;
                case D:
                case RIGHT:
                    MoveRight();
                    this.updateGridsNumber();
                    break;
                default:
                    break;
            }
        });
    }

    public void updateGridsNumber() {
        labelStep.setText("Step: "+Integer.toString(steps));
        labelscores.setText("Scores: "+Integer.toString(chessNumber.getScores()));
        chessPane.ChessPanePaint(chessNumber.getNumber());
        if(chessNumber.checkGameOver()){
            GameOver gameover=new GameOver(chessNumber.getScores());
            Scene overScene=gameover.getScene();
            Stage overStage=new Stage();

            gameover.getRestartButn().setOnAction(event ->{
                restartGame();
                overStage.close();
            });
            gameover.getExitButn().setOnAction(event ->{
                overStage.close();
            });

            overStage.setTitle("Game Over");
            overStage.setScene(overScene);
            overStage.show();
        }
    }

    public Scene getScene(){return scene;}
    public void MoveUp(){
        if(!chessNumber.moveUp())return;
        steps++;
        System.out.println("Up button clicked");
        //Paint();
    }
    public void MoveDown(){
        if(!chessNumber.moveDown())return;
        steps++;
        System.out.println("Down button clicked");
        //Paint();
    }
    public void MoveLeft(){
        if(!chessNumber.moveLeft())return;
        steps++;
        System.out.println("Left button clicked");
        //Paint();
    }
    public void MoveRight(){
        if(!chessNumber.moveRight())return;
        steps++;
        System.out.println("Right button clicked");
        //Paint();
    }
    public void Paint()
    {
        for(int i=0;i<x_Count;i++)
        {
            for(int j=0;j<y_Count;j++)
                System.out.printf(" %d",chessNumber.getNumber(i,j));
            System.out.println();
        }
    }
    public void restartGame()
    {
        steps=0;
        chessNumber.clearNumbers();
        chessNumber.initialNumbers();
        this.updateGridsNumber();
    }
    public void restartGame(int step,ChessNumber chessNum){
        int scores=chessNumber.getScores();
        Label settleLabel= new Label("本局游戏得分为："+ Integer.toString(scores));
        Label levelLabel= new Label();
        Label hintLabel = new Label("你希望保存本局游戏吗");
        settleLabel.setLayoutX(101);
        settleLabel.setLayoutY(50);
        levelLabel.setLayoutX(110);
        levelLabel.setLayoutY(85);
        hintLabel.setLayoutX(111);
        hintLabel.setLayoutY(130);

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

        Button saveAndRestart = new Button("Save and Restart");
        Button restartButton = new Button("I can't wait");
        saveAndRestart.setLayoutX(50);
        saveAndRestart.setLayoutY(200);
        restartButton.setLayoutX(170);
        restartButton.setLayoutY(200);

        Pane settlePane=new Pane();
        settlePane.getChildren().addAll(settleLabel,levelLabel,hintLabel,saveAndRestart,restartButton);

        Scene settleScene=new Scene(settlePane,300,300);
        Stage stageGameSettle= new Stage();

        saveAndRestart.setOnAction(event ->{
            chessNumber.saveNumber(step,chessNum.getNumber());
            restartGame();
            stageGameSettle.close();
        });
        settlePane.setOnMouseClicked(event->{
            restartGame();
            stageGameSettle.close();
        });
        restartButton.setOnAction(event->{
            restartGame();
            stageGameSettle.close();
        });
        stageGameSettle.setTitle("Restart Game");
        stageGameSettle.setScene(settleScene);
        stageGameSettle.initModality(APPLICATION_MODAL);
        stageGameSettle.show();
    }
    public void LoadGame()
    {
        //加入一个提醒界面
        Label hintlabel=new Label("你确定要加载存档吗（可能会丢失当前游戏进度）");
        Button YesButn=new Button("Yes");
        Button NoButn=new Button("No");
        hintlabel.setLayoutX(25);
        hintlabel.setLayoutY(100);
        YesButn.setLayoutX(70);
        YesButn.setLayoutY(190);
        NoButn.setLayoutX(150);
        NoButn.setLayoutY(190);

        Pane loadPane=new Pane();
        loadPane.getChildren().addAll(hintlabel,YesButn,NoButn);
        Scene loadScene=new Scene(loadPane,300,300);
        Stage loadStage=new Stage();

        YesButn.setOnAction(Newevent->{
            int Step=steps;
            steps=chessNumber.loadNumber();
            this.updateGridsNumber();
            loadStage.close();
            if(steps==-1){
                //需加入继续或者重开的选项
            }
        });
        NoButn.setOnAction(Newevent->{
            loadStage.close();
        });

        loadStage.setTitle("正在玩命加载");
        loadStage.setScene(loadScene);
        loadStage.initModality(APPLICATION_MODAL);
        loadStage.show();
    }
}
