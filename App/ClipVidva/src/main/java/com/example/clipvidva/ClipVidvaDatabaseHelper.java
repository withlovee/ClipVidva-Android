package com.example.clipvidva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Vee on 7/9/2556.
 */
public class ClipVidvaDatabaseHelper extends SQLiteOpenHelper {

    private String[] tables;
    private String[] creates;
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_SUBJECTS = "subjects";
    public static final String TABLE_VIDEOS = "videos";
    public static final String TABLE_QUIZZES = "quizzes";

    public static final String CATEGORY_COL_ID = "_id";
    public static final String CATEGORY_COL_NAME = "name";
    public static final String CATEGORY_COL_IMG = "img";
    public static final String SUBJECT_COL_ID = "_id";
    public static final String SUBJECT_COL_NAME = "_name";
    public static final String SUBJECT_COL_CATEGORY = "category_id";
    public static final String VIDEO_COL_ID = "_id";
    public static final String VIDEO_COL_NAME = "name";
    public static final String VIDEO_COL_FILE = "file";
    public static final String VIDEO_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_ID = "_id";
    public static final String QUIZ_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_QUESTION = "question";
    public static final String QUIZ_COL_TYPE = "type";
    public static final String QUIZ_COL_CHOICES = "choices";
    public static final String QUIZ_COL_HINT = "hint";
    public static final String QUIZ_COL_ANSWER = "answer";
    public static final String QUIZ_COL_DESCRIPTION = "description";

    private static final String DATABASE_NAME = "clipvidva.db";
    private static final int DATABASE_VERSION = 11;

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
    private static final String VIDEOS_CREATE = "create table "
            + TABLE_VIDEOS + "("
            + VIDEO_COL_ID + " integer primary key autoincrement, "
            + VIDEO_COL_NAME + " text not null, "
            + VIDEO_COL_FILE + " text not null, "
            + VIDEO_COL_SUBJECT + " integer not null);";
    private static final String QUIZZES_CREATE = "create table "
            + TABLE_QUIZZES + "("
            + QUIZ_COL_ID + " integer primary key autoincrement, "
            + QUIZ_COL_SUBJECT + " integer not null, "
            + QUIZ_COL_QUESTION + " text not null, "
            + QUIZ_COL_TYPE + " text not null, "
            + QUIZ_COL_CHOICES + " text, "
            + QUIZ_COL_ANSWER + " text not null, "
            + QUIZ_COL_HINT + " text, "
            + QUIZ_COL_DESCRIPTION + " text);";

    public ClipVidvaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        String[] all_tables = {TABLE_CATEGORIES, TABLE_SUBJECTS, TABLE_VIDEOS, TABLE_QUIZZES};
        String[] all_creates = {CATEGORIES_CREATE, SUBJECTS_CREATE, VIDEOS_CREATE, QUIZZES_CREATE};
        this.tables = all_tables;
        this.creates = all_creates;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for(String create_query: this.creates){
            database.execSQL(create_query);
        }
        // TODO: Change from hard-code to read from file
        database.execSQL("INSERT INTO categories VALUES(1,'" + context.getResources().getString(R.string.math_category) + "','math');");
        database.execSQL("INSERT INTO categories VALUES(2,'" + context.getResources().getString(R.string.askvidva_category) + "','ask');");
        // Subjects for Maths category
        database.execSQL("INSERT INTO subjects VALUES(1,'" + context.getResources().getString(R.string.subject_real_number) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(2,'" + context.getResources().getString(R.string.subject_conic_section) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(3,'" + context.getResources().getString(R.string.subject_functions) + "', 1);");
        // Videos for Real Number
        database.execSQL("INSERT INTO videos VALUES(1,'Video 1','real123', 1);");
        database.execSQL("INSERT INTO videos VALUES(2,'Video 2','real223', 1);");
        database.execSQL("INSERT INTO videos VALUES(3,'Video 3','real323', 1);");
        // Videos for Conic Section
        database.execSQL("INSERT INTO videos VALUES(4,'Conic Video 1','conic123', 2);");
        database.execSQL("INSERT INTO videos VALUES(5,'Conic Video 2','conic223', 2);");
        // Question
        database.execSQL("INSERT INTO quizzes VALUES(1, 1, 'Real Question', 'text', '', '1', '', 'description goes here');");
        database.execSQL("INSERT INTO quizzes VALUES(2, 1, '22 Real Question 2', 'text', '', '2', '', 'description goes here 22');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClipVidvaDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        for(String table_name: this.tables){
            db.execSQL("DROP TABLE IF EXISTS " + table_name);
        }
        onCreate(db);
    }

}