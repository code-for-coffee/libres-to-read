package org.codeforcoffee.librestoread;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db;
        db = openOrCreateDatabase("Libres.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);

        db.setVersion(1);
        db.setLocale(Locale.getDefault());

        String titles[] = {
            "How to win at Fantasy Sports",
            "How to keep your hair long and youthful",
            "How to dread your hair when you give up"
        };
        String authors[] = {
            "Jack",
            "Bryon",
            "James"
        };
        Integer ratings[] = {
          5, 5, 1
        };

        /* CREATE TABLE IF NOT EXISTS libres
            (_id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT,
            author TEXT,
            rating INTEGER);
       */

        String createTableSql = "CREATE TABLE IF NOT EXISTS libres\n" +
                "            (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            title TEXT,\n" +
                "            author TEXT,\n" +
                "            rating INTEGER);";

        db.execSQL(createTableSql);

        // for each item in my [], let us INSERT the sql
//        for (int inc = 0; inc < titles.length; inc++) {
//            /*
//            INSERT INTO libres Values (null, "booktitle", "author", 5);
//             */
//            String temporaryQuery = "INSERT INTO libres Values (null, '" + titles[inc] + "', '" +
//                    authors[inc] + "', " + Integer.toString(ratings[inc]) + ");";
//            db.execSQL(temporaryQuery);
//        }

        Cursor cursor = db.query("libres", null, null, null, null, null, null, null);
        ListView mListOfBooks = (ListView) findViewById(R.id.list_of_libres);

        CursorAdapter mCursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,
                        parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                String rowData = cursor.getString(cursor.getColumnIndex("author")) +
                        " WROTE " + cursor.getString(cursor.getColumnIndex("title"));
                txt.setText(rowData);
            }
        };

        mListOfBooks.setAdapter(mCursorAdapter);

    }
}
