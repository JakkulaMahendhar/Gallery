package com.example.gallery.remote;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;


import com.example.gallery.MainActivity;
import com.example.gallery.model.GalleryCategorie;
import com.example.gallery.viewmodel.GalleryViewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositry {

    public MutableLiveData<ArrayList<GalleryViewModel>> data = new MutableLiveData<>();
    private ArrayList<Photo> items;
    private ArrayList<GalleryViewModel> categoryViewModels;

    public UserRepositry() {


    }


    public MutableLiveData<ArrayList<GalleryViewModel>> getData(String page) {


        ApiService apiService = RetroClass.getAPIService();
        Call<GalleryList> categoryListCall;
        if (MainActivity.mLoading) {
            categoryListCall = apiService.getGalleryList("flickr.photos.getRecent", "20", page, "6f102c62f41998d151e5a1b48713cf13", "json", "1", "url_s");
        } else {
            categoryListCall = apiService.getGalleryList("flickr.photos.getRecent", "20", "1", "6f102c62f41998d151e5a1b48713cf13", "json", "1", "url_s");
        }
        categoryListCall.enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {


                items = response.body().getPhotos().getPhoto();
                GalleryCategorie categories;
                GalleryViewModel categoryViewModel;

                categoryViewModels = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {

                    categories = new GalleryCategorie(items.get(i).getId(), items.get(i).getFarm(), items.get(i).getSecret(), items.get(i).getServer(), items.get(i).getUrl_s());
                    categoryViewModel = new GalleryViewModel(categories);
                    categoryViewModels.add(categoryViewModel);


                }


                data.setValue(categoryViewModels);


            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {

            }
        });


        return data;
    }
}


