package com.example.gallery.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gallery.PhotoActivity;
import com.example.gallery.R;
import com.example.gallery.databinding.InnerLayoutBinding;
import com.example.gallery.model.GalleryCategorie;
import com.example.gallery.viewmodel.GalleryViewModel;

import java.util.ArrayList;

public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.CustomView> {

        public ArrayList<GalleryViewModel> arrayList;

        private Context context;

        private LayoutInflater layoutInflater;


        public CustomAdapter(Context context,ArrayList<GalleryViewModel> arrayList){
            this.context = context;
            this.arrayList = arrayList;
        }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            if(layoutInflater == null){
                layoutInflater = LayoutInflater.from(viewGroup.getContext());

            }

            InnerLayoutBinding imageGalleryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innerlayout,viewGroup,false);



        return new CustomView(imageGalleryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView customView, int i) {

            GalleryViewModel galleryViewModel = arrayList.get(i);
            customView.bind(galleryViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CustomView extends RecyclerView.ViewHolder{

            private InnerLayoutBinding imageGalleryBinding;

        public CustomView(InnerLayoutBinding imageGalleryBinding) {
            super(imageGalleryBinding.getRoot());

            this.imageGalleryBinding = imageGalleryBinding;
        }

        public void bind(final GalleryViewModel galleryViewModel){
            this.imageGalleryBinding.setGalleryviewmodel(galleryViewModel);
            imageGalleryBinding.executePendingBindings();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PhotoActivity.class);
                    intent.putExtra("id", galleryViewModel.id);
                    intent.putExtra("url",galleryViewModel.url_s);
                    context.startActivity(intent);
                }
            });
        }

        public InnerLayoutBinding getImageGalleryBinding(){

            return imageGalleryBinding;

        }
    }
}
