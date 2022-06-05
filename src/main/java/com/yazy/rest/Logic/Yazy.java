package com.yazy.rest.Logic;

public class Yazy {
    private Cell ones,twos,threes,fours,fives,sixes,threeTimes,fourTimes,small,large,fullHouse,yazy,any,bonus;
    private boolean isFinish;

    public Yazy() {
    }

    public int calcauateResult(){
        return ones.getValue()+twos.getValue()+threes.getValue()+fours.getValue()+fives.getValue()+ sixes.getValue()+ threes.getValue()+
                fourTimes.getValue()+ small.getValue()+ large.getValue()+ yazy.getValue()+ any.getValue() + bonus.getValue() ;
    }
    public Yazy(int ones,int twos,int threes,int fours,int fives,int sixes,int threeTimes,int fourTimes,int small,int large ,int fullHouse ,int yazy,int any,int bonus,boolean isFinish){
        this.ones = new Cell(ones,true);
        this.twos = new Cell(twos,true);
        this.threes = new Cell(threes,true);
        this.fours = new Cell(fours,true);
        this.fives = new Cell(fives,true);
        this.sixes = new Cell(sixes,true);
        this.threeTimes = new Cell(threeTimes,true);
        this.fourTimes = new Cell(fourTimes,true);
        this.small = new Cell(small,true);
        this.large = new Cell(large,true);
        this.fullHouse = new Cell(fullHouse,true);
        this.yazy = new Cell(yazy,true);
        this.any = new Cell(any,true);
        this.bonus = new Cell(bonus,false);
        this.isFinish = isFinish;
    }
    public Yazy(Cell ones, Cell twos, Cell threes, Cell fours, Cell fives, Cell sixes, Cell threeTimes, Cell fourTimes, Cell small, Cell large, Cell fullHouse, Cell yazy, Cell any, Cell bonus, boolean isFinish) {
        this.ones = ones;
        this.twos = twos;
        this.threes = threes;
        this.fours = fours;
        this.fives = fives;
        this.sixes = sixes;
        this.threeTimes = threeTimes;
        this.fourTimes = fourTimes;
        this.small = small;
        this.large = large;
        this.fullHouse = fullHouse;
        this.yazy = yazy;
        this.any = any;
        this.bonus = bonus;
        this.isFinish = isFinish;
    }

    public Cell getOnes() {
        return ones;
    }

    public void setOnes(Cell ones) {
        this.ones = ones;
    }

    public Cell getTwos() {
        return twos;
    }

    public void setTwos(Cell twos) {
        this.twos = twos;
    }

    public Cell getThrees() {
        return threes;
    }

    public void setThrees(Cell threes) {
        this.threes = threes;
    }

    public Cell getFours() {
        return fours;
    }

    public void setFours(Cell fours) {
        this.fours = fours;
    }

    public Cell getFives() {
        return fives;
    }

    public void setFives(Cell fives) {
        this.fives = fives;
    }

    public Cell getSixes() {
        return sixes;
    }

    public void setSixes(Cell sixes) {
        this.sixes = sixes;
    }

    public Cell getThreeTimes() {
        return threeTimes;
    }

    public void setThreeTimes(Cell threeTimes) {
        this.threeTimes = threeTimes;
    }

    public Cell getFourTimes() {
        return fourTimes;
    }

    public void setFourTimes(Cell fourTimes) {
        this.fourTimes = fourTimes;
    }

    public Cell getSmall() {
        return small;
    }

    public void setSmall(Cell small) {
        this.small = small;
    }

    public Cell getLarge() {
        return large;
    }

    public void setLarge(Cell large) {
        this.large = large;
    }

    public Cell getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(Cell fullHouse) {
        this.fullHouse = fullHouse;
    }

    public Cell getYazy() {
        return yazy;
    }

    public void setYazy(Cell yazy) {
        this.yazy = yazy;
    }

    public Cell getAny() {
        return any;
    }

    public void setAny(Cell any) {
        this.any = any;
    }

    public Cell getBonus() {
        return bonus;
    }

    public void setBonus(Cell bonus) {
        this.bonus = bonus;
    }

    public boolean isFinish() {
        return    !(this.ones.isCanClick() ||
        this.twos.isCanClick() ||
        this.threes.isCanClick() ||
        this.fours.isCanClick() ||
        this.fives.isCanClick() ||
        this.sixes.isCanClick() ||
        this.threeTimes.isCanClick() ||
        this.fourTimes.isCanClick() ||
        this.small.isCanClick() ||
        this.large.isCanClick() ||
        this.fullHouse.isCanClick() ||
        this.yazy.isCanClick() ||
        this.any.isCanClick() ||
        this.bonus.isCanClick()  );

    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public String toString() {
        return "Yazy{" +
                "ones=" + ones +
                ", twos=" + twos +
                ", threes=" + threes +
                ", fours=" + fours +
                ", fives=" + fives +
                ", sixes=" + sixes +
                ", threeTimes=" + threeTimes +
                ", fourTimes=" + fourTimes +
                ", small=" + small +
                ", large=" + large +
                ", fullHouse=" + fullHouse +
                ", yazy=" + yazy +
                ", any=" + any +
                ", bonus=" + bonus +
                ", isFinish=" + isFinish +
                '}';
    }
}
