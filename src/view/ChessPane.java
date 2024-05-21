package view;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.layout.StackPane.setAlignment;

public class ChessPane {
    int [][] grid;
    private GridPane gridPane;
    public ChessPane(int x_Count,int y_Count,int [][]num, int gridSize) {
        gridPane = new GridPane();
        grid = new int[x_Count][y_Count];
        gridPane.setHgap(10); // 设置水平间距
        gridPane.setVgap(10); // 设置垂直间距
        gridPane.setPadding(new Insets(10)); // 设置边距
        gridPane.setAlignment(Pos.CENTER);
        ChessPanePaint(num);
    }
    public void ChessPanePaint(int [][]num)
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j]=num[i][j];
                int value = grid[i][j];
                Label cell;
                Color color;
                if(value==0)color=Color.web("#CDC0B4");
                else color = getColorForValue(value);
                if(value==0) cell =new Label();
                else cell = new Label(String.valueOf(value));
                cell.setFont(new Font("Arial",/*FontWeight.BOLD, FontPosture.REGULAR,*/30));
                // 根据值的大小设置颜色
                //Color.web("#CDC0B4");
                cell.setBackground(new Background(new BackgroundFill(color, null, null)));

                cell.setPrefSize(100, 100);
                cell.setAlignment(Pos.CENTER);

                GridPane.setRowIndex(cell, i);
                GridPane.setColumnIndex(cell, j);
                gridPane.getChildren().add(cell);
            }
        }
    }
    public Color getColorForValue(int value) {
        // 简单的颜色映射，你可以根据需要调整
        if (value == 2) return Color.web("#E5E3D6");
        else if(value==4)return Color.web("#E5DFC5");
        else if(value==8)return Color.web("#EDAE78");
        else if(value==16)return Color.web("#ED9361");
        else if(value==32)return Color.web("#F27853");
        else if(value==64)return Color.web("#F15B38");
        else if (value == 128)return Color.web("##E8CA72");
        else if(value==256)return Color.web("#E6CC6F");
        else if (value == 512)return Color.web("#E7C95B");
        else if(value==1024)return Color.web("#E8C023");
        else if (value == 2048) {
            return Color.web("#E8C023"); //金色
        } else {
            return Color.rgb(15,15,15); // 红色（表示非常高的值）
        }
    }
    public GridPane getGridPane(){return gridPane;}
}
