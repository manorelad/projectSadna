package com.yazy.rest.Controllers;

import com.yazy.rest.Logic.GameLogic;
import com.yazy.rest.Logic.Move;
import com.yazy.rest.Logic.MyId;
import com.yazy.rest.Model.Game;
import com.yazy.rest.Model.User;
import com.yazy.rest.Repo.GameRepo;
import com.yazy.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class API {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GameRepo gameRepo;
    private UUID gameHelper = null;
    private ArrayList<UUID> userRequestedGames = new ArrayList<>();
    private Map<UUID, GameLogic> activeGames = new HashMap<>();


    @GetMapping("/users/{userId}")
    public User getUsers(@PathVariable("userId") UUID userId){
        System.out.println(userId);
      return userRepo.findAllById(userId.toString()).get(0);
    }
    @PostMapping(path="/user",produces = "application/json",consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user){

        user.setId(UUID.randomUUID().toString());
        if(!userRepo.findByEmail(user.getEmail()).isEmpty()) {
            System.out.println("Email Already Exist");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exist");

        }
        userRepo.save(user);
        return ResponseEntity.ok().body(user.getId());
    }
    @GetMapping(path="/user/{email}")
    public ResponseEntity getUser(@PathVariable String email){
        List<User> users = userRepo.findByEmail(email);
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok().body(users.get(0).getId());
    }

    @GetMapping(path = "/startGame/{userId}",produces = "application/json")
    public ResponseEntity createGame(@PathVariable("userId") UUID userId){

        if(userRepo.findAllById(userId.toString()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userId);
        }
        if(gameHelper == null){
            gameHelper = UUID.randomUUID();
        }
        UUID gameId = gameHelper;
        userRequestedGames.add(userId);
        if(userRequestedGames.size() >= 2){
            UUID firstUser = userRequestedGames.remove(0);
            UUID secondUser = userRequestedGames.remove(0);
            activeGames.put(gameId,new GameLogic(gameId,firstUser,secondUser));
            gameHelper = null;
        }
        if(gameHelper != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(gameId.toString()+"\n"+ "FIRST_PLAYER");
        }
        else{
            return ResponseEntity.status(HttpStatus.CREATED).body(gameId.toString()+"\n"+"SECOND_PLAYER");
        }


    }
    @PostMapping(path = "/game/{gameId}",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Move> playGame(@PathVariable("gameId") UUID gameId , @RequestBody Move move){


        if(!activeGames.containsKey(gameId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GameLogic currentGame =  activeGames.get(gameId);
        if(!currentGame.isPlayerTurn(move.getUserId())){
          return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        currentGame.makeAMove(move);

        if(currentGame.isFinish()){
            User firstUser = userRepo.findAllById(currentGame.getfPlayer().toString()).get(0);
            User secondUser = userRepo.findAllById(currentGame.getsPlayer().toString()).get(0);
            int firstPlayerResult = currentGame.firstPlayerResult();
            int secondPlayerResult = currentGame.secondPlayerResult();
            gameRepo.save(new Game(gameId.toString(),firstUser,secondUser,firstPlayerResult,secondPlayerResult));
            if(firstPlayerResult > secondPlayerResult){
                firstUser.addWin();
                secondUser.addLose();
            }
            else if(firstPlayerResult < secondPlayerResult){
                firstUser.addLose();
                secondUser.addWin();
            }else{
                firstUser.addTie();
                secondUser.addTie();
            }
            firstUser.updateHighScore(firstPlayerResult);
            secondUser.updateHighScore(secondPlayerResult);
            userRepo.save(firstUser);
            userRepo.save(secondUser);
            activeGames.remove(gameId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Move>(activeGames.get(gameId).getOpponentLastMove(move.getUserId()),HttpStatus.OK);
    }
    @GetMapping(path = "/isMyTurn/{gameId}/{userId}",produces = "application/json")
    public ResponseEntity<Move> isMyTurn(@PathVariable("gameId") UUID gameId,@PathVariable("userId") UUID userId){

        if(!activeGames.containsKey(gameId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GameLogic currentGame =  activeGames.get(gameId);
        if(currentGame.isPlayerTurn(userId)){
            return ResponseEntity.ok(currentGame.getOpponentLastMove(userId));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

    }

    @GetMapping(path = "/game/{gameId}/{numberOfUser}",produces = "application/json")
    public ResponseEntity<String[]> getGameResult(@PathVariable("gameId") UUID gameId,
                                              @PathVariable("numberOfUser") String numberOfUser){

        try{
            Game game = gameRepo.findAllById(gameId.toString()).get(0);
            int firstPlayerScore = game.getScoreFirstUser();
            int secondPlayerScore = game.getScoreSecondUser();
            String[] result ={"",firstPlayerScore+"",secondPlayerScore+""};
            if(numberOfUser.equals("FIRST_PLAYER")){
                if(firstPlayerScore > secondPlayerScore){
                    result[0] = "YOU WON!";
                }
                else if(firstPlayerScore < secondPlayerScore){
                    result[0] = "YOU LOST!";
                }else{
                    result[0] = "GAMED TIED!";
                }
            }
            else{
                if(firstPlayerScore > secondPlayerScore){
                    result[0] = "YOU LOST!";
                }
                else if(firstPlayerScore < secondPlayerScore){
                    result[0] = "YOU WON!";
                }else{
                    result[0] = "GAMED TIED!";
                }

            }
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
