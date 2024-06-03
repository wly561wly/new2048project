package view;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AI_trainer;
import model.Assess;
import model.ChessNumber;
import model.RankElement;

import java.time.Instant;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class AIscene {
    private int time;
    private int steps;
    private int scores;
    private int x_Count;
    private int y_Count;
    private ChessNumber chessNumber;
    private ChessPane chessPane;
    private Scene scene;
    private int pattern;
    private int running;//表示游戏是否在运行
    private Instant startTime;
    private Label titileLabel=new Label();
    private Label timeLabel=new Label("时间：00:00");//时间轴

    private Label labelStep=new Label("Step: 0");
    private Label labelscores=new Label("Scores: 0");
    private Button pauseBtn=new Button("Pause");
    private Button restartBtn=new Button("Restart");
    private Button exitButton=new Button("Exit");
    // 创建方向按键
    private Image image;
    Assess assess;
    private int[] a;
    AI_trainer ai_trainer=new AI_trainer(0);
    public AIscene(int X_COUNT,int Y_COUNT,String usersName,int pattern,String mode,int choice){
        time=0;
        x_Count=X_COUNT;y_Count=Y_COUNT;

        this.pattern=pattern;
        running=1;
        image= new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\5.jpg");
        if(pattern==1)new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\2.jpg");
        if(pattern==2)new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\4.jpg");
        if(pattern==3)new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\5.jpg");

        titileLabel.setText("模式：无尽模式");

        chessNumber=new ChessNumber(X_COUNT,Y_COUNT,usersName,mode,choice);

        chessPane=new ChessPane(X_COUNT,Y_COUNT,chessNumber.getNumber(),500,pattern);

        //布局

        titileLabel.setLayoutX(580);
        titileLabel.setLayoutY(40);
        timeLabel.setLayoutX(580);
        timeLabel.setLayoutY(90);
        labelStep.setLayoutX(580);
        labelStep.setLayoutY(120);
        labelscores.setLayoutX(580);
        labelscores.setLayoutY(150);

        titileLabel.setFont(new Font(24));
        timeLabel.setFont(new Font(24)); // 设置字体大小
        labelStep.setFont(new Font(24));
        labelscores.setFont(new Font(24));

        // 将方向按键放入BorderPane中
        BorderPane directionButtons= new BorderPane();

//        loadButton.getStyleClass().add("my-button");//  尝试使用Css对按钮改良

        // 将功能按钮放入VBox中
        VBox functionButtons = new VBox(10,pauseBtn,restartBtn,exitButton);
        functionButtons.setAlignment(Pos.TOP_RIGHT); // 顶部右对齐

        //menuBar
        //VBox menuBox =new VBox(10,menubar.getMenuBar());

        // 初始化时间轴
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // 更新显示的时间
            java.time.Duration elapsed = java.time.Duration.between(startTime, Instant.now());

            if(running>0)time++;

            if(mode.equals("challenge")){
                if(choice==time&&running==1)doGameOver(0);
                if(time>choice){
                    time=choice;
                    if(running==1)doGameOver(0);
                }
            }

            long minutes =time/60;
            long seconds =time%60;
            timeLabel.setText(String.format("时间：%02d:%02d", minutes, seconds));

        }));
        // 设置时间轴无限循环
        timeline.setCycleCount(Timeline.INDEFINITE);

        // 开始时间轴之前记录开始时间
        startTime = Instant.now();
        timeline.play();

        // 整体位置布局
        Pane root = new Pane();
        Node ChesPane=chessPane.getGridPane();
        ChesPane.setLayoutX(50);
        ChesPane.setLayoutY(30);
        directionButtons.setLayoutX(650);
        directionButtons.setLayoutY(200);
        functionButtons.setLayoutX(650);
        functionButtons.setLayoutY(300);

        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root.setBackground(background);
        root.getChildren().addAll(titileLabel,timeLabel,ChesPane,directionButtons,functionButtons,labelStep,labelscores);

//        String cssUrl = Objects.requireNonNull(this.getClass().getResource("MyStyle.css")).toExternalForm(); // 假设CSS文件位于项目的根资源文件夹中
//        root.getStylesheets().add(cssUrl); // 将CSS添加到根节点

        // 创建一个场景并设置到舞台上
        scene = new Scene(root, 900, 550);
        restartBtn.setOnAction(event->{
            restartGame();
            StartGameLoop();
        });
        pauseBtn.setOnAction(event->{
            if(running==0){
                running=1;
                StartGameLoop();
                pauseBtn.setText("Pause");
            }
            else {
                running=0;
                pauseBtn.setText("Continue");
            }
        });

        a=ai_trainer.getP()[0];
        StartGameLoop();
    }
    public void StartGameLoop()
    {
        if(running==0)return;
        int[][]save=new int[4][4];
        int ini_scores=chessNumber.getScores();
        int[][]num=chessNumber.getNumber();
        int[] mark={0,0,0,0};
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                save[i][j]=num[i][j];
            }
        }
        int p=0;
        if(chessNumber.moveRight()) {
            assess = new Assess(chessNumber.getNumber(), a, 0);
            mark[0] = assess.getScores();
        }
        else mark[0]=-9999999;

        p=1;chessNumber.setNumber(save);
        if(chessNumber.moveLeft()){
            assess=new Assess(chessNumber.getNumber(),a,1);
            mark[1]=assess.getScores();
        }
        else mark[1]=-9999999;

        p=2;chessNumber.setNumber(save);
        if(chessNumber.moveUp()){
            assess=new Assess(chessNumber.getNumber(),a,2);
            mark[2]=assess.getScores();
        }
        else mark[2]=-9999999;

        p=3;chessNumber.setNumber(save);
        if(chessNumber.moveDown()) {
            assess = new Assess(chessNumber.getNumber(), a, 3);
            mark[3] = assess.getScores();
        }
        else mark[3]=-9999999;

        chessNumber.setNumber(save);
        chessNumber.setScores(ini_scores);
        if(mark[0]>=mark[1]&&mark[0]>=mark[2]&&mark[0]>=mark[3])MoveRight();
        else if(mark[1]>=mark[0]&&mark[1]>=mark[2]&&mark[1]>=mark[3])MoveLeft();
        else if(mark[2]>=mark[0]&&mark[2]>=mark[1]&&mark[2]>=mark[3])MoveUp();
        else MoveDown();
        updateGridsNumber();
        PauseTransition pause = new PauseTransition(Duration.seconds(0.05));//停顿时间
        pause.setOnFinished(event->{
            if(chessNumber.checkGameOver(0,"scores",0,0)==0)StartGameLoop();
        });
        pause.play();
    }

    //更新棋盘，检测游戏是否解释
    public void updateGridsNumber() {
        labelStep.setText("Step: "+Integer.toString(steps));
        labelscores.setText("Scores: "+Integer.toString(chessNumber.getScores()));
        chessPane.ChessPanePaint(chessNumber.getNumber());
        int p=chessNumber.checkGameOver(pattern,"scores",0,time);
        if(p==1)doGameOver(0);
        else if(p==2)doGameOver(1);
    }
    //游戏结束的操作
    public void doGameOver(int p){//p表示经典模式下胜利
        running=0;//停止运行
        GameOver gameover=new GameOver(p,chessNumber.getScores());
        Scene overScene=gameover.getScene();
        Stage overStage=new Stage();
        overStage.initModality(APPLICATION_MODAL);
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

    public Scene getScene(){return scene;}
    public void MoveUp(){
        if(running==0)return;
        if(!chessNumber.moveUp())return;
        steps++;
        System.out.println("Up button clicked");
        //Paint();
    }
    public void MoveDown(){
        if(running==0)return;
        if(!chessNumber.moveDown())return;
        steps++;
        System.out.println("Down button clicked");
        //Paint();
    }
    public void MoveLeft(){
        if(running==0)return;
        if(!chessNumber.moveLeft())return;
        steps++;
        System.out.println("Left button clicked");
        //Paint();
    }
    public void MoveRight(){
        if(running==0)return;
        if(!chessNumber.moveRight())return;
        steps++;
        System.out.println("Right button clicked");
        //Paint();
    }
    public Button getExitButton() {
        return exitButton;
    }
    public ChessNumber getChessNumber() {
        return chessNumber;
    }
    public int getSteps(){return steps;}
    public int getTime(){return time;}

    public ChessPane getChessPane() {
        return chessPane;
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
        time=0;
        running=1;
        if(titileLabel.getTextFill()==Color.RED)titileLabel.setTextFill(Color.BLACK);
        chessNumber.clearNumbers();
        chessNumber.initialNumbers();
        this.updateGridsNumber();
    }
    public void setRunning(int x){
        running=x;
    }
    public void restartGame(int step,ChessNumber chessNum){
        int scores=chessNumber.getScores();
        setRunning(0);
        Label settleLabel= new Label("本局游戏得分为："+ Integer.toString(scores));
        Label levelLabel= new Label();
        Label hintLabel = new Label("你希望保存本局游戏吗");
        settleLabel.setLayoutX(101);
        settleLabel.setLayoutY(50);
        levelLabel.setLayoutX(110);
        levelLabel.setLayoutY(85);
        hintLabel.setLayoutX(111);
        hintLabel.setLayoutY(130);

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
        else if(scores>=5500){
            levelLabel.setText("你的评分为 A+");
        }
        else if(scores>=3550){
            levelLabel.setText("你的评分为 A");
        }
        else if(scores>=1850){
            levelLabel.setText("你的评分为 B+");
        }
        else if(scores>=1050){
            levelLabel.setText("你的评分为 B");
        }
        else if(scores>=550){
            levelLabel.setText("你的评分为 C");
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
            chessNumber.saveNumber(step,1,time);
            restartGame();
            setRunning(1);
            stageGameSettle.close();
        });
        stageGameSettle.setOnCloseRequest(e -> {
            setRunning(1);
        });
        settlePane.setOnMouseClicked(event->{
            restartGame();
            setRunning(1);
            stageGameSettle.close();
        });
        restartButton.setOnAction(event->{
            restartGame();
            setRunning(1);
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
        setRunning(0); //时间暂停
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
        loadStage.setOnCloseRequest(e -> {
            setRunning(1);
        });
        YesButn.setOnAction(Newevent->{
            int Step=steps;
            steps=chessNumber.loadNumber();
            titileLabel.setText("AI 积分模式");
            time=chessNumber.getTime();
            if(x_Count!=chessNumber.getX_COUNT()){
                setX_Count(chessNumber.getX_COUNT());
            }
            this.updateGridsNumber();
            setRunning(1);
            loadStage.close();
            if(steps==-1){
                //需加入继续或者重开的选项
                //主要是想尽可能恢复当前存档
            }
        });
        NoButn.setOnAction(Newevent->{
            setRunning(1);
            loadStage.close();
        });

        loadStage.setTitle("正在玩命加载");
        loadStage.setScene(loadScene);
        loadStage.initModality(APPLICATION_MODAL);
        loadStage.show();
    }
    public void setChessPane(ChessPane pane){chessPane=pane;}
    public void setChessNumber(ChessNumber chessNumber){this.chessNumber=chessNumber;}
    public void setPattern(int x){
        pattern=x;
        if(chessPane!=null)chessPane.setPattern(x);
    }
    public void setX_Count(int x)
    {
        if(x_Count==x)return;
        x_Count=x;
        y_Count=x_Count;
        if(chessNumber.getX_COUNT()!=x)chessNumber=new ChessNumber(x,y_Count,"AI","scores",0);
        if(chessPane.getX_count()!=x)chessPane=new ChessPane(x_Count,y_Count,chessNumber.getNumber(),500,pattern);
    }
    public int getX_Count(){return x_Count;}
}
