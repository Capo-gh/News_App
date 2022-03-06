package com.donsmart.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.donsmart.newsapp.model.NewsApiResponse;
import com.donsmart.newsapp.model.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    List<NewsArticle> newsArticles;
    ItemClicked itemClicked;

    public CustomAdapter(Context context, List<NewsArticle> newsArticles, ItemClicked itemClicked) {
        this.context = context;
        this.newsArticles = newsArticles;
        this.itemClicked = itemClicked;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.headline_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.tvTitle.setText(newsArticles.get(position).getTitle());
        holder.tvSource.setText(newsArticles.get(position).getSource().getName());

        if (newsArticles.get(position).getUrlToImage() != null) {
            Picasso.get().load(newsArticles.get(position).getUrlToImage()).into(holder.ivHeadline);
        }

        holder.main_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.onItemClicked(newsArticles.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView main_container;
        TextView tvTitle;
        TextView tvSource;
        ImageView ivHeadline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            main_container = itemView.findViewById(R.id.main_container);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            ivHeadline = itemView.findViewById(R.id.ivHeadline);

        }
    }
}
