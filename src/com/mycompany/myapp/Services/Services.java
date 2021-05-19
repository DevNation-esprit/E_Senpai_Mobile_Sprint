/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utilities.Lien;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Methnani
 */
public class Services {
    
    public ArrayList<Blog> listeb;
    public ArrayList<Post> listep;
    
    
    public static Services instance = null;
    public ConnectionRequest req;
    public boolean var;
    
    public static Services getInstance(){
    if (instance == null)
    
        instance = new Services();
    return instance;
    }
    
    public Services(){
        req = new ConnectionRequest();
    }
    
    
    
    public boolean ajoutBlog (Blog b){
        
        String url = Lien.BASE_URL+"/blog/addBlog/new?titre="+b.getTitre()+"&contenu="+b.getContenu();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                var = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
//            String str = new String(req.getResponseData());
//            System.out.println("data ="+str);
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        Message m = new Message("Body of message");
        String textAttachmentUri = null;
        m.getAttachments().put(textAttachmentUri, "text/plain");
        String imageAttachmentUri = null;
        m.getAttachments().put(imageAttachmentUri, "image/png");
        Display.getInstance().sendMessage(new String[] {"amani.methnani@esprit.tn"}, "Subject of message", m);
        
        
        
        return var;
    }
    
    public boolean ajoutPost (Post p){
        
        String url = Lien.BASE_URL+"/client/addPost/new?caption="+p.getCaption();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                var = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
//            String str = new String(req.getResponseData());
//            System.out.println("data ="+str);
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return var;
    }
    
    
    public ArrayList<Blog> parseblog(String jsonText){
        try {
            listeb = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> clientBlogJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)clientBlogJson.get("root");
            
            
            for(Map<String,Object> obj : list){
                
                Blog b = new Blog();
                float id = Float.parseFloat(obj.get("id").toString());
                        b.setId_blog((int)id);
                        b.setTitre(obj.get("titre").toString());
                        b.setContenu(obj.get("contenu").toString());
                        
                listeb.add(b);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return listeb;
    }
    
//    public ArrayList<Blog> parsepost(String jsonText){
//        try {
//            listep = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> postListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)postListJson.get("root");
//            
//            
//            for(Map<String,Object> obj : list){
//                
//                Blog p = new Blog();
//                float id = Float.parseFloat(obj.get("id").toString());
//                        p.setId_blog((int)id);
//                        p.setTitre(obj.get("Titre").toString());
//                        p.setContenu(obj.get("contenu").toString());
//                        
//                        
//                listeb.add(p);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        
//        return listeb;
//    }
    
    
    public ArrayList<Blog> afficheBlog(){
        String url = Lien.BASE_URL+"/blog/Blogshow";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listeb = parseblog(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listeb;
    }
    
//    public ArrayList<Post> affichePost(){
//        String url = Lien.BASE_URL+"/client/Postshow";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                listep = parsepost(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return listep;
//    }

    
    
    
    



    public boolean SuppBlog(int id){
        
        String url = Lien.BASE_URL+"/blog/suppBlog/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return var;
    }
    
    public boolean SuppPost(int id){
        
        String url = Lien.BASE_URL+"/client/suppPost/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return var;
    }
    
    
    public boolean modifBlog(Blog b,int id){
        
       String url = Lien.BASE_URL+"/client/editClient/"+id+"?titre="+b.getTitre()+"&contenu="+b.getContenu();
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               var = req.getResponseCode() == 200;
               req.removeResponseListener(this);
       }
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       return var;
       
    }  
       
    
    public boolean modifPost(Post p,int id){
        
       String url = Lien.BASE_URL+"/post/editPost/"+id+"?titre="+p.getCaption();
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               var = req.getResponseCode() == 200;
               req.removeResponseListener(this);
       }
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       return var;
       
    }    
}
    

