/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Test;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author damos
 */
public class ServiceTest {
    private ArrayList<Test> tests ;
    private static ServiceTest instance = null ;
    private boolean  resultOK ;
    private ConnectionRequest req ;
    private Test t ;
    
    private ServiceTest(){
        req = new ConnectionRequest() ;            
    }
    
    public static ServiceTest getInstance(){
        if(instance == null)
            instance = new ServiceTest() ;
        
      return instance ;
    }
    
    public ArrayList<Test> getListTestFormateur(int idFormateur){
        String url = Statics.BASE_URL+"quiz_front_json/test_formateur/"+idFormateur;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tests = parseTest(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests ;
    }
    
    public ArrayList<Test> parseTest(String jsonTxt){
        try {
            tests = new ArrayList<>() ;          
            JSONParser j = new JSONParser() ;
            Map<String,Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)quizsListJson.get("root");
            if(list.size() == 1){
                t = new Test() ;
                float id = Float.parseFloat(list.get(0).get("id").toString());
                t.setId((int)id);
                t.setSujet(list.get(0).get("sujet").toString());
                t.setNbEtudiantsPasses((int)Float.parseFloat(list.get(0).get("nbEtudiantPasses").toString()));
                t.setNbEtudiantsAdmis((int)Float.parseFloat(list.get(0).get("nbEtudiantsAdmis").toString()));
                t.setDuree((int)Float.parseFloat(list.get(0).get("duree").toString()));
                t.setFormation(list.get(0).get("formation").toString());
                tests.add(t);
                return tests ;
            }
            else if(list.isEmpty())
            {
                return tests ;
            }
            else{                
                for(Map<String,Object> obj : list){
                    t = new Test();
                    float id = Float.parseFloat(obj.get("id").toString());
                    t.setId((int)id);
                    t.setSujet(obj.get("sujet").toString());
                    t.setNbEtudiantsPasses((int)Float.parseFloat(obj.get("nbEtudiantPasses").toString()));
                    t.setNbEtudiantsAdmis((int)Float.parseFloat(obj.get("nbEtudiantsAdmis").toString()));
                    t.setDuree((int)Float.parseFloat(obj.get("duree").toString()));
                    t.setFormation(obj.get("formation").toString());
                    tests.add(t);
                }
            }
            
            
        } catch (IOException e) {
        }
        return tests ;
    }
    
    
}
