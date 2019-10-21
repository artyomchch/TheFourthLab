package com.example.fourthlab;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView InfoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfoTextView = findViewById(R.id.infoTextView);


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