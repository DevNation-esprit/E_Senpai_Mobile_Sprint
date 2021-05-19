/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import com.mycomponay.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dahmani
 */
public class ServiceUser {

    public static ServiceUser instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
    public int currentId;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    private ServiceUser() {

        req = new ConnectionRequest();

    }

    public ArrayList<User> affichageUsers() {
        ArrayList<User> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/users_api";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        User ur = new User();
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String dateNaissance = obj.get("dateNaissance").toString();
                        String sexe = obj.get("sexe").toString();
                        String email = obj.get("email").toString();
                        String role = obj.get("role").toString();
                        String password = obj.get("password").toString();
                        String status = obj.get("status").toString();
                        String photoProfil = obj.get("photoProfil").toString();
                        String biography = obj.get("biography").toString();
                        
                        
                        ur.setId(getUserId(email));
                        ur.setNom(nom);
                        ur.setPrenom(prenom);
                        ur.setDateNaissance(dateNaissance);
                        ur.setSexe(sexe);
                        ur.setStatus(status);
                        ur.setBiography(biography);
                        ur.setEmail(email);
                        ur.setRole(role);
                        ur.setPassword(password);
                        ur.setPhotoProfil(photoProfil);
                        

                        result.add(ur);
                    }
                } catch (Exception e) {
                    System.out.print("");
                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean authentifier(String email, String password) {
        String url = Statics.BASE_URL + "/user_auth?email=" + email + "&password=" + password;

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response = new String(req.getResponseData()) + "";
                System.out.println("" + response);
                req.removeResponseListener(this);
                if (response.equals("\"not ok\"")) {
                    resultOK = false;
                } else {
                    resultOK = true;
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public User getUser(String email, String password) {
        String url = Statics.BASE_URL + "/user_auth?email=" + email + "&password=" + password;
        User u = new User();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(req.getResponseData()) + "";
                System.out.println("" + str);
                req.removeResponseListener(this);
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
                    //u.setId(Integer.parseInt(obj.get("id").toString()) );
                    u.setId(getUserId(obj.get("email").toString()));
                    u.setNom(obj.get("nom").toString());
                    u.setPrenom(obj.get("prenom").toString());
                    u.setEmail(obj.get("email").toString());
                    u.setDateNaissance(obj.get("dateNaissance").toString());
                    u.setSexe(obj.get("sexe").toString());
                    u.setRole(obj.get("role").toString());
                    u.setStatus(obj.get("status").toString());
                    u.setPhotoProfil(obj.get("photoProfil").toString());
                    u.setBiography(obj.get("biography").toString());
                } catch (Exception e) {
                    System.out.println("Pas de Photo de profil ou pas de biography");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //////////////////////////////////////////////////////////////////////////////

        return u;
    }
    
    public int getUserId(String email){
        String url = Statics.BASE_URL + "/users_api_by_email?email=" + email;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response = new String(req.getResponseData()) + "";
                System.out.println("" + response);
                req.removeResponseListener(this);
                JSONParser jsonp = new JSONParser();
                try {
                    currentId = Integer.parseInt(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return currentId;
    }

}
