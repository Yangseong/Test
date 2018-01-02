package com.example.koreabnc.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value;
    TextView textView = null;

    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        textView.setText("value 값 : " + value);
                        break;
                }
                super.handleMessage(msg);
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    value++;
                    try {
                        Thread.sleep(1000);
                        Message msg = mHandler.obtainMessage(1, value);
                        mHandler.sendMessage(msg);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}