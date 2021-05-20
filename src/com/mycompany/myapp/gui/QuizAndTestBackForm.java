/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author damos
 */
public class QuizAndTestBackForm extends Form{
    
    Form current; 
    Session s = new Session() ;
    User u =  Session.getUser() ;

    public QuizAndTestBackForm() {
        current = this ;
        setTitle("Quiz & Test");
        setLayout(BoxLayout.y());
        Label title = new Label("choisir une option") ;
        title.setUIID("title") ;
        add(title);  
        
        Label btnQuiz = new Label("Tous les Quizzes") ;
        Label btnTest = new Label("Tous les Tests") ;
       
        btnQuiz.setUIID("lbl");
        btnTest.setUIID("lbl");
        add(btnQuiz) ;
        add(btnTest) ;
        
        btnQuiz.addPointerPressedListener((evt) -> {
            new QuizBackForm(current).show();
        });
        
        btnTest.addPointerPressedListener((evt) -> {
            new TestBackForm(current).show();
        });
        
    }
    
}
