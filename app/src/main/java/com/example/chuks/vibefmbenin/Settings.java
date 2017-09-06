package com.example.chuks.vibefmbenin;


public class Settings {

    //All editable settings will be displayed here.

    //Name of Radio Station
    private final String radioName = "VibesFM 97.3";

    // Url of the radio stream
    private String radioStreamUrl = "http://74.50.122.103:9516/";

    //Email address of the radio station
    private String emailAddress = "obiichukwuma@gmail.com";

    //Phone no
    private String phoneNo = "08133072164";

    //Website Url
    private String websiteUrl = "http://vibesfm.com.ng";

    //Main notification message
    private String notificationMessage = "You're listening to VibesFM 97.3";

    //Play notification message
    private String playNotificationMessage = "Starting VibesFM 97.3";

    public String getRadioName() {
        return radioName;
    }

    public String getRadioStreamUrl() {
        return radioStreamUrl;
    }

    public void setRadioStreamUrl(String radioStreamUrl) {
        this.radioStreamUrl = radioStreamUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getPlayNotificationMessage() {
        return playNotificationMessage;
    }

    public void setPlayNotificationMessage(String playNotificationMessage) {
        this.playNotificationMessage = playNotificationMessage;
    }

    //
}
