package model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jdk.jfr.Unsigned;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class ChessNumber {
    private int X_COUNT;
    private int Y_COUNT;
    private String userName;

    private int time=0;
    private int scores;
    private String Mode;

    private int choice;

    private int[][] numbers;
    private boolean backYes =false;//表示能不能撤销
    private int[][] preNum;//存储上一步棋盘
    private int preScore;//存储上一步分数

    static Random random = new Random();
    public ChessNumber(int x_COUNT, int y_COUNT,String UserName,String mode,int choice)
    {
        this.X_COUNT = x_COUNT;
        this.Y_COUNT = y_COUNT;
        this.userName=UserName;
        this.Mode=mode;
        this.choice=choice;
        scores=0;
        numbers=new int[x_COUNT][y_COUNT];
        this.initialNumbers();
    }
    public void clearNumbers(){
        scores=0;time=0;
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
    public void savePre(int[][]a,int b)
    {
        backYes =true;
        preNum=a;
        preScore=b;
    }
    //todo: finish the method of four direction moving.
    public boolean moveRight() {

        int b=scores;
        int[][]a=new int[X_COUNT][Y_COUNT];//临时存储
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<Y_COUNT;j++)
                a[i][j]=numbers[i][j];
        }

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
        savePre(a,b);//替换上一步
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveUp() {

        int b=scores;
        int[][]a=new int[X_COUNT][Y_COUNT];//临时存储
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<Y_COUNT;j++)
                a[i][j]=numbers[i][j];
        }

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
        savePre(a,b);//替换上一步
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveLeft() {

        int b=scores;
        int[][]a=new int[X_COUNT][Y_COUNT];//临时存储
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<Y_COUNT;j++)
                a[i][j]=numbers[i][j];
        }

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
        savePre(a,b);//替换上一步
        RandomGeneraterNumber();
        return true;
    }
    public boolean moveDown() {

        int b=scores;
        int[][]a=new int[X_COUNT][Y_COUNT];//临时存储
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<Y_COUNT;j++)
                a[i][j]=numbers[i][j];
        }

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
        savePre(a,b);//替换上一步
        RandomGeneraterNumber();
        return true;
    }
    public boolean doTheBack()
    {
        if(backYes){
            backYes=false;
            System.out.println("撤销成功！");
            scores=preScore;
            setNumber(preNum);
            return true;
        }
        else{
            System.out.println("撤销失败！！！");
            return false;
        }
    }
    public int getNumber(int i, int j) {
        return numbers[i][j];
    }
    public int getTime() {
        return time;
    }
    public int[][] getNumber(){return numbers;}
    public void setNumber(int[][] num){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                numbers[i][j]=num[i][j];
    }

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
        int rand1=random.nextInt(xp.size()),rand2=random.nextInt(9);
        if(rand2==0)rand2=4;
        else rand2=2;
        numbers[xp.get(rand1)][yp.get(rand1)]=rand2;
    }
    public int getScores(){return scores;}
    public void setScores(int scores1){scores=scores1;}

    public void saveNumber(int step,int type,int time){saveNumber(step,type,numbers,time);}

    public void saveNumber(int step,int type,int[][] num,int time){
        String filePath = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\"+userName+"\\"+Mode+"\\save.csv";
        Label label=new Label();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            //先保存步数,分数,时间,x_count,
            writer.write(String.valueOf(step));
            writer.write(",");
            writer.write(String.valueOf(scores));
            writer.write(",");
            writer.write(String.valueOf(time));
            writer.write(",");
            writer.write(String.valueOf(X_COUNT));
            writer.write(",");
            writer.write(String.valueOf(choice));
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
            //增加一个密钥
            writer.write(String.valueOf(getKeyword(step,num,time)));
            writer.newLine();

            System.out.println("数组已成功保存到文件：" + filePath);
            label.setText("已成功保存到文件：" + userName+"\\save.csv");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("保存数组到文件时出错");
            label.setText("保存文件时出错：" + e.getMessage());
        }

        if(type==0)return;//自动保存没有弹窗

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
    public int loadNumber(){
        //还需要加入

        String filePath = "C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\users\\"+userName+"\\"+Mode+"\\save.csv";
        Label label=new Label();
        int steps=0;

        List<List<Integer>> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            line=reader.readLine();
            String[] values = line.split(",");
            steps = Integer.parseInt(values[0]);
            int nxtscores=Integer.parseInt(values[1]);
            time=Integer.parseInt(values[2]);
            int Count=Integer.parseInt(values[3]);
            choice=Integer.parseInt(values[4]);
            System.out.println("Count:"+Count);
            for(int i=1;i<=Count;i++){
                line=reader.readLine();
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

            // 将二维列表转换为二维数组
            int[][] array = new int[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i).stream().mapToInt(Integer::intValue).toArray();
            }

            //在这里简单检测array和scores，step是否合理
            if(!checkArray(array,Count)||(steps<0)||scores<0||scores%2>0){
                System.out.println("文件错误");
                //尝试还原文件
                doReserve();
            }

            putKeyword(steps,scores,time,Count,array,choice);
            line=reader.readLine();
            long Key=0;
 //           if(line!=null)Key=Long.decode(line);
/*            //Hash密钥验证
            if(!checkKeyword(steps,nxtscores,time,Count,array,choice,Key)){
                System.out.println("文件错误");
                doReserve();
            }*/

            scores=nxtscores;
            numbers=array;
            X_COUNT=Count;
            Y_COUNT=Count;
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
    public int checkGameOver(int pattern, String mode, int choice,int time)
    {
        if(mode.equals("classic")){
            int maxNum=0;
            for(int i=0;i<X_COUNT;i++)
            {
                for(int j=0;j<Y_COUNT;j++)
                {
                    if(numbers[i][j]>maxNum)maxNum=numbers[i][j];
                }
            }
            if(maxNum==choice)return 2;
        }
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<X_COUNT;j++)
            {
                if(numbers[i][j]==0)return 0;
                if(i>0&&numbers[i][j]==numbers[i-1][j])return 0;
                if(j>0&&numbers[i][j]==numbers[i][j-1])return 0;
            }
        }
        return 1;
    }
    public void setUserName(String s) {userName=s;}
    public int getX_COUNT() {
        return X_COUNT;
    }

    public String getUserName() {
        return userName;
    }

    public String getMode() {
        return Mode;
    }

    public int getChoice() {
        return choice;
    }

    public int[][] getNumbers() {
        return numbers;
    }
    public boolean checkArray(int[][] array,int len)
    {
        int x=array.length;
        if(x!=len)return false;
        for(int i=0;i<x;i++)
        {
            if(x!=array[i].length)return false;
            for(int j=0;j<x;j++)
            {
                if(array[i][j]<0||array[i][j]%2>0)return false;
            }
        }
        return true;
    }
    public long getKeyword(int steps,int [][]num,int time)
    {
        long Key=0,p=1000000093,t=100043;
        Key=((steps*t+scores)%p*t+time)%p;
        Key=((Key*t+X_COUNT)%p*t+choice)%p;
        for(int i=0;i<X_COUNT;i++)
        {
            for(int j=0;j<Y_COUNT;j++){Key=(Key*t+num[i][j])%p;}
        }
        return Key;
    }
    public boolean checkKeyword(int step,int score,int time,int Count,int[][]num,int choice,long trueKey) {
        long Key = 0, p = 1000000093, t = 100043;
        Key = ((step * t + score) % p * t + time) % p;
        Key = ((Key * t + Count) % p * t + choice) % p;
        for (int i = 0; i < Count; i++) {
            for (int j = 0; j < Count; j++) {
                Key = (Key * t + num[i][j]) % p;
            }
        }
        System.out.println("Key:"+Key);
        return Key == trueKey;
    }
    public void putKeyword(int step,int score,int time,int Count,int[][]num,int choice) {
        long Key = 0, p = 1000000093, t = 100043;
        Key = ((step * t + score) % p * t + time) % p;
        Key = ((Key * t + Count) % p * t + choice) % p;
        for (int i = 0; i < Count; i++) {
            for (int j = 0; j < Count; j++) {
                Key = (Key * t + num[i][j]) % p;
            }
        }
        System.out.println("Key:"+Key);
    }
    public void doReserve()
    {

    }
}