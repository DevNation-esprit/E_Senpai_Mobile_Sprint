/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Methnani
 */
public class Post {
    
     private int id_post;
    private String caption;

    public Post(int id_post, String caption) {
        this.id_post = id_post;
        this.caption = caption;
    }

    public Post(String caption) {
        this.caption = caption;
    }

    public Post() {
    }
    
    public int getId_post() {
        return id_post;
    }

    public String getCaption() {
        return caption;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", caption=" + caption + '}';
    }
    
    
}
