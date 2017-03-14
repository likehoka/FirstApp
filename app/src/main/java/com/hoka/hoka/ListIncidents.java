package com.hoka.hoka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import java.util.ArrayList;


public class ListIncidents extends AppCompatActivity implements OnGroupClickListener,OnChildClickListener,
        SwipeRefreshLayout.OnRefreshListener{
    DBHelper dbHelper;
    private ArrayList<ContactGroup> list;
    private CustomAdapter adapter;
    private SwipeRefreshLayout swipe_refresh_layout;
    ExpandableListView listView;
    Parcelable state;
    SharedPreferences pref;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_incidents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ExpandableListView)findViewById(R.id.expListView);


        listView.setSelected(true);
        dbHelper = new DBHelper(this);
        swipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);

        initContacts(); //создать метод private void initContacts()

        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);
        if(state != null) {
            listView.onRestoreInstanceState(state);
        }
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        listView.setSelectionFromTop(pref.getInt("index",0), pref.getInt("top",0));
        dbHelper.close();

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
    public boolean onGroupClick(ExpandableListView parent, View v,
                                int groupPosition, long id) {
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View v, int groupPosition, int childPosition, long l) {
        ContactItem item = adapter.getChild(groupPosition, childPosition);

        ContactItem.selectedItem = item;
        Intent intent = new Intent();
        intent.setClass(this, DetailsActivity.class);
        startActivity(intent);
        return false;
    }

    //Инициализация
    private void initContacts(){

        list = new ArrayList<ContactGroup>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_MALFUNCTIONS, null, null, null, null, null, null);
        ContactGroup group = new ContactGroup("ТО");
        if (cursor.moveToFirst()){

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
            do {
                if (cursor.getInt(idService)==0){
                    group.addContact(new ContactItem(cursor.getInt(idKey_id), cursor.getInt(idKey_pid), cursor.getString(idName_company), cursor.getString(idTelephone),
                            cursor.getString(idDetails), cursor.getString(idAddress), cursor.getInt(idStatus), cursor.getInt(idService),
                            cursor.getString(idNumber_assets), cursor.getString(idComment)));
                    Log.d("mLog", "TO");
                }
            }while (cursor.moveToNext());
        }
        list.add(group);

        cursor.moveToFirst();
        group = new ContactGroup("АВР");
        if (cursor.moveToFirst()){

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
            do {
                if (cursor.getInt(idService)==1){
                    group.addContact(new ContactItem(cursor.getInt(idKey_id), cursor.getInt(idKey_pid), cursor.getString(idName_company), cursor.getString(idTelephone),
                            cursor.getString(idDetails), cursor.getString(idAddress), cursor.getInt(idStatus), cursor.getInt(idService),
                            cursor.getString(idNumber_assets), cursor.getString(idComment)));
                    Log.d("mLog", "АВР");
                }
            }while (cursor.moveToNext());
        }
        list.add(group);

        cursor.moveToFirst();
        group = new ContactGroup("ПТО");
        if (cursor.moveToFirst()){

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
            do {
                if (cursor.getInt(idService)==2){
                    group.addContact(new ContactItem(cursor.getInt(idKey_id), cursor.getInt(idKey_pid), cursor.getString(idName_company), cursor.getString(idTelephone),
                            cursor.getString(idDetails), cursor.getString(idAddress), cursor.getInt(idStatus), cursor.getInt(idService),
                            cursor.getString(idNumber_assets), cursor.getString(idComment)));
                    Log.d("mLog", "ПTO");
                }
            }while (cursor.moveToNext());
            cursor.close();
        }
        list.add(group);
    }

    @Override
    public void onRefresh(){
        swipe_refresh_layout.setRefreshing(true);

        initContacts();

        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);
        if(state != null) {
            listView.onRestoreInstanceState(state);
        }
        swipe_refresh_layout.setRefreshing(false);
    }

    @Override
    public void onPause() {
        state = listView.onSaveInstanceState();
        super.onPause();
    }

    @Override
    public void onResume(){
        initContacts();
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);
        if(state != null) {
            listView.onRestoreInstanceState(state);
        }
        super.onResume();
        //http://ru.stackoverflow.com/questions/460799/listview-%D0%B7%D0%B0%D0%BF%D0%BE%D0%BC%D0%BD%D0%B8%D1%82%D1%8C-%D0%B2%D0%BE%D1%81%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%B8%D1%82%D1%8C-%D0%BF%D0%BE%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D1%81%D0%BA%D1%80%D0%BE%D0%BB%D0%B0
    }
    @Override
    public void onRestart(){
        state = listView.onSaveInstanceState();
        super.onRestart();
    }
    @Override
    public void onStop(){
        state = listView.onSaveInstanceState();
        super.onStop();
    }
    @Override
    public void onStart(){
        initContacts();
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);
        if(state != null) {
            listView.onRestoreInstanceState(state);
        }
        super.onStart();
    }

}
