package com.planning.plan.controller;

import com.planning.plan.domain.Auteur;
import com.planning.plan.domain.Role;
import com.planning.plan.reposit.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/auteur")
@PreAuthorize("hasAuthority('ADMIN')")//les pages de gestion des utilisateurs ne sont disponibles qu'aux utilisateurs avec un Role admin

public class AuteurController {
    @Autowired
    AuteurRepository auteurRepository;

    @GetMapping
    public String listAuteurs(Model model){
        model.addAttribute("auteurs", auteurRepository.findAll());
        return "listAuteurs";
    }

    @GetMapping("{auteur}")//redirige sur la page du formulaire de modification d'utilisateur
    public String editerAuteur(@PathVariable Auteur auteur, Model model){
        model.addAttribute("auteur", auteur);
        return "editerAuteur";
    }

    @PostMapping//sauvegarde les modifications du profil de l'utilisateur
    public String sauvegarderModifications(@RequestParam("code_autor") Auteur auteur,
                                           @RequestParam String login, @RequestParam String[] roles, @RequestParam String enabled){
        auteur.setLogin(login);
        Set<Role> rs = new HashSet<>();
        for(String r: roles){
            if(r.equals("user")) rs.add(Role.USER);
            else if(r.equals("admin")) rs.add(Role.ADMIN);
        }
        auteur.getRoles().clear();
        auteur.setRoles(rs);
        boolean en = enabled.equalsIgnoreCase("oui");
        auteur.setEnabled(en);
        auteurRepository.save(auteur);
        return "redirect:/auteur";
    }
}
