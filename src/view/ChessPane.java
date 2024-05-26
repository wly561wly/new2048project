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
    int[] numTable={1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536};
    String[][] colorTable;
    private GridPane gridPane;
    private int pattern;
    public ChessPane(int x_Count,int y_Count,int [][]num, int gridSize,int pattern) {
        gridPane = new GridPane();
        grid = new int[x_Count][y_Count];
        this.pattern=pattern;
        setColorTable();
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
                cell.setFont(Font.font("Arial",FontWeight.BLACK, FontPosture.REGULAR,35));
                // 根据值的大小设置颜色
                //Color.web("#CDC0B4");
                cell.setBackground(new Background(new BackgroundFill(color, null, null)));

                cell.setPrefSize(110, 110);
                cell.setAlignment(Pos.CENTER);

                GridPane.setRowIndex(cell, i);
                GridPane.setColumnIndex(cell, j);
                gridPane.getChildren().add(cell);
            }
        }
    }
    public Color getColorForValue(int value) {
        // 简单的颜色映射，你可以根据需要调整
        if(value==0)return Color.web("#CDC1B4");
        for(int i=1;i<=14;i++)if(value==numTable[i])return Color.web(colorTable[pattern][i]);
        return Color.web("#E5E3D6");
    }
    public GridPane getGridPane(){return gridPane;}
    public void setColorTable(){
        String[][] Colortable={
                //经典配色
                {
                    "#9EE682", "#EEE4DA", "#EDE0C8", "#F2B179", "#F59563", "#F67C5F",
                    "#F65E3B", "#EDCF72", "#EDCC61", "#E6C95B", "#EDC952", "#EDC642",
                    "#E8C023", "#B49F3D", "#7C7437", "#BFAB02"
                },
                // 由绿到蓝
                {
                    "#9EE682", "#85FFAB", "#74E88E", "#5DE67B", "#53D687", "#59E2BF",
                    "#59E2BF", "#46CFD6", "#46CFD6", "#52AFE5", "#2AA3E9", "#2AA3E9",
                    "#4059E7", "#2541DF", "#433DBD", "#29249C"
                },
                // 由粉到红
                {
                        "#FFDAB9", "#FFB6A3", "#FFA28D", "#FF8E77", "#FF7A61", "#FF664B",
                        "#FF5235", "#FF3E1F", "#FF2A0A", "#FF1600", "#E60000", "#CC0000",
                        "#B30000", "#990000", "#800000", "#660000"
                },
                // 由浅蓝到紫
                {
                        "#BFEFFF", "#A6D9FF", "#8CC5FF", "#72B1FF", "#589DFF", "#3E89FF",
                        "#2475FF", "#0A61FF", "#2049F3", "#4C5CD6", "#2E42DF", "#7052D8",
                        "#562FDE", "#451ADD","#551ADD"
                },
                // 类似黄红的渐变
                {
                        "#FFFFE0", "#FFF9C4", "#FFF3A8", "#FFED8C", "#FFE770", "#FFE154",
                        "#FFDB38", "#FFD51C", "#FFCF00", "#FFC900", "#FFC200", "#FFBB00",
                        "#FFB400", "#FFAC00", "#FFA500", "#FF9E00", "#FF9700", "#FF9000"
                }
        };
        colorTable=Colortable;
    }
}
