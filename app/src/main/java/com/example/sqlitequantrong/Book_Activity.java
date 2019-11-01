package com.example.sqlitequantrong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Book_Activity extends AppCompatActivity {
    EditText etnhapmaso,etnhaptieude,ettentacgia;
    Button btnexit,btnselect,btnsave,btndelete,btnupdate;
    GridView gridview1;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);
        etnhapmaso = (EditText)findViewById(R.id.etnhapmaso);
        etnhaptieude = (EditText)findViewById(R.id.etnhaptieude);
        ettentacgia = (EditText)findViewById(R.id.ettentacgia);
        gridview1 = (GridView)findViewById(R.id.gridview1);
        dbhelper = new DBHelper(this);
//        btnsave = (Button)findViewById(R.id.btnsave);
//        btnsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Book book = new Book();
//                book.setId_book(Integer.parseInt(etnhapmaso.getText().toString()));
//                book.setTitle(etnhaptieude.getText().toString());
//                book.setId_author(Integer.parseInt(ettentacgia.getText().toString()));
//                if(dbhelper.insertbook(book))
//                    Toast.makeText(getApplicationContext(),"Đã lưu thành công",Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(getApplicationContext(),"Lưu không thành công",Toast.LENGTH_LONG).show();
//            }
//        });
        btnexit = (Button)findViewById(R.id.btnexit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*btnselect = (Button)findViewById(R.id.btnselect);
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> booklist = new ArrayList<>();

                try{
                    booklist = dbhelper.getBook(Integer.parseInt(etnhapmaso.getText().toString()));

                }catch (Exception e){
                    booklist = dbhelper.getAllBook();
                }
                for(Book b:booklist){
                    list.add(b.getId_book()+"");
                    list.add(b.getTitle()+"");
                    list.add(b.getId_author()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Book_Activity.this,android.R.layout.simple_list_item_1,list);
                gridview1.setAdapter(adapter);
            }
        });*/
        btnselect = (Button)findViewById(R.id.btnselect);
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();
                String uri = "content://com.example.sqlitequantrong";
                Uri book = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(book,null,null,null,"title");
                if(cursor !=null){
                    cursor.moveToFirst();
                    do{
                        list_string.add(cursor.getInt(0)+"");
                        list_string.add(cursor.getString(1));
                        list_string.add(cursor.getInt(2)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Book_Activity.this,android.R.layout.simple_list_item_1,list_string);
                    gridview1.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext(),"Khong co ket qua",Toast.LENGTH_LONG).show();
            }
        });
        btnsave = (Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id_book",etnhapmaso.getText().toString());
                values.put("title",etnhaptieude.getText().toString());
                values.put("id_author",ettentacgia.getText().toString());
                String uri ="content://com.example.sqlitequantrong";
                Uri book = Uri.parse(uri);
                Uri insert_uri = getContentResolver().insert(book,values);
                Toast.makeText(getApplicationContext(),"Luu thanh cong",Toast.LENGTH_LONG).show();
            }
        });
    }

}
