package com.example.fourthlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView InfoTextView;
    protected TextView LogTextView;
    protected int c = 1;
    protected Button ThreadUI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfoTextView = findViewById(R.id.infoTextView);
        LogTextView = findViewById(R.id.LogTextViev);

        ThreadUI = findViewById(R.id.threadui);
        ThreadUI.setOnClickListener(this);


        Thread mainThread = Thread.currentThread();
        InfoTextView.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MireaThread");
        InfoTextView.append("\nНовое имя потока: " + mainThread.getName());


    }


    public void onClick(View view) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyRunnable runnable = new MyRunnable("mirea ");
        new Thread(runnable).start();

        if (c % 2 == 1) {
            LogTextView.setVisibility(View.VISIBLE);
            c++;
        }
        else {
            LogTextView.setVisibility(View.INVISIBLE);
            c++;
        }
        LogTextView.setText("D/MyThread: Mой поток запущен...");
        LogTextView.append("\nD/MyRunnable:  Mой поток запущен:mirea");


        switch (view.getId()) {
            case R.id.threadui:

                goToActive(ThreadUi.class);
                break;
        }

    }


    protected void goToActive(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }



}


class MyThread extends Thread {
    private static final String TAG = MyThread.class.getSimpleName();
    public void run() {
        Log.d(TAG, "Mой поток запущен...");

    }
}
class MyRunnable implements Runnable {
    private static final String TAG = MyRunnable.class.getSimpleName();
    private String goal;
    public MyRunnable(String goal) {
        this.goal=goal;
    }
    @Override
    public void run() {
        Log.d(TAG, " Mой поток запущен:" + goal);
    }
}