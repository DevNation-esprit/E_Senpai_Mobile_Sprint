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
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import com.mycomponay.entities.Reclamation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dahmani
 */
public class ServiceReclamation {
    
    public static ServiceReclamation instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
    
    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    private ServiceReclamation() {

        req = new ConnectionRequest();
    }
    
    /*public ArrayList<Reclamation> getAllReclamations(){
        ArrayList<Reclamation> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/users_api";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try{
                    Map<String, Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Reclamation rec = new Reclamation();
                        
                    }
                    
                }catch(Exception e){
                    
                }
            }
            
        });
    }*/
    
    public void ajouterReclamation(Reclamation r){
        
        
        
    }
    
}
