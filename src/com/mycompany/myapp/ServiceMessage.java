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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Discussion;
import com.mycompany.entities.Message;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21656
 */
public class ServiceMessage {

    String json = "";
    //singleton
    public static ServiceMessage instance = null;

    //initialisation connection request 
    private ConnectionRequest req;

    public static ServiceMessage getInstance() {
        if (instance == null) {
            instance = new ServiceMessage();
        }
        return instance;
    }

    public ServiceMessage() {
        req = new ConnectionRequest();

    }

    public void ajoutMessage(Message message) {

        String url = Statics.BASE_URL + "/message/new?IdDiscussion=" + Statics.SelectedDiscussion + "&IdUserEmetteur=6&contenu=" + message.getContenu();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            if (str.equals("Done")) {
                Dialog.show("Succes", "Ajoute avec succes", "OK", null);
            } else {
                Dialog.show("Succes", "Ajoute avec succes", "OK", null);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Message> AffichageMessages() {
        ArrayList<Message> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/message/all";

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapMessage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapMessage.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Message m = new Message();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String contenu = (obj.get("contenu").toString());

//                        String idDiscussion = (obj.get("idDiscussion").toString());
//                        String idUserEmetteur = (obj.get("idUserEmetteur").toString());
                        m.setId((int) id);
                        m.setContenu(contenu);
//                        m.setUser((obj.get("idUserEmetteur")));
//                        m.setDiscussion(obj.get(idDiscussion));

//                        String DateConverter = obj.get("date_discussion").toString().substring(obj.get("date_discussion").toString().indexOf("timestamp") + 10, obj.get("obj").toString().lastIndexOf("}"));
//
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                          d.setDate_discussion(currentTime);
                        result.add(m);
                    }

                } catch (IOException ex) {
                    System.out.println("error : " + ex.toString());
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);

        return result;

    }

//    public Message AffichageMessage(Discussion discussion) {
//
//        Message message = new Message();
//        System.out.println("Diraaaaaaaaaaaaaaaa !  " + discussion.getId());
//        String url = Statics.BASE_URL + "/message/show?id=" + discussion.getId();
//        req.setUrl(url);
//
//        req.addResponseListener(((evt) -> {
//            JSONParser jsonp = new JSONParser();
//            try {
//
//                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) obj.get("root");
//                    for (Map<String, Object> objj : listOfMaps) {
//                        
//                    }
////                message.setId((int) id);
////                message.setContenu(obj.get("contenu").toString());
////                message.setDiscussion(obj.get("contenu").toString());
////                message.setUser(obj.get("contenu").toString());
////                message.setDate_msg(obj.get("contenu").toString());
////                System.out.println("******Voici la discussion de l'id :" +contenu);
//
//            } catch (IOException ex) {
//                System.out.println("error related to sql :  " + ex.getMessage());
//            }
//
//        }));
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return message;
//    }
    //affichage
    public ArrayList<Message> AffichageMessage(Discussion discussion) {
        ArrayList<Message> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/message/show?id=" + Statics.SelectedDiscussion.getId();

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapMessage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println("***** I'M HERE ++" + mapMessage);
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapMessage.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Message m = new Message();
                        float id = Float.parseFloat(obj.get("id").toString());
//                      float idUser1 = Float.parseFloat(obj.get("idUser1").toString());
//                        System.out.println("This -> "+idUser1);
//                        float idUser2 = Float.parseFloat(obj.get("idUser2").toString());
//                        System.out.println("This -> "+idUser2);
                        m.setId((int) id);

                        m.setContenu(obj.get("contenu").toString());
//                        String DateConverter = obj.get("date_discussion").toString().substring(obj.get("date_discussion").toString().indexOf("timestamp") + 10, obj.get("obj").toString().lastIndexOf("}"));
//
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                          d.setDate_discussion(currentTime);

                        result.add(m);
                    }

                } catch (IOException ex) {
                    System.out.println("error : " + ex.toString());
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    public ArrayList<Message> AffichageMessagenew(Discussion discussion) {
        ArrayList<Message> result = new ArrayList<>();
        if (Statics.SelectedDiscussion.getId() != discussion.getId()) {
            String url = Statics.BASE_URL + "/message/show?id=" + Statics.SelectedDiscussion.getId();

            req.setUrl(url);

            req.addResponseListener((evt) -> {
                JSONParser jsonp = new JSONParser();
                     
                    try {
                        Map<String, Object> mapMessage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        System.out.println("***** I'M HERE ++" + mapMessage);
                        List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapMessage.get("root");
                        for (Map<String, Object> obj : listOfMaps) {
                            Message m = new Message();
                            float id = Float.parseFloat(obj.get("id").toString());
//                      float idUser1 = Float.parseFloat(obj.get("idUser1").toString());
//                        System.out.println("This -> "+idUser1);
//                        float idUser2 = Float.parseFloat(obj.get("idUser2").toString());
//                        System.out.println("This -> "+idUser2);
                            m.setId((int) id);

                            m.setContenu(obj.get("contenu").toString());
//                        String DateConverter = obj.get("date_discussion").toString().substring(obj.get("date_discussion").toString().indexOf("timestamp") + 10, obj.get("obj").toString().lastIndexOf("}"));
//
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                          d.setDate_discussion(currentTime);

                            result.add(m);
                        }

                    } catch (IOException ex) {
                        System.out.println("error : " + ex.toString());
                    }
                
            });
                
                    
          

            NetworkManager.getInstance().addToQueueAndWait(req);
        }

        return result;

    }

    public void deleteMsg(Message m) {
        String url = Statics.BASE_URL + "/message/delete?id=" + m.getId();
        req.setUrl(url);
        req.addResponseListener((evt) -> {
            json = new String(req.getResponseData()) + "";

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void modifMsg(Message m, TextField gui_Text_Field_Message) {
        String url = Statics.BASE_URL + "/message/edit?id=" + m.getId() + "&contenu=" + gui_Text_Field_Message.getText();
        req.setUrl(url);
        req.addResponseListener((evt) -> {
            json = new String(req.getResponseData()) + "";
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
