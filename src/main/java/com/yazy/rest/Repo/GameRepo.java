package com.yazy.rest.Repo;

import com.yazy.rest.Model.Game;

import com.yazy.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GameRepo extends JpaRepository<Game, UUID> {
    @Query("SELECT g FROM Game g WHERE g.id =:id")
    List<Game> findAllById(@Param("id") String id);
}
