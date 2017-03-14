package com.hoka.hoka;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener  {
    private String id; //pid;
    private TextView pidd;
    private TextView name; //название компании
    private TextView phone; //контактный телефон
    private TextView about; //содержание заявки
    private TextView address; //адрес
    private TextView status; //статус заявки
    private TextView service; //Исходная услуга (Ремонт, ТО)
    private TextView number_assets; //Номер актива
    private TextView comment; //коментарий инжнера
    ImageView imageView;
    TextView textView;
    String str_status;
    int n_service=4;
    int n_status=4;
    int pid=0;
    DBHelper dbHelper;

    ImageButton  bar2, back;//кнопка смены статуса
    //Button btn_ok, btn_cancel;
    ContactItem item = ContactItem.selectedItem;

    String st_name, st_phone, st_about,st_address, st_number_assets,
            st_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView)findViewById(R.id.imagestatus);
        textView = (TextView)findViewById(R.id.textViewstatus);

        pidd = (TextView) findViewById(R.id.detailsPid);
        name = (TextView)findViewById(R.id.detailsName);
        phone = (TextView)findViewById(R.id.detailsPhone);
        about = (TextView)findViewById(R.id.detailsAbout);
        address = (TextView)findViewById(R.id.detailsAddress);
        status = (TextView)findViewById(R.id.detailsStatus);
        service = (TextView)findViewById(R.id.detailsService);
        number_assets = (TextView)findViewById(R.id.detailsNumber_assets);
        comment = (TextView)findViewById(R.id.detailsComment);
        //imageButton = (ImageButton)findViewById(R.id.imageButton);
        bar2 = (ImageButton)findViewById(R.id.bar2);
        back = (ImageButton)findViewById(R.id.back);
        id = item.getId()+"";

        pid = item.getPid();



        pidd.setText(item.getPid()+"");
        name.setText(item.getName());
        phone.setText(item.getPhone());
        about.setText(item.getAbout());
        address.setText(item.getAddress());
        status.setId(item.getStatus());

        setTitle("заявка № " + item.getPid());

        n_status = item.getStatus();
        if (n_status==0){
            status.setText("принято");
            textView.setText("принято");
        }
        if (n_status==1){
            status.setText("в работе");
            textView.setText("в работе");
        }
        if (n_status==2){
            status.setText("выполнено");
            textView.setText("выполнено");
        }

        service.setId(item.getService());
        n_service = item.getService();
        if (n_service==0){
            service.setText("ТО");
        }
        if (n_service==1){
            service.setText("АВР");
        }
        if (n_service==2){
            service.setText("ПТО");
        }

        number_assets.setText(item.getNumber_assets());
        comment.setText(item.getComment());

        st_name = name.getText().toString();
        st_phone = phone.getText().toString();
        st_about = about.getText().toString();
        st_address = address.getText().toString();
        st_number_assets = number_assets.getText().toString();
        st_comment = comment.getText().toString();

        //btn_ok = (Button)findViewById(R.id.btn_ok);
        //btn_ok.setOnClickListener(this);
        //btn_cancel = (Button)findViewById(R.id.btn_cancel);
        //btn_cancel.setOnClickListener(this);
        //imageButton.setOnClickListener(this);
        bar2.setOnClickListener(this);
        back.setOnClickListener(this);
        str_status = status.getText().toString();
        //n_service = service.getId();
        if (n_status == 0){
            //imageButton.setImageResource(R.drawable.a1);
            imageView.setImageResource(R.drawable.a1);
        }

        if (n_status == 1){
            //imageButton.setImageResource(R.drawable.a2);
            imageView.setImageResource(R.drawable.a2);
        }
        if (n_status == 2){
            //imageButton.setImageResource(R.drawable.a3);
            imageView.setImageResource(R.drawable.a3);
        }

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
        if (view.getId() == R.id.bar2){
            dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            //ContentValues используется для добавления новых строк в таблицу
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_PID, pid);
            contentValues.put(DBHelper.KEY_NAME_COMPANY, st_name);
            contentValues.put(DBHelper.KEY_TELEPHONE, st_phone);
            contentValues.put(DBHelper.KEY_DETAILS, st_about);
            contentValues.put(DBHelper.KEY_NUMBER_ASSETS, st_number_assets);
            contentValues.put(DBHelper.KEY_STATUS, n_status);
            contentValues.put(DBHelper.KEY_ADDRESS, st_address);
            contentValues.put(DBHelper.KEY_COMMENT, st_comment);
            contentValues.put(DBHelper.KEY_SERVICE, n_service);
            if (n_status == 1){
                status.setText("выполнено");
                textView.setText("выполнено");
                n_status = 2;
                contentValues.put(DBHelper.KEY_STATUS, n_status);
                int updCount = database.update(DBHelper.TABLE_MALFUNCTIONS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
                //imageButton.setImageResource(R.drawable.a3);
                imageView.setImageResource(R.drawable.a3);
                //http://developer.alexanderklimov.ru/android/database.php редактирование базы
            }
            if (n_status == 0){
                status.setText("в работе");
                textView.setText("в работе");
                n_status = 1;
                contentValues.put(DBHelper.KEY_STATUS, n_status);
                int updCount = database.update(DBHelper.TABLE_MALFUNCTIONS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
                //imageButton.setImageResource(R.drawable.a2);
                imageView.setImageResource(R.drawable.a2);
            }
            dbHelper.close();
        }

        if (view.getId() == R.id.back){
            finish();
        }
    }
    public void init(){

    }

    private void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Закрыть заявку?");
        // Создаем кнопку "Yes" и обработчик события
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DetailsActivity.this.finish();
                    }
                });
        // Создаем кнопку "No" и обработчик события
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setCancelable(false);
        builder.create();
        builder.show();

    }
}
