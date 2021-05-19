/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;
import com.mycomponay.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Dahmani
 */
public class GestionUsers extends Form{
    
    public GestionUsers(Resources res, User u){
        setTitle("Utilisateurs | ID ADMIN "+u.getId());
        setLayout(BoxLayout.y());
        
        ArrayList<User> listUsers =ServiceUser.getInstance().affichageUsers();
        for (User us : listUsers){
            Container c = new Container();
            c.setLayout(BoxLayout.x());
            c.add(new Label(us.getNom()+" "+us.getId()));
            Container paddX = new Container();
            paddX.setPreferredSize(new Dimension(10,8));
            Button btn = new Button("Voir");
            btn.addActionListener((e) -> new DetailUser(res, u, us,this).show());
            c.add(paddX);
            c.add(btn);
            add(c);
            Container padd = new Container();
            padd.setPreferredSize(new Dimension(8,10));
            add(padd);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new HomeAdmin(u,res).show());
    }
    
}
