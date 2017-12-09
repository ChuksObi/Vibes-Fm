package com.example.chuks.vibefmbenin.Model;

/**
 * Created by chuks on 9/23/2017.
 */

public class PodList {

    String podtitle;
    String poddate;
    String podlink;
    String podID;
    String podimage;
    String podtime;
    String podoap;
    String poddescription;

    public PodList(){

    }



    public PodList(String podtitle, String poddate, String podlink, String podID,
                   String podimage, String podtime, String podoap,
                   String poddescription) {
        this.podtitle = podtitle;
        this.poddate = poddate;
        this.podlink = podlink;
        this.podID = podID;
        this.podimage = podimage;
        this.podtime = podtime;
        this.podoap = podoap;
        this.poddescription = poddescription;
    }

    public String getPodimage() {
        return podimage;
    }

    public String getPodtime() {
        return podtime;
    }


    public String getPodoap() {
        return podoap;
    }

    public String getPoddescription() {
        return poddescription;
    }

    public String getPodtitle() {
        return podtitle;
    }

    public String getPoddate() {
        return poddate;
    }

    public String getPodlink() {
        return podlink;
    }

    public String getPodID() {
        return podID;
    }






}
