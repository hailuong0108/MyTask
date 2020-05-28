package com.example.floatingactionbutton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.os.Build.VERSION_CODES.O;

public class EditActivity extends AppCompatActivity {
    EditText etName, etDescription;
    ImageView imBtnUpdate;
    Object obj;
    TaskTab taskTab;
    TextView tvShowDate, tvShowHour;
    private String time2;
    int y, m,d;ContractDatabase db;
String id;
    BottomNavigationView bottomNavigationView;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
etName = findViewById(R.id.etName);
tvShowDate= findViewById(R.id.tvTime);
etDescription = findViewById(R.id.etDescription);
imBtnUpdate = findViewById(R.id.btnUpdate);
try {
    Intent i = getIntent();
    id = i.getStringExtra("id");
    obj = (Object) i.getSerializableExtra("obj");
}
catch (Exception e )
{

}
        Toast.makeText(this, " id : "+ id, Toast.LENGTH_SHORT).show();

   db= new ContractDatabase(this);
        if(obj!=null) {
            etName.setText(obj.getName());
            etDescription.setText(obj.getDescription());
            imBtnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        for(int j  = 0 ; j <     Applications.list.size(); j++ ){

                            if( Applications.list.get(j).getName().equals(obj.getName())&&Applications.list.get(j).getDescription().equals(obj.getDescription())) {
                                Applications.list.get(j).setName(etName.getText().toString());
                                Applications.list.get(j).setDescription(etDescription.getText().toString());
                                obj=null;
//            Toast.makeText(EditActivity.this, "Success", Toast.LENGTH_LONG).show();
                                etName.setText("");
                                etDescription.setText("");
                                FragmentManager fragmentManager =getSupportFragmentManager();
                                taskTab=(TaskTab) fragmentManager.findFragmentById(R.id.idtasktab);
                                taskTab.adapter.notifyDataSetChanged();
                                startActivity(new Intent(EditActivity.this, MainActivity.class));

                            }
                            else{

                            }
                        }
                    }catch (Exception e) {
                        Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            imBtnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        db.openDb();
                        db.inserTask(etName.getText().toString(),etDescription.getText().toString(),"low",0,id);
                        db.closeDb();
                        Toast.makeText(EditActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        etName.setText("");
                        etDescription.setText("");
                        FragmentManager fragmentManager =getSupportFragmentManager();
                        taskTab=(TaskTab) fragmentManager.findFragmentById(R.id.idtasktab);
                        taskTab.adapter.notifyDataSetChanged();
                        startActivity(new Intent(EditActivity.this, MainActivity.class));
                    }catch (Exception e) {
                        Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }




        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);




    }
    public void setTime(View v){
        final Calendar calendar1 = Calendar.getInstance();
        int hour= Calendar.HOUR;
        int minute = Calendar.MINUTE;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                tvShowHour.setText(new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(calendar1.getTime()));

            }
        },hour,minute,true);
        timePickerDialog.show();
        Toast.makeText(this, "click button settime", Toast.LENGTH_SHORT).show();

    }
    public void clickPriority(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.select_priority);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heigt = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        WindowManager.LayoutParams wd = new WindowManager.LayoutParams();
        wd.copyFrom(dialog.getWindow().getAttributes());
        wd.height= WindowManager.LayoutParams.WRAP_CONTENT;
        wd.width= (int)(width*0.7f);
        dialog.getWindow().setAttributes(wd);
        dialog.show();
    }
    @RequiresApi(api = O)
    public void clickDate(){

        // các thuộc tính( biến) phải khai báo là biến final khi truy cập lớp vô danh (Anonymous Class) ở đây thường là các phương thức Override
        // lý do là vì nó liên quan đến tính bao đóng trong java
        final Calendar calendar = Calendar.getInstance();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pick_time_dialog);
        DatePicker datePicker = dialog.findViewById(R.id.idDatePicker);
        tvShowHour = dialog.findViewById(R.id.tvHour);



        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final String time= simpleDateFormat.format(calendar.getTime());
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                y=year;
                m=monthOfYear;
                calendar.set(year,monthOfYear,dayOfMonth);
                tvShowDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        });
        Button btnSave,btnCancel ;
        btnSave = dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams windows = new WindowManager.LayoutParams();
        windows.copyFrom(dialog.getWindow().getAttributes()); // copy thực thể của dialog
        windows.width= WindowManager.LayoutParams.MATCH_PARENT;// cài đặt rộng tối đa
        windows.height= WindowManager.LayoutParams.MATCH_PARENT;// dài
        dialog.getWindow().setAttributes(windows);// cài đặt lại thực thể
        dialog.show();




    }
        private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.itemNode : {
                        clickDate();

//                        final Calendar calendar = Calendar.getInstance();
//                      int ngay=  calendar.get(Calendar.DATE);
//                      int thang = calendar.get(Calendar.MONTH);
//                      int nam = calendar.get(Calendar.YEAR);
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                calendar.set(year,month,dayOfMonth);
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                                tvShowDate.setText(simpleDateFormat.format(calendar.getTime()));
//
//                            }
//                        }, nam , thang, ngay);
//                        datePickerDialog.show();

                        Toast.makeText(EditActivity.this, "item time", Toast.LENGTH_SHORT).show();
                    }break;
                    case R.id.itemPriority :
                    {
                        clickPriority();
//                        menuItem.setIcon(R.drawable.set_time);

//                        menuItem.setTitleCondensed("test");
//                        menuItem.setContentDescription("hello");

                        Toast.makeText(EditActivity.this, "case R.id.itemPriority\n ", Toast.LENGTH_SHORT).show();
                    }break;
                    case R.id.itemSelectdevice :
                    {
                        Toast.makeText(EditActivity.this, "itemSelectdevice", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
            return true;
        }
    };
}
