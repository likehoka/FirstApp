package com.hoka.hoka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundTask extends AsyncTask<Void, ContactItem, Void> {
    Context ctx;

    Activity activity;
    DBHelper dbHelper;
    ProgressDialog progressDialog;

    String json_string = "http://10.0.2.2/denwer/synch/get.php";

    public BackgroundTask(Context ctx){
        this.ctx = ctx;
        activity = (Activity)ctx;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Base is loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }



    @Override
    protected Void doInBackground(Void... params) {


        int key_pid; //id в mysql
        String name_company; //название компании
        String telephone; //контактный телефон
        String address; //адресс
        int status; //Статус заявки
        int service; //Исходная услуга(ремонт, ТО, ПТР)
        String number_assets; // номер актива
        String details; //Подробности заявки
        String comment; //коментарий инжнера

        try {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();
            Log.d("JSON STRING", json_string);


            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            boolean BaseStatus = false;

            dbHelper = new DBHelper(ctx);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = database.query(DBHelper.TABLE_MALFUNCTIONS, null, null, null, null, null, null);


            while(count<jsonArray.length()){
                BaseStatus=false;
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                ContactItem incident = new ContactItem(JO.getInt("pid"), JO.getString("name"), JO.getString("phone"), JO.getString("about"),
                        JO.getString("address"), JO.getInt("status"), JO.getInt("service"), JO.getString("number_assets"),
                        JO.getString("comment"));

                Log.d("mLog", "pid: " + incident.getPid() + " name: " + incident.getName());
                key_pid = incident.getPid();
                //String key = key_pid + "";
                name_company = incident.getName();
                telephone = incident.getPhone();
                address = incident.getAddress();
                status = incident.getStatus();
                service = incident.getService();
                number_assets = incident.getNumber_assets();
                details = incident.getAbout();
                comment = incident.getComment();

                if(cursor.moveToFirst()){
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
                        if (key_pid==cursor.getInt(idKey_pid)){
                            BaseStatus = true;
                            Log.d("mLog", "WOW");
                        }
                        /*Log.d("mLog", "ID = " + cursor.getInt(idKey_id) +
                                ", name = " + cursor.getString(idName_company) + ", Telephone = " +
                                cursor.getString(idTelephone) + ", address = " + cursor.getString(idAddress) + ", Статус = " + cursor.getString(idStatus) +
                                ", Сервис = " + cursor.getString(idService) + ", Номер актива = " + cursor.getString(idNumber_assets) + ", About = " +
                                cursor.getString(idDetails) + ", Комент: " + cursor.getString(idComment));*/
                    }while (cursor.moveToNext());
                }

                if (BaseStatus==false){
                    cursor.moveToFirst();
                    contentValues.put(DBHelper.KEY_PID, key_pid);
                    contentValues.put(DBHelper.KEY_NAME_COMPANY, name_company);
                    contentValues.put(DBHelper.KEY_TELEPHONE, telephone);
                    contentValues.put(DBHelper.KEY_ADDRESS, address);
                    contentValues.put(DBHelper.KEY_STATUS, status);
                    contentValues.put(DBHelper.KEY_SERVICE, service);
                    contentValues.put(DBHelper.KEY_NUMBER_ASSETS, number_assets);
                    contentValues.put(DBHelper.KEY_DETAILS, details);
                    contentValues.put(DBHelper.KEY_COMMENT, comment);
                    database.insert(DBHelper.TABLE_MALFUNCTIONS, null, contentValues);
                    //Log.d("mLog", "WOW");
                    publishProgress(incident);
                    Thread.sleep(100);
                }

                if(cursor.moveToFirst()){
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

                        Log.d("mLog", "ID = " + cursor.getInt(idKey_id) +
                                ", name = " + cursor.getString(idName_company) + ", Telephone = " +
                                cursor.getString(idTelephone) + ", address = " + cursor.getString(idAddress) + ", Статус = " + cursor.getString(idStatus) +
                                ", Сервис = " + cursor.getString(idService) + ", Номер актива = " + cursor.getString(idNumber_assets) + ", About = " +
                                cursor.getString(idDetails) + ", Комент: " + cursor.getString(idComment));
                    }while (cursor.moveToNext());
                }
            }
            cursor.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dbHelper.close();
        return null;
    }

    @Override
    protected void onProgressUpdate(ContactItem... values) {

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }


}
