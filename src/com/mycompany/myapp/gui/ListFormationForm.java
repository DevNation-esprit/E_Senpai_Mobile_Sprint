/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.services.FormationService;
import java.util.ArrayList;

/**
 *
 * @author wajdi
 */
public class ListFormationForm extends BaseForm {

     Form current;
    public ListFormationForm(Form previous) {
        current = this;
        setTitle("Liste Formations");
        setLayout(BoxLayout.y());
        Container cnt = new Container();
        ArrayList<Formation> liste = FormationService.getInstance().affichageFormation();
        for (Formation f : liste) {
            Button t = new Button(f.getTitre());

            add(t);

            createLineSeparator();

            t.addActionListener(d -> {
                 new DetailForamtionForm(current,  f).show();
            });
            TextField searchField;
            searchField = new TextField("", "recherche une événement");
            searchField.getHintLabel().setUIID("Title");
            searchField.setUIID("Title");
            getToolbar().setTitleComponent(searchField);

            searchField.addDataChangeListener((i1, i2) -> {
                String s = searchField.getText();
                if (s.length() < 1) {
                    for (Component cmp : getContentPane()) {
                        cmp.setHidden(false);
                        cmp.setVisible(true);
                    }

                } else {
                    s = s.toLowerCase();
                    for (Component cmp : getContentPane()) {
                       
                        String val = ((Button)((cmp))).getText();
                        System.out.println(val);
                        boolean show = val != null && val.toLowerCase().indexOf(s) > -1;
                        cmp.setHidden(!show);
                        cmp.setVisible(show);
                    }
                }
                });

            }
            
            
            

        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                g -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
