package com.mokska.accboost.bubume;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mokska.accboost.bubume.Adapter.ListItemAdapter;
import com.mokska.accboost.bubume.model.ToDo;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference db;

    RecyclerView listItem;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;
    AlertDialog dialog;

    public MaterialEditText title, description; //public because I want access from ListAdapter
    public boolean isUpdate = false; // flag to check if update or is add new
    public String idUpdate ="" // ID of item need to be updated.

    ListItemAdapter adapter;

    List<ToDo> toDoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init firebase database
        db = FirebaseDatabase.getInstance().getReference();

        ///View
        dialog = new SpotsDialog(this);
        title  = (MaterialEditText)findViewById(R.id.title);
        description  = (MaterialEditText)findViewById(R.id.description);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new
                if(!isUpdate) {
                    setData(title.getText().toString(), description.getText().toString())
                }

            }
        });

        listItem = (RecyclerView)findViewById(R.id.listTodo);
        listItem.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listItem.setLayoutManager(layoutManager);


        loadData(); // Load data from firebase database


    }

    private void setData(String title, String description) {
        //Random id
        String id = UUID.randomUUID().toString();
        Map<String,Object> todo = new HashMap<>();
        todo.put("id",id);
        todo.put("title", title);
        todo.put("description", description);;


    }

    private void loadData() {
        //later
    }
}
