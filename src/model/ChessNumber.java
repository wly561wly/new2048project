package model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class ChessNumber {
    private final int X_COUNT;
    private final int Y_COUNT;
    private String userName;
    private int scores;

    private int[][] numbers;

    static Random random = new Random();
    public ChessNumber(int x_COUNT, int y_COUNT,String UserName)
    {
        this.X_COUNT = x_COUNT;
        this.Y_COUNT = y_COUNT;
        this.userName=UserName;
        scores=0;
        numbers=new int[x_COUNT][y_COUNT];
        this.initialNumbers();
    }
    public void clearNumbers(){
        scores=0;
        for(int i=0;i<X_COUNT;i++)
            for(int j=0;j<Y_COUNT;j++)
                numbers[i][j]=0;
    }
    public void initialNumbers() {
        int x1,x2,y1,y2;
        x1=random.nextInt(X_COUNT);x2=random.nextInt(X_COUNT);
        y1=random.nextInt(Y_COUNT);y2=random.nextInt(Y_COUNT);
        while(x1==x2)x1=random.nextInt(X_COUNT);
        numbers[x1][y1]=2;numbers[x2][y2]=4;
        System.out.println("Game begin");
        for(int i=0;i<numbers.length;i++)
        {
            for(int j=0;j<numbers[0].length;j++)System.out.printf(" %d",numbers[i][j]);
            System.out.println();
        }
    }
    //todo: finish the method of four direction moving.
    public boolean moveRight() {
        int num=0;
        for(int i=0;i<numbers.length;i++) {
            int p = numbers[i].length - 1;
            for (int j = numbers[i].length - 1; j >= 0; j--)
                if (numbers[i][j] > 0) {
                    if (p != j) {
                        num++;
                        numbers[i][p] = numbers[i][j];
                        numbers[i][j] = 0;
                    }
                    p--;
                }
        }
        for(int i=0;i<numbers.length;i++) {
            for (int j = numbers[i].length - 1; j > 0; j--)
            {
                if(numbers[i][j]==0)continue;
                if(numbers[i][j]==numbers[i][j-1]){
                    num++;
                    numbers[i][j]*=2;
                    numbers[i][j-1]=0;
                    scores+=numbers[i][j];
                }
            }
        }
        if(num==0)return false;
        for(int i=0;i<numbers.length;i++) {
            int p = numbers[i].length - 1;
            for (int j = numbers[i].length - 1; j >= 0; j--)
                if (numbers[i][j] > 0) {
                    if (p != j) {
                        numbers[i][p] = numbers[i][j];
                        numbers[i][j] = 0;
                    }
                    p--;
                }
        }
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveUp() {
        int num=0;
        for(int j=0;j<Y_COUNT;j++)
        {
            int p=0;
            for(int i=0;i<X_COUNT;i++)
                if(numbers[i][j]>0){
                    if(p!=i){
                        numbers[p][j]=numbers[i][j];
                        numbers[i][j]=0;
                        num++;
                    }
                    p++;
                }
        }
        for(int j=0;j<Y_COUNT;j++)
        {
            for(int i=0;i<X_COUNT-1;i++)
            {
                if(numbers[i][j]==0)continue;
                if(numbers[i][j]==numbers[i+1][j]){
                    numbers[i][j]*=2;
                    numbers[i+1][j]=0;
                    num++;
                    scores+=numbers[i][j];
                }
            }
        }
        if(num==0)return false;
        for(int j=0;j<Y_COUNT;j++)
        {
            int p=0;
            for(int i=0;i<X_COUNT;i++)
                if(numbers[i][j]>0){
                    if(p!=i){
                        numbers[p][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                    p++;
                }
        }
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveLeft() {
        int num=0;
        for(int i=0;i<numbers.length;i++)
        {
            int p=0;
            for(int j=0;j<numbers[i].length;j++)
                if(numbers[i][j]>0){
                    if(p!=j){
                        numbers[i][p]=numbers[i][j];
                        numbers[i][j]=0;
                        num++;
                    }
                    p++;
                }
        }
        for(int i=0;i<numbers.length;i++)
        {
            for(int j=0;j<Y_COUNT-1;j++)
            {
                if(numbers[i][j]==0)continue;
                if(numbers[i][j+1]==numbers[i][j]){
                    numbers[i][j]*=2;
                    numbers[i][j+1]=0;
                    num++;
                    scores+=numbers[i][j];
                }
            }
        }
        if(num==0)return false;
        for(int i=0;i<numbers.length;i++)
        {
            int p=0;
            for(int j=0;j<numbers[i].length;j++)
                if(numbers[i][j]>0){
                    if(p!=j){
                        numbers[i][p]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                    p++;
                }
        }
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveDown() {
        int num=0;
        for(int j=0;j<Y_COUNT;j++)
        {
            int p=X_COUNT-1;
            for(int i=X_COUNT-1;i>=0;i--)
                if(numbers[i][j]>0){
                    if(p!=i){
                        numbers[p][j]=numbers[i][j];
                        numbers[i][j]=0;
                        num++;
                    }
                    p--;
                }
        }
        for(int j=0;j<Y_COUNT;j++)
        {
            for(int i=X_COUNT-1;i>0;i--)
            {
                if(numbers[i][j]==0)continue;
                if(numbers[i][j]==numbers[i-1][j]){
                    numbers[i][j]*=2;
                    numbers[i-1][j]=0;
                    num++;
                    scores+=numbers[i][j];
                }
            }
        }
        if(num==0)return false;
        for(int j=0;j<Y_COUNT;j++)
        {
            int p=X_COUNT-1;
            for(int i=X_COUNT-1;i>=0;i--)
                if(numbers[i][j]>0){
                    if(p!=i){
                        numbers[p][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                    p--;
                }
        }
        RandomGeneraterNumber();
        return true;
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
    }
    public int[][] getNumber(){return numbers;}

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }

    //随机在空白位置新生成一个数
    public void RandomGeneraterNumber()
    {
        ArrayList<Integer> xp =new ArrayList<>();
        ArrayList<Integer> yp =new ArrayList<>();
        for(int i=0;i<X_COUNT;i++) {
            for(int j=0;j<Y_COUNT;j++) {
                if(numbers[i][j]==0){
                    xp.add(i);
                    yp.add(j);
                }
            }
        }
        int rand1=random.nextInt(xp.size()),rand2=random.nextInt(2);
        if(rand2==0)rand2=2;
        else rand2=4;
        numbers[xp.get(rand1)][yp.get(rand1)]=rand2;
    }
    public int getScores(){return scores;}

    public void saveNumber(int step){
        saveNumber(step,numbers);
    }
    public void saveNumber(int step,int[][] num){
        String filePath = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\"+userName+"\\save.csv";
        Label label=new Label();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            //先保存步数和分数
            writer.write(String.valueOf(step));
            writer.write(",");
            writer.write(String.valueOf(scores));
            writer.newLine();
            // 遍历二维数组
            for (int[] row : num) {
                // 将每行的元素转换为字符串并用逗号分隔，然后写入文件
                for (int i = 0; i < row.length; i++) {
                    writer.write(String.valueOf(row[i]));
                    if (i < row.length - 1) { // 如果不是当前行的最后一个元素，则添加逗号
                        writer.write(",");
                    }
                }
                writer.newLine(); // 换行，以便下一行从新的行开始
            }
            System.out.println("数组已成功保存到文件：" + filePath);
            label.setText("已成功保存到文件：" + userName+"\\save.csv");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("保存数组到文件时出错：" + e.getMessage());
            label.setText("保存文件时出错：" + e.getMessage());
        }

        Button YesButn=new Button("确认");
        label.setLayoutX(40);
        label.setLayoutY(100);
        YesButn.setLayoutX(140);
        YesButn.setLayoutY(200);

        Pane root=new Pane();
        root.getChildren().addAll(label,YesButn);
        Scene AlertScene=new Scene(root,300,300);
        Stage AlertStage=new Stage();

        root.setOnMouseClicked(event->{
            AlertStage.close();
        });
        YesButn.setOnAction(event->{
            AlertStage.close();
        });

        AlertStage.setTitle("正在保存文件");
        AlertStage.setScene(AlertScene);
        AlertStage.initModality(APPLICATION_MODAL);
        AlertStage.show();
    }
    public int loadNumber(){//还需要加入

        String filePath = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\"+userName+"\\save.csv";
        Label label=new Label();
        int steps;

        List<List<Integer>> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            line=reader.readLine();
            String[] values = line.split(",");
            steps = Integer.parseInt(values[0]);
            int nxtscores=Integer.parseInt(values[1]);

            while ((line = reader.readLine()) != null) {
                // 按逗号分割每行的内容
                values = line.split(",");
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    // 将字符串转换为整数并添加到当前行列表中
                    row.add(Integer.parseInt(value.trim()));
                }
                // 将当前行列表添加到二维列表中
                list.add(row);
            }
            // 如果你需要将二维列表转换为二维数组（可选）
            int[][] array = new int[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i).stream().mapToInt(Integer::intValue).toArray();
            }

            //在这里还需要检测array和scores，step是否合理
            //check(array);

            scores=nxtscores;
            numbers=array;
            // 打印二维数组（可选）
            for (int[] rowArray : array) {
                for (int value : rowArray) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
            label.setText("已成功读取文件");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件时出错：" + e.getMessage());
            label.setText("读取文件时出错");
            steps=-1;
        }

        Button YesButn=new Button("确认");
        label.setLayoutX(100);
        label.setLayoutY(100);
        YesButn.setLayoutX(120);
        YesButn.setLayoutY(190);

        Pane root=new Pane();
        root.getChildren().addAll(label,YesButn);
        Scene AlertScene=new Scene(root,300,300);
        Stage AlertStage=new Stage();

        root.setOnMouseClicked(event->{
            AlertStage.close();
        });
        YesButn.setOnAction(event->{
            AlertStage.close();
        });

        AlertStage.setTitle("正在玩命加载");
        AlertStage.setScene(AlertScene);
        AlertStage.initModality(APPLICATION_MODAL);
        AlertStage.show();
        return steps;
    }
    public boolean checkGameOver()
    {
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<X_COUNT;j++)
            {
                if(numbers[i][j]==0)return false;
                if(i>0&&numbers[i][j]==numbers[i-1][j])return false;
                if(j>0&&numbers[i][j]==numbers[i][j-1])return false;
            }
        }
        return true;
    }
}
