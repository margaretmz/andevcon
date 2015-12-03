package edu.uw.aad.mzm.sample.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uw.aad.mzm.sample.sqlite.model.AndroidVersion;

/**
 * Created by Margaret on 2/19/2015.
 *
 * Helps create the database and its tables
 */
public class VersionDbHelper extends SQLiteOpenHelper {

    private final static String LOG_TAG = VersionDbHelper.class.getSimpleName();

    // Database name
    private final static String DB_NAME = VersionContract.DATABASE_NAME;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + VERSION_TABLE_NAME);
        onCreate(db);

    }

    public void insertAndroidVersion(AndroidVersion androidVersion) {

        Log.i(LOG_TAG, "Added a Android version - " + androidVersion.toString());

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add data
        ContentValues contentValues = new ContentValues();
        contentValues.put(VERSION_ROW_CODE_NAME, androidVersion.getCodeName());
        contentValues.put(VERSION_ROW_VERSION_NO, androidVersion.getVersionNo());
        contentValues.put(VERSION_ROW_API_LEVEL, androidVersion.getApiLevel());
        contentValues.put(VERSION_ROW_RELEASE_DATE, androidVersion.getReleaseDate());
        contentValues.put(VERSION_ROW_FEATURES, androidVersion.getFeatures());

        // Insert data to table
        db.insert(VERSION_TABLE_NAME, // table name
                null,
                contentValues);

        // Remember to close the db
        db.close();
    }


    public List<AndroidVersion> getAndroidVersions() {

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        List<AndroidVersion> androidVersions = new ArrayList<AndroidVersion>();

        Cursor cursor = db.query(VERSION_TABLE_NAME,
                VersionContract.Version.PROJECTION,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            AndroidVersion androidVersion = cursorToAndroidVersion(cursor);
            androidVersions.add(androidVersion);
            cursor.moveToNext();
        }

        cursor.close(); // close the cursor
        db.close();     // close the db

        return androidVersions;
    }

    /**
     * Convert a cursor to a AndroidVersion object
     * @param cursor
     * @return
     */
    private AndroidVersion cursorToAndroidVersion(Cursor cursor) {

        AndroidVersion androidVersion = new AndroidVersion();
        androidVersion.setId(cursor.getInt(0));
        androidVersion.setCodeName(cursor.getString(1));
        androidVersion.setVersionNo(cursor.getString(2));
        androidVersion.setApiLevel(cursor.getString(3));
        androidVersion.setReleaseDate(cursor.getString(4));
        androidVersion.setFeatures(cursor.getString(5));

        return androidVersion;
    }
}
