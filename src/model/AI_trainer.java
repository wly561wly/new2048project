package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AI_trainer {
    ChessNumber chessNumber;
    int Max_Scores=0;
    int[][] P=new int[10][18];
    public AI_trainer(int flag)
    {
        ReadFile();
        if(flag>0)DoTrain();
    }
    public void DoTrain()
    {
        Random random=new Random();
        int t=5;
        while(t-->=0){
            int scores=0;
            int[] a=new int[18];
            for(int i=0;i<18;i++)
            {
                if(i==13)continue;
                int ii=(random.nextInt()%10+10)%10;
                a[i]=P[ii][i]+(random.nextInt()%20);
                a[i]=a[i]-(random.nextInt()%20);
            }
            for(int i=0;i<=1000;i++)
            {
                scores+=GameStart(a);
            }
            a[13]=scores/1000;
            for(int i=9;i>=0;i--)
            {
                if(P[i][13]<a[13]){
                    P[i]=a;
                    break;
                }
            }
        }
    }
    public int GameStart(int[] a)
    {
        Assess assess;
        chessNumber=new ChessNumber(4,4,"UserName","scores",0);
        int[][]save=new int[4][4];
        while(true){
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
            if(mark[0]>=mark[1]&&mark[0]>=mark[2]&&mark[0]>=mark[3])chessNumber.moveRight();
            else if(mark[1]>=mark[0]&&mark[1]>=mark[2]&&mark[1]>=mark[3])chessNumber.moveLeft();
            else if(mark[2]>=mark[0]&&mark[2]>=mark[1]&&mark[2]>=mark[3])chessNumber.moveUp();
            else chessNumber.moveDown();
            if(chessNumber.checkGameOver(0,"scores",0,0)!=0)break;
        }
        System.out.println("这局："+chessNumber.getScores());
        if(chessNumber.getScores()>Max_Scores)Max_Scores=chessNumber.getScores();
        return chessNumber.getScores();
    }
    public int getMax_Scores() {
        return Max_Scores;
    }

    public int[][] getP() {
        return P;
    }
    public void ReadFile()
    {
        String path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\AI_p.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            for(int i=0;i<10;i++){
                // 假设每行都是 "username,email,password" 格式，且没有额外的空格或特殊字符
                line= reader.readLine();
                String[] parts = line.split(",");
                for(int j=0;j<parts.length;j++)P[i][j]=Integer.parseInt(parts[j]);
            }
        } catch (IOException e) {
            System.out.println("读取用户文件出错");
            throw new RuntimeException(e);
        }
    }
}
