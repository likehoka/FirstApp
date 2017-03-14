package com.hoka.hoka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;


public class InterfaceActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_synch, btn_clear, btn_list, btn_add, btn_read;
    DBHelper dbHelper;
    TextView Login, Pass;
    Password password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DBHelper(this);

        password = new Password();
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        btn_list = (Button)findViewById(R.id.btn_list);
        btn_list.setOnClickListener(this);

        btn_synch = (Button)findViewById(R.id.btn_synch);
        btn_synch.setOnClickListener(this);

        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);

        Login = (TextView)findViewById(R.id.Login);
        Login.setText(password.getLogin());

        Pass = (TextView)findViewById(R.id.Password);
        Pass.setText(password.getPassword());


        btn_read = (Button)findViewById(R.id.btn_read);
        btn_read.setOnClickListener(this);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:{
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CreateDB.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_list:{
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ListIncidents.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_read:{
                final SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.query(DBHelper.TABLE_MALFUNCTIONS, null, null, null, null, null, null);
                int idKey_id = cursor.getColumnIndex(DBHelper.KEY_ID);
                int idKey_pid = cursor.getColumnIndex(DBHelper.KEY_PID);
                int idName_company = cursor.getColumnIndex(DBHelper.KEY_NAME_COMPANY);
                int idTelephone = cursor.getColumnIndex(DBHelper.KEY_TELEPHONE);
                int idAddress = cursor.getColumnIndex(DBHelper.KEY_ADDRESS);
                int idStatus = cursor.getColumnIndex(DBHelper.KEY_STATUS);
                int idService = cursor.getColumnIndex(DBHelper.KEY_SERVICE);
                int idNumber_assets = cursor.getColumnIndex(DBHelper.KEY_NUMBER_ASSETS);
                int idDetails = cursor.getColumnIndex(DBHelper.KEY_DETAILS);
                int idComment = cursor.getColumnIndex(DBHelper.KEY_COMMENT);

                if (cursor.moveToFirst()){
                    do {
                        /*Log.d("mLog", "ID = " + cursor.getInt(idKey_id) + ", PID = " + cursor.getString(idKey_pid) +
                                ", name = " + cursor.getString(idName_company) + ", Telephone = " +
                                cursor.getString(idTelephone) + ", address = " + cursor.getString(idAddress) + ", Статус = " + cursor.getString(idStatus) +
                                ", Сервис = " + cursor.getString(idService) + ", Номер актива = " + cursor.getString(idNumber_assets) + ", About = " +
                                cursor.getString(idDetails) + ", Комент: " + cursor.getString(idComment));*/
                    }while (cursor.moveToNext());
                }else Log.d("mLog", "0 rows");
                cursor.close();
                dbHelper.close();
                break;
            }
            case R.id.btn_synch:{
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.query(DBHelper.TABLE_MALFUNCTIONS, null, null, null, null, null, null);
                int idKey_id = cursor.getColumnIndex(DBHelper.KEY_ID);
                int idKey_pid = cursor.getColumnIndex(DBHelper.KEY_PID);
                int idName_company = cursor.getColumnIndex(DBHelper.KEY_NAME_COMPANY);
                int idTelephone = cursor.getColumnIndex(DBHelper.KEY_TELEPHONE);
                int idAddress = cursor.getColumnIndex(DBHelper.KEY_ADDRESS);
                int idStatus = cursor.getColumnIndex(DBHelper.KEY_STATUS);
                int idService = cursor.getColumnIndex(DBHelper.KEY_SERVICE);
                int idNumber_assets = cursor.getColumnIndex(DBHelper.KEY_NUMBER_ASSETS);
                int idDetails = cursor.getColumnIndex(DBHelper.KEY_DETAILS);
                int idComment = cursor.getColumnIndex(DBHelper.KEY_COMMENT);

                int intpid=0;

                if(cursor.moveToFirst()){

                    do {
                        if (cursor.getInt(idStatus)>0) {
                            HashMap postData = new HashMap();
                            postData.put("mobile", "android");
                            postData.put("txtPid", "" + cursor.getInt(idKey_pid));
                            postData.put("txtName", cursor.getString(idName_company));
                            postData.put("txtTelephone", cursor.getString(idTelephone));
                            postData.put("txtAddress", cursor.getString(idAddress));
                            postData.put("txtStatus", "" + cursor.getInt(idStatus));
                            postData.put("txtService", "" + cursor.getInt(idService));
                            postData.put("txtNumberAssets", cursor.getString(idNumber_assets));
                            postData.put("txtDetails", cursor.getString(idDetails));
                            postData.put("txtComment", cursor.getString(idComment));
                            PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(this, postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    if (s.contains("success")) {
                                        Toast.makeText(InterfaceActivity.this, "Update", Toast.LENGTH_SHORT).show();
                                        Log.d("mLog", "Обнова");
                                    } else {
                                        //Toast.makeText(InterfaceActivity.this, "Ошибка при обновлении", Toast.LENGTH_LONG).show();
                                        Log.d("mLog", "Ошибка при оновлении" );
                                    }
                                }
                            });
                            taskUpdate.execute("http://10.0.2.2/denwer/synch/update.php");
                        }
                        //intpid = cursor.getInt(idKey_pid);
                        //Log.d("mLog", "Обнова" + intpid);

                    }while (cursor.moveToNext());
                }

                BackgroundTask backgroundTask = new BackgroundTask(InterfaceActivity.this);
                backgroundTask.execute();
                dbHelper.close();
                break;
            }
            case R.id.btn_clear:{
                final SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete(DBHelper.TABLE_MALFUNCTIONS, null, null);
                dbHelper.close();
                break;
            }
        }

    }

}
