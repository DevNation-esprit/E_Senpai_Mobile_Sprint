/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.services.FormationService;

/**
 *
 * @author wajdi
 */
public class ModifierFormationForm extends BaseForm {

    public ModifierFormationForm(int idUser, Form previous, Formation f) {
        super("Modifier evenement", BoxLayout.y());
        getContentPane().setScrollVisible(false);

        TextField titre = new TextField(f.getTitre(), "titre", 20, TextField.ANY);
        titre.setUIID("TextFieldBack");

        TextField description = new TextField(f.getDescription(), "description", 20, TextField.ANY);
        description.setUIID("TextFieldBack");

        Button btnmodifier = new Button("Modifier");
        btnmodifier.addPointerPressedListener(m -> {
            f.setId_formateur(idUser);
            f.setTitre(titre.getText());
            f.setDescription(description.getText());
            System.out.println(f.toString());

            if (FormationService.getInstance().updateFormation(f, idUser)) {

                new HomeForm(idUser).show();
            }
        });
        Button btnannuler = new Button("Annuler");
        btnannuler.addActionListener(a -> {
            new ListFormationForm(previous).show();

        });
        Label l1 = new Label("Titre");

        Label l4 = new Label("description");

        Container content = BoxLayout.encloseY(
                l1,
                titre,
                l4,
                description,
                btnmodifier,
                btnannuler
        );

        add(content);
        show();
      
    }
}
