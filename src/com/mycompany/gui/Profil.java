/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.utils.Statics;
import com.mycomponay.entities.User;
import java.io.IOException;

/**
 *
 * @author Dahmani
 */
public class Profil extends Form {

    public Profil(User u, Resources res) {
        setTitle("Profil");
        setLayout(BoxLayout.y());
        Container padd = new Container();
        padd.setPreferredSize(new Dimension(8, 30));
        add(padd);

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth() / 3, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, u.getPhotoProfil(),
                Statics.ASSETS_URL+u.getPhotoProfil());
        background.fetch();
        add(background);
        add(new Label(""+u.getNom()));
        add(new Label(""+u.getPrenom()));
        add(new Label(""+u.getDateNaissance()));
        add(new Label(""+u.getSexe()));
        add(new Label(""+u.getEmail()));
        add(new Label(""+u.getRole()));
        add(new Label(""+u.getBiography()));
        Button btnPosts = new Button("Mes Posts");
        add(btnPosts);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new Home(u,res).show());
    }

}
