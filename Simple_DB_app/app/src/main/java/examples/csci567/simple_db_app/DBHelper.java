package examples.csci567.simple_db_app;

/**
 * Created by Siddharth on 3/8/2015.
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname = "test1.db";
    private static final int version = 1;
    private final String EXAMPLE_TABLE = "exampleTable1";


      public DBHelper(Context context){
            super(context,dbname,null,version);
        }

       @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + EXAMPLE_TABLE + " (text VARCHAR UNIQUE);");

        }


   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertText(String text) {



            try {
                SQLiteDatabase qdb = this.getWritableDatabase();
                Log.d("DB Insert: ", "INSERT OR REPLACE INTO " +
                        EXAMPLE_TABLE + " (text) Values (" + text + ");");
                //returned = qdb.insertOrThrow(EXAMPLE_TABLE, null, );
                qdb.execSQL("INSERT OR REPLACE INTO " +
                        EXAMPLE_TABLE + " (text) Values (\"" + text + "\");");
                qdb.close();
            } catch (SQLiteException se) {

                Log.d("DB Insert Error: ", se.toString());
                //Toast.makeText(getActivity(), "text present previously in the file", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;

    }

    private Context getApplicationContext() {
        return null;
    }

    public String getText(){
        String toReturn = "";
        try{
           SQLiteDatabase qdb = this.getReadableDatabase();
            qdb.execSQL("CREATE TABLE IF NOT EXISTS " + EXAMPLE_TABLE + " (text VARCHAR UNIQUE);");
            Cursor c = qdb.rawQuery("SELECT * FROM " +
                    EXAMPLE_TABLE, null);
            if (c != null ) {
             if (c.moveToFirst()) {
                    do {
                        String text = c.getString(c.getColumnIndex("text"));
                        toReturn += text + "\n";
                    }
                 while (c.moveToNext());
               }
            }
            qdb.close();
        }
       catch(SQLiteException se){
            Log.d("DB Select Error: ",se.toString());
         return "";
       }
       return toReturn;
    }
}