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
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author damos
 */
public class ServiceQuestion {
    ArrayList<Question> questions ;
    public static ServiceQuestion instance = null ;
    public boolean resultOK ;
    private ConnectionRequest req ;
    private Question q ;
    
    private ServiceQuestion(){
        req = new ConnectionRequest() ;
    }
    
    public static ServiceQuestion getInstance(){
        if(instance == null)
            instance = new ServiceQuestion() ;
       return instance ;
    }
    
    public ArrayList<Question> getQuestionByQuiz(int idQuiz){
        String url = Statics.BASE_URL+"quiz_front_json/get_questions/"+idQuiz;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return questions ;
    }
    
    public ArrayList<Question> getQuestionByTest(int idTest){
        String url = Statics.BASE_URL+"quiz_front_json/get_questions_test/"+idTest;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return questions ;
    }
    
    public Question getQuestionById(int id){
        String url = Statics.BASE_URL+"quiz_front_json/get_one_question/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {              
             q = parseSingleQuestion(new String(req.getResponseData()));              
             req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return q ;
    }
    
    public boolean addQuestion(Question q,int idQ) {
       String url = Statics.BASE_URL+"quiz_front_json/addNewQuestion?qposee="+q.getQuestionPosee()+
                             "&rep="+q.getReponseCorrecte()+"&prop1="+q.getReponseFausse1()+
                             "&prop2="+q.getReponseFausse2()+"&prop3="+q.getReponseFausse3()+
                             "&note="+q.getNote()+"&idQ="+idQ ;
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
    
     public boolean updateQuestion(Question q,int idQ) {
       String url = Statics.BASE_URL+"quiz_front_json/updateQuestion?qposee="+q.getQuestionPosee()+
                             "&rep="+q.getReponseCorrecte()+"&prop1="+q.getReponseFausse1()+
                             "&prop2="+q.getReponseFausse2()+"&prop3="+q.getReponseFausse3()+
                             "&note="+q.getNote()+"&idQ="+idQ+"&id="+q.getId() ;
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
     
     public Question parseSingleQuestion(String jsonTxt){
            questions = new ArrayList<>() ;
             JSONParser j = new JSONParser() ;
             Map<String,Object> questionListJson;
        try {
            questionListJson = j.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));       
            
                   q = new Question();
                    float id = Float.parseFloat(questionListJson.get("id").toString());
                    q.setId((int)id);
                    q.setQuestionPosee(questionListJson.get("designation").toString());
                    q.setReponseCorrecte(questionListJson.get("reponseCorrecte").toString());
                    q.setReponseFausse1(questionListJson.get("reponseFausse1").toString());
                    q.setReponseFausse2(questionListJson.get("reponseFausse2").toString());
                    q.setReponseFausse3(questionListJson.get("reponseFausse3").toString());
                    float note = Float.parseFloat(questionListJson.get("note").toString());
                    q.setNote((int)note);
                    } catch (IOException ex) {
           
        }
        return q ;
       
     }
    
    public ArrayList<Question> parseQuestion(String jsonTxt){
        try {
            questions = new ArrayList<>() ;
             JSONParser j = new JSONParser() ;
             Map<String,Object> questionListJson = j.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
             List<Map<String,Object>> list = (List<Map<String,Object>>)questionListJson.get("root");
             if(list.isEmpty()){
                   q = new Question();
                    float id = Float.parseFloat(questionListJson.get("id").toString());
                    q.setId((int)id);
                    q.setQuestionPosee(questionListJson.get("designation").toString());
                    q.setReponseCorrecte(questionListJson.get("reponseCorrecte").toString());
                    q.setReponseFausse1(questionListJson.get("reponseFausse1").toString());
                    q.setReponseFausse2(questionListJson.get("reponseFausse2").toString());
                    q.setReponseFausse3(questionListJson.get("reponseFausse3").toString());
                    float note = Float.parseFloat(questionListJson.get("note").toString());
                    q.setNote((int)note);
                    questions.add(q);
                   return questions ;
             }
             else if(list.size() == 1){
                  q = new Question();
                    float id = Float.parseFloat(list.get(0).get("id").toString());
                    q.setId((int)id);
                    q.setQuestionPosee(list.get(0).get("designation").toString());
                    q.setReponseCorrecte(list.get(0).get("reponseCorrecte").toString());
                    q.setReponseFausse1(list.get(0).get("reponseFausse1").toString());
                    q.setReponseFausse2(list.get(0).get("reponseFausse2").toString());
                    q.setReponseFausse3(list.get(0).get("reponseFausse3").toString());
                    float note = Float.parseFloat(list.get(0).get("note").toString());
                    q.setNote((int)note);
                    questions.add(q);
                   return questions ;
             }
             else{
                 for(Map<String,Object> obj : list){
                     q = new Question();
                    float id = Float.parseFloat(obj.get("id").toString());
                    q.setId((int)id);
                    q.setQuestionPosee(obj.get("designation").toString());
                    q.setReponseCorrecte(obj.get("reponseCorrecte").toString());
                    q.setReponseFausse1(obj.get("reponseFausse1").toString());
                    q.setReponseFausse2(obj.get("reponseFausse2").toString());
                    q.setReponseFausse3(obj.get("reponseFausse3").toString());
                    float note = Float.parseFloat(obj.get("note").toString());
                    q.setNote((int)note);
                    questions.add(q);
                }
             }
             
        } catch (IOException e) {
        }
        return questions ;
    }
    
}
