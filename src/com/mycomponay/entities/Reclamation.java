/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomponay.entities;

/**
 *
 * @author Dahmani
 */
public class Reclamation {
    
    private int id;
    private int idUser;
    private int idAdminTrait;
    private String statut;
    private String sujet;
    private String contenu;

    public Reclamation(int idUser, int idAdminTrait, String statut, String sujet, String contenu) {
        this.idUser = idUser;
        this.idAdminTrait = idAdminTrait;
        this.statut = statut;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public Reclamation(int id, int idUser, int idAdminTrait, String statut, String sujet, String contenu) {
        this.id = id;
        this.idUser = idUser;
        this.idAdminTrait = idAdminTrait;
        this.statut = statut;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public Reclamation() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAdminTrait() {
        return idAdminTrait;
    }

    public void setIdAdminTrait(int idAdminTrait) {
        this.idAdminTrait = idAdminTrait;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
    
}
