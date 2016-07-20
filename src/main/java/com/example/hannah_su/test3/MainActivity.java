package com.example.hannah_su.test3;


import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {
    String uriAPI;
    EditText getid,getpw;
    Button login;
    String ID,pw,id_app_device,username,userlogon,domain,auth_status,emp_no;
    String macAddress;
    String fcmtoken="eHbbc95it5o:APA91bFsWcqYLSjFQg8XH8AUa2BT-WZHKUqZ8n4n8uQKe_P4ak8ztJ32BFdZpEkoqUj2A5NqcqMPql9VM7TCJzkZRaOvrHF8p3vD1qI3_qcfIwtGlGHYYLrBvjQrXAoR7roOBSg2jfiV";
    String deviceName=Build.MODEL;
    String version=Build.VERSION.RELEASE;
    String size;

    JSONObject responseProfile;
    private Spinner sp;
    ArrayAdapter adapter;
    String PREFS_NAME = "accton";//一個標籤 可以設定為代表此APP的String Tag
    //private static final String token = "token"; //鍵盤ㄉ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        haveInternet(); //確認使用者有開網路
        iniView();    //keyboard disappear aftear text

        //取screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        int dens=dm.densityDpi;
        double wi=(double)width/(double)dens;
        double hi=(double)height/(double)dens;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);
        size=new Double(screenInches).toString();
        //將token傳到這
        LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver,
                new IntentFilter("tokenReceiver"));
        //取得mac address
        WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi = wm.getConnectionInfo();
        macAddress = wifi.getMacAddress();
        getid=(EditText)findViewById(R.id.id);
        getpw=(EditText)findViewById(R.id.pw);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(LOGIN);
        getid.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                getid.setText("");
            }});
        getpw.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                getpw.setText("");
            }});
        sp = (Spinner) findViewById(R.id.type);
        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item, new String[] { "Accton TW", "JoyTech", "Accton Logistics","ATC CHINA"
                ,"Metalligence TW","SMC TW","ATAN","Edgecore Sin","NocSYS CHINA","EdgeCore USA","Edgecore TW","SMC US","AWB"}  );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);

        //開啟app  if有登入過 -> 先取得資料
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        id_app_device= settings.getString("id_app_device",""); //settings.getString(所對應的key,如果抓不到對應的值要給什麼預設值)
        username =  settings.getString("username","");
        userlogon = settings.getString("userlogon","");
        domain = settings.getString("domain","");

        if(!"".equals(id_app_device))
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,menu.class);
            intent.putExtra("id_app_device",id_app_device);//可放所有基本類別
            intent.putExtra("username",username);
            intent.putExtra("userlogon",userlogon);
            intent.putExtra("domain",domain);
            intent.putExtra("emp_no",emp_no);
            startActivity(intent);
        }
    }

    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            fcmtoken = intent.getStringExtra("token");
            Log.d("debug", "token : " + fcmtoken);
        }
    };

    private boolean haveInternet()
    {
        boolean result = false;
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected())
        {
            Toast.makeText(MainActivity.this, "請確認網路開啟再登入。", Toast.LENGTH_LONG).show();
            result = false;
        }
        else
        {
            if (!info.isAvailable())
            {
                Toast.makeText(MainActivity.this, "請確認網路開啟再登入。", Toast.LENGTH_LONG).show();
                result =false;
            }
            else
            {
                result = true;
            }
        }
        return result;
    }

    //keyboard disappear aftear text
    public void iniView() {}
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            switch (sp.getSelectedItemPosition()) {
                case 0:
                    domain = "accton";break;
                case 1:
                    domain = "joytech";break;
                case 2:
                    domain = "accton";break;
                case 3:
                    domain = "atc";break;
                case 4:
                    domain = "accton";break;
                case 5:
                    domain = "smcnet";break;
                case 6:
                    domain = "atc";break;
                case 7:
                    domain = "accton";break;
                case 8:
                    domain = "accton";break;
                case 9:
                    domain = "accton";break;
                case 10:
                    domain = "accton";break;
                case 11:
                    domain = "smcnet";break;
                case 12:
                    domain = "accton";break;
            }}
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            domain = "accton";
        }
    };
        private View.OnClickListener LOGIN =new View.OnClickListener(){
        public void onClick(View v) {
            ID=getid.getText().toString();
            pw=getpw.getText().toString();
            String DEVICE = null;
            try {
                DEVICE = URLEncoder.encode(deviceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            uriAPI="https://accmis-t.accton.com.tw/accappservice/api/Login?user_logon="+ID+"&password="+pw+"&domain="+domain+"&device={device_token:"+"\""+fcmtoken+"\""+",device_name:"+"\""+DEVICE+"\""+",device_version:"+"\""+version+"\""+",device_size:"+"\""+size+"\""+",device_mac_addr:"+"\""+macAddress+"\""+"}";
            Thread t1=new Thread(r1);
            t1.start();
        }
    };
    private Runnable r1=new Runnable() {
        @Override
        public void run() {
            try {
                responseProfile = getJSONObjectFromURL(uriAPI);
                Message message = new Message();
                message.what = 1;
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
            if (msg.what == 1) {
                try {
                    id_app_device=responseProfile.getString("id_app_device");
                    username=responseProfile.getString("user_name");
                    userlogon=responseProfile.getString("user_logon");
                    //domain=responseProfile.getString("domain");
                    auth_status=responseProfile.getString("auth_status");
                    emp_no=responseProfile.getString("emp_no");

                    if("false".equals(auth_status)){
                        Toast.makeText(MainActivity.this, "您所輸入的帳號與密碼不相符。", Toast.LENGTH_SHORT).show();}

                    else
                    {
                        //存入資料
                        SharedPreferences settings=  getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("id_app_device",id_app_device);
                        editor.putString("username",username);
                        editor.putString("userlogon",userlogon);
                        //editor.putString("domain",domain);
                        editor.putString("emp_no",emp_no);
                        editor.commit();//要記得加  儲存上面所修改的

                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,menu.class);
                        intent.putExtra("id_app_device",id_app_device);//可放所有基本類別
                        intent.putExtra("username",username);
                        intent.putExtra("userlogon",userlogon);
                        //intent.putExtra("domain",domain);
                        intent.putExtra("emp_no",emp_no);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            super.handleMessage(msg);
        }
    }};

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
}
