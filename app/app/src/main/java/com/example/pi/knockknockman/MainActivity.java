package com.example.pi.knockknockman;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public String piAddr = "http://192.168.0.11:8000/index.html";
    WebView mWebView;
    Button SendMsg;
    Button AExit;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference DataRef = firebaseDatabase.getReference("homeCCTV");

    private Handler mHandler;
    private Runnable mRunnable;

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.mWebView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(piAddr);

        SendMsg = findViewById(R.id.SendMSG);
        AExit = findViewById(R.id.Exit);


        SendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SendActivity.class);
                startActivity(i);
            }
        });

        AExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        mRunnable = new Runnable() {
            @Override
            public void run() {
                DataRef.child("test").setValue("\"0\"");
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 10000);
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Knock Knock Man");
        builder.setMessage("Knock Knock Man 앱을 종료하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

    /*
    class MyMainLocalReceiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SMS_RECEIVED)) {
                Log.d(TAG, "onReceiver() 호출");

                Bundle bundle = intent.getExtras();
                SmsMessage[] messages = parseSmsMessage(bundle);

                if (messages.length > 0) {
                    String sender = messages[0].getOriginatingAddress();
                    String contents = messages[0].getMessageBody();
                    Date receivedDate = new Date(messages[0].getTimestampMillis());

                    Log.d(TAG, "sender : " + sender);
                    Log.d(TAG, "contents : " + contents);
                    Log.d(TAG, "receivedDate : " + receivedDate);

                    final Notification.Builder builder = new Notification.Builder(getApplicationContext());

                    if (sender.equals("")) {
                        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("TEST_TITLE").setContentText("TEST_BODY").setContentTitle("TEST_SUBJECT").setSmallIcon(R.mipmap.ic_launcher_round).build();
                        notif.notify(0, notify);
                    }
                }
            }
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for (int i = 0; i < objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }

    */

}
