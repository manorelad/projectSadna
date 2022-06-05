package com.yazy.rest.Logic;

import java.util.UUID;

public class Move {
    private UUID userId;
    private Yazy yazy;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    public Yazy getYazy() {
        return yazy;
    }

    public void setYazy(Yazy yazy) {
        this.yazy = yazy;
    }



    public Move(UUID userId, Yazy yazy) {
        this.userId = userId;
        this.yazy = yazy;
    }
    public boolean endingMove(){
        //System.out.println(yazy);
        return this.yazy.isFinish();
    }
    @Override
    public String toString() {
        return "Move{" +
                "userId=" + userId +
                ", yazy='" + yazy + '\'' +
                '}';
    }
}
