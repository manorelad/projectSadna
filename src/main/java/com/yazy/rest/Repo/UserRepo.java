package com.yazy.rest.Repo;

import com.yazy.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.email =:email")
    List<User> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.id =:id")
    List<User> findAllById(@Param("id") String id);


}
