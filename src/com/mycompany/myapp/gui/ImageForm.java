/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import java.io.IOException;

/**
 *
 * @author wajdi
 */
public class ImageForm extends BaseForm {

    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec ;
    
    Form current;
    public ImageForm(String image) {
        current = this;
           String url = "http://localhost/img/"+image;
        
        
        SpanLabel titre = new SpanLabel(image);
       
        try {
            ec = EncodedImage.create("/load.png");
            img = URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE);
            iv = new ImageViewer(img);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        add(iv);
    }
    
}
