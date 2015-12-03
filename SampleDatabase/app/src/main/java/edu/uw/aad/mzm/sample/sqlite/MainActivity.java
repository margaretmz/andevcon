package edu.uw.aad.mzm.sample.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import edu.uw.aad.mzm.sample.sqlite.data.VersionContract;
import edu.uw.aad.mzm.sample.sqlite.data.VersionDbHelper;
import edu.uw.aad.mzm.sample.sqlite.model.AndroidVersion;

/**
 * Created by Margaret on 2/19/2015
 * This sample app demos how to create a SQLite database in Android
 *
 * 1. Create a class that defines the database schema
 * 2. Create a class that extends SQLiteOpenHelper
 * 3. Use the Database helper class to create & manage db
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private VersionDbHelper mDbHelper;
    private List<AndroidVersion> androidVersionList;
    private ListView mListViewAndroid;
    private ArrayAdapter<AndroidVersion> mAndroidArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new VersionDbHelper(this);

        // Add a few Android versions
        mDbHelper.insertAndroidVersion(new AndroidVersion("Cupcake", "1.5", "API 3", "April 2009", "Support for Widgets"));
        mDbHelper.insertAndroidVersion(new AndroidVersion("Donut", "1.6", "API 4", "September 2009", "Improvements in search"));
        mDbHelper.insertAndroidVersion(new AndroidVersion("Éclair", "2.0-2.1", "API 5-7", "Oct 2009 - Jan 2010", "Improvements in Google Maps"));

        // Get all the Android Versions data from db
        androidVersionList = mDbHelper.getAndroidVersions();


        // Set up UI ListView
        mListViewAndroid = (ListView)findViewById(R.id.listViewAndroid);
        mAndroidArrayAdapter = new ArrayAdapter<AndroidVersion>(this,  // context
                android.R.layout.simple_list_item_1,                        // UI layout
                androidVersionList);                                                     // objects
        mListViewAndroid.setAdapter(mAndroidArrayAdapter);

        mListViewAndroid.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            db.delete(VersionContract.Version.TABLE_NAME, null, null);
            db.close();
            mAndroidArrayAdapter.clear();
            mAndroidArrayAdapter.addAll(mDbHelper.getAndroidVersions());
            mAndroidArrayAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Item position in list is " + position, Toast.LENGTH_LONG).show();

    }
}
