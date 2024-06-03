package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class Help {
    Label indexlabel = new Label("目录");
    Button outlook = new Button("游玩");
    Button classic = new Button("经典模式玩法");
    Button challenge = new Button("挑战模式玩法");
    Button infinite = new Button("无尽模式玩法");
    Button key = new Button("按键");
    Button other =new Button("其他");
    Font font1 = new Font("Times New Roman",16);
    Font font2 = new Font("Helvetica",14);
    AnchorPane root = new AnchorPane();
    Stage stage = new Stage();
    public Help(){
        indexlabel.setFont(font1);
        outlook.setFont(font2);
        classic.setFont(font2);
        challenge.setFont(font2);
        key.setFont(font2);
        other.setFont(font2);
        VBox index =new VBox();
        index.setAlignment(Pos.CENTER_LEFT);
        index.setSpacing(10);
        index.setPadding(new Insets(10));
        index.getChildren().addAll(outlook,classic,challenge,infinite,key);

        VBox root1 = new VBox();
        root1.setAlignment(Pos.TOP_LEFT);
        root1.setPadding(new Insets(15));
        root1.setSpacing(20);
        root1.getChildren().addAll(indexlabel,index);
        root.getChildren().addAll(root1);

        outlook.setOnAction(event1 -> {
            TextArea t1 = new TextArea(
                    "游戏默认在一个4x4的网格上进行，\n\n" +
                            "游戏开始时，面板上会有两个随机位置的数字方块2和4。\n\n" +
                            "玩家可以用鼠标点击网格左边的的“↑”“↓”“←”“→”控件，\n\n" +
                            "或键盘上的箭头键和“W”“S”“A”“D”键来移动滑块。\n\n" +
                            "每次移动都会导致所有滑块向一个方向移动，\n\n" +
                            "直到不能再移动为止。移动的方向有上、下、左、右四个。\n\n" +
                            "每次移动后，面板上会随机位置生成一个新的数字方块，\n\n" +
                            "这个数字通常是2，偶尔是4。\n\n" +
                            "当相同数字的滑块在移动过程中碰撞时，\n\n" +
                            "它们会合并成一个滑块，其数字是原来两个数字的和。\n\n" +
                            "例如，两个“2”滑块合并成一个“4”滑块。\n\n" +
                            "每次合并滑块都会增加玩家的得分。\n\n" +
                            "当面板上没有空位，且无法再进行合并时，游戏结束。");
            t1.setEditable(false); // 文本区域不可编辑
            t1.setFont(new Font("Times New Roman", 15));
            t1.setPrefSize(400, 500);
            t1.setLayoutX(180);
            t1.setLayoutY(30);
            root.getChildren().add(t1);
        });

        classic.setOnAction(event -> {
            TextArea t1 = new TextArea(
                    "最经典的2048玩法，在进入该模式前，玩家可以选择目标合成块\n\n" +
                            "如“2048”“1024”\n\n" +
                            "当玩家合成出满足要求的滑块时，即可赢得游戏胜利");
            t1.setEditable(false); // 文本区域不可编辑
            t1.setFont(new Font("Times New Roman", 15));
            t1.setPrefSize(400, 500);
            t1.setLayoutX(180);
            t1.setLayoutY(30);
            root.getChildren().add(t1);
        });

        challenge.setOnAction(event -> {
            TextArea t1 = new TextArea(
                            "在限时模式中，玩家有一个固定的时间限制，在进入模式时选择。\n\n" +
                            "玩家可能需要在一个固定的时间内尽可能地达到最高分数。\n\n" +
                            "由于时间限制，玩家需要快速思考并做出决策。\n\n" +
                            "这不仅要求玩家有良好的策略，还要求玩家能够迅速地执行这些策略。\n\n" +
                            "当时间耗尽时，游戏结束。玩家的最终得分取决于\n\n" +
                            "他们在有限时间内能够达到的分数");
            t1.setEditable(false); // 文本区域不可编辑
            t1.setFont(new Font("Times New Roman", 15));
            t1.setPrefSize(400, 500);
            t1.setLayoutX(180);
            t1.setLayoutY(30);
            root.getChildren().add(t1);
        });

        infinite.setOnAction(event -> {
            TextArea t1 = new TextArea("在无尽模式中，玩家不必担心游戏板被填满，\n\n" +
                    "因为即使面板被填满，游戏也不会结束。\n\n" +
                    "玩家可以通过继续合并数字来清空空间，继续游戏。\n\n" +
                    "由于游戏没有结束的限制，玩家的目标\n\n" +
                    "是尽可能达到更高的分数。\n\n" +
                    "在无尽模式中，策略变得更加重要，因为玩家需要\n\n" +
                    "考虑长期的布局和合并计划，而不仅仅是达到2048。\n\n" +
                    "随着游戏的进行，游戏板上的数字会越来越大，\n\n" +
                    "合并的机会也会越来越难，因此游戏的难度会逐渐增加。");
            t1.setEditable(false); // 文本区域不可编辑
            t1.setFont(new Font("Times New Roman", 15));
            t1.setPrefSize(400, 500);
            t1.setLayoutX(180);
            t1.setLayoutY(30);
            root.getChildren().add(t1);
        });

        key.setOnAction(event -> {
            TextArea t1 = new TextArea(
                        "玩家可以用鼠标点击网格左边的的“↑”“↓”“←”“→”控件，\n\n" +
                                "或键盘上的箭头键和“W”“S”“A”“D”键来移动滑块");
            t1.setEditable(false); // 文本区域不可编辑
            t1.setFont(new Font("Times New Roman", 15));
            t1.setPrefSize(400, 500);
            t1.setLayoutX(180);
            t1.setLayoutY(30);
            root.getChildren().add(t1);
        });

        Scene scene = new Scene(root,600,600);
        stage.initModality(APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("帮助");
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
