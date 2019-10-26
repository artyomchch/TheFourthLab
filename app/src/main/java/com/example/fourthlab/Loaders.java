package com.example.fourthlab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static android.provider.VoicemailContract.Voicemails.CONTENT_URI;


public class Loaders extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    public final String TAG = this.getClass().getSimpleName();
    private TextView mResultTextView;
    public static final int LOADER_ID = 1;
    private Loader<String> mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaders);

        mResultTextView =  findViewById(R.id.resultTxt);
        Bundle bundle = new Bundle();
        bundle.putString(RandomLoader.ARG_WORD, "test");
        // Инициализируем загрузчик с идентификатором
        // Если загрузчик не существует, то он будет создан,
        // иначе он будет перезапущен.
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
    }


    @Override
    public Loader<String> onCreateLoader(int id,  Bundle args) {
        // Создаем новый CursorLoader с нужными параметрами
        Loader<String> mLoader = null;
        // условие можно убрать, если вы используете только один загрузчик
        if (id == LOADER_ID) {
            mLoader = new RandomLoader(this, args);
            Log.d(TAG, "onCreateLoader");
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished( Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished");
        mResultTextView.setText(data);

        // Если используется несколько загрузчиков, то удобнее через оператор switch-case
//        switch (loader.getId()) {
//            case LOADER_ID:
//                // Данные загружены и готовы к использованию
//
//                break;
//        }
        // список теперь содержит данные на экране
    }

    // Вызовется при уничтожении активности

    @Override
    public void onLoaderReset( Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    public void onClick(View view) {
        Log.d(TAG, "startLoad");
        mLoader.onContentChanged();
    }

}
