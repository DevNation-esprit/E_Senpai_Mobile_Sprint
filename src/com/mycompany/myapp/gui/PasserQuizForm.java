/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.services.ServiceQuestion;
import com.mycompany.myapp.services.ServiceQuiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author damos
 */
public class PasserQuizForm extends Form{
    Form current; 
    private Quiz quiz = new Quiz();
    private ArrayList<Question> questions ;
    private HashMap<String,String> reponses = new HashMap<>() ; 
    private int currentIndex = 0 ;
    private int note,nbrReponse ;
    
    Label lbSujet = new Label() ;
    Label lbQposee ;
    RadioButton rbtn1  ;
    RadioButton rbtn2  ;
    RadioButton rbtn3  ;
    RadioButton rbtn4  ;
    Label lbNote = new Label() ;
    
    Button btnNext = new Button("Next") ;
    Button btnPrev = new Button("Prev") ;
    Button btnValider = new Button("Valider") ;
    Button btnReesayer = new Button("Réessayer") ;
       
    
    Container c1  ;
    Container c2  ;
    Container c3  ;    
    
    public PasserQuizForm(Form previous,int idQuiz){
        current = this ;
        quiz = ServiceQuiz.getInstance().getQuiz(idQuiz );
        questions = ServiceQuestion.getInstance().getQuestionByQuiz(idQuiz );
        quiz.setQuestions(questions);
        setTitle("Passer quiz");
        setLayout(BoxLayout.y());       
        this.showQuestion(currentIndex);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());     
        showNextQuestion();
        showPrevQuestion() ;
        validerQuiz() ;
            
    }
    
    private void showQuestion(int index){
        Question q = questions.get(index) ; 
        c1 = new Container( new BoxLayout(BoxLayout.Y_AXIS)) ;
        c2 = new Container( new BoxLayout(BoxLayout.X_AXIS)) ;
        c3 = new Container( new BoxLayout(BoxLayout.X_AXIS)) ;            
        c3.addAll(btnPrev,btnNext) ;
        
        Label title = new Label(quiz.getSujet()) ;
        title.setUIID("title") ;
        add(title);       
               
        lbQposee = new Label((index+1)+ "/-"+ q.getQuestionPosee());
        c2.add(lbQposee) ;
        
        rbtn1 = new RadioButton(q.getReponseFausse1());
        rbtn2 = new RadioButton(q.getReponseFausse2());
        rbtn3 = new RadioButton(q.getReponseFausse3());
        rbtn4 = new RadioButton(q.getReponseCorrecte());
        
        ButtonGroup rbtn = new ButtonGroup() ;
        rbtn.addAll(rbtn4,rbtn1,rbtn2,rbtn3);
        
        Random rd = new Random() ;
        int randomNumber =  rd.nextInt(4) ;
        switch(randomNumber){
            case 0 : 
               c1.addAll(rbtn4,rbtn1,rbtn2,rbtn3);
                break;
            case 1 : 
                c1.addAll(rbtn1,rbtn4,rbtn2,rbtn3);
                break;
            case 2 : 
                c1.addAll(rbtn2,rbtn1,rbtn4,rbtn3);
                break;
            case 3 : 
                c1.addAll(rbtn3,rbtn1,rbtn2,rbtn4);
                break;
            default :
                break;
        }
        c1.setUIID("rbtns"); 
        c2.setUIID("Qposee");
        btnNext.setUIID("btn"); 
        btnPrev.setUIID("btn");
        btnValider.setUIID("btn");
        addAll(c2,c1,c3) ;
        add(btnValider) ;
        btnValider.setEnabled(false);
        refreshTheme();
        rbtn.clearSelection();  
        updateSelectedRadioButton() ;
        
        rbtn.addActionListener((evt) -> {
          String reponse = rbtn.getSelected().getText() ;
          reponses.put(q.getQuestionPosee(),reponse) ;
       });
           
    }
    
    private void showNextQuestion(){
         btnNext.addActionListener((evt) -> {
            c3.removeAll();
            c1.removeAll();
            c2.removeAll(); 
            removeAll();
           if(currentIndex >=0 && currentIndex < questions.size()-1){
            currentIndex += 1 ;
            showQuestion(currentIndex);
            btnNext.setEnabled(true);
            btnPrev.setEnabled(true);
            btnValider.setEnabled(false);
        }else{
           btnPrev.setEnabled(true);
           btnNext.setEnabled(false);
            showQuestion(questions.size()-1); 
            btnValider.setEnabled(true);
        }
        updateSelectedRadioButton() ;
       });
    }
    
    
    private void showPrevQuestion(){
        btnPrev.addActionListener((evt) -> {
        c3.removeAll();
        c1.removeAll();
        c2.removeAll();
          removeAll();
          if(currentIndex > 0 && currentIndex < questions.size()){
            currentIndex -= 1 ;
            showQuestion(currentIndex);
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);
            btnValider.setEnabled(false);
        }
        else{
            btnPrev.setEnabled(false);
            btnNext.setEnabled(true);
            btnValider.setEnabled(false);
            showQuestion(0);
            
        } 
          updateSelectedRadioButton() ;
       });
    }
    
    private void updateSelectedRadioButton(){
        if(!reponses.isEmpty()){
            if(reponses.containsValue(rbtn1.getText()))
                rbtn1.setSelected(true);
            else if(reponses.containsValue(rbtn2.getText()))
                rbtn2.setSelected(true);
            else if(reponses.containsValue(rbtn3.getText()))
                rbtn3.setSelected(true);
            else if(reponses.containsValue(rbtn4.getText()))
                rbtn4.setSelected(true);  
        }
       
    }
    
    private void validerQuiz(){
        btnValider.addActionListener((evt) -> {
            if(Dialog.show("Valider Quiz","voulez-vous valider votre reponse ?", "oui", "non")){
               btnValider.setEnabled(false);  
               note = 0 ; nbrReponse = 0 ;
                for (Map.Entry<String, String> entry : reponses.entrySet()) {
                   String key = entry.getKey();
                   String value = entry.getValue();

                    for (int i = 0; i < questions.size(); i++) {
                        if(value.equals(questions.get(i).getReponseCorrecte()) &&
                                key.equals(questions.get(i).getQuestionPosee())){
                            note += questions.get(i).getNote() ;
                            nbrReponse += 1 ;
                        }

                    }                   
               }
              int percent = (nbrReponse*100)/quiz.getQuestions().size();
              if(percent == 100){
                  if(Dialog.show(" Quiz : " + quiz.getSujet(),
                          "Pourcentage bonnes reponses " + percent + "%"+"\nNote obtenue : " + note + "/" + quiz.getTotalNote(),
                          "acceuil","")){
                      new QuizFrontForm().showBack();
                  }
              }
              else{
                  if(Dialog.show(" Quiz : " + quiz.getSujet(),
                          "Pourcentage bonnes reponses " + percent + "%"+"\nNote obtenue : " + note + "/" + quiz.getTotalNote(),
                          "Réessayer","Correction")){
                      reponses = new HashMap<>() ;
                      currentIndex = 0 ;
                      c3.removeAll();
                      c1.removeAll();
                      c2.removeAll();
                      removeAll();
                      showQuestion(currentIndex);
                      
                  }else{
                      reponses = new HashMap<>() ;
                      currentIndex = 0 ;
                      c3.removeAll();
                      c1.removeAll();
                      c2.removeAll();
                      removeAll();
                      for(Question q : questions){
                          reponses.put(q.getQuestionPosee(),q.getReponseCorrecte()) ;
                      }
                      showQuestion(currentIndex);
                  }
              }
           }
        });
    }
    
}
