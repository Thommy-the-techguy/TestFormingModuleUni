package com.tomomoto.testformingmodule.repos;

import com.tomomoto.testformingmodule.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findUserByUsername(String username);
}
