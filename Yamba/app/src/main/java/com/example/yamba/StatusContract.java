package com.example.yamba;

import android.net.Uri;
import android.provider.BaseColumns;

public class StatusContract {
    // DB specific constants
    public static final String DB_NAME = "timeline.db";
    public static final int DB_VERSION = 2;
    public static final String TABLE = "status";
    public static final String AUTHORITY = "com.example.yamba.StatusProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final int STATUS_ITEM = 1;
    public static final int STATUS_DIR = 2;
    public static final String STATUS_TYPE_ITEM =
            "vnd.android.cursor.item/vnd.com.example.yamba.provider.status";
    public static final String STATUS_TYPE_DIR =
            "vnd.android.cursor.dir/vnd.com.example.yamba.provider.status";
    public static final String DEFAULT_SORT = Column.CREATED_AT + " DESC";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USER = "user";
        public static final String MESSAGE = "message";
        public static final String CREATED_AT = "created_at";
    }
}