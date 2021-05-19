/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.entities.Slide;
import com.mycompany.myapp.services.FormationService;
import com.mycompany.myapp.services.SlideService;
import java.util.ArrayList;

/**
 *
 * @author wajdi
 */
public class DetailForamtionForm extends BaseForm {

    public DetailForamtionForm(Form previous, Formation f) {
        super("detail de l'evenement", BoxLayout.y());
        TextComponent titre = new TextComponent().label(f.getTitre().toString());
        TextComponent desc = new TextComponent().label(f.getDescription().toString());
        add(titre);

        add(desc);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                g -> previous.showBack()); // Revenir vers l'interface précédente

        ArrayList<Slide> liste = SlideService.getInstance().affichageSlideFormation(f.getId());
        for (Slide s : liste) {
            if (s.getText_slide().equals("") && s.getVideo_slide().equals("")) {
                Button i = new Button(s.getImage_slide());
                i.addActionListener(d -> {
                    new ImageForm(s.getImage_slide()).show();
                });

                add(i);
            } else if (s.getImage_slide().equals("") && s.getVideo_slide().equals("")) {
                Button t = new Button(s.getText_slide());
                t.addActionListener(e -> {
                    FileSystemStorage fs = FileSystemStorage.getInstance();
                    System.out.println(s.getText_slide());     
                    String fileName = fs.getAppHomePath() + s.getText_slide();
                    if (!fs.exists(fileName)) {
                        Util.downloadUrlToFile("http://localhost/img/", s.getText_slide(), true);
                    }
                    Display.getInstance().execute(fileName);
                });
                add(t);
            } else {
                Button v = new Button(s.getVideo_slide());
                 v.addActionListener(d -> {
                    new VideoForm(s.getVideo_slide()).show();
                });
                add(v);
            }

            createLineSeparator();

        }
    }

}
