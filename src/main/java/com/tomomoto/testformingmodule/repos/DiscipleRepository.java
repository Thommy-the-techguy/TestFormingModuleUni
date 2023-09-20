package com.tomomoto.testformingmodule.repos;

import com.tomomoto.testformingmodule.entities.Disciple;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscipleRepository extends CrudRepository<Disciple, Integer> {
    Optional<Disciple> findDiscipleByName(String name);
}
