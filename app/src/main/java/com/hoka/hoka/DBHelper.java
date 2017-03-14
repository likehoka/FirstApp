package com.hoka.hoka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbase";
    public static final String TABLE_MALFUNCTIONS = "malfunctions";

    public static final String KEY_PID = "pid"; //id с сервера
    public static final String KEY_ID = "_id";//id
    public static final String KEY_NAME_COMPANY = "name_company";//название компании
    public static final String KEY_TELEPHONE = "telephone";//контактный телефон
    public static final String KEY_ADDRESS = "address";//адресс
    public static final String KEY_STATUS = "status";//Статус заявки
    public static final String KEY_SERVICE = "service";//Исходная услуга(ремонт, ТО)
    public static final String KEY_NUMBER_ASSETS = "number_assets";// номер актива
    public static final String KEY_DETAILS = "details";//Подробности заявки
    public static final String KEY_COMMENT = "comment";//коментарий инженера

    public DBHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MALFUNCTIONS + "(" + KEY_ID + " integer primary key," +
                KEY_PID + " integer," +
                KEY_NAME_COMPANY + " text," + KEY_TELEPHONE + " integer," + KEY_ADDRESS + " text,"
                + KEY_STATUS + " integer," + KEY_SERVICE + " integer," + KEY_NUMBER_ASSETS +
                " integer," + KEY_DETAILS + " text," + KEY_COMMENT + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_MALFUNCTIONS);
    }
}
