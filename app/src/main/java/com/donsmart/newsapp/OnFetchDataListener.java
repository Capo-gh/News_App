package com.donsmart.newsapp;

import com.donsmart.newsapp.model.NewsArticle;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsArticle> newsArticleList, String message);
    void onError(String message);


}
