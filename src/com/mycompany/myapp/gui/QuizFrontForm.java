/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.services.ServiceQuiz;
import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class QuizFrontForm extends Form {
    Form current; 

    public QuizFrontForm() {
        current = this ;
        setTitle("Quiz");
        setLayout(BoxLayout.y());
        add(new Label("Tous les Quiz"));
        
        ArrayList<Quiz> quizzes = ServiceQuiz.getInstance().getListQuiz() ;
        for(Quiz q : quizzes){
            addItem(q) ;
        }
    }

    private void addItem(Quiz q) {
       Container c1 = new Container( new BoxLayout(BoxLayout.Y_AXIS)) ;
       Label lbSujet = new Label("Quiz : " + q.getSujet()) ;
       Label lbid = new Label(q.getId()+"") ;
       lbid.setVisible(false);
       
       lbSujet.addPointerPressedListener((evt) -> {
           if(Dialog.show("Datails Quiz", q.getSujet(), "passer", "fermer")){
               new PasserQuizForm(current, q.getId()).show() ;
           }
       });
       
       c1.add(lbSujet) ;
       c1.setLeadComponent(lbSujet);
       add(c1 );
       refreshTheme(); 
    }
    
    
}
