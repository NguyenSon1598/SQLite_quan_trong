package com.example.sqlitequantrong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    DBHelper(Context context){
        super(context,"book_list",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Book(id integer primary key,"+"title text,author text)");
        db.execSQL("CREATE TABLE Author("+
                "id_author integer primary key,"+
                "name text,"+
                "address text,"+
                "email text )");
        db.execSQL("CREATE TABLE Books("+
                "id_book integer primary key,"+
                "title text,"+
                "id_author integer"+
                " constraint id_author references Author(id_author)"+
                "On Delete Cascade ON UPDATE CASCADE )");
        //-------------------------------
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Books");
        db.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(db);
    }
    public int insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author",author.getId_author());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int result = (int)db.insert("Author",null,contentValues);
        db.close();
        return result;
    }
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select* from Book",null);
        if(cursor != null)
            cursor.moveToFirst();
        while(cursor.isAfterLast() == false)
        {
            list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    // chèn book vào cơ sở dữ liệu
    public boolean insertbook (Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_book",book.getId_book());
        contentValues.put("title",book.getTitle());
        contentValues.put("id_author",book.getId_author());
        db.insert("Book",null,contentValues);
        return true;
    }
    //truy vấn dữ liệu
    public ArrayList<Book> getBook (int id){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select* from Book where id =" + id,null);
        if(cursor != null)
            cursor.moveToFirst();
        Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        list.add(book);
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select* from Book",null);
        if(cursor != null)
            cursor.moveToFirst();
        while(cursor.isAfterLast() == false)
        {
            list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteBook(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = db.delete("Book","id"+ "=?",new String[]{String.valueOf(id)});
        db.close();
        if(count>0)
            return true;
        return false;
    }
    public boolean updateBook (Book book){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_book",book.getId_book());
        contentValues.put("title",book.getTitle());
        contentValues.put("id_author",book.getId_author());
        int count = db.update("Book",contentValues,"id" + "=?",new String[]{String.valueOf(book.getId_book())});
        if(count>0)
            return true;
        return false;
    }
    public ArrayList<String> getBookAuthor(int id){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlstr = "select Books.id_author, title"+
                "from Authors inner join Books on"+
                "Authors.id.author = Books.id_author"+
                "where Authors.id_author="+id;
        Cursor cursor = db.rawQuery("select* from Book",null);
        if(cursor != null)
            cursor.moveToFirst();
        while(cursor.isAfterLast() == false)
        {
            list.add(cursor.getInt(0)+"");
            list.add(cursor.getString(1)+"");
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
}
