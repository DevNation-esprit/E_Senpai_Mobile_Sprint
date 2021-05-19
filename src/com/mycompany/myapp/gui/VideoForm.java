/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author wajdi
 */
public class VideoForm extends BaseForm {

    Media img = null;
    MediaPlayer iv = null;
    EncodedImage ec;
    Form current;

    public VideoForm(String video) {
        current = this;
        Button mb = new Button("hello");
        String url = "http://localhost/img/" + video;
        Button t = new Button(video);
        t.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            System.out.println(video);
            String fileName = fs.toNativePath(url);
            if (!fs.exists(fileName)) {
                Util.downloadUrlToFile("http://localhost/img/", video, true);
            }
            Display.getInstance().execute(fileName);
        });
        add(t);
    }
}
