/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.URL;
import com.codename1.io.URL.HttpURLConnection;
import com.codename1.io.URL.URLConnection;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Discussion;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.projectmonkey.object.mapper.ObjectMapper;

/**
 *
 * @author 21656
 */
public class ServiceDiscussion {

    //singleton
    public static ServiceDiscussion instance = null;

    //initialisation connection request 
    private ConnectionRequest req;

    public static ServiceDiscussion getInstance() {
        if (instance == null) {
            instance = new ServiceDiscussion();
        }
        return instance;
    }

    public ServiceDiscussion() {
        req = new ConnectionRequest();

    }

    //ajout
    public void ajoutDiscussion(Discussion discussion) {

        String url = Statics.BASE_URL + "/discussion/new?" + discussion.getId() + "/" + discussion.getDate_discussion() + "/" + discussion.getId_user1() + "/" + discussion.getId_user2();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //affichage
    public ArrayList<Discussion> AffichageDiscussions() {
        ArrayList<Discussion> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/discussion/all";

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                                try {

                    Map<String, Object> mapDiscussion = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                         
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapDiscussion.get("root");
                   
                    for (Map<String, Object> obj : listOfMaps) {
                        Discussion d = new Discussion();    
                        
                        float id = Float.parseFloat(obj.get("id").toString());

//                      float idUser1 = Float.parseFloat(obj.get("idUser1").toString());
//                        System.out.println("This -> "+idUser1);
//                        float idUser2 = Float.parseFloat(obj.get("idUser2").toString());
//                        System.out.println("This -> "+idUser2);
                        d.setId((int) id);
                        
                       

//                        String DateConverter = obj.get("date_discussion").toString().substring(obj.get("date_discussion").toString().indexOf("timestamp") + 10, obj.get("obj").toString().lastIndexOf("}"));
//
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                          d.setDate_discussion(currentTime);

                        result.add(d);
                    }

                } catch (IOException ex) {
                    System.out.println("error : " + ex.toString());
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    public Discussion afficherDiscussion(int id, Discussion discussion) {

        String url = Statics.BASE_URL + "/discussion/" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                discussion.setId_user1(Integer.parseInt(obj.get("id_user1").toString()));
                discussion.setId_user2(Integer.parseInt(obj.get("id_user2").toString()));

            } catch (IOException ex) {
                System.out.println("error related to sql :  " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);

        return discussion;
    }

    public void getreqest() throws IOException {
        String urlString = Statics.BASE_URL + "/discussion";

        try {

            URL url = new URL(urlString);
            URLConnection conn;
            conn = url.openConnection();
            InputStream is = conn.getInputStream();

//         (
        } catch (URISyntaxException ex) {
            System.out.println(" Error : " + ex.toString());
        }

    }

}
