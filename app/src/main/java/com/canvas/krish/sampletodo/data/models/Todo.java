package com.canvas.krish.sampletodo.data.models;

import java.sql.Timestamp;

/**
 * Created by krishnakandula on 4/1/17.
 */

public class Todo {
    private String text;
    private boolean completed;
    private Timestamp createdOn;
    private Timestamp completedOn;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
