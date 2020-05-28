package com.example.floatingactionbutton;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskTab extends Fragment {
    EditText et;
    ImageView iv;
    RecyclerView recyclerView;

    static ObjectAdapter adapter;
    View v ;
    private FloatingActionButton fab;
    public TaskTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return v=inflater.inflate(R.layout.fragment_task_tab, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        et = v.findViewById(R.id.editText);
        iv = v.findViewById(R.id.imButton);

//        et.setText("add");
        if(et.getText().toString().equals(""))
            iv.setVisibility(View.GONE);
       et.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {
               if(editable.toString().equals(""))
                   iv.setVisibility(View.GONE);
               else
                   iv.setVisibility(View.VISIBLE);

           }
       });
       iv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Applications  applications = new Applications();
               ArrayList<Object> list = new ArrayList<>();
               list.add(new Object(et.getText().toString(),et.getText().toString(),"","","",0));
               applications.addList(list);
               adapter.notifyDataSetChanged();
               et.setText("");
           }
       });
        recyclerView = v.findViewById(R.id.idList);

        adapter = new ObjectAdapter(Applications.list,this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
