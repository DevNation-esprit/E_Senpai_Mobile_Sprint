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
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wajdi
 */
public class FormationService {

    public static FormationService instance = null;
public static boolean res = true;
    private ConnectionRequest req;

    public static FormationService getInstance() {
        if (instance == null) {
            instance = new FormationService();
        }

        return instance;

    }

    public FormationService() {
        req = new ConnectionRequest();

    }

    public void ajouterFormation(Formation form, int idUser) {

        String url = Statics.BASE_URL + "/new?idFormateur=" + idUser + "&titre=" + form.getTitre() + "&description=" + form.getDescription();

        req.setUrl(url);

        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public Formation detailFormation(int id, Formation f) {
        String url = Statics.BASE_URL + "/singleshowevent" + id;
        req.setUrl(url);
        String str = new String((req.getResponseData()));
        req.addResponseListener((fr -> {

            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                f.setTitre(obj.get("titre").toString());
                f.setDescription(obj.get("description").toString());

            } catch (IOException ex) {
                System.out.println("error mon ami:" + ex.getMessage());
            }

            System.out.println("Data ====" + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);

        return f;

    }

    public ArrayList<Formation> affichageFormation() {
        ArrayList<Formation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/Allmform";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapFormas = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapFormas.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Formation f = new Formation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String titreFormation = obj.get("titre").toString();
                        float noteFormation = Float.parseFloat(obj.get("note").toString());
                        String description = obj.get("description").toString();
                        f.setId((int) id);
                        f.setTitre(titreFormation);
                        f.setNote((int) noteFormation);
                        f.setDescription(description);
                        result.add(f);

                    }

                } catch (Exception ex) {
                    System.out.println("");
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
     public ArrayList<Formation> affichageMesFormations(int idUser) {
        ArrayList<Formation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/indexFormM/" + idUser;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvents.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                         Formation f = new Formation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String titreFormation = obj.get("titre").toString();
                        float noteFormation = Float.parseFloat(obj.get("note").toString());
                        String description = obj.get("description").toString();
                        f.setId((int) id);
                        f.setTitre(titreFormation);
                        f.setNote((int) noteFormation);
                        f.setDescription(description);
                        result.add(f);

                    }

                } catch (Exception ex) {
                    System.out.println("");
                }
            }
        });
               NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean deleteFormation(int idEvent) {
        String url = Statics.BASE_URL + "/delete/" + idEvent;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return res;

    }
     public boolean updateFormation(Formation formation, int idUser) {
        String url = Statics.BASE_URL + "/edit/?id="+formation.getId()+"&titre=" + formation.getTitre()+ "&description=" + formation.getDescription();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance()
                .addToQueueAndWait(req);
        return res;

    }
}
