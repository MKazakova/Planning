package com.planning.plan.domain;

import javax.persistence.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity // This tells Hibernate to make a table out of this class
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String description;
    private LocalDateTime whenis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="code_autor")
    private Auteur auteur;

    public Action(String description, LocalDateTime whenis, Auteur auteur){
        this.description = description;
        this.whenis = whenis;
        this.auteur= auteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Action(){

    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getWhenis() {
        return whenis;
    }

    public void setWhenis(LocalDateTime whenis) {
        this.whenis = whenis;
    }

    public String toString(){
        LocalTime lt=whenis.toLocalTime();
        return lt.format(DateTimeFormatter.ofPattern("HH:mm"))+" : "+description;
    }
}