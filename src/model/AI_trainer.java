package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class AI_trainer {
    ChessNumber chessNumber;
    int Max_Scores=0;
    int[][] P=new int[25][16];
    public AI_trainer(int flag)
    {
        ReadFile();
        if(flag>0)DoTrain();
    }
    public void DoTrain()
    {
        Random random=new Random();
        int t=1000;
        while(t-->=0){
            if(t%5==0)WriteFile();
            int scores=0;
            int[] a=new int[16];
            a[15]=99999999;
            for(int i=0;i<15;i++)
            {
                if(i==13)continue;
                int ii=(random.nextInt()%25+25)%25;
                a[i]=P[ii][i]+(random.nextInt()%20);
                a[i]=a[i]-(random.nextInt()%15);
            }
            a[0]+=50;a[1]+=80;
            for(int i=0;i<=800;i++)
            {
                scores+=GameStart(a);
            }
            a[13]=scores/800;
            for(int i=24;i>=0;i--)
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
        System.gc();
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
                int sum=0,t=5;
                while(t>0){
                    t--;
                    chessNumber.setNumber(save);
                    chessNumber.setScores(ini_scores);
                    chessNumber.moveRight();
                    assess=new Assess(chessNumber.getNumber(),a,0);
                    sum+=assess.getScores();
                }
                mark[0]=sum/5;
            }
            else mark[0]=-9999999;

            p=1;chessNumber.setNumber(save);
            if(chessNumber.moveLeft()){
                int sum=0,t=5;
                while(t>0){
                    t--;
                    chessNumber.setNumber(save);
                    chessNumber.setScores(ini_scores);
                    chessNumber.moveLeft();
                    assess=new Assess(chessNumber.getNumber(),a,1);
                    sum+=assess.getScores();
                }
                mark[1]=sum/5;
            }
            else mark[1]=-9999999;

            p=2;chessNumber.setNumber(save);
            if(chessNumber.moveUp()){
                int sum=0,t=5;
                while(t>0){
                    t--;
                    chessNumber.setNumber(save);
                    chessNumber.setScores(ini_scores);
                    chessNumber.moveUp();
                    assess=new Assess(chessNumber.getNumber(),a,2);
                    sum+=assess.getScores();
                }
                mark[2]= sum/5;
            }
            else mark[2]=-9999999;

            p=3;chessNumber.setNumber(save);
            if(chessNumber.moveDown()) {
                int sum=0,t=5;
                while(t>0){
                    t--;
                    chessNumber.setNumber(save);
                    chessNumber.setScores(ini_scores);
                    chessNumber.moveDown();
                    assess=new Assess(chessNumber.getNumber(),a,3);
                    sum+=assess.getScores();
                }
                mark[3]= sum/5;
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
        if(chessNumber.getScores()<a[15])a[15]=chessNumber.getScores();
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
            for(int i=0;i<25;i++){
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
    public void WriteFile()
    {
        String path="C:\\Users\\Taxes\\IdeaProjects\\cs109\\resources\\AI_p.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for(int i=0;i<25;i++) {
                for(int j=0;j<16;j++)
                {
                    writer.write(String.valueOf(P[i][j]));
                    if(j!=16-1)writer.write(",");
                }
                writer.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("写入用户文件出错");
            throw new RuntimeException(e);
        }
    }
}
