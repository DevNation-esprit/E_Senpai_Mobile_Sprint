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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceQuiz;

/**
 *
 * @author damos
 */
public class UpdateQuizForm extends Form {
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;
    
     public UpdateQuizForm(Form previous,int idQuiz) {
        Quiz q = ServiceQuiz.getInstance().getQuiz(idQuiz);
        setTitle("Update Quiz");
        setLayout(BoxLayout.y());
        Label title = new Label("Update Quiz") ;
        title.setUIID("title") ;
        add(title); 
        TextField tfSujet = new TextField(q.getSujet(),"sujet");
        Button btnValider = new Button("Update Quiz");
        
        btnValider.addActionListener((evt) -> {
            if( tfSujet.getText().length() == 0 )
                    Dialog.show("Error", "*champ obligatoire", new Command("OK"));
                else
                {
                    try {                    
                        q.setSujet(tfSujet.getText());
                        if(ServiceQuiz.getInstance().updateQuiz(q, user.getId()))
                            Dialog.show("Success","Quiz mis à jour avec success",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
        });
        
        btnValider.setUIID("btnAdd");
        tfSujet.setUIID("inputField");
        addAll(tfSujet,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); 
                
    }
}
