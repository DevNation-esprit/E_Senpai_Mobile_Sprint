/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.Test;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceTest;
import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class TestBackForm extends Form {
    Form current; 
    Session s = new Session() ;
    User user = Session.getUser() ;

    public TestBackForm(Form previous) {
        current = this ;
        setTitle("Test");
        setLayout(BoxLayout.y());
        Label title = new Label("Tous les Tests") ;
        title.setUIID("title") ;
        add(title);  
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
        
        ArrayList<Test> tests = ServiceTest.getInstance().getListTestFormateur(user.getId());
        for(Test t : tests){
            addItem(t) ;
        }
    }

    private void addItem(Test t) {
       Container c1 = new Container( new BoxLayout(BoxLayout.Y_AXIS)) ;
       Label lbSujet = new Label("Test : " + t.getSujet()) ;
       Label lbid = new Label(t.getId()+"") ;
       lbid.setVisible(false);
       
       lbSujet.addPointerPressedListener((evt) -> {
           new TestDetailsForm(current, t.getId()).show(); 
       });
       lbSujet.setUIID("lbl");
        c1.setUIID("contQuiz"); 
       c1.add(lbSujet) ;
       c1.setLeadComponent(lbSujet);
       add(c1 );
       refreshTheme(); 
    }
}
