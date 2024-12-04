package com.example.doga;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvAuthor = findViewById(R.id.tvDetailAuthor);
        TextView tvPages = findViewById(R.id.tvDetailPages);
        TextView tvYear = findViewById(R.id.tvDetailYear);
        Button btnBack = findViewById(R.id.btnBack);

        Book book = (Book) getIntent().getSerializableExtra("book");

        if (book != null) {
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());
            tvPages.setText("Oldalszám: " + book.getPages());
            tvYear.setText("Random év: " + (1800 + (int) (Math.random() * 223)));
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
