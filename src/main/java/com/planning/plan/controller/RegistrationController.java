package com.planning.plan.controller;

import com.planning.plan.domain.Auteur;
import com.planning.plan.domain.Role;
import com.planning.plan.reposit.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private AuteurRepository auteurRepository;


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


    @PostMapping("/registration")
    /*l'enregistrement de nouvel utilisateur d'Agenda et génération des messages d'erreur s'il y a un problème*/
    public String ajouteAuteur(Auteur aut, Map<String, Object> model, String pass2){
        Auteur auteurBD = auteurRepository.findByLogin(aut.getLogin());
        if(auteurBD!=null){
            model.put("message", "L'auteur avec ce login existe déjà dans la base");
            model.put("lg", aut.getLogin());
            return "registration";
        }
        if(!aut.getPass().equals(pass2)){
            model.put("message2", "Les mots de passe ne sont pas identiques");
            model.put("lg", aut.getLogin());
            return "registration";
        }
        aut.setRoles(Collections.singleton(Role.USER));
        auteurRepository.save(aut);
        model.put("message", "Vous êtes enregistré avec succes, autorisez vous : ");
        return "redirect:/login";
    }
}
