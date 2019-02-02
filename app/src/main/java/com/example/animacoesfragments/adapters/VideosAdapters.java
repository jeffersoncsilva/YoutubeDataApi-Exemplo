package com.example.animacoesfragments.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animacoesfragments.R;
import com.example.animacoesfragments.models.Video;
import com.example.animacoesfragments.utilities.ScreenHelper;

import java.util.List;

public class VideosAdapters extends RecyclerView.Adapter<VideosAdapters.MyViewHolder> {

    private List<Video> videos;
    private Context context;

    public VideosAdapters(List<Video> videos, Context con){
        this.context = con;
        this.videos = videos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup conteiner, int i) {
        View v = LayoutInflater.from(conteiner.getContext()).inflate(R.layout.item_video, conteiner, false);

        ViewGroup.LayoutParams layout= v.getLayoutParams();
        layout.height = ScreenHelper.getHeighRecyclerview(this.context, 0.85f);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder h, int i) {
        Video v = videos.get(i);

        h.tx_title.setText(v.getTitle());

    }

    @Override
    public int getItemCount() {
        return (videos == null) ? 0 : videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tx_title;
        public ImageView thumbnails;

        public MyViewHolder(View v){
            super(v);
            tx_title = (TextView) v.findViewById(R.id.tx_title);
            thumbnails = (ImageView) v.findViewById(R.id.thubnails);
        }
    }
}
