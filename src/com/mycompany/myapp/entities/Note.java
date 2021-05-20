/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author damos
 */
public class Note {
    private String nomEtudiant ;
    private int noteObtenue ;

    public Note() {
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public int getNoteObtenue() {
        return noteObtenue;
    }

    public void setNoteObtenue(int noteObtenue) {
        this.noteObtenue = noteObtenue;
    }
    
    
}
