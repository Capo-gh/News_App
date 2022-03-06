package com.donsmart.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.donsmart.newsapp.model.NewsArticle;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView tvDetailTitle;
    TextView tvDetailAuthor;
    ImageView ivDetailNews;
    TextView tvDetailTime;
    TextView tvDetailDetail;
    TextView tvDetailContent;

    NewsArticle newsArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvDetailAuthor = findViewById(R.id.tvDetailAuthor);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailTime = findViewById(R.id.tvDetailTime);
        ivDetailNews = findViewById(R.id.ivDetailNews);
        tvDetailContent = findViewById(R.id.tvDetailContent);
        tvDetailDetail = findViewById(R.id.tvDetailDetail);

        newsArticle = (NewsArticle) getIntent().getSerializableExtra("data");

        tvDetailTime.setText(newsArticle.getPublishedAt());
        tvDetailAuthor.setText(newsArticle.getAuthor());
        tvDetailTitle.setText(newsArticle.getTitle());
        tvDetailContent.setText(newsArticle.getContent());
        tvDetailDetail.setText(newsArticle.getDescription());

        Picasso.get().load(newsArticle.getUrlToImage()).into(ivDetailNews);

    }
}