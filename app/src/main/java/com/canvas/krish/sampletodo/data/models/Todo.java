package com.canvas.krish.sampletodo.data.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by krishnakandula on 4/1/17.
 */

public class Todo {
    private UUID uuid;
    private String text;
    private boolean completed;
    private Timestamp createdOn;
    private Timestamp completedOn;

    public Todo(){
        UUID id = UUID.randomUUID();
        setUuid(id);
        completed = false;
        createdOn = new Timestamp(System.currentTimeMillis());
        completedOn = null;
    }

    public Todo(UUID uuid, String text, boolean completed, Date createdOn, Date completedOn){
        this.uuid = uuid;
        this.text = text;
        this.completed = completed;
        // TODO: 4/2/2017 Change createdOn and completedOn to Date types 
//        this.createdOn = createdOn;
//        this.completedOn = completedOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Timestamp completedOn) {
        this.completedOn = completedOn;
    }
}
