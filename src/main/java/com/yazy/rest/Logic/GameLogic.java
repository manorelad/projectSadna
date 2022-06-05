package com.yazy.rest.Logic;

import com.yazy.rest.Model.User;

import java.util.ArrayList;
import java.util.UUID;

public class GameLogic {
    private ArrayList<Move> firstPlayer;
    private UUID fPlayer,sPlayer;
    private ArrayList<Move> secondPlayer;
    private UUID gameId;
    private boolean firstPlayerTurn;

    public UUID getfPlayer() {
        return fPlayer;
    }




    public UUID getsPlayer() {
        return sPlayer;
    }

    public GameLogic(UUID gameId, UUID firstPlayer, UUID secondPlayer) {
        this.firstPlayer = new ArrayList<Move>();
        this.firstPlayer.add(new Move(firstPlayer,new Yazy(0,0,0,0,0,0,0,0,0,0,0,0,0,0,false)));
        this.secondPlayer = new ArrayList<Move>();
        this.secondPlayer.add(new Move(secondPlayer,new Yazy(0,0,0,0,0,0,0,0,0,0,0,0,0,0,false)));
        fPlayer = firstPlayer;
        sPlayer = secondPlayer;
        firstPlayerTurn = true;


    }
    public void makeAMove(Move move){

        if(fPlayer.equals(move.getUserId()) && firstPlayerTurn){
            firstPlayer.add(move);
            firstPlayerTurn = false;

        }
        else if(sPlayer.equals(move.getUserId()) && !firstPlayerTurn ){
            secondPlayer.add(move);
            firstPlayerTurn = true;

        }
        else{
            System.out.println("Its Not Your turn");
        }


    }
    public Move getMyLastMove(){
        if(firstPlayerTurn){
            return firstPlayer.get(firstPlayer.size()-1);
        }
        else{
            return secondPlayer.get(secondPlayer.size()-1);
        }
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public boolean isFinish(){
      return getFirstPlayerLastMove().endingMove() && getSecondPlayerLastMove().endingMove();
    }
    public int firstPlayerResult(){
        return this.firstPlayer.get(firstPlayer.size()-1).getYazy().calcauateResult();
    }
    public int secondPlayerResult(){
        return this.secondPlayer.get(secondPlayer.size()-1).getYazy().calcauateResult();
    }
    public Move getOpponentLastMove(UUID myId){
        if(myId.equals(fPlayer)){
            return this.secondPlayer.get(secondPlayer.size()-1);
            }
        else if(myId.equals(sPlayer)){
            return  this.firstPlayer.get(firstPlayer.size()-1);

        }
        return null;
    }
    public boolean isPlayerTurn(UUID userId){
        if(firstPlayerTurn){
            return userId.equals(fPlayer);
        }
        else{
            return userId.equals(sPlayer);
        }

    }
    public Move getFirstPlayerLastMove(){
        return firstPlayer.get(firstPlayer.size()-1);
    }
    public Move getSecondPlayerLastMove(){
        return secondPlayer.get(secondPlayer.size()-1);
    }
    public String[] getGameResult(UUID userId){
        String[] value = {"Tie",firstPlayerResult()+"",secondPlayerResult()+""};
        if(userId.equals(fPlayer)){
            fPlayer = null;
        }
        else{
            sPlayer = null;
        }
        if(firstPlayerResult() > secondPlayerResult()){
            value[0] = "First Player Won";
        }
        else if(firstPlayerResult() > secondPlayerResult()){
            value[0] = "Second Player Won";
        }
      return value;
    }

}
