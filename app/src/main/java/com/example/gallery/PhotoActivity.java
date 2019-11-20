package com.example.gallery;

import android.app.DownloadManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gallery.model.GalleryCategorie;

public class PhotoActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private DownloadManager mDownloadManager;
    String id,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

         id = getIntent().getStringExtra("id");
        url = getIntent().getStringExtra("url");
        mDownloadManager = (DownloadManager) this.getSystemService(
                this.DOWNLOAD_SERVICE);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        ImageView mPhoto = (ImageView) findViewById(R.id.photo);
        Glide.with(this).load(url).thumbnail(0.5f).into(mPhoto);
        mProgressBar.setVisibility(View.GONE);

        LinearLayout downloadView = (LinearLayout) findViewById(R.id.download);
        downloadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPhoto();
                Toast.makeText(PhotoActivity.this, "Start downloading", Toast.LENGTH_LONG).show();
            }
        });


    }

    // function for downloading original photo when download button is pressed
    private void downloadPhoto() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("SharkFeed Download");
        request.setDescription(url);
        mDownloadManager.enqueue(request);
    }
}
