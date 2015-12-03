package com.mzm.sample.content_provider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Margaret on 11/21/15.
 *
 * 1. Create a new project
 * 2. Create a class that defines the database schema
 * 3. Create a class that extends SQLiteOpenHelper
 * 4. Use the Database helper class to create & manage db
 * 5. Create a ContentProvider class
 * 6. Add Content Authority, Path, Content URI, Content Type (for single & multiple items)
 * 7. Add URIMatcher
 * 8. Implement the query(), insert(), update(), delete(), getTYpe() methods in Content Provider
 * 9. Register Content Provider in AndroidManifest.xml
 * 10. Update fragment classes to use CursorLoader to load data from ContentProvider
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }
}
