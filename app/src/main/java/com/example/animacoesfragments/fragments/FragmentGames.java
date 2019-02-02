package com.example.animacoesfragments.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animacoesfragments.R;
import com.example.animacoesfragments.adapters.VideosAdapters;
import com.example.animacoesfragments.models.Video;

import java.util.ArrayList;
import java.util.List;

public class FragmentGames extends Fragment {
    private RecyclerView rc_games;
    private VideosAdapters adapter;
    private List<Video> videos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_games, container, false);

        this.rc_games = v.findViewById(R.id.rc_videos);
        videos = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            videos.add(new Video());
        adapter = new VideosAdapters(videos, getContext());

        this.rc_games.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rc_games.setHasFixedSize( true );
        this.rc_games.setAdapter(adapter);



        return v;
    }

}
