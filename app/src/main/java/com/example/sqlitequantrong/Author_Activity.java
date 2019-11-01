package com.example.sqlitequantrong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Author_Activity extends AppCompatActivity {
    EditText etnhapmaso,etnhaphoten,etnhapdiachi,etnhapemail;
    Button btnselect,btnsave,btndelete,btnupdate,btnexit;
    GridView gridview1;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_);
        etnhapmaso = (EditText)findViewById(R.id.etnhapmaso);
        etnhaphoten = (EditText)findViewById(R.id.etnhaphoten);
        etnhapdiachi = (EditText)findViewById(R.id.etnhapdiachi);
        etnhapemail = (EditText)findViewById(R.id.etnhapemail);
        gridview1 = (GridView)findViewById(R.id.gridview1);
        dbhelper = new DBHelper(this);
        btnsave = (Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(etnhapmaso.getText().toString()));
                author.setName(etnhaphoten.getText().toString());
                author.setAddress(etnhapdiachi.getText().toString());
                author.setEmail(etnhapemail.getText().toString());
                if(dbhelper.insertAuthor(author)>0)
                    Toast.makeText(getApplicationContext(),"Đã lưu thành công",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Lưu không thành công",Toast.LENGTH_LONG).show();
            }
        });
        btnexit = (Button)findViewById(R.id.btnexit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnselect = (Button)findViewById(R.id.btnselect);
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Author> list_author = new ArrayList<>();
                list_author = dbhelper.getAllAuthor();
                for(Author author:list_author){
                    list.add(author.getId_author()+"");
                    list.add(author.getName()+"");
                    list.add(author.getAddress()+"");
                    list.add(author.getEmail()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Author_Activity.this,android.R.layout.simple_list_item_1,list);
                gridview1.setAdapter(adapter);
            }
        });
    }
}
