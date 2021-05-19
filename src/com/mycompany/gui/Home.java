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
public class Home extends Form{
    
    
    public Home(User u, Resources res) {
        
        setTitle("Accueil");
        setLayout(BoxLayout.y());

        Button btnProfil = new Button("Profil");
        Button btnFormations = new Button("Formations");
        Button btnChat = new Button("Chat");
        Button btnBlog = new Button("Blogs");
        Button btnEvents = new Button("Evenements");
        Button btnQuiz = new Button("Quizs & Tests");
        Button btnDeconnect = new Button("Déconnecter");
        
        Container padd = new Container();
        padd.setPreferredSize(new Dimension(8,120));
        
        btnProfil.addActionListener((e) -> new Profil(u,res).show());
        btnDeconnect.addActionListener((e) -> new SignIn(res).show());
        
        add(new Label("Bienvenue "+u.getNom()+"  Id :"+u.getId()));
        addAll(btnProfil, btnFormations, btnChat, btnBlog, btnEvents, btnQuiz,padd,btnDeconnect);
        
    }
    
}
