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
public class Abonnement {

    private int id_formation;
    private int id_etudiant;
    private String date_abonnement;
    private int rated;

    public Abonnement() {
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getDate_abonnement() {
        return date_abonnement;
    }

    public void setDate_abonnement(String date_abonnement) {
        this.date_abonnement = date_abonnement;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

   

  

    @Override
    public String toString() {
        return "Abonnement{" + "id_formation=" + id_formation + ", id_etudiant=" + id_etudiant + ", date_abonnement=" + date_abonnement + '}';
    }

}
