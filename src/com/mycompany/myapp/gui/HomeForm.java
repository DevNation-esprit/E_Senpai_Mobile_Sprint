/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author wajdi
 */
public class HomeForm extends BaseForm {

    Form current;

    public HomeForm(int idUser) {
        current = this;
        setTitle("Acceuil formateur");
        setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToSideMenu("Formation", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 new HomeForm(idUser).show();
            }
        });
        add(new Label("Choisir une option"));
        Button btnAjoutForm = new Button("Ajouter une formation");
        Button btnListeForm = new Button("Liste des formations");
        Button btnMesForm = new Button("Mes formations");
        btnAjoutForm.addActionListener(e -> new AjoutFormationForm(current, idUser).show());
        btnListeForm.addActionListener(e1 -> new ListFormationForm(current).show());
        btnMesForm.addActionListener(e2 -> new MesFormationsFormateurForm(current,idUser).show());
        addAll(btnAjoutForm, btnListeForm, btnMesForm);

    }
}
