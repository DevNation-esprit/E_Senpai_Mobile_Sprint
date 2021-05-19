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
public class Blog {
    
    private int id_blog;
    private String titre;
    private String contenu;

    public Blog() {
    }
    
    public Blog(int id_blog, String titre, String contenu) {
        this.id_blog = id_blog;
        this.titre = titre;
        this.contenu = contenu;
    }

    public Blog(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    public int getId_blog() {
        return id_blog;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Blog{" + "id_blog=" + id_blog + ", titre=" + titre + ", contenu=" + contenu + '}';
    }
    
    
    
}
