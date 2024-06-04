package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
import java.util.Random;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class GameScene {
    private int time;//时间
    private int steps;//步数
    private int scores;
    private int x_Count;
    private int y_Count;
    private MenuBar menubar = new MenuBar();
    private ChessNumber chessNumber;
    private ChessPane chessPane;
    private Scene scene;
    private int pattern;
    private String mode;
    private int choice;
    private int running;//表示游戏是否在运行
    private String userName;
    private boolean autosave;
    private Instant startTime;//开始时间
    private RankList rankList; //排行榜
    private Label titileLabel=new Label();
    private Label timeLabel=new Label("时间：00:00");//时间轴

    private Label labelStep=new Label("步数: 0");
    private Label labelscores=new Label("分数: 0");
    private Label Hintlabel = new Label();

    // 创建方向按键
    private Button upButton = new Button("↑");
    private Button downButton = new Button("↓");
    private Button leftButton = new Button("←");
    private Button rightButton = new Button("→");

    // 创建功能按钮
    private Button loadButton = new Button("加载存档");
    private Button saveButton = new Button("保存游戏");
    private Button restartButton = new Button("重新开始");
    private Button rankButton = new Button("排行榜");
    private Button exitButton = new Button("退出");
    private Pane root = new Pane();
    private Node ChesPane;  //棋盘node
    private Image image;  //背景图片
    public GameScene(int X_COUNT,int Y_COUNT,String usersName,int pattern,String mode,int choice,boolean AutoSave){
        time=0;
        x_Count=X_COUNT;y_Count=Y_COUNT;

        this.userName=usersName;
        this.pattern=pattern;
        this.mode=mode;
        this.choice=choice;
        this.autosave=AutoSave;
        this.rankList=new RankList(mode);
        running=1;
        image= new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\5.jpg");
        if(pattern==1)image=new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\7.jpg");
        //if(pattern==2)image=new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\5.png");
        if(pattern==3)image=new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\6.jpg");
        if(pattern==4)image=new Image("file:C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\image\\4.jpg");

        if(mode.equals("classic"))titileLabel.setText("模式：经典模式 "+String.valueOf(choice)+"场");
        else if(mode.equals("challenge"))titileLabel.setText("模式：挑战模式 "+String.valueOf(choice)+"s 场");
        else titileLabel.setText("模式：无尽模式");

        chessNumber=new ChessNumber(X_COUNT,Y_COUNT,userName,mode,choice);

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
        Hintlabel.setFont(new Font(35));

        leftButton.setPrefSize(30, 30);
        rightButton.setPrefSize(30, 30);
        upButton.setPrefSize(30, 30);
        downButton.setPrefSize(30, 30);
        HBox h1 = new HBox(0,leftButton,downButton,rightButton);
        VBox directionButtons = new VBox(0,upButton,h1,Hintlabel);
        directionButtons.setAlignment(Pos.CENTER);

//        loadButton.getStyleClass().add("my-button");//  尝试使用Css对按钮改良
//        restartButton.setStyle("-fx-background-color: #00eaff");
//        restartButton.setPrefSize(70, 40);
//        saveButton.setStyle("-fx-background-color: #00eaff");
//        saveButton.setPrefSize(70, 40);
//        exitButton.setStyle("-fx-background-color: #00eaff");
//        exitButton.setPrefSize(70, 40);
//        loadButton.setStyle("-fx-background-color: #00eaff");
//        loadButton.setPrefSize(70, 40);
//        rankButton.setStyle("-fx-background-color: #00eaff");
//        rankButton.setPrefSize(70, 40);

        restartButton.setPrefSize(70, 40);

        saveButton.setPrefSize(70, 40);

        exitButton.setPrefSize(70, 40);

        loadButton.setPrefSize(70, 40);

        rankButton.setPrefSize(70, 40);

        // 将功能按钮放入VBox中
        VBox functionButtons = new VBox(10, loadButton, saveButton, restartButton, rankButton, exitButton);
        functionButtons.setAlignment(Pos.TOP_RIGHT); // 顶部右对齐

        //menuBar
        VBox menuBox =new VBox(10,menubar.getMenuBar());

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

            // 仅测试Rank
            //if(time==5&&running==1)doGameOver();

            //增加红色提醒
            if(mode.equals("challenge")){
                if(choice-time<=10){
                    timeLabel.setTextFill(Color.RED);
                }
                if(choice==time&&running==1)doGameOver(0);
                if(time>choice){
                    time=choice;
                    doGameOver(0);
                }
            }
            // autoSave();
            if(AutoSave==true){
                if(seconds==15||seconds==45){
                    if(userName.equals("Visitor_1231")){
                        System.out.println("Visitor Don't have save");
                    }
                    else {
                        updateRank();
                        chessNumber.saveNumber(steps,0,time);
                    }
                }
            }
        }));
        // 设置时间轴无限循环
        timeline.setCycleCount(Timeline.INDEFINITE);

        // 开始时间轴之前记录开始时间
        startTime = Instant.now();
        timeline.play();

        // 整体位置布局
        ChesPane=chessPane.getGridPane();
        ChesPane.setLayoutX(50);
        ChesPane.setLayoutY(30);
        directionButtons.setLayoutX(650);
        directionButtons.setLayoutY(200);
        functionButtons.setLayoutX(650);
        functionButtons.setLayoutY(300);

        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root.setBackground(background);
        root.getChildren().addAll(titileLabel,timeLabel,menuBox,ChesPane,directionButtons,functionButtons,labelStep,labelscores);

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
        exitButton.setFocusTraversable(false);
        saveButton.setFocusTraversable(false);
        rankButton.setFocusTraversable(false);

        //添加按钮的监听
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

        loadButton.setOnAction(event ->{
            if(userName.equals("Visitor_1231")){
                System.out.println("Visitor Don't have save");
            }
            else {
                LoadGame();
                System.out.println("Do LoadGame here!");
            }
        });
        saveButton.setOnAction(event ->{
            if(userName.equals("Visitor_1231")){
                System.out.println("Visitor Don't have save");
            }
            else {
                updateRank();
                System.out.println("Do SaveGame here!");
                chessNumber.saveNumber(steps,1,time);
                this.updateGridsNumber();
            }
        });
        restartButton.setOnAction(event -> {
            restartGame(steps,chessNumber);
            System.out.println("Do RestartGame here!");
        });
        rankButton.setOnAction(event->{
            ShowRank();
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

        //菜单栏操作
        getMenubar().getRestartItem().setOnAction(event -> {
            restartGame(steps,chessNumber);
            System.out.println("Do RestartGame here!");
        });
        getMenubar().getSave().setOnAction(event ->{  //保存操作
            if(userName.equals("Visitor_1231")){
                System.out.println("Visitor Don't have save");
            }
            else {
                updateRank();
                System.out.println("Do SaveGame here!");
                chessNumber.saveNumber(steps,1,time);
                this.updateGridsNumber();
            }
        });
        getMenubar().getMenuback().setOnAction(event->{  //撤销操作
            if(chessNumber.doTheBack()) {
                steps--;
                this.updateGridsNumber();
            }
        });
        getMenubar().getMenuHint().setOnAction(event->{  //提示操作
            doTheHint();
        });

    }
    //更新棋盘，检测游戏是否解释
    public void updateGridsNumber() {
        labelStep.setText("步数: "+Integer.toString(steps));
        labelscores.setText("分数: "+Integer.toString(chessNumber.getScores()));
        chessPane.ChessPanePaint(chessNumber.getNumber());
        int p=chessNumber.checkGameOver(pattern,mode,choice,time);
        if(p==1)doGameOver(0);
        else if(p==2)doGameOver(1);

    }
    //游戏结束的操作
    public void doGameOver(int p){
        running=0;//停止运行
        updateRank();
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

        overStage.setTitle("游戏结束");
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
    public int getChoice(){return choice;}

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

        Button saveAndRestart = new Button("保存并重新开始");
        Button restartButton = new Button("直接重开！");
        saveAndRestart.setLayoutX(50);
        saveAndRestart.setLayoutY(200);
        restartButton.setLayoutX(170);
        restartButton.setLayoutY(200);

        Pane settlePane=new Pane();
        settlePane.getChildren().addAll(settleLabel,levelLabel,hintLabel,saveAndRestart,restartButton);

        Scene settleScene=new Scene(settlePane,300,300);
        Stage stageGameSettle= new Stage();

        saveAndRestart.setOnAction(event ->{
            if(userName.equals("Visitor_1231")){
                System.out.println("Visitor Don't have save");
            }
            else {
                updateRank();
                chessNumber.saveNumber(step,1,time);
                restartGame();
            }
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
            if(steps==0){
                steps=Step;
                loadStage.close();
                setRunning(1);
                return;
            }
            time=chessNumber.getTime();
            if(choice!=chessNumber.getChoice()){
                choice=chessNumber.getChoice();
                if(mode.equals("classic"))titileLabel.setText("模式：经典模式 "+String.valueOf(choice)+"场");
                else if(mode.equals("challenge"))titileLabel.setText("模式：挑战模式 "+String.valueOf(choice)+"s 场");
            }
            if(x_Count!=chessNumber.getX_COUNT()){
                setX_Count(chessNumber.getX_COUNT());
            }
            this.updateGridsNumber();
            setRunning(1);
            loadStage.close();
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
    public void setUserName(String s)
    {
        userName=s;
        chessNumber.setUserName(s);
    }
    public void setChessPane(ChessPane pane){chessPane=pane;}
    public void setChessNumber(ChessNumber chessNumber){this.chessNumber=chessNumber;}
    public String getMode(){return mode;}
    public void setAutosave(boolean x){
        autosave=x;
    }
    public void setPattern(int x){
        pattern=x;
        if(chessPane!=null)chessPane.setPattern(x);
        updateGridsNumber();
    }
    public void setX_Count(int x)
    {
        if(x_Count==x)return;
        x_Count=x;
        y_Count=x_Count;
        if(chessNumber.getX_COUNT()!=x)chessNumber=new ChessNumber(x_Count,y_Count,userName,mode,choice);
        if(chessPane.getX_count()!=x){
            root.getChildren().remove(ChesPane);
            chessPane=new ChessPane(x_Count,y_Count,chessNumber.getNumber(),500,pattern);
            ChesPane=chessPane.getGridPane();
            ChesPane.setLayoutX(50);
            ChesPane.setLayoutY(30);
            root.getChildren().add(ChesPane);
        }
        updateGridsNumber();
    }
    public int getX_Count(){return x_Count;}
    public MenuBar getMenubar() {
        return menubar;
    }
    public void ShowRank()
    {
        setRunning(0);
        if(mode.equals("classic"))rankList=new RankList(mode);
        else rankList=new RankList(mode);
        Stage stage=new Stage();
        stage.setTitle("Rank");
        stage.setScene(rankList.getScene());
        stage.show();
        stage.setOnCloseRequest(e -> {
            setRunning(1);
        });
    }
    public void updateRank()
    {
        if(userName.equals("Visitor_1231"))return;
        if(x_Count==4)rankList.WriteFile(mode,new RankElement(userName,steps,chessNumber.getScores(),time));
        rankList.DoSort();
    }

    public void doTheHint() //提示，选择一个方向
    {
        if(x_Count!=4){

            return;
        }
        else {
            //针对网格大小为4的
            Random random=new Random();
            AI_trainer aiTrainer=new AI_trainer(0);
            Assess assess;//评估函数
            int[] a=aiTrainer.getP()[(random.nextInt(10)+10)%10];//随机选择一个参数
            int ini_scores=chessNumber.getScores();
            int[][]num=chessNumber.getNumber();
            int[] mark={0,0,0,0};
            int[][]save=new int[4][4];
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
            if(mark[0]>=mark[1]&&mark[0]>=mark[2]&&mark[0]>=mark[3])Hintlabel.setText("→");
            else if(mark[1]>=mark[0]&&mark[1]>=mark[2]&&mark[1]>=mark[3])Hintlabel.setText("←");
            else if(mark[2]>=mark[0]&&mark[2]>=mark[1]&&mark[2]>=mark[3])Hintlabel.setText("↑");
            else Hintlabel.setText("↓");

            //设置动画
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(Hintlabel.opacityProperty(), 1.0)),
                    new KeyFrame(Duration.seconds(5), new KeyValue(Hintlabel.opacityProperty(), 0))
            );
            //timeline.setCycleCount(Timeline.INDEFINITE); // 设置无限循环
            //timeline.setAutoReverse(true); // 设置自动反向播放
            // 启动动画
            timeline.play();

        }
    }
}
