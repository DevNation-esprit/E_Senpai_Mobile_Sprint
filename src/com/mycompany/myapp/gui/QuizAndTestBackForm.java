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
        add(new Label("choisir une option"));
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        Button btnQuiz = new Button("Tous les Quizzes") ;
        Button btnTest = new Button("Tous les Tests") ;
        Style quizStyle = btnQuiz.getAllStyles();
        quizStyle.setBorder(RoundBorder.create().
        rectangle(true).
        color(0xffffff).
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        
        Style testStyle = btnTest.getAllStyles() ;
        testStyle.setBorder(Border.createBevelLowered(RIGHT, RIGHT, HAND_CURSOR, BASELINE));
        testStyle.setMarginTop(10);
        testStyle.setMarginLeft(30);
        testStyle.setMarginRight(30);
        
        add(btnQuiz) ;
        add(btnTest) ;
        
        btnQuiz.addActionListener((evt) -> {
            new QuizBackForm(current).show();
        });
        
        btnTest.addActionListener((evt) -> {
            new TestBackForm(current).show();
        });
        
    }
    
}
