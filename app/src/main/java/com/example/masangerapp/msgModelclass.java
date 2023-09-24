package com.example.masangerapp;

public class msgModelclass {
    String massage;
    String senderid;
    long timestamp;


    public msgModelclass(String massage, String senderid, long timestamp) {
        this.massage = massage;
        this.senderid = senderid;
        this.timestamp = timestamp;
    }

    public msgModelclass() {
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
