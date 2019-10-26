package com.example.fourthlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class Looper1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper1);

    }

    public void onClick(View view) {
        MyLooper ml = new MyLooper();
        ml.start();

        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "mirea");
        msg.setData(bundle);
        if (ml != null) {
            ml.handler.sendMessage(msg);
        }
    }

}
