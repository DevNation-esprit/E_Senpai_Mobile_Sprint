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
public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String dateNaissance;
    private String email;
    private String password;
    private String role;
    private String login;
    private String status;
    private String photoProfil;
    private String biography;
    
    
    public User(){
        
    }

    public User(int id, String nom, String prenom, String sexe, String dateNaissance, String email, String password, String role, String login, String status, String photoProfil, String biography, String curriculumVitae) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.role = role;
        this.login = login;
        this.status = status;
        this.photoProfil = photoProfil;
        this.biography = biography;
    }

    public User(String nom, String prenom, String sexe, String dateNaissance, String email, String password, String role, String login, String status, String photoProfil, String biography, String curriculumVitae) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.role = role;
        this.login = login;
        this.status = status;
        this.photoProfil = photoProfil;
        this.biography = biography;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    
    
}
