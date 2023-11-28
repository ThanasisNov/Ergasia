package Accounts;

import java.io.Serializable;

public class Message implements Serializable {
    private boolean isRead;
    private String sender;
    private String receiver;
    private String body;
    private int Message_ID;

    public void setMessage_ID(int message_ID) {
        Message_ID = message_ID;
    }

    public int getMessage_ID() {
        return Message_ID;
    }

    public String getBody() {
        return body;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
    public boolean isRead()
    {
        return isRead;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
