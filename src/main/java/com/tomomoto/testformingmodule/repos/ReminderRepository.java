package com.tomomoto.testformingmodule.repos;

import com.tomomoto.testformingmodule.entities.Reminder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReminderRepository extends CrudRepository<Reminder, Integer> {

}
