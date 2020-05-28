package com.example.floatingactionbutton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContractDatabase {
    public final static String keyIdList= "id_list";
    public final  static String keyNameList= "name_list";
    public final  static String keyId= "_id";
    public final  static String keyDate ="date";
    public final  static String keyNameTask ="nameTask";
    public final  static String keyDescription= "description";
    public final static String keyPriority= "priority";
    public final static int databaseVertion= 1;

    public final  static String nameDatabase = "databasetask";
    public final static String tableListTask= "tablelist";
    public final static String tableTask= "tabletask";

    private SQLiteDatabase dbOur ;
    private DbOpen dbHelper;
    private Context context;

    public ContractDatabase (Context context ){
        this.context= context;
    }

    public class DbOpen extends SQLiteOpenHelper {
        public DbOpen(@Nullable Context context) {
            super(context, nameDatabase, null,databaseVertion );
        }



        @Override
        public void onCreate(SQLiteDatabase db) {


            String createTableListTask= "CREATE TABLE "+ tableListTask + "  (    " + keyIdList  +" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    keyNameList +" TEXT NOT NULL )"
                    ;
            String createTableTask="CREATE TABLE " + tableTask +" ( " +keyId + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    keyNameTask +" TEXT NOT NULL, "+
                    keyDescription +" TEXT," +
                    keyPriority +" TEXT , " +
                    keyDate + " INTEGER,   "+
                    keyIdList +" INTEGER NOT NULL , FOREIGN KEY ("+ keyIdList + ") REFERENCES "+ tableListTask+"("+keyIdList+") ) ";

            db.execSQL(createTableListTask);
            db.execSQL(createTableTask);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS "+ tableTask;
        String drop2= "DROP TABLE IF EXISTS " + tableListTask;
        db.execSQL(drop);
        db.execSQL(drop2);}

    }
    public ContractDatabase openDb() throws SQLException {
        dbHelper = new DbOpen(context);
        dbOur= dbHelper.getWritableDatabase();

        return  this;
    }
    public boolean insertListTask(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(keyNameList,name);

        return  dbOur.insert(tableListTask,null,contentValues )>0;


    }
    public boolean inserTask(String name, String description, String priority, int date , String id){
        ContentValues cv = new ContentValues();
        cv.put(keyNameTask,name );
        cv.put(keyDescription,description);
        cv.put(keyDate,date);
        cv.put(keyPriority,priority);
        cv.put(keyIdList,id);

        return dbOur.insert(tableTask,null,cv)>0;
    }
    public void deleteTask(String id ){
        dbOur.delete(tableTask,keyId +"="+id ,null);

    }
    public ArrayList<Object> getDataTask(){
        String []colums = new String[]{keyId,keyNameTask,keyDescription,keyPriority,keyDate,keyIdList};
        Cursor cs = dbOur.query(tableTask,colums,null,null,null,null,null);
//        Cursor cs = dbOur.rawQuery("SElECT * FROM " +tableTask,null);
        int id = cs.getColumnIndex(keyId);
        int name = cs.getColumnIndex(keyNameTask);
        int description= cs.getColumnIndex(keyDescription);
        int priority =cs.getColumnIndex(keyPriority);
        int time = cs.getColumnIndex(keyDate);
        int idList= cs.getColumnIndex(keyIdList);
        ArrayList<Object> result= new ArrayList<>();
        for(cs.moveToFirst(); !cs.isAfterLast();cs.moveToNext())
        {
            result.add(new Object(cs.getString(description),cs.getString(name),cs.getString(priority),cs.getString(id),cs.getString(idList),12));
//

        }
        return result;
    }
    public void deleteListTask(String idListtask){
//        String []colums = new String [] {keyId,keyNameTask,keyDescription,keyPriority,keyDate};
//        ArrayList<String> listId= new ArrayList<>();
//
//        Cursor select= dbOur.query(tableTask,colums,idListtask,null,null,null,null);
//        int id = select.getColumnIndex(keyId);
//        for(select.moveToFirst();  !select.isAfterLast(); select.moveToNext()){
// listId.add(select.getString(id));
//
//        }
//        for(int i = 0; i<listId.size(); i++){
//            deleteTask(listId.get(i));


//        }
         dbOur.delete(tableTask,keyIdList+"=" +idListtask,null);
        dbOur.delete(tableListTask,keyIdList+"=" +idListtask,null);

    }
    public ArrayList<ListTask> getData(){
        ArrayList<ListTask> resutl = new ArrayList<>();
        String []colums = new String[]{keyIdList,keyNameList};
        Cursor cs = dbOur.query(tableListTask,colums,null,null,null,null,null);
        int id= cs.getColumnIndex(keyIdList);
        int name = cs.getColumnIndex(keyNameList);
        for(cs.moveToFirst(); !cs.isAfterLast(); cs.moveToNext()){
            resutl.add(new ListTask(cs.getString(id),cs.getString(name)));
        }
        return  resutl;
    }
    public void closeDb(){
        dbHelper.close();
    }
}
