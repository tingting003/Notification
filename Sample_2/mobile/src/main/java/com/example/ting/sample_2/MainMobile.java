package com.example.ting.sample_2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainMobile extends ActionBarActivity implements View.OnClickListener{

    EditText notesET;

    private static final int notificationId=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);

        notesET=(EditText)findViewById(R.id.editText);

        Button bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(this);
    }

    private void showNotification(String sMsg){
        //Build intent for notification content
        Intent it=new Intent(getApplicationContext(),MainMobile.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //------------------------------------Open on phone------------------------------------------------------------------------------------------
        PendingIntent petIt=PendingIntent.getActivity(getApplicationContext(),0,it,PendingIntent.FLAG_CANCEL_CURRENT);

        //------------------------------------Open web----------------------------------------------------------------------------------------------
        Uri uri=Uri.parse("https://www.google.com.tw/");
        Intent itOpenWebsite=new Intent(Intent.ACTION_VIEW,uri);

        PendingIntent penInOpenWebsite=PendingIntent.getActivity(getApplicationContext(),0,itOpenWebsite,PendingIntent.FLAG_CANCEL_CURRENT);
        //-------------------------------------------------------------------------------------------------------------------------------------------
        //Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());

        // Build the notification and issues it with notification manager.
        Notification notification=new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.btn_star_big_on)
                .setTicker(sMsg)
                .setContentText(sMsg)
                .setContentIntent(petIt)//Open on phone
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .addAction(android.R.drawable.ic_menu_share,"開啟網頁",penInOpenWebsite)//Open web
                .build();
        //Issue the notification with notification manager.
        notificationManagerCompat.notify(notificationId,notification);
        //-------------------------------------------------------------------------------------------------------------------------------------------
    }
    @Override
    public void onClick(View view){
        String card=notesET.getText()+"";
        showNotification(card);
    }
}
