package com.example.servingwebcontent.model;

public class UrlModel {
    private String inputURL = "";
    private String userSessionId = "";


    public String getInputURL() {
        return inputURL;
    }

    public void setInputURL(String inputURL) {
        this.inputURL = inputURL;
    }

    public void setUserSessionId(String userSessionId){
        this.userSessionId = userSessionId;
    }

    public String getUserSessionId(){
        return this.userSessionId;
    }
}