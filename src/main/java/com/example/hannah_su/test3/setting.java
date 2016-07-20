package com.example.hannah_su.test3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Created by hannah_su on 07/11/2016.
 */
public class setting extends AppCompatActivity {
    String id_app_device, username, userlogon, domain,emp_no;
    ImageButton personal, home;
    Switch switch1, switch2;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.setting);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        //set the switch to ON
        switch1.setChecked(true);
        //attach a listener to check for changes in state


        Intent intent = this.getIntent();
        id_app_device = intent.getStringExtra("id_app_device");
        username = intent.getStringExtra("username");
        userlogon = intent.getStringExtra("userlogon");
        //domain = intent.getStringExtra("domain");
        emp_no=intent.getStringExtra("emp_no");
        personal = (ImageButton) findViewById(R.id.personal);
        home = (ImageButton) findViewById(R.id.home);


        home.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(setting.this,menu.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});
        personal.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(setting.this,personal.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});



    }


}