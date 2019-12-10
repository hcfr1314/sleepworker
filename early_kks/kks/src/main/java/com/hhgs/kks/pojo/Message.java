package com.hhgs.kks.pojo;

public class Message {

    private String status;

    public Message(String status) {
        this.status = status;
    }

    public Message() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status='" + status + '\'' +
                '}';
    }
}
