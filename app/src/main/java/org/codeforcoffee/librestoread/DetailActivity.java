package org.codeforcoffee.librestoread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    TextView mTxtTitle;
    TextView mTxtAuthor;
    TextView mTxtRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTxtTitle = (TextView) findViewById(R.id.book_title);
        mTxtAuthor = (TextView) findViewById(R.id.book_author);
        mTxtRating = (TextView) findViewById(R.id.book_rating);

        Intent listIntent = getIntent();
        String title = listIntent.getStringExtra("TITLE");
        String author = listIntent.getStringExtra("AUTHOR");
        String rating = listIntent.getStringExtra("RATING");

        mTxtTitle.setText(title);
        mTxtAuthor.setText(author);
        mTxtRating.setText(rating);

    }
}
