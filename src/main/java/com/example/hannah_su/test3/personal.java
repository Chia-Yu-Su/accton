package com.example.hannah_su.test3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.plus.Plus;

import java.io.IOException;

/**
 * Created by hannah_su on 07/11/2016.
 */
public class personal extends AppCompatActivity {

    String id_app_device,username,userlogon,domain,emp_no;
    TextView getname,getid,tv;
    ImageButton setting,home;
    Button logout;

    String PREFS_NAME = "accton";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.personal);
        Intent intent = this.getIntent();
        id_app_device = intent.getStringExtra("id_app_device");
        username = intent.getStringExtra("username");
        userlogon= intent.getStringExtra("userlogon");
        //domain = intent.getStringExtra("domain");
        emp_no=intent.getStringExtra("emp_no");
        getname=(TextView)findViewById(R.id.textView4);
        getid=(TextView)findViewById(R.id.textView5);
        getname.setText("     "+userlogon+"  "+username);
        getid.setText("     "+emp_no);
        setting=(ImageButton)findViewById(R.id.setting);
        home=(ImageButton)findViewById(R.id.home);
        logout=(Button)findViewById(R.id.button);

        setting.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(personal.this,setting.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});
        home.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(personal.this,menu.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});

        logout.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences settings=  getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                //editor.remove("KEY_STRING");//移除 KEY_STRING 對應的資料
                editor.clear(); //清除 SharedPreferences 檔案中所有資料
                editor.commit();

                Intent intent = new Intent();
                intent.setClass(personal.this,MainActivity.class);
                startActivity(intent);
            }});

    }// end oncreate



}
