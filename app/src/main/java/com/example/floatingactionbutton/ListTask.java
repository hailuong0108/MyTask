package com.example.floatingactionbutton;

public class ListTask {
    private String id, nameList ;

    public ListTask(String id, String nameList) {
        this.id = id;
        this.nameList = nameList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
