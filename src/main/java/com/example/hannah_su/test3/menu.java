package com.example.hannah_su.test3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {
    private WebView mWebView,mWebView2,mWebView3,mWebView4;
    ImageButton signbox, report, personal,setting;
    String id_app_device, username, userlogon,domain,menuNum1, menuNum2,emp_no;
    String getmenuUriAPI;
    JSONObject responseMenu;
    String url1,url2,url3,url4;
    ImageView dot;
    int c1, c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        signbox=(ImageButton)findViewById(R.id.signbox);
        report=(ImageButton)findViewById(R.id.report);
        personal=(ImageButton)findViewById(R.id.personal);
        setting=(ImageButton)findViewById(R.id.setting);
        dot=(ImageView)findViewById(R.id.dotimage);

        Intent intent = this.getIntent();
        id_app_device = intent.getStringExtra("id_app_device");
        username = intent.getStringExtra("username");
        userlogon= intent.getStringExtra("userlogon");
        //domain= intent.getStringExtra("domain");
        emp_no= intent.getStringExtra("emp_no");
        getmenuUriAPI="https://accmis-t.accton.com.tw/accappservice/api/home?id_app_device="+id_app_device;
        Thread t2=new Thread(r2);
        t2.start();

        signbox.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(menu.this,signbox.class);
                intent.putExtra("id_app_device",id_app_device);//可放所有基本類別
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }

        });
        report.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(menu.this,report.class);
                intent.putExtra("id_app_device",id_app_device);//可放所有基本類別
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
                intent.setClass(menu.this,personal.class);
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
                intent.setClass(menu.this,setting.class);
                intent.putExtra("id_app_device",id_app_device);
                intent.putExtra("username",username);
                intent.putExtra("userlogon",userlogon);
                //intent.putExtra("domain",domain);
                intent.putExtra("emp_no",emp_no);
                startActivity(intent);
            }});

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mWebView = (WebView) findViewById(R.id.webView);
                        mWebView.setWebViewClient(new WebViewClient());
                        WebSettings webSettings = mWebView.getSettings();
                        mWebView.setInitialScale(1);
                        webSettings.setLoadWithOverviewMode(true);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setJavaScriptEnabled(true);;
                        mWebView.loadUrl(url1);
                        dot.setImageResource(R.mipmap.dot1);
                        break;
                    case 1:
                        mWebView2 = (WebView) findViewById(R.id.webView2);
                        mWebView2.setWebViewClient(new WebViewClient());
                        webSettings = mWebView2.getSettings();
                        mWebView2.setInitialScale(1);
                        webSettings.setLoadWithOverviewMode(true);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setJavaScriptEnabled(true);
                        mWebView2.loadUrl(url2);
                        dot.setImageResource(R.mipmap.dot2);
                        break;
                    case 2:
                        mWebView3 = (WebView) findViewById(R.id.webView3);
                        mWebView3.setWebViewClient(new WebViewClient());
                        webSettings = mWebView3.getSettings();
                        mWebView3.setInitialScale(1);
                        webSettings.setLoadWithOverviewMode(true);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setJavaScriptEnabled(true);
                        mWebView3.loadUrl(url3);
                        dot.setImageResource(R.mipmap.dot3);
                        break;
                    case 3:
                        mWebView4 = (WebView) findViewById(R.id.webView4);
                        mWebView4.setWebViewClient(new WebViewClient());
                        webSettings = mWebView4.getSettings();
                        mWebView4.setInitialScale(1);
                        webSettings.setLoadWithOverviewMode(true);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setJavaScriptEnabled(true);
                        mWebView4.loadUrl(url4);
                        dot.setImageResource(R.mipmap.dot4);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        View v1 = mInflater.inflate(R.layout.intro1, null);
        View v2 = mInflater.inflate(R.layout.intro2, null);
        View v3 = mInflater.inflate(R.layout.intro3, null);
        View v4 = mInflater.inflate(R.layout.intro4, null);

        ArrayList viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        viewList.add(v4);

        mViewPager.setAdapter(new MyViewPagerAdapter(viewList));
        mViewPager.setCurrentItem(0);
    }//end oncreate

    private Runnable r2=new Runnable() {
        @Override
        public void run() {
            try {
                responseMenu = getJSONObjectFromURL(getmenuUriAPI);
                Message message = new Message();
                message.what = 2;
                h1.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Handler h1 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                try {
                    JSONObject chart1=(JSONObject) responseMenu.getJSONArray("list_chart").get(0);
                    url1=chart1.getString("url");
                    mWebView = (WebView) findViewById(R.id.webView);
                    mWebView.setWebViewClient(new WebViewClient());
                    WebSettings webSettings = mWebView.getSettings();
                    mWebView.setInitialScale(1);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setJavaScriptEnabled(true);
                    mWebView.loadUrl(url1);
                    JSONObject chart2=(JSONObject) responseMenu.getJSONArray("list_chart").get(1);
                    url2=chart2.getString("url");
                    JSONObject chart3=(JSONObject) responseMenu.getJSONArray("list_chart").get(2);
                    url3=chart3.getString("url");
                    JSONObject chart4=(JSONObject) responseMenu.getJSONArray("list_chart").get(3);
                    url4=chart4.getString("url");
                    JSONObject first = (JSONObject) responseMenu.getJSONArray("list_menu_count").get(0);
                    menuNum1=first.getString("count");
                    JSONObject second = (JSONObject) responseMenu.getJSONArray("list_menu_count").get(1);
                    menuNum2=second.getString("count");
                    c1 = Integer.parseInt(menuNum1);
                    c2 = Integer.parseInt(menuNum2);
                    if (c1>10)
                        signbox.setImageResource(R.mipmap.eapprove10plus);
                    else {
                        switch  (c1)
                        {  case 0:
                            signbox.setImageResource(R.mipmap.eapprove);
                            break;
                            case 1:
                                signbox.setImageResource(R.mipmap.eapprove1);
                                break;
                            case 2:
                                signbox.setImageResource(R.mipmap.eapprove2);
                                break;
                            case 3:
                                signbox.setImageResource(R.mipmap.eapprove3);
                                break;
                            case 4:
                                signbox.setImageResource(R.mipmap.eapprove4);
                                break;
                            case 5:
                                signbox.setImageResource(R.mipmap.eapprove5);
                                break;
                            case 6:
                                signbox.setImageResource(R.mipmap.eapprove6);
                                break;
                            case 7:
                                signbox.setImageResource(R.mipmap.eapprove7);
                                break;
                            case 8:
                                signbox.setImageResource(R.mipmap.eapprove8);
                                break;
                            case 9:
                                signbox.setImageResource(R.mipmap.eapprove9);
                                break;
                            case 10:
                                signbox.setImageResource(R.mipmap.eapprove10);
                                break;
                        }
                    }
                    if (c2>10)
                        report.setImageResource(R.mipmap.report10plus);
                    else {
                        switch  (c2)
                        {  case 0:
                            report.setImageResource(R.mipmap.report);
                            break;
                            case 1:
                                report.setImageResource(R.mipmap.report1);
                                break;
                            case 2:
                                report.setImageResource(R.mipmap.report2);
                                break;
                            case 3:
                                report.setImageResource(R.mipmap.report3);
                                break;
                            case 4:
                                report.setImageResource(R.mipmap.report4);
                                break;
                            case 5:
                                report.setImageResource(R.mipmap.report5);
                                break;
                            case 6:
                                report.setImageResource(R.mipmap.report6);
                                break;
                            case 7:
                                report.setImageResource(R.mipmap.report7);
                                break;
                            case 8:
                                report.setImageResource(R.mipmap.report8);
                                break;
                            case 9:
                                report.setImageResource(R.mipmap.report9);
                                break;
                            case 10:
                                report.setImageResource(R.mipmap.report10);
                                break;
                        }}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];
        String jsonString;
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListViews.get(position);
            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return  mListViews.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }
}
