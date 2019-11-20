package com.example.gallery.remote;

import com.example.gallery.model.GalleryCategorie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryList
{
    private String stat;

    private Photos photos;

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    public Photos getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos photos)
    {
        this.photos = photos;
    }

//    @Override
//    public String toString()
//    {
//        return "ClassPojo [stat = "+stat+", photos = "+photos+"]";
//    }
}

