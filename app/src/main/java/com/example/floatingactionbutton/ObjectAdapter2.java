package com.example.floatingactionbutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObjectAdapter2 extends RecyclerView.Adapter<ObjectAdapter2.ViewData> {
ArrayList<Object> list;
   private Applications applications = new Applications();
public interface Selected{
    public void onClickItem2(int item);
    public void onClickButtonDeleteItemDone(int item);}
    public Selected activity;
    public ObjectAdapter2(ArrayList<Object> list, Context context){
        activity = (Selected) context;
        this.list = list;
    }

    public void renew(Object obj){
        this.list.add(obj);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview2,parent,false);

    return new ViewData(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewData holder, final int position) {
    holder.itemView.setTag(list.get(position));
    holder.tv.setText(list.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
//
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ViewData extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView tv;
    Button btn;
         public ViewData(@NonNull View itemView) {
             super(itemView);
             tv = itemView.findViewById(R.id.tv2);
             imageView = itemView.findViewById(R.id.iv2);


             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     activity.onClickItem2(list.indexOf(view.getTag()) );
                 }
             });



             // ghi de
         }
     }

}
