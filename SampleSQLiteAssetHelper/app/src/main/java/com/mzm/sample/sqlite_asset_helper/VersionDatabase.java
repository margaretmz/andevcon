package com.mzm.sample.sqlite_asset_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Margaret on 11/26/15.
 */
public class VersionDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "android-release.db";
    private static final int DATABASE_VERSION = 1;

    public VersionDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getAndroidVersions() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String sqlTable = "version";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,
                null, // projection
                null, // selection
                null, // selectionArgs
                null, // groupBy
                null, // having
                null);// sortOrder

        c.moveToFirst();
        return c;

    }
}
