package com.donsmart.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.donsmart.newsapp.model.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    private Context context;

    public RequestManager(Context context) {
        this.context = context;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build();

    public void getNewsArticles(OnFetchDataListener listener,  String category, String searchQuery) {

        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);

        Call<NewsApiResponse> call = callNewsApi.callNewsArticles("us", category, searchQuery, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> callNewsArticles(@Query("country") String country, @Query("category") String category,
                                               @Query("q") String searchQuery, @Query("apiKey") String api_key);
    }


}
