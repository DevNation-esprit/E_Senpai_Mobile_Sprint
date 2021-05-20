/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;








/**
 *
 * @author 21656
 */
public class Discussion {

    private int id;
    private int id_user1;
    private int id_user2;
    private Date date_discussion;

    public Discussion(int id, int id_user1) {
        this.id=id;
        this.id_user1= id_user1;
    }

    public Discussion(int id, int id_user1, int id_user2, Date date_discussion) {
        this.id = id;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.date_discussion = date_discussion;
    }

    public Discussion() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user1() {
        return id_user1;
    }

    public void setId_user1(int id_user1) {
        this.id_user1 = id_user1;
    }

    public int getId_user2() {
        return id_user2;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }

    public Date getDate_discussion() {
        return date_discussion;
    }

    public void setDate_discussion(Date date_discussion) {
        this.date_discussion = date_discussion;
    }
    
}
