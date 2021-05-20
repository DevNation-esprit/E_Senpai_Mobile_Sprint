/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.Test;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceQuestion;
import com.mycompany.myapp.services.ServiceTest;
import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class TestDetailsForm extends Form {
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;

    public TestDetailsForm(Form previous,int idTest) {
        current = this ;
        setTitle("Questions");
        setLayout(BoxLayout.y());
        Button btnNote = new Button("Les Notes") ;
        Label title = new Label("Questions Test") ;
        title.setUIID("title") ;
        add(title); 
        add(btnNote) ;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
        
        Test test = ServiceTest.getInstance().getListTestFormateur(user.getId()).get(0);
        ArrayList<Question> questions = ServiceQuestion.getInstance().getQuestionByTest(idTest) ;
        for(Question q : questions){
            addItem(q) ;
        }
        
        btnNote.addActionListener((evt) -> {
            Form hi = new NotesChart().execute(1); 
            hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack()); 
            hi.show(); 
        });
    }

    private void addItem(Question q) {
       Container c1 = new Container( new BoxLayout(BoxLayout.Y_AXIS)) ;
        SpanLabel lbQposee = new SpanLabel("Question posée : ".toUpperCase() + q.getQuestionPosee()) ;
        SpanLabel lbRep = new SpanLabel("Reponse : ".toUpperCase() + q.getReponseCorrecte()) ;
        SpanLabel lbProp1 = new SpanLabel("Prop 1 :".toUpperCase() + q.getReponseFausse1()) ;
        SpanLabel lbProp2 = new SpanLabel("Prop 2 : ".toUpperCase() + q.getReponseFausse2()) ;
        SpanLabel lbPop3 = new SpanLabel("Prop 3 : ".toUpperCase() + q.getReponseFausse3()) ;
        SpanLabel lbNote = new SpanLabel("Point(s) : ".toUpperCase() + q.getNote()) ;
       Label lbid = new Label(q.getId()+"") ;
       lbid.setVisible(false);     
       
        c1.setUIID("containerQuestion");
       c1.addAll(lbQposee,lbRep,lbProp1,lbProp2,lbPop3,lbNote) ;
       c1.setLeadComponent(lbQposee);
       add(c1 );
       refreshTheme(); 
    }
}
