package com.example.floatingactionbutton;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class Applications extends Application {
    public static ArrayList<Object> list ;
    public static ArrayList<Object> list2 ;
    public static ArrayList<ListTask> list3 ;
    public static Object obj ;

    @Override
    public void onCreate() {
        super.onCreate();
        list =  new ArrayList<>();
        list2= new ArrayList<>();
        list3 = new ArrayList<>();


        list.add(new Object("abc ", "test1","abc","cde","ea",1));





    }


    public void addListTask(ListTask l){
        list3.add(l);
    }
    public void addObject(ArrayList<Object> obj){
        list= obj;
    }
    public void addListDone(Object obj)
    {
        this.list2.add(obj);
    }
    public void addList( ArrayList<Object> list)
    {
        for(int i = 0 ; i <list.size(); i++)
        this.list.add(list.get(i));
    }
}
