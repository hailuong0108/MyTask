package com.example.floatingactionbutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListTaskAdapter extends RecyclerView.Adapter<ListTaskAdapter.ViewData> {
    ArrayList<ListTask> list ;
    public interface  Selected {
        public void onclickListtask(int item);
    }
    Selected activity ;
    public ListTaskAdapter(ArrayList<ListTask> list, Context context) {
        this.list = list;
        activity= (Selected)context;
    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtask, parent,false);
        return new ViewData(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, int position) {
        holder.tv.setText(list.get(position).getNameList());
        holder.itemView.setTag(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewData extends RecyclerView.ViewHolder {
        TextView tv ;
        public ViewData(@NonNull final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvListTask);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onclickListtask( (list.indexOf(v.getTag())) );
                }
            });

        }
    }

}
