/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.services.FormationService;
import java.io.IOException;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

/**
 *
 * @author wajdi
 */
public class AjoutFormationForm extends BaseForm {

    Form current;
    EncodedImage enc;
  private Resources theme;
    public AjoutFormationForm(Form previous, int idUser) {
        super("Newsfeed", BoxLayout.x());
        current = this;
        setTitle("Ajouter nouvelle formation");
        getContentPane().setScrollVisible(false);
      
        setLayout(BoxLayout.y());

        TextField titre = new TextField("", "entrer titre");
        titre.setUIID("TextFieldBack");
        addStringValue("titre", titre);

        TextArea description = new TextArea("",8,2000);
        description.setUIID("TextFieldBack");
        addStringValue("description", description);

        Button btnAjouter = new Button("Ajouter formation");
        addStringValue("", btnAjouter);

        btnAjouter.addActionListener((e) -> {

            try {

                if (titre.getText() == "" || description.getText() == "") {
                    Dialog.show("Veuillez vérifier les données ", "", "Annuler", "Ok");

                } else {
                    InfiniteProgress ip = new InfiniteProgress();

                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Formation f = new Formation(1, String.valueOf(titre.getText().toString()),
                            String.valueOf(description.getText()).toString());

                    System.out.println("data " + f);
                    FormationService.getInstance().ajouterFormation(f, idUser);

                    iDialog.dispose();
                    refreshTheme();
                    new AjoutSlideForm(previous,idUser).show();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

        add(createLineSeparator(0xeeeeee));

    }

}
