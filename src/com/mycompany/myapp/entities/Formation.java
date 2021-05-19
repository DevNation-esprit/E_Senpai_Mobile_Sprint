/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author wajdi
 */
public class Formation {

    private int id;
    private int id_formateur;
    private String titre;
    private int note;
    private String description;

    public Formation() {
    }

    public Formation(int id_formateur, String titre, String description) {
        this.id_formateur = id_formateur;
        this.titre = titre;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formateur() {
        return id_formateur;
    }

    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
   
    
    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", id_formateur=" + id_formateur + ", titre=" + titre + ", note=" + note + ", description=" + description + '}';
    }
  

}
