package com.planning.plan.reposit;
import com.planning.plan.domain.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {
    Auteur findByLogin(String login);
}
