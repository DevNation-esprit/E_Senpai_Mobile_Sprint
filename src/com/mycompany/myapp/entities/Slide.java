/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author wajdi
 */
public class Slide {

    private int id;
    private int id_formation;
    private String video_slide;
    private String image_slide;
    private String text_slide;
    private int ordre;
    private String loc;
    private String url;

    public Slide() {
    }

    public Slide(int id_formation, String video_slide, String image_slide, String text_slide) {
        this.id_formation = id_formation;
        this.video_slide = video_slide;
        this.image_slide = image_slide;
        this.text_slide = text_slide;
    }

    public Slide(String image_slide) {
        this.image_slide = image_slide;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public String getVideo_slide() {
        return video_slide;
    }

    public void setVideo_slide(String video_slide) {
        this.video_slide = video_slide;
    }

    public String getImage_slide() {
        return image_slide;
    }

    public void setImage_slide(String image_slide) {
        this.image_slide = image_slide;
    }

    public String getText_slide() {
        return text_slide;
    }

    public void setText_slide(String text_slide) {
        this.text_slide = text_slide;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   

    @Override
    public String toString() {
        return "Slide{" + "id=" + id + ", id_formation=" + id_formation + ", video_slide=" + video_slide + ", image_slide=" + image_slide + ", text_slide=" + text_slide + ", ordre=" + ordre + '}';
    }

}
