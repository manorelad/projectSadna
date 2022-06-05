package com.yazy.rest.Model;



import javax.persistence.*;
import java.util.List;
import java.util.UUID;


    @Entity
    public class User {
        @Id
        private String id;
        @Column
        private String firstName;
        @Column
        private String lastName;
        @Column
        private String email;
        @Column
        private int wins;
        @Column
        private int loses;
        @Column
        private int tie;
        @Column
        private int highScore;
        @OneToMany
        private List<Game> games;

        public User(){
            setWins(0);
            setHighScore(0);
            setLoses(0);
            setTie(0);
        }
        public void addWin(){
            this.setWins(this.wins+1);
        }
        public void addLose(){
            this.setLoses(this.loses+1);
        }
        public void addTie(){
            this.setTie(this.tie+1);
        }
        public void updateHighScore(int highScore){
            this.setHighScore(Math.max(highScore,this.getHighScore()));
        }
        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLoses() {
            return loses;
        }

        public void setLoses(int loses) {
            this.loses = loses;
        }

        public int getTie() {
            return tie;
        }

        public void setTie(int tie) {
            this.tie = tie;
        }

        public int getHighScore() {
            return highScore;
        }

        public void setHighScore(int highScore) {
            this.highScore = highScore;
        }

        public List<Game> getGames() {
            return games;
        }

        public void setGames(List<Game> games) {
            this.games = games;
        }

        @Override
        public String toString() {
            return "User{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    "id=" + id + '\'' +
                    '}';
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }


