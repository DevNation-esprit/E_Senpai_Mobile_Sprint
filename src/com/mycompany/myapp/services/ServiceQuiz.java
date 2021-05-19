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
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author damos
 */
public class ServiceQuiz {
    private ArrayList<Quiz> quizzes ;
    private static ServiceQuiz instance = null ;
    private boolean resultOK ;
    private ConnectionRequest req;
    Quiz q ;
    
    private ServiceQuiz(){
        req = new ConnectionRequest() ;
    }
    
    public static ServiceQuiz getInstance(){
        if(instance == null)
            instance = new ServiceQuiz() ;
       return instance ;
    }
    
    public ArrayList<Quiz> getListQuiz(){
        String url = Statics.BASE_URL+"quiz_front_json/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizzes = parseQuiz(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return quizzes ;
    }
    
     public ArrayList<Quiz> getListQuizFormateur(int idFormateur){
        String url = Statics.BASE_URL+"quiz_front_json/quiz_formateur/"+idFormateur;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizzes = parseQuiz(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return quizzes ;
    }
    
    public Quiz getQuiz(int idQuiz){
        String url = Statics.BASE_URL+"quiz_front_json/show_quiz/"+idQuiz;
        req.setUrl(url);
        req.setPost(false);
        q = new Quiz() ;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                q = parseSingleQuiz(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return q ;
    }
    
    public boolean addQuiz(Quiz q,int idFormateur) {
       String url = Statics.BASE_URL+"quiz_front_json/addNewQuiz?id="+idFormateur+"&sujet="+q.getSujet();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean updateQuiz(Quiz q,int idFormateur) {
       String url = Statics.BASE_URL+"quiz_front_json/updateQuiz?id="+q.getId()+"&sujet="+q.getSujet()+"&idF="+idFormateur;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean deleteQuiz(Quiz q,int idFormateur) {
       String url = Statics.BASE_URL+"quiz_front_json/deleteQuiz?id="+q.getId()+"&idF="+idFormateur;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public Quiz parseSingleQuiz(String jsonTxt){
        try { 
            q = new Quiz() ;
            JSONParser j = new JSONParser() ;
            Map<String,Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            float id = Float.parseFloat(quizsListJson.get("id").toString());
            q.setId((int)id);
            q.setSujet(quizsListJson.get("sujet").toString());
            
        } catch (IOException e) {
        }
        return q ;
    }
    
    public ArrayList<Quiz> parseQuiz(String jsonTxt){
        try {
            quizzes = new ArrayList<>() ;          
            JSONParser j = new JSONParser() ;
            Map<String,Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)quizsListJson.get("root");
            if(list.size() == 1){
                q = new Quiz() ;
                float id = Float.parseFloat(list.get(0).get("id").toString());
                q.setId((int)id);
                q.setSujet(list.get(0).get("sujet").toString());
                quizzes.add(q );
                return quizzes ;
            }
            else if(list.isEmpty())
            {
                return quizzes ;
            }
            else{                
                for(Map<String,Object> obj : list){
                    q = new Quiz();
                    float id = Float.parseFloat(obj.get("id").toString());
                    q.setId((int)id);
                    q.setSujet(obj.get("sujet").toString());
                    quizzes.add(q);
                }
            }
            
            
        } catch (IOException e) {
        }
        return quizzes ;
    }
   
}
