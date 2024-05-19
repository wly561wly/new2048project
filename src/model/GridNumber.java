package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];//数组大小
        this.initialNumbers();
    }

    public void initialNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                //todo: update generate numbers method
                int rand=random.nextInt(2);  //设计概率，1/2 有数字，1/6为4，1/3为2
                if(rand==0){
                    rand=random.nextInt(3);
                    if(rand==2)numbers[i][j]=4;
                    else numbers[i][j]=2;
                }
                else numbers[i][j]=0;
            }
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
}
