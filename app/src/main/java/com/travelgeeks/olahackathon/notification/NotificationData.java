package com.travelgeeks.olahackathon.notification;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class NotificationData {

    private String title;
    private String content;
    private String id;


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public String getId() {
        return id;
    }

    public NotificationData(String id) {
        this.id = id;
        this.title = "Title";
        this.content = "The text will go here if avialable. The text will go here if avialable.";
    }
}
