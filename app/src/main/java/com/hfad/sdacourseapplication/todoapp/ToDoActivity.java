package com.hfad.sdacourseapplication.todoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoActivity extends AppCompatActivity implements OnItemStateChanged {

    ToDoListAdapter toDoListAdapter;
    String activityTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toDoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter(toDoListAdapter);
        toDoListAdapter.setOnItemStateChanged(this);

        final EditText editText = (EditText) findViewById(R.id.todo_editText);

        Button addButton = (Button) findViewById(R.id.todo_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length() > 0) {
                    toDoListAdapter.addItem(editText.getText().toString());
                    editText.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_item){
            toDoListAdapter.deleteAllCheckedItem();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemStateChanged(int checkedItemsCount) {
        if(checkedItemsCount > 0){
            getSupportActionBar().setTitle("Checked items: " + checkedItemsCount);
        } else{
            getSupportActionBar().setTitle(activityTitle);
        }
    }
}
