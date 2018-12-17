package com.planning.plan.controller;

import com.planning.plan.domain.Action;
import com.planning.plan.domain.Auteur;
import com.planning.plan.domain.Role;
import com.planning.plan.reposit.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private ActionRepository actionRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")/*gère les données de la page d'accueil d'utilisateur ou il voit ses evenements pour une journée en cours
    * et le calendrier ou il peut regarder/ajouter/supprimer les evenements des autres jours */
    public String main(@RequestParam(name="date", required=false)String date,@RequestParam(name="day", required=false)String day, Map<String, Object> model){
        LocalDate ld;
        if(date==null) ld = LocalDate.now();
        else ld=LocalDate.parse(date).withDayOfMonth(Integer.parseInt(day));
        model = setModel( model, ld);
        return "main";
    }

    @PostMapping("/addnote")//ajoute une nouvelle action
    public String add(@AuthenticationPrincipal Auteur auteur, @RequestParam Integer timeaction, @RequestParam String description, String date, Map<String, Object> model){
        LocalDate ld= LocalDate.parse(date);
        LocalDateTime ldt = ld.atTime(timeaction, 0);
        Action action = new Action(description, ldt, auteur);
        actionRepository.save(action);
        model = setModel( model, ld);
        return "main";
    }
    @GetMapping("/addnote")//redirige l'utilisateur sur la page de formulaire d'ajout de nouvelle action
    public String formAddNote(@RequestParam String date, Map<String, Object> model){
        Auteur activeUser = (Auteur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDate ld= LocalDate.parse(date);
        model=setModel( model, ld);
        return "addAction";
    }


    @GetMapping("/calendrier")//quand l'utilisateur veut changer le mois dans le calendrier
    public String bougerCalendrier(@RequestParam String date, @RequestParam String direction, Map<String, Object> model){
        Auteur activeUser = (Auteur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDate ld=direction.equalsIgnoreCase("forward")?LocalDate.parse(date).plusMonths(1):LocalDate.parse(date).minusMonths(1);
        model=setModel(model,ld.withDayOfMonth(1) );//on met le premier jour de mois par default quand ce n'est pas un mois en cours
        return "main";
    }

    @PostMapping("/supprimerAction")//pour supprimer une action donnée
    public String deleteAction(@RequestParam String date, @RequestParam String id, Map<String, Object> model){
        Auteur activeUser = (Auteur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        actionRepository.deleteById(Integer.parseInt(id));
        LocalDate ld=LocalDate.parse(date);
        model=setModel(model, ld);
        return "main";
    }

    private Iterable<Action> returnActions(LocalDate ld, Auteur activeUser){
        LocalDateTime start = ld.atTime(0,0);
        LocalDateTime end = ld.atTime(23, 59);
        List<Action> actions = actionRepository.findByWhenisBetweenAndAuteur(start, end, activeUser);
        Collections.sort(actions, Comparator.comparing(Action::getWhenis));
        return actions;
    }

    private String[] getCalendar(LocalDate ld){
        String [] calendar = new String[42];
        int start = ld.withDayOfMonth(1).getDayOfWeek().ordinal();
        int end = ld.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth();
        for(int i=0, j=1; i<42; i++){
            if(i<=start||j>end) {
                calendar[i]="";
            }
            else{
                calendar[i]=""+j++;
            }
        }
        return calendar;
    }

    private Map<String, Object> setModel(Map<String, Object> model, LocalDate ld){
        Auteur activeUser = (Auteur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("admin", activeUser.getRoles().contains(Role.ADMIN));
        model.put("actions", returnActions(ld, activeUser));
        model.put( "date", ld);
        model.put( "login", activeUser.getUsername());
        model.put("calendar", getCalendar(ld));
        return model;
    }

}