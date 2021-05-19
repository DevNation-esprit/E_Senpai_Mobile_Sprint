/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
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
import com.mycompany.myapp.entities.Post;
import java.util.Map;

/**
 *
 * @author Methnani
 */
public class AddPost extends Form {


public AddPost(Form previous){
    
    setTitle("Ajouter un nouveau blog");
        setLayout(BoxLayout.y());
        
        TextField ctcaption = new TextField("","Caption");
        
    
        
        
        
        Button btnValider = new Button("Ajouter Post");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((ctcaption.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Post p = new Post(ctcaption.getText());
                        if( Services.getInstance().ajoutPost(p))
                        {
                            Dialog.show("Success","Post ajouté",new Command("OK"));
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
        
        addAll(ctcaption,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
        
    }
   
    
    
}
