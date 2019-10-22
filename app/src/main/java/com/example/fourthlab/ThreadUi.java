package com.example.fourthlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ThreadUi extends AppCompatActivity {


    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_ui);

        tvInfo = findViewById(R.id.tvInfooo);

        final Runnable runn1 = new Runnable() {
            public void run() {
                tvInfo.setText("Load Thread: runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                tvInfo.setText("Load Thread: runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                tvInfo.setText("Load Thread: runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
