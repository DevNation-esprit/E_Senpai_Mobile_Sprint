/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.URL;
import com.codename1.ui.Dialog;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.entities.Slide;
import static com.mycompany.myapp.services.FormationService.res;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wajdi
 */
public class SlideService {

    public static SlideService instance = null;

    private ConnectionRequest req;
    int id;
    Slide s;

    public static SlideService getInstance() {
        if (instance == null) {
            instance = new SlideService();
        }

        return instance;

    }

    public SlideService() {
        req = new ConnectionRequest();

    }

    public void ajouterSlide(File file, String nameFile) {

        MultipartRequest request = new MultipartRequest();

        String url = Statics.BASE_URL + "/mnewSlide";
        request.setUrl(url);
        request.setPost(true);

        try {
            String urll = file.toString();

            System.out.println(nameFile);
            request.addData("file", urll, "application/json");
            request.addArgument("name", nameFile);

        } catch (IOException ex) {

        }

        NetworkManager.getInstance().addToQueueAndWait(request);

    }

    public ArrayList<Slide> affichageSlideFormation(int idFormation) {
        ArrayList<Slide> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/AllmSlide/" + idFormation;
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
                        Slide s = new Slide();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String imageSlide = obj.get("imageSlide").toString();
                        String videoSlide = obj.get("videoSlide").toString();
                        String textSlide = obj.get("textSlide").toString();
                        float ordre = Float.parseFloat(obj.get("ordre").toString());

                        s.setId((int) id);
                        s.setImage_slide(imageSlide);
                        s.setVideo_slide(videoSlide);
                        s.setText_slide(textSlide);
                        s.setOrdre((int) ordre);

                        result.add(s);

                    }

                } catch (Exception ex) {
                    System.out.println("");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

}
