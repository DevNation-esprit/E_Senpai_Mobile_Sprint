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
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceQuestion;

/**
 *
 * @author damos
 */
public class AddQuestionForm extends Form {
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;
    
     public AddQuestionForm(Form previous,int idQuiz) {
        setTitle("Add a new Question");
        setLayout(BoxLayout.y());
         Label title = new Label("Ajouter Question") ;
        title.setUIID("title") ;
        add(title); 
        TextField tfQposee = new TextField("","question posée");
        TextField tfRep = new TextField("","Reponse Corr");
        TextField tfProp1 = new TextField("","Proposition 1");
        TextField tfProp2 = new TextField("","Proposition 2");
        TextField tfProp3 = new TextField("","Proposition 3");
        TextField tfnote = new TextField("","nbre point ");
        Button btnValider = new Button("Ajouter Question");
        
        btnValider.addActionListener((evt) -> {
            if( (tfQposee.getText().length() == 0) || (tfRep.getText().length() == 0) || (tfProp1.getText().length() == 0)
              || (tfProp2.getText().length() == 0) || (tfProp3.getText().length() == 0) || tfnote.getText().length() ==0){
               Dialog.show("Error", "*champs obligatoires", new Command("OK"));
            }                 
            else
            {
                try {
                    Question q = new Question() ;
                    q.setQuestionPosee(tfQposee.getText());
                    q.setReponseCorrecte(tfRep.getText());
                    q.setReponseFausse1(tfProp1.getText());
                    q.setReponseFausse2(tfProp2.getText());
                    q.setReponseFausse3(tfProp3.getText());
                    q.setNote(Integer.parseInt(tfnote.getText()));
                    if(ServiceQuestion.getInstance().addQuestion(q,idQuiz))
                        Dialog.show("Success","Question ajoutée avec success",new Command("OK"));
                    else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        });
        btnValider.setUIID("btnAdd");
        tfQposee.setUIID("inputField");
        tfRep.setUIID("inputField");
        tfProp1.setUIID("inputField");
        tfProp2.setUIID("inputField");
        tfProp3.setUIID("inputField");
        tfnote.setUIID("inputField");
        addAll(tfQposee,tfRep,tfProp1,tfProp2,tfProp3,tfnote,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); 
                
    }
}
