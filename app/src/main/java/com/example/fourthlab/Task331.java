package com.example.fourthlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task331 extends AppCompatActivity {

    TextView resultTextView;
    TextView Time;
    TextView Finish;
    String K;
    Long L;
    String day;
    public Task331() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task331);
        resultTextView = findViewById(R.id.textView2);
        Time = findViewById(R.id.time);
        Finish = findViewById(R.id.finish);


        MyAsyncTask myAsyncTask = new MyAsyncTask(resultTextView, Time);
        myAsyncTask.execute();

        day = getCurrentTimeStamp();
        System.out.println(getDayCount(day, "20.06.2020"));
        L = getDayCount(day, "20.06.2020");
        K = L.toString();
        Finish.setText("До защиты диплома осталось " + K + " дней!");


        System.out.println(getCurrentTimeStamp());
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static long getDayCount(String start, String end) {
        long diff = -1;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
            //handle the exception according to your own situation
        }
        return diff;
    }



}
class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    private static final String TAG = MyAsyncTask.class.getSimpleName();
    private WeakReference<TextView> wTextView;
    private WeakReference<TextView> curTime;

    MyAsyncTask(TextView textView, TextView time) {
        this.wTextView = new WeakReference<>(textView);
        this.curTime = new WeakReference<>(time);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        updateText("Запуск");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Date date = new Date();
        updateText("Выполнено: " + values[0]);
        updateTime("Current Time: " + date.toString());
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            int counter = 0;
            for (int i = 0; i < 20000; i++) {
                getTime();
                publishProgress(++counter);
            }
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        updateText("Выполнено");
    }

    private void getTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(20);
    }
    private void updateText(String str){
        final TextView textView = wTextView.get();
        if (textView != null) {
            textView.setText(str);
        } else {
            Log.d(TAG, "Что-то пошло не так!");
        }
    }

    private void updateTime(String str){
        final TextView textView = curTime.get();
        if (textView != null) {
            textView.setText(str);
        } else {
            Log.d(TAG, "Что-то пошло не так!");
        }
    }
}

