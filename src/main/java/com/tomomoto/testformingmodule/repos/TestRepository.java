package com.tomomoto.testformingmodule.repos;

import com.tomomoto.testformingmodule.entities.Disciple;
import com.tomomoto.testformingmodule.entities.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {
    Optional<Test> findTestByName(String name);
    List<Test> findTestByNameContainingIgnoreCaseAndDisciple(String name, Disciple disciple);
}
