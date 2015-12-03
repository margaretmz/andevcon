package com.mzm.sample.content_provider.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Margaret on 11/21/15.
 *
 * Contains static classes to define table schema etc.
 */
public class VersionContract {

    // Name of the Content Provider, use package name by convention so that it's unique on device
    public static final String CONTENT_AUTHORITY = "com.mzm.sample.content_provider";

    // A path that points to the version table
    public static final String PATH_VERSION = "version";

    // Construct the Base Content Uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Define the Version table
     */
    public static final class Version implements BaseColumns {

        // Content Uri = Content Authority + Path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VERSION).build();

        // Use MIME type prefix android.cursor.dir/ for returning multiple items
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/edu.uw.aad.mzm.sample.com.mzm.sample.sqlite.provider.versions";
        // Use MIME type prefix android.cursor.item/ for returning a single item
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/edu.uw.aad.mzm.sample.com.mzm.sample.sqlite.provider.version";

        // Define table name
        public static final String TABLE_NAME = "version";

        // Define table columns
        public static final String ID = BaseColumns._ID;
        public static final String CODE_NAME = "code_name";
        public static final String VERSION_NO = "version_no";
        public static final String API_LEVEL = "api_level";
        public static final String RELEASE_DATE = "release_date";
        public static final String FEATURES = "features";

        // Define projection for Version table
        public static final String[] PROJECTION = new String[]{
                /*0*/ Version.ID,
                /*1*/ Version.CODE_NAME,
                /*2*/ Version.VERSION_NO,
                /*3*/ Version.API_LEVEL,
                /*4*/ Version.RELEASE_DATE,
                /*5*/ Version.FEATURES
        };
    }
}