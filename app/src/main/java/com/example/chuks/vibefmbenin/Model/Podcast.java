package com.example.chuks.vibefmbenin.Model;



public class Podcast {

    private String image;
    private String text;
    private String description;
    private String oap;
    private String episode;

    public Podcast() {
    }




    public Podcast(String Image, String Text, String Description, String Oap, String Episode) {

        image = Image;
        text = Text;
        description = Description;
        oap = Oap;
        episode = Episode;

    }

    public String getOap() {
        return oap;
    }

    public String getEpisode() {
        return episode;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }


}