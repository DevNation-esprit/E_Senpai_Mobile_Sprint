/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Methnani
 */
public class Home extends Form {
    
    Form current;
     
     public Home(){
         
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Blogs & Posts");
        setLayout(BoxLayout.y());

        add(new Label(""));
        Button btnAddBlog = new Button("Ajouter un Blog");
        Button btnListBlog = new Button("Afficher les Blogs");
        Button btnAddPost = new Button("Ajouter un Post");
        Button btnListPost = new Button("Afficher les Posts");

        btnAddBlog.addActionListener(e -> new AddBlog(current).show());
        btnAddPost.addActionListener(e -> new AddPost(current).show());
        btnListBlog.addActionListener(e -> new ShowBlog(current).show());
//        btnListPost.addActionListener(e -> new ShowPost(current).show());
        addAll(btnAddBlog, btnAddPost,btnListBlog,btnListPost);
     }
    
    
    
    
}
