package com.planning.plan.reposit;
import com.planning.plan.domain.Action;
import com.planning.plan.domain.Auteur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

public interface ActionRepository extends CrudRepository<Action, Integer>{
    List<Action> findByWhenisBetweenAndAuteur(LocalDateTime dayBegin, LocalDateTime dayEnd, Auteur auteur);
    @Modifying
    @Transactional
    void deleteById(Integer id);
}
