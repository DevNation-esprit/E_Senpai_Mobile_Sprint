/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;
import com.mycomponay.entities.User;

/**
 *
 * @author Dahmani
 */
public class SignIn extends Form{
    
    public SignIn(Resources res){
        setTitle("Bienvenue à E-Senpai");
        setLayout(BoxLayout.y());
        Image img = res.getImage("Logo.png");
        img.scaledHeight(200);
        img.scaledWidth(200);
        Label lbl = new Label(img, "LogoLabel");
        add(lbl);
        TextField emailInput = new TextField("", "Entrer votre E-mail");
        TextField passwordInput = new TextField("", "Entrer votre mot de passe", 20, TextField.PASSWORD);
        
        Button btnAuthentifier = new Button("S'authentifier");
        
        btnAuthentifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(emailInput.getText().length()==0||passwordInput.getText().length()==0)
                {
                    Dialog.show("Alert", "Remplisser tous les champs", new Command("OK"));
                }
                else
                {
                    if(ServiceUser.getInstance().authentifier(emailInput.getText().toString(), passwordInput.getText().toString())){
                        User u = ServiceUser.getInstance().getUser(emailInput.getText().toString(), passwordInput.getText().toString());
                        if(u.getRole().toLowerCase().equals("formateur")){
                            new HomeFormateur(u,res).show();
                        }else{
                            if(u.getRole().toLowerCase().equals("admin")){
                                new HomeAdmin(u,res).show();
                            }
                            else{
                                new Home(u,res).show();
                            }
                        }
                        
                        
                    }
                        
                    else
                        Dialog.show("Alert", "Vérifier vos coordonnées", new Command("OK"));
                }
            }
        });
        
        addAll(new Label("E-mail:"),emailInput,new Label("Mot de passe:"),passwordInput);
        addAll(btnAuthentifier);
    }
    
}
