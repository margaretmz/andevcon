package com.mzm.sample.content_provider.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Margaret on 11/21/15.
 *
 * * Helps create the database and its tables
 */

public class VersionDbHelper extends SQLiteOpenHelper {

    private final static String LOG_TAG = VersionDbHelper.class.getSimpleName();

    // Database name
    private final static String DB_NAME = "android-release.db";

    // Database version
    private final static int DB_VERSION = 1;

    // Version table
    private final static String VERSION_TABLE_NAME = VersionContract.Version.TABLE_NAME;
    private final static String VERSION_ROW_ID = VersionContract.Version.ID;
    private final static String VERSION_ROW_CODE_NAME = VersionContract.Version.CODE_NAME;
    private final static String VERSION_ROW_VERSION_NO = VersionContract.Version.VERSION_NO;
    private final static String VERSION_ROW_API_LEVEL = VersionContract.Version.API_LEVEL;
    private final static String VERSION_ROW_RELEASE_DATE = VersionContract.Version.RELEASE_DATE;
    private final static String VERSION_ROW_FEATURES = VersionContract.Version.FEATURES;

    // SQL statement to create the Version table
    private final static String VERSION_TABLE_CREATE =
            "CREATE TABLE " +
                    VERSION_TABLE_NAME + " (" +
                    VERSION_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    VERSION_ROW_CODE_NAME + " TEXT, " +
                    VERSION_ROW_VERSION_NO + " TEXT, " +
                    VERSION_ROW_API_LEVEL + " TEXT, " +
                    VERSION_ROW_RELEASE_DATE + " TEXT, " +
                    VERSION_ROW_FEATURES + " TEXT" + ");";

    public VersionDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Version table
        db.execSQL(VERSION_TABLE_CREATE);
        Log.i(LOG_TAG, "Creating table with query: " + VERSION_TABLE_CREATE);

        // Create initial data
        ContentValues contentValues = new ContentValues();
        contentValues.put(VERSION_ROW_CODE_NAME, "Cupcake");
        contentValues.put(VERSION_ROW_VERSION_NO, "1.5");
        contentValues.put(VERSION_ROW_API_LEVEL, "API 3");
        contentValues.put(VERSION_ROW_RELEASE_DATE, "April 2009");
        contentValues.put(VERSION_ROW_FEATURES, "Support for Widgets");

        db.insert(VERSION_TABLE_NAME, // table name
                null,
                contentValues);

        contentValues.put(VERSION_ROW_CODE_NAME, "Donut");
        contentValues.put(VERSION_ROW_VERSION_NO, "1.6");
        contentValues.put(VERSION_ROW_API_LEVEL, "API 4");
        contentValues.put(VERSION_ROW_RELEASE_DATE, "September 2009");
        contentValues.put(VERSION_ROW_FEATURES, "Quick search box, screen size diversity, Android market");

        db.insert(VERSION_TABLE_NAME, // table name
                null,
                contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + VERSION_TABLE_NAME);
        onCreate(db);

    }

}
