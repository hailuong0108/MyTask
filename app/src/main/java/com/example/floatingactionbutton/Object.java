package com.example.floatingactionbutton;

import java.io.Serializable;

public class Object implements Serializable {
    private String description, name, priority,id, idList;
    private int time;



    public Object(String description, String name, String priority, String id, String idList, int time) {
        this.description = description;
        this.name = name;
        this.priority = priority;
        this.id = id;
        this.idList = idList;
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
