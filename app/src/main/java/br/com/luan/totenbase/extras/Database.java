package br.com.luan.totenbase.extras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bsilva on 17/04/17.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE = "smartkiosk.db";
    private static final String TABLE    = "surveys";
    private static final String ID       = "_id";
    private static final String DATA     = "data";
    private static final int    VERSION  = 1;

    public Database(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ TABLE +"("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ DATA +" TEXT) ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);

    }

}
