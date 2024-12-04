package com.example.doga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTitle, editAuthor, editPages;

    private ArrayList<Book> books;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        editPages = findViewById(R.id.editPages);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView listViewBooks = findViewById(R.id.listViewBooks);

        books = new ArrayList<>();
        adapter = new BookAdapter(this, books);
        listViewBooks.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String author = editAuthor.getText().toString().trim();
                String pagesText = editPages.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || pagesText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Minden mezőt tölts ki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pages;
                try {
                    pages = Integer.parseInt(pagesText);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Oldalszám érvénytelen!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pages < 50) {
                    Toast.makeText(MainActivity.this, "Oldalszámnak legalább 50-nek kell lennie!", Toast.LENGTH_SHORT).show();
                    return;
                }

                books.add(new Book(title, author, pages));
                adapter.notifyDataSetChanged();

                editTitle.setText("");
                editAuthor.setText("");
                editPages.setText("");
            }
        });


        listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = books.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });
    }
}
