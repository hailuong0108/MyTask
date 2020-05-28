package com.example.floatingactionbutton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ObjectAdapter.Selected,ObjectAdapter2.Selected, ListTaskAdapter.Selected {
    TabLayout tbLayOut;
    private FloatingActionButton fab;
    ViewPager viewPager;
    DoneTab doneTab;
    String idListTask;
    PageAdapter pagerAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView recyclerView ;
    static ListTaskAdapter listTaskAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

try {
    ContractDatabase db = new ContractDatabase(this);
    db.openDb();
    ArrayList<ListTask> listx =db.getData();
//
    ArrayList<Object> listO= db.getDataTask();

    if(listx.size()==0){

        db.insertListTask("Task one ");
        db.closeDb();
        db.openDb();
             listx=db.getData();

        new Applications().addListTask(listx.get(0));
        db.closeDb();

    }
    else
    {// đổ dữ liệu từ database ra list
      Applications applications=  new Applications();
        applications.addListTask(listx.get(0));
        applications.addObject(listO);
        db.closeDb();

    }


}catch (SQLiteException e){
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
}
        idListTask= Applications.list3.get(0).getId();
//        Toast.makeText(this, ""+Applications.list3.size(), Toast.LENGTH_SHORT).show();
        listTaskAdapter = new ListTaskAdapter(Applications.list3,this);
        listTaskAdapter.notifyDataSetChanged();



        // 5 bước tạo tab activity  gồm - 3 bước khởi tao  1 bước cài đặt adapter 1 bước cái đặt viewpager
        tbLayOut = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPage);
        pagerAdapter= new PageAdapter(getSupportFragmentManager(),tbLayOut.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tbLayOut.setupWithViewPager(viewPager);
try {
    recyclerView = findViewById(R.id.idRecyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(listTaskAdapter);
        notifyDataTaskTab();
}catch (Exception e ){
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
}





// 6 bước tạo navigation
        drawerLayout = findViewById(R.id.drawelayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);// truyen tham so vao su kien cua draerlayout
        actionBarDrawerToggle.syncState();// xu ly bat dong bo su kien tren
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.naview);
        //setNavigationItemSelectedListener
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.idItemAdd){

                    Toast.makeText(MainActivity.this, " this is item add", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, NewWorkActivity.class );
                    startActivity(intent);
//                    startActivity(new Intent(MainActivity.this, EditActivity.class));
                }
                else if(id==R.id.idSetting){
                    Toast.makeText(MainActivity.this, " this is setting", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        try {

            new Applications().addListDone(new Object(" ", "khoi tao ","","","",0));
        }
       catch (Exception e)
       {
           Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();

       }

        fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Click fab", Toast.LENGTH_SHORT).show();
                try {

                    Intent intent = new Intent(MainActivity.this,com.example.floatingactionbutton.EditActivity.class);
                    intent.putExtra("id",idListTask);
                    startActivity(intent);
                }
                catch (Exception e )
                {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.idItemDelete :
            {Toast.makeText(MainActivity.this, " this is idItemDelete", Toast.LENGTH_SHORT).show();

            }break;
            case R.id.idItemSort :
            {Toast.makeText(MainActivity.this, " this is idItemSort", Toast.LENGTH_SHORT).show();

            }break;
            case R.id.idItemSkin :
            {Toast.makeText(MainActivity.this, " this is idItemSkin", Toast.LENGTH_SHORT).show();

            }break;
            case R.id.idrename :
            {
                Toast.makeText(MainActivity.this, " this is idrename", Toast.LENGTH_SHORT).show();
            }break;
            default:


        }
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickItem(int item) {
        Toast.makeText(this, Applications.list.get(item).getName(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, EditActivity.class );

        intent.putExtra("obj",  Applications.list.get(item));
        startActivity(intent);
        getIntent().getSerializableExtra("obj");


        Toast.makeText(this, "delete : " +Applications.list.size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItem2(int item) {

    }

    @Override
    public void onClickButtonDeleteItemDone(int item) {

    }

    private void notifyDataTaskTab(){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        doneTab= (DoneTab) fragmentManager.findFragmentById(R.id.iddone);

        doneTab.notify1();
    }
    @Override
    public void onClickButtonDelete(int item) {

//
        try{
notifyDataTaskTab();


        }catch (Exception e){


            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "size list : " + Applications.list2.size(),Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onclickListtask(int item) {

     idListTask   =Applications.list3.get(item).getId();
        Toast.makeText(this, "click "+item, Toast.LENGTH_SHORT).show();
    }
}
