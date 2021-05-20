/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceQuiz;
import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class QuizBackForm extends Form{
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;

    public QuizBackForm(Form previous) {
        current = this ;
        setTitle("Quiz");
        setLayout(BoxLayout.y());
        Button btnAddQuiz = new Button("Add New Quiz") ;
        add(btnAddQuiz) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
        
        ArrayList<Quiz> quizzes = ServiceQuiz.getInstance().getListQuizFormateur(user.getId());
        for(Quiz q : quizzes){
            addItem(q) ;
        }
        
        btnAddQuiz.addActionListener((evt) -> {
            new AjouterQuizForm(previous).show();
        });
    }

    private void addItem(Quiz q) {
       Container c1 = new Container( new BoxLayout(BoxLayout.Y_AXIS)) ;
       Label lbSujet = new Label("Quiz : " + q.getSujet()) ;
       Label lbid = new Label(q.getId()+"") ;
       lbid.setVisible(false);
       
       lbSujet.addPointerPressedListener((evt) -> {
           String cmd = Dialog.show("Detais","Quiz : " + q.getSujet(), new Command("questions"),
                            new Command("update"),new Command("delete")).getCommandName() ;
                switch (cmd) {
                    case "delete":
                        if(ServiceQuiz.getInstance().deleteQuiz(q, user.getId())){
                            Dialog.show("Success","Quiz supprimé avec success",new Command("OK"));
                        refreshTheme() ;
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        break;
                    case "update":
                        new UpdateQuizForm(current, q.getId()).show() ; 
                        break;
                    case "questions":
                        new QuizDetailsForm(current,q.getId()).show() ;
                        break;
                    default:
                        break;
                }          
       });
       lbSujet.setUIID("lbl");
       c1.setUIID("contQuiz"); 
       c1.add(lbSujet) ;
       
       c1.setLeadComponent(lbSujet);
       add(c1 );
       refreshTheme(); 
    }
}
