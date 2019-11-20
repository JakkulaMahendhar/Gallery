package com.example.gallery.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gallery.R;
import com.example.gallery.model.GalleryCategorie;
import com.example.gallery.remote.Photo;
import com.example.gallery.remote.UserRepositry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {


    public String id = "";
    public String farm = "";
    public String secret= "";
    public String server = "";
    public String url_s="";

    public MutableLiveData<ArrayList<GalleryViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private UserRepositry userRepositry;
    public ArrayList<GalleryViewModel> categoryViewModels;


    public GalleryViewModel() {
        userRepositry = new UserRepositry();
        arrayListMutableLiveData = userRepositry.getData("");
    }


    public GalleryViewModel(GalleryCategorie categories)
    {
        this.id = categories.id;
        this.farm = categories.farm;
        this.secret = categories.secret;
        this.server  = categories.server;
        this.url_s = categories.url_s;



    }


    public String getImageUrl()
    {
        return url_s;
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView , String imageUrl)

    {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .thumbnail(0.5f)
                .into(imageView);

    }


    public MutableLiveData<ArrayList<GalleryViewModel>> getArrayListMutableLiveData() {

/*

        categoryViewModels = new ArrayList<>();
        Categories categories = new Categories("1","Title one ","This is desc","image1.png");
        CategoryViewModel categoryViewModel = new CategoryViewModel(categories);


        Categories categories1 = new Categories("2","Title two ","This is desc two","image1.png");
        CategoryViewModel categoryViewModel1 = new CategoryViewModel(categories1);



        categoryViewModels.add(categoryViewModel);
        categoryViewModels.add(categoryViewModel1);
        arrayListMutableLiveData.setValue(categoryViewModels);
*/


        return arrayListMutableLiveData;
    }
}
