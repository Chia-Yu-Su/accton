package com.example.hannah_su.test3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class signbox extends AppCompatActivity {
    private WebView mWebView;
    String url;
    ImageButton personal, home,setting;
    String id_app_device, username, userlogon, domain,emp_no;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signbox);
        Intent intent = this.getIntent();
        id_app_device = intent.getStringExtra("id_app_device");
        username = intent.getStringExtra("username");
        userlogon= intent.getStringExtra("userlogon");
        //domain = intent.getStringExtra("domain");
        emp_no=intent.getStringExtra("emp_no");
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        url="https://accmis-t.accton.com.tw/system_mobile?id_app_device="+id_app_device+"&to=signbox";
        mWebView.loadUrl(url);
        personal = (ImageButton) findViewById(R.id.personal);
        home = (ImageButton) findViewById(R.id.home);
        setting =(ImageButton) findViewById(R.id.setting);
        home.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(signbox.this,menu.class);
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
                intent.setClass(signbox.this,personal.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});
        setting.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(signbox.this,setting.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
