package com.donsmart.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.donsmart.newsapp.model.NewsApiResponse;
import com.donsmart.newsapp.model.NewsArticle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFetchDataListener<NewsApiResponse>, ItemClicked, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;

    Button button1,button2,button3,button4,button5,button6,button7;
    SearchView searchView;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsArticles(this, "general", null);

        button1 = findViewById(R.id.btn_1);
        button2 = findViewById(R.id.btn_2);
        button3 = findViewById(R.id.btn_3);
        button4 = findViewById(R.id.btn_4);
        button5 = findViewById(R.id.btn_5);
        button6 = findViewById(R.id.btn_6);
        button7 = findViewById(R.id.btn_7);
        searchView = findViewById(R.id.searchView);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Fetching news articles of " + query);
                dialog.show();

                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsArticles(MainActivity.this, "general", query);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onFetchData(List<NewsArticle> list, String message) {

        if (list.isEmpty()) {
            Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
        }
        else {
            showNews(list);
            dialog.dismiss();
        }

    }

    @Override
    public void onError(String message) {
        Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
    }

    private void showNews(List<NewsArticle> list) {
        recyclerView = findViewById(R.id.recyclerView_main);

        adapter = new CustomAdapter(MainActivity.this, list, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClicked(NewsArticle newsArticle) {

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("data", newsArticle);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        String category = button.getText().toString();

        dialog.setTitle("Fetching news articles of " + category);
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsArticles(this, category, null);

    }
}