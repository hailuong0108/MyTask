package com.example.floatingactionbutton;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTab extends Fragment {

    RecyclerView recyclerView;
   public static ObjectAdapter2 adapter;
    View v ;
    public DoneTab() {
        // Required empty public constructor
    }

//    @Override
    public void onResume() {

      adapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return v=inflater.inflate(R.layout.fragment_done_task, container, false);
    }
    public void notify1(){
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = v.findViewById(R.id.idList2);
        adapter = new ObjectAdapter2(Applications.list2,this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
//

    }
}
