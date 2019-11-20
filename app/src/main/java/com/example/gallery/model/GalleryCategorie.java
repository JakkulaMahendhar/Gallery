package com.example.gallery.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryCategorie {

    @SerializedName("id")
    @Expose
    public String id = "";

    @SerializedName("farm")
    @Expose
    public String farm = "";


    @SerializedName("secret")
    @Expose
    public String secret= "";


    @SerializedName("server")
    @Expose
    public String server="";

    @SerializedName("url_s")
    @Expose
    public String url_s="";

    public GalleryCategorie(String id, String farm, String secret, String server,String url_s) {
        this.id = id;
        this.farm = farm;
        this.secret = secret;
        this.server  = server;
        this.url_s = url_s;
    }

    public GalleryCategorie() {
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        Log.d("GalleryItem", "-------------getUrl-------: http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg" );

        return "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg" ;
    }
}
