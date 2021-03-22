package com.yash10019coder.whatsapp.models;

public class MessageModel {
    String uID, message;
    Long timestamp;


    public Long getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public MessageModel(String uID, String message, Long timestamp) {
        this.uID = uID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageModel(String uID, String message) {
        this.uID = uID;
        this.message = message;
    }

    public String getuID() {
        return uID;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimestamp(Long time) {
        return timestamp;
    }

    public MessageModel() {
    }


}
