package com.yazy.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Optional;
import java.util.UUID;

@Entity
public class Game {
    @Id
    private String id;
    @Column
    private int scoreFirstUser;
    @Column
    private int scoreSecondUser;

    public Game() {
    }

    public Game(String id, User firstUser, User secondUser, int scoreFirstUser, int scoreSecondUser) {
        this.id = id;
        this.scoreFirstUser = scoreFirstUser;
        this.scoreSecondUser = scoreSecondUser;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }



    public int getScoreFirstUser() {
        return scoreFirstUser;
    }

    public void setScoreFirstUser(int scoreFirstUser) {
        this.scoreFirstUser = scoreFirstUser;
    }

    public int getScoreSecondUser() {
        return scoreSecondUser;
    }

    public void setScoreSecondUser(int scoreSecondUser) {
        this.scoreSecondUser = scoreSecondUser;
    }

    @ManyToOne
    private User firstUser;
    @ManyToOne
    private User secondUser;

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }
}
