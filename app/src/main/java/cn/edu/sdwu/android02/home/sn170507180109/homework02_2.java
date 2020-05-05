package cn.edu.sdwu.android02.home.sn170507180109;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.id;


public class homework02_2 extends AppCompatActivity {
    private ArrayList list;
    private SimpleAdapter simpleAdapter;
    private MyOpenHelper myOpenHelper;
    private ArrayList userList;
    private HashMap hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework02_2);

        userList=new ArrayList();

        myOpenHelper=new MyOpenHelper(this);

        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        //使用完毕，将数据库关闭
        sqLiteDatabase.close();

    }

    public void insert(View view){
        EditText editText1=(EditText)findViewById(R.id.h2_et1);
        EditText editText2=(EditText)findViewById(R.id.h2_et2);
        EditText editText3=(EditText)findViewById(R.id.h2_et3);
        String et1=editText1.getText().toString();
        String et2=editText2.getText().toString();
        String et3=editText3.getText().toString();
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务
            ContentValues contentValues=new ContentValues();
            contentValues.put("username",et1);
            contentValues.put("userage",et2);
            contentValues.put("userheight",et3);
            sqLiteDatabase.insert("user",null,contentValues);
            //参数：向哪个表插入数据，对空的列怎么处理，ContentValues类型
            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void select(View view){
        //以只读方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();

        try {
            //返回游标类型
            Cursor cursor=sqLiteDatabase.rawQuery("select * from user",null);
            list= new ArrayList();
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String username=cursor.getString(cursor.getColumnIndex("username"));
                String userage=cursor.getString(cursor.getColumnIndex("userage"));
                String userheight=cursor.getString(cursor.getColumnIndex("userheight"));


                //hashmap保存多项数据
                HashMap hashMap=new HashMap();
                hashMap.put("id",id);
                hashMap.put("name",username);
                hashMap.put("age",userage);
                hashMap.put("height",userheight);
                list.add(hashMap);

            }
            //实例化适配器
            simpleAdapter=new SimpleAdapter(this,list,R.layout.layout_homework02_2_userinfo,new String[]{"id","name","age","height"},new int[]{R.id.h2_userinfo_id,R.id.h2_userinfo_name,R.id.h2_userinfo_age,R.id.h2_userinfo_height});//上下文，list类型的数据，资源(展示每个商品的界面),显示的数据的名称，整型数组
            //设置到listview
            ListView listView=(ListView)findViewById(R.id.h2_lv1);
            listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            cursor.close();
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void clear(View view){
        ListView listView=(ListView)findViewById(R.id.h2_lv1);
        listView.setAdapter(null);
    }

    public void delete(View view){
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            sqLiteDatabase.delete("user",null,null);//数据表名字，where条件,问号具体值

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void deleteid(View view){
        EditText editText=(EditText)findViewById(R.id.h2_et4);
        String deleteContent=editText.getText().toString();
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            //sqLiteDatabase.delete("student","id=?",new String[]{"1"});//数据表名字，where条件,问号具体值
            sqLiteDatabase.delete("user","id=?",new String[]{deleteContent});

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void selectid(View view){
        EditText editText=(EditText)findViewById(R.id.h2_et4);
        String selectContent=editText.getText().toString();
        //以只读方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();

        try {
            list= new ArrayList();
            //返回游标类型
            Cursor cursor=sqLiteDatabase.rawQuery("select * from user where id=?",new String[]{selectContent});
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String username=cursor.getString(cursor.getColumnIndex("username"));
                String userage=cursor.getString(cursor.getColumnIndex("userage"));
                String userheight=cursor.getString(cursor.getColumnIndex("userheight"));
                //hashmap保存多项数据
                HashMap hashMap=new HashMap();
                hashMap.put("id",id);
                hashMap.put("name",username);
                hashMap.put("age",userage);
                hashMap.put("height",userheight);
                list.add(hashMap);
            }
            //实例化适配器
            simpleAdapter=new SimpleAdapter(this,list,R.layout.layout_homework02_2_userinfo,new String[]{"id","name","age","height"},new int[]{R.id.h2_userinfo_id,R.id.h2_userinfo_name,R.id.h2_userinfo_age,R.id.h2_userinfo_height});//上下文，list类型的数据，资源(展示每个商品的界面),显示的数据的名称，整型数组
            //设置到listview
            ListView listView=(ListView)findViewById(R.id.h2_lv1);
            listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            cursor.close();
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void updateid(View view){
        EditText editText1=(EditText)findViewById(R.id.h2_et1);
        EditText editText2=(EditText)findViewById(R.id.h2_et2);
        EditText editText3=(EditText)findViewById(R.id.h2_et3);
        String et1=editText1.getText().toString();
        String et2=editText2.getText().toString();
        String et3=editText3.getText().toString();

        EditText editText=(EditText)findViewById(R.id.h2_et4);
        String selectContent=editText.getText().toString();


        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            ContentValues contentValues=new ContentValues();
            contentValues.put("username",et1);
            contentValues.put("userage",et2);
            contentValues.put("userheight",et3);

            sqLiteDatabase.update("user",contentValues,"id=?",new String[]{selectContent});
            //参数：数据表的名字，更新的内容，查询条件，查询条件

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(homework02_2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }



}