/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.services.FormationService;
import java.util.ArrayList;

/**
 *
 * @author wajdi
 */
public class MesFormationsFormateurForm extends BaseForm {
    Form current;
    public MesFormationsFormateurForm(Form previous, int idUser) {
        setTitle("Mes Formations");
        setLayout(BoxLayout.y());
        ArrayList<Formation> liste = FormationService.getInstance().affichageMesFormations(idUser);
        for (Formation f : liste) {
            Button t = new Button(f.getTitre());

            add(t);

            createLineSeparator();

            FloatingActionButton lSupp = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
            FloatingActionButton lMod = FloatingActionButton.createFAB(FontImage.MATERIAL_EDIT);
            add(lSupp);
            add(lMod);

            lSupp.addActionListener(l -> {

                Dialog dig = new Dialog("Suppression");
                if (dig.show("Supprimer", "Vous voulez supprimer cet Formation?", "Annuler", "Oui")) {

                    dig.dispose();

                } else {

                    dig.dispose();
                    if (FormationService.getInstance().deleteFormation(f.getId())) {
                        new HomeForm(idUser).show();
                    }

                }

            });
            lMod.addActionListener(l -> {
                new ModifierFormationForm(idUser,current, f).show();

            });

            t.addActionListener(d -> {
                new DetailForamtionForm(previous, f).show();

            });

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                g -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
