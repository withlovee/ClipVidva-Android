package com.example.clipvidva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Vee on 7/9/2556.
 */
public class ClipVidvaDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_CATEGORIES = "categories";
    public static final String CATEGORY_COL_ID = "_id";
    public static final String CATEGORY_COL_NAME = "name";
    public static final String CATEGORY_COL_IMG = "img";

    private static final String DATABASE_NAME = "clipvidva.db";
    private static final int DATABASE_VERSION = 4;

    private Context context;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CATEGORIES + "("
            + CATEGORY_COL_ID + " integer primary key autoincrement, "
            + CATEGORY_COL_NAME + " text not null, "
            + CATEGORY_COL_IMG + " text);";

    public ClipVidvaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        // TODO: Change from hard-code to read from file
        database.execSQL("INSERT INTO categories VALUES(1,'" + context.getResources().getString(R.string.math_category) + "','math');");
        database.execSQL("INSERT INTO categories VALUES(2,'" + context.getResources().getString(R.string.askvidva_category) + "','ask');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClipVidvaDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }

}