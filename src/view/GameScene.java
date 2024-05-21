package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ChessNumber;

public class GameScene {
    private int steps;
    private int scores;
    private int x_Count;
    private int y_Count;
    private ChessNumber chessNumber;
    private ChessPane chessPane;
    Scene scene;
    public GameScene(int X_COUNT,int Y_COUNT){
        x_Count=X_COUNT;y_Count=Y_COUNT;
        chessNumber=new ChessNumber(X_COUNT,Y_COUNT);
        chessPane=new ChessPane(X_COUNT,Y_COUNT,chessNumber.getNumber(),500);
        Label labelStep=new Label();
        Label labelscroe=new Label();

        // 创建方向按键
        Button upButton = new Button("↑");
        Button downButton = new Button("↓");
        Button leftButton = new Button("←");
        Button rightButton = new Button("→");

        // 将方向按键放入HBox中
        HBox directionButtons = new HBox(10, leftButton, rightButton, upButton, downButton);
        directionButtons.setAlignment(Pos.BOTTOM_CENTER); // 底部居中对齐

        // 创建功能按钮
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button restartButton = new Button("Restart");
        Button menuButton = new Button("Menu");

        // 将功能按钮放入VBox中
        VBox functionButtons = new VBox(10, loadButton, saveButton, restartButton, menuButton);
        functionButtons.setAlignment(Pos.TOP_RIGHT); // 顶部右对齐

        // 使用BorderPane组合所有元素
        BorderPane root = new BorderPane();
        root.setLeft(chessPane.getGridPane()); // GridPane放在左边
        root.setBottom(directionButtons); // 方向按键放在底部
        root.setRight(functionButtons); // 功能按钮放在右上角

        // 创建一个场景并设置到舞台上
        scene = new Scene(root, 900, 550);

        //无用操作
        upButton.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        // 当按钮失去焦点时，将焦点转移到下一个按钮
                        if (downButton.isFocused()) {
                            leftButton.requestFocus();
                        } else {
                            rightButton.requestFocus();
                        }
                    }
                })
        ;

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
        chessPane.ChessPanePaint(chessNumber.getNumber());
    }

    public Scene getScene(){return scene;}
    public void MoveUp(){
        if(!chessNumber.moveUp())return;
        steps++;
        System.out.println("Up button clicked");
        Paint();
    }
    public void MoveDown(){
        if(!chessNumber.moveDown())return;
        steps++;
        System.out.println("Down button clicked");
        Paint();
    }
    public void MoveLeft(){
        if(!chessNumber.moveLeft())return;
        steps++;
        System.out.println("Left button clicked");
        Paint();
    }
    public void MoveRight(){
        if(!chessNumber.moveRight())return;
        steps++;
        System.out.println("Right button clicked");
        Paint();
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
}
