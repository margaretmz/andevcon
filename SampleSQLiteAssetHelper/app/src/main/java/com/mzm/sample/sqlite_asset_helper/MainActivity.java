package com.mzm.sample.sqlite_asset_helper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Margaret on 11/26/2015
 *
 * A simple demo of how to use the SQLiteAssetHelper library to ship with an existing db
 * 1. Create a new project
 * 2. Create the /assets/databases folder
 * 3. Copy over a sqlite db file (android-version.db) to /assets/databases/
 * 4. In app build.grade add dependency - compile 'com.readystatesoftware.sqliteasset:sqliteassethelper:+'
 * 5. Create a class that extends SQLiteAssetHelper (instead of SQLiteOpenHelper)
 */
public class MainActivity extends AppCompatActivity {

    private VersionDatabase mDatabase;
    private Cursor mAndroidVersions;
    private SimpleCursorAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.listViewAndroid);
        mDatabase = new VersionDatabase(this);
        mAndroidVersions = mDatabase.getAndroidVersions(); // sample app only, this needs to run in background thread

        String[] fromFields = new String[] {"code_name"};
        int[] toFields = new int[] {android.R.id.text1};

        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_1,
                mAndroidVersions,
                fromFields,
                toFields,
                0
                );

        mListView.setAdapter(mAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAndroidVersions.close(); // close the cursor
        mDatabase.close(); // close the database
    }

}
