package com.hoka.hoka;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CreateDB extends AppCompatActivity implements View.OnClickListener {
    EditText etKey_id, etKey_pid, etName_company, etTelephone, etAddress, etNumber_assets,
            etDetails, etKey_comment;
    Spinner etStatus, etService;
    //String status, service;
    public TextView spinnerStatus, spinnerService;
    Button save, cancel, exit;
    DBHelper dbHelper;
    String[] spinner_status = {"принято", "в работе", "выполнено"};
    String[] spinner_service = {"ТО", "АВР", "ПТО"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // адаптер
        ArrayAdapter<String> adapter_status = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_status);
        adapter_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_service = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_service);
        adapter_service.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner_status = (Spinner)findViewById(R.id.spinner_edit_status);
        spinner_status.setAdapter(adapter_status);
        Spinner spinner_service = (Spinner)findViewById(R.id.spinner_edit_service);
        spinner_service.setAdapter(adapter_service);
        // заголовок
        spinner_status.setPrompt("Статус");
        spinner_service.setPrompt("Сервис");

        // выделяем элемент
        spinner_status.setSelection(0);
        spinner_service.setSelection(0);

        // устанавливаем обработчик нажатия
        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Object item = parent.getItemAtPosition(position);
                //spinnerStatus.setText(item.toString());

                //http://metanit.com/java/android/5.4.php
                //http://startandroid.ru/ru/uroki/vse-uroki-spiskom/115-urok-56-spinner-vypadajuschij-spisok.html
                //https://www.youtube.com/watch?v=gYXsSEs1khc


                //Показываем позицию нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Object item = parent.getItemAtPosition(position);
                //spinnerService.setText(item.toString());
                //Показываем позицию нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        etKey_id = (EditText)findViewById(R.id.edit_key_id);
        //etKey_id.setOnClickListener(this);

        etKey_pid = (EditText)findViewById(R.id.edit_key_pid);
        //etKey_pid.setOnClickListener(this);

        etName_company = (EditText)findViewById(R.id.edit_name_company);
        //etName_company.setOnClickListener(this);

        etTelephone = (EditText)findViewById(R.id.edit_telephone);
        //etTelephone.setOnClickListener(this);

        etAddress = (EditText)findViewById(R.id.edit_address);
        //etAddress.setOnClickListener(this);

        etStatus = (Spinner) findViewById(R.id.spinner_edit_status);
        //etStatus.setOnClickListener(this);

        etService = (Spinner) findViewById(R.id.spinner_edit_service);
        //etService.setOnClickListener(this);

        etNumber_assets = (EditText)findViewById(R.id.edit_number_assets);
        //etNumber_assets.setOnClickListener(this);

        etDetails = (EditText)findViewById(R.id.edit_details);
        //etDetails.setOnClickListener(this);

        etKey_comment = (EditText)findViewById(R.id.edit_key_comment);
        //etKey_comment.setOnClickListener(this);

        save = (Button)findViewById(R.id.edit_save);
        save.setOnClickListener(this);


        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        String key_pid = etKey_pid.getText().toString();
        String name_company = etName_company.getText().toString();
        String telephone = etTelephone.getText().toString();
        String address = etAddress.getText().toString();
        String status = etStatus.getSelectedItem().toString();
        String service = etService.getSelectedItem().toString();
        String number_assets = etNumber_assets.getText().toString();
        String details = etDetails.getText().toString();
        String comment = etKey_comment.getText().toString();

        int n_service = 0;//0-ТО, 1-АВР, 2-ПТО
        if(service=="ТО"){
            n_service=0;
        }
        if(service=="АВР"){
            n_service=1;
        }
        if (service=="ПТО"){
            n_service=2;
        }

        int n_status = 0;//0-принято, 1- в работе, 2- выполнено
        if(status=="принято"){
            n_status=0;
        }
        if (status=="в работе"){
            n_status=1;
        }
        if (status=="выполнено"){
            n_status=2;
        }



        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //ContentValues используется для добавления новых строк в таблицу
        ContentValues contentValues = new ContentValues();
        switch (view.getId()){
            case R.id.edit_save: {
                contentValues.put(DBHelper.KEY_PID, key_pid);
                contentValues.put(DBHelper.KEY_NAME_COMPANY, name_company);
                contentValues.put(DBHelper.KEY_TELEPHONE, telephone);
                contentValues.put(DBHelper.KEY_ADDRESS, address);
                contentValues.put(DBHelper.KEY_STATUS, n_status);
                contentValues.put(DBHelper.KEY_SERVICE, n_service);
                contentValues.put(DBHelper.KEY_NUMBER_ASSETS, number_assets);
                contentValues.put(DBHelper.KEY_DETAILS, details);
                contentValues.put(DBHelper.KEY_COMMENT, comment);
                //Записываем данные в таблицу
                database.insert(DBHelper.TABLE_MALFUNCTIONS, null, contentValues);
                Log.d("String", " is saved" + key_pid);
                break;
            }

        }
        dbHelper.close();
    }
}
