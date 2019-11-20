package com.example.gallery;

import android.app.DownloadManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gallery.adapter.CustomAdapter;
import com.example.gallery.remote.UserRepositry;
import com.example.gallery.viewmodel.GalleryViewModel;
import com.reginald.swiperefresh.CustomSwipeRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private CustomSwipeRefreshLayout mCustomSwipeRefreshLayout;
    public  static  GridLayoutManager mLayoutManager;
    public static boolean mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);


        galleryViewModel.getArrayListMutableLiveData().observe(this, new Observer<ArrayList<GalleryViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<GalleryViewModel> galleryViewModels) {

                int numberOfColumns = 3;
                mLayoutManager = new GridLayoutManager(MainActivity.this, numberOfColumns);
                recyclerView.setLayoutManager(mLayoutManager);
                customAdapter = new CustomAdapter(MainActivity.this, galleryViewModels);
                //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(customAdapter);
                mLoading = false;
                mCustomSwipeRefreshLayout.refreshComplete();
            }
        });

        mCustomSwipeRefreshLayout = (CustomSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mCustomSwipeRefreshLayout.setOnRefreshListener(
                new CustomSwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refresh();
                    }
                }
        );

    }

    public void refresh() {
       // customAdapter.clear();
        String page = startLoading();
        UserRepositry userRepositry = new UserRepositry();
        userRepositry.getData(page);
    }


        public static String startLoading() {
            Log.d(TAG, "startLoading");
             mLoading = true;

            int totalItem = mLayoutManager.getItemCount();
            final int page = totalItem / 100 + 1;
            return String.valueOf(page);
    }
}
