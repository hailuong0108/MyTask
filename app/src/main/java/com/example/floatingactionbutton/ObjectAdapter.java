package com.example.floatingactionbutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewData> {
ArrayList<Object> list;
    int temp;
public interface Selected{
    public void onClickItem(int item);
    public void onClickButtonDelete(int item);}
    public Selected activity;
    public ObjectAdapter(ArrayList<Object> list, Context context){
        activity = (Selected) context;
        this.list = list;
    }
    public ObjectAdapter(ArrayList<Object> list){

        this.list = list;
    }
    public void renew(){
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);

    return new ViewData(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, final int position) {
    holder.itemView.setTag(list.get(position));
    holder.tv.setText(list.get(position).getName()+" " + position);
         final Object object =(Object) list.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
//        list.remove()
//               remove(object);
                new Applications().addListDone(list.get(position));
                list.remove(position);
                notifyItemRemoved( position);
                notifyItemRangeChanged(position,list.size());// thông báo thay đổi phạm vi của danh sách
                activity.onClickButtonDelete(position);

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewData extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView tv;

         public ViewData(@NonNull View itemView) {
             super(itemView);
             tv = itemView.findViewById(R.id.tv);
             imageView = itemView.findViewById(R.id.iv);


             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     activity.onClickItem(list.indexOf((Object) view.getTag()) );
                 }
             });



             // ghi de
         }
     }

}
