/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Services.Services;
import com.mycompany.myapp.entities.Blog;

/**
 *
 * @author Methnani
 */
public class AddBlog extends Form {
    
    public AddBlog(Form previous){
    
    setTitle("Ajouter un nouveau blog");
        setLayout(BoxLayout.y());
        
        TextField cttitre = new TextField("","Tire");
        TextField ctcontenu= new TextField("", "Contenu");
        Button btnValider = new Button("Ajouter Blog");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((cttitre.getText().length()==0)||(ctcontenu.getText().length()==0 ))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Blog b = new Blog(cttitre.getText(), ctcontenu.getText());
                        if( Services.getInstance().ajoutBlog(b))
                        {
                            Dialog.show("Success","Blog ajouté",new Command("OK"));
                            new Home().show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(cttitre,ctcontenu,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
        
    }
   
    
}

    

