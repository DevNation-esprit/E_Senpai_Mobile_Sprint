/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomponay.entities.User;

/**
 *
 * @author Dahmani
 */
public class DetailUser extends Form{
    
    
    public DetailUser(Resources res, User u, User x, Form previous){
        setTitle("Utilisateurs | ID ADMIN "+u.getId());
        setLayout(BoxLayout.y());
        
        add(new Label("ID: "+x.getId()));
        add(new Label("Nom: "+x.getNom()));
        add(new Label("Prenom: "+x.getPrenom()));
        add(new Label("Sexe: "+x.getSexe()));
        add(new Label("Date Naisssance: "+u.getDateNaissance()));
        add(new Label("Email: "+x.getEmail()));
        add(new Label("Status: "+x.getStatus()));
        add(new Label("Rôle: "+x.getRole()));
        add(new Label("PhotoProfil: "+x.getPhotoProfil()));
        add(new Label("Biography: "+x.getBiography()));
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
    }
}
