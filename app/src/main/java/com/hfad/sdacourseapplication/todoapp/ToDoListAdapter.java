package com.hfad.sdacourseapplication.todoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hfad.sdacourseapplication.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {

    private List<ToDoListItem> items = new ArrayList<>();
    private OnItemStateChanged checkListener;

    public void setOnItemStateChanged(OnItemStateChanged checkListener) {
        this.checkListener = checkListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ToDoListItem listItem = items.get(position);
        holder.textView.setText(items.get(position).getText());
        holder.checkBox.setChecked(listItem.isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
                if(checkListener != null){
                    checkListener.OnItemStateChanged(getChceckedItemsCount());
                }
            }
        });
    }

    private int getChceckedItemsCount(){
        int count = 0;
        for (ToDoListItem item:items) {
            if(item.isChecked()){
                count++;
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(String item){
        items.add(new ToDoListItem(item));
        notifyDataSetChanged();
    }

    public void deleteAllCheckedItem() {
        for (int i = 0; i<items.size(); i++){
            if(items.get(i).isChecked()) {
                items.remove(i);
            }
        }
        notifyDataSetChanged();
        if(checkListener != null){
            checkListener.OnItemStateChanged(getChceckedItemsCount());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CheckBox checkBox;
        public MyViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.list_item);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
