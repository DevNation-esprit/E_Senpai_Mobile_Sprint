/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceQuestion;
import com.mycompany.myapp.services.ServiceQuiz;
import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class QuizDetailsForm extends Form{
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;
    Quiz quiz ;
    public QuizDetailsForm(Form previous,int idquiz) {
        current = this ;
        setTitle("Questions");
        setLayout(BoxLayout.y());
        Button btnAdddQuest = new Button("Add new Question") ;
        add(btnAdddQuest) ;
        Label title = new Label("Questions Quiz") ;
        title.setUIID("title") ;
        add(title); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
        
        quiz = ServiceQuiz.getInstance().getQuiz(idquiz);
        ArrayList<Question> questions = ServiceQuestion.getInstance().getQuestionByQuiz(idquiz) ;
        for(Question q : questions){
            addItem(q) ;
        }
        
        btnAdddQuest.addActionListener((evt) -> {
            new AddQuestionForm(previous, quiz.getId()).show(); 
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
       
       lbQposee.addPointerPressedListener((evt) -> {
           String cmd = Dialog.show("Details", "Question ", new Command("update"),
                   new Command("delete"),new Command("fermer")).getCommandName() ;
                switch (cmd) {
                    case "delete":
                        if(ServiceQuestion.getInstance().deleteQuestion(q,quiz.getId())){
                            Dialog.show("Success","Question supprimée avec success",new Command("OK"));
                        refreshTheme() ;
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        break;
                    case "update":
                       new updateQuestionForm(current,quiz.getId(), q.getId()).show();
                        break;
                    case "fermer" : 
                        break;
                    default:
                        break;
                }  
       });
       c1.setUIID("containerQuestion");
       c1.addAll(lbQposee,lbRep,lbProp1,lbProp2,lbPop3,lbNote) ;
       c1.setLeadComponent(lbQposee);
       add(c1 );
       refreshTheme(); 
              
    }
}
