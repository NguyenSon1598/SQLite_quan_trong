package com.example.sqlitequantrong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Search_activity extends AppCompatActivity {
    EditText etnhapmaso;
    Button btnexit,btnsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        etnhapmaso = (EditText)findViewById(R.id.etnhapmaso);
        btnexit = (Button) findViewById(R.id.btnexit);
        btnexit = (Button) findViewById(R.id.btnsearch);

    }
}
