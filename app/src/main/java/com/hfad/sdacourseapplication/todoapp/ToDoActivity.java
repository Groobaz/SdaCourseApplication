package com.hfad.sdacourseapplication.todoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hfad.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
    }
}
