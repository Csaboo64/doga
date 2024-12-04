package com.example.doga;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private  Context context;
    private ArrayList<Book> books;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvAuthor);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        Book book = books.get(position);
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Törlés")
                        .setMessage("Biztos törölni szeretnéd ezt a könyvet?")
                        .setPositiveButton("Igen", (dialog, which) -> {
                            books.remove(position);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("Nem", null)
                        .show();
            }
        });

        return convertView;
    }
}

