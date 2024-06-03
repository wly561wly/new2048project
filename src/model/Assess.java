package model;
public class Assess {
    int scores=0;
    public Assess(int[][] num,int[] p,int dir)//num是棋盘，p其中存储了权值列表，dir代表了移动方向
    {
        check0(num,p,dir);
        check1(num,p,dir);
        check2(num,p,dir);
        check3(num,p,dir);
        check4(num,p,dir);
        check5(num,p);
    }
    public void check0(int[][] num,int[] p,int dir){//判断最大的数在角落或者边缘
        int Max=-1,flag=0;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {
                if(num[i][j]<Max)continue;
                if(num[i][j]>Max){
                    Max=num[i][j];
                    flag=0;
                    if(i==0||i==3)flag++;
                    if(j==0||j==3)flag++;
                }
                else {
                    if(flag==2)continue;
                    int flag1=0;
                    if(i==0||i==3)flag1++;
                    if(j==0||j==3)flag1++;
                    if(flag1>flag)flag=flag1;
                }
            }
        if(flag==1)scores+=p[0];
        else if(flag==2)scores+=p[1];
    }
    public void check1(int[][] num,int[] p,int dir)//判断最大的数所在的行或列是不是满的
    {
        int Max=-1,flag=0;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {
                if(num[i][j]<Max)continue;
                if(num[i][j]>=Max){
                    Max=num[i][j];
                    flag=1;
                    for(int k=0;k<4;k++){
                        if(num[i][k]==0){
                            flag=0;
                            break;
                        }
                    }
                    int flag1=2;
                    for(int k=0;k<4;k++){
                        if(num[k][j]==0){
                            flag1=0;
                            break;
                        }
                    }
                    if(flag==0)flag=flag1;
                    else if(flag1!=0)flag=3;
                }
            }
        if(flag==0)return;
        if(flag!=0)scores+=p[2];  //存在行列满
        if(flag==3)scores+=p[3];  //行和列都满
        if(flag==1&&dir<=1)scores+=p[4];//行满，且移动方向是左右
        if(flag==2&&dir>=2)scores+=p[5];//列满，且移动方向是上下
    }
    public void check2(int[][] num,int[] p,int dir) { //评估空白位置
        int emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (num[i][j] == 0) {
                    emptyCount++;
                }
            }
        }
        scores -= p[6] * emptyCount; // p[6] 是每个空余位置的扣分值
    }
    public void check3(int[][] num,int[] p,int dir) { //数字的分布情况
        int[] counts = new int[16]; // 假设棋盘上的最大数字不会超过2^16
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (num[i][j] != 0) {
                    counts[(int) (Math.log(num[i][j]) / Math.log(2))]++;
                }
            }
        }
        int distributionScore = 0;
        for (int count : counts) {
            if (count > 0) {
                distributionScore += count;
            }
        }
        scores += p[7] * distributionScore; // p[7] 是数字分布的加分值
    }
    public void check4(int[][] num,int[] p,int dir) {  //检查每行每列的单调性
        int flag1=1,flag2=1;
        double monotonicityScore = 0;
        // 遍历每一行和每一列
        for (int i = 0; i < 4; i++) {
            {
                int t=checkLineMonotonicity(p,num[i]);
                monotonicityScore += t;
            }
        }
        if(monotonicityScore==4||monotonicityScore==-4)scores+=p[11];
        monotonicityScore=0;
        for (int j = 0; j < 4; j++) {
            int[] column = new int[4];
            for (int i = 0; i < 4; i++) {
                column[i] = num[i][j];
            }
            monotonicityScore += checkLineMonotonicity(p,column);
        }
        if(monotonicityScore==4||monotonicityScore==-4)scores+=p[11];
    }
    public void check5(int[][]numbers,int[] p)
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(numbers[i][j]==0){return;}
                if(i>0&&numbers[i][j]==numbers[i-1][j])return;
                if(j>0&&numbers[i][j]==numbers[i][j-1])return;
            }
        }
        scores+=p[12];
    }
    private int checkLineMonotonicity(int[] p,int[] line) {
        int previous = 0;
        boolean hasIncreasingSequence = false;
        boolean hasDecreasingSequence = false;

        for (int num : line) {
            if (num != 0) {
                if (previous == 0) {
                    previous = num;
                } else {
                    if (num > previous) {
                        hasIncreasingSequence = true;
                    } else if (num < previous) {
                        hasDecreasingSequence = true;
                    }
                    if (hasIncreasingSequence && hasDecreasingSequence) {
                        // 如果既有递增又有递减，则单调性较差
                        break;
                    }
                    previous = num;
                }
            }
        }

        if (hasIncreasingSequence && !hasDecreasingSequence)
        {
            scores+=p[8];
            return 1;
        } else if (!hasIncreasingSequence && hasDecreasingSequence)
        {
            scores+=p[9];
            return -1;
        }
        if(!hasIncreasingSequence&&!hasDecreasingSequence)
        {
            scores+=p[10];
            return 0;
        }
        return 0;
    }
    private void check6(int[][]num)
    {

    }
    public int getScores(){return scores;}
}
