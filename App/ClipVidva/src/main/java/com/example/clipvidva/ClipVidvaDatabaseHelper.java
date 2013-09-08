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
    public static final String TABLE_SUBJECTS = "subjects";

    public static final String CATEGORY_COL_ID = "_id";
    public static final String CATEGORY_COL_NAME = "name";
    public static final String CATEGORY_COL_IMG = "img";
    public static final String SUBJECT_COL_ID = "_id";
    public static final String SUBJECT_COL_NAME = "_name";
    public static final String SUBJECT_COL_CATEGORY = "category_id";

    private static final String DATABASE_NAME = "clipvidva.db";
    private static final int DATABASE_VERSION = 8;

    private Context context;

    // Database creation sql statement
    private static final String CATEGORIES_CREATE = "create table "
            + TABLE_CATEGORIES + "("
            + CATEGORY_COL_ID + " integer primary key autoincrement, "
            + CATEGORY_COL_NAME + " text not null, "
            + CATEGORY_COL_IMG + " text);";
    private static final String SUBJECTS_CREATE = "create table "
            + TABLE_SUBJECTS + "("
            + SUBJECT_COL_ID + " integer primary key autoincrement, "
            + SUBJECT_COL_NAME + " text not null, "
            + SUBJECT_COL_CATEGORY + " integer not null);";

    public ClipVidvaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CATEGORIES_CREATE);
        database.execSQL(SUBJECTS_CREATE);
        // TODO: Change from hard-code to read from file
        database.execSQL("INSERT INTO categories VALUES(1,'" + context.getResources().getString(R.string.math_category) + "','math');");
        database.execSQL("INSERT INTO categories VALUES(2,'" + context.getResources().getString(R.string.askvidva_category) + "','ask');");
        // Subjects for Maths category
        database.execSQL("INSERT INTO subjects VALUES(1,'" + context.getResources().getString(R.string.subject_real_number) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(2,'" + context.getResources().getString(R.string.subject_conic_section) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(3,'" + context.getResources().getString(R.string.subject_functions) + "', 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClipVidvaDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        onCreate(db);
    }

}