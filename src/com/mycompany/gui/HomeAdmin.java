/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomponay.entities.User;

/**
 *
 * @author Dahmani
 */
public class HomeAdmin extends Form{
    
    public HomeAdmin(User u, Resources res){
        
        setTitle("Dashboard Admin");
        setLayout(BoxLayout.y());
        Button btnUsers = new Button("Gestion Utilisateurs");
        Button btnFormations = new Button("Gestion Formations");
        Button btnTests = new Button("Gestion Tests&Quizs");
        Button btnEvents = new Button("Gestion Evenements");
        
        Button btnDeconnect = new Button("Déconnecter");
        
        Container padd = new Container();
        padd.setPreferredSize(new Dimension(8,120));
        
        btnUsers.addActionListener((e) -> new GestionUsers(res,u).show());
        btnDeconnect.addActionListener((e) -> new SignIn(res).show());
        
        addAll(btnUsers, btnFormations, btnTests, btnEvents,padd,btnDeconnect);
    }
    
}
