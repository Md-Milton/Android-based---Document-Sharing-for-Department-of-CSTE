package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 11/7/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String>items = new ArrayList<>();
    ArrayList<String>urls=new ArrayList<>();

    public void update(String name,String url){
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();//refreshes the recycler view automatically
    }


    public MyAdapter(RecyclerView recyclerView,Context context, ArrayList<String>items , ArrayList<String>urls) {
        this.context = context;
        this.items = items;
        this.recyclerView = recyclerView;
        this.urls=urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//to crate view for recycler list item
       View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            //initialize the elements of individual items;
        holder.nameofFile.setText(items.get(position).toString());

    }

    @Override
    public int getItemCount() {//return the numbe rof items
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameofFile;

        public ViewHolder(final View itemView){//represent individual items
            super(itemView);
            nameofFile =  (TextView)itemView.findViewById(R.id.nameOfFile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildLayoutPosition(view);
                    Intent intent = new Intent();
                    intent.setType("*/*");
                    //intent.setType(Intent.ACTION_VIEW);//denote that we are going to view something
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
        }
    }
}
