package com.example.gallery.remote;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("services/rest/")
    Call<GalleryList> getGalleryList(@Query("method")String method, @Query("per_page") String per_page, @Query("page") String page, @Query("api_key") String api_key, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback, @Query("extras") String extras);
}
