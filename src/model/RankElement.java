package model;

public class RankElement {
    private String userName;
    private int rank;
    private int step;
    private int score;
    private int times;

    public RankElement(String userName, int steps, int scores, int times)
    {
        this.userName=userName;
        this.step=steps;
        this.score=scores;
        this.times=times;
    }

    public String getUserName() {
        return userName;
    }

    public int getStep() {
        return step;
    }

    public int getScore() {
        return score;
    }

    public int getTimes() {
        return times;
    }
    public int getRank(){return rank;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimes(int times) {
        this.times = times;
    }
    public void setRank(int rank){this.rank=rank;}
}
