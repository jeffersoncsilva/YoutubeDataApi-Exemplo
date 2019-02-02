package com.example.animacoesfragments.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animacoesfragments.R;
import com.example.animacoesfragments.adapters.VideosAdapters;
import com.example.animacoesfragments.models.Video;

import java.util.ArrayList;
import java.util.List;

public class FragmentGames extends Fragment {
    public static final String TAG = "frag_games";
    private RecyclerView rc_games;
    private VideosAdapters adapterGames;
    private List<Video> videosGames;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_games, container, false);

        this.rc_games = v.findViewById(R.id.rc_videos);
        videosGames = new ArrayList<>();
        for(int i = 0; i < 30; i++)
            videosGames.add(new Video());
        adapterGames = new VideosAdapters(videosGames, getContext());

        this.rc_games.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rc_games.setHasFixedSize( true );
        this.rc_games.setAdapter(adapterGames);


        return v;
    }


    @Override
    public void onStart(){
        super.onStart();
        Bundle b = getArguments();
        if(b == null){
            Log.d(TAG, "onStart: nenhum bundle foi recebido.");
        }else{
            /* Restaura os dados do fragmento atual. */
            String str = b.getString("chave");
            Parcelable scrollPos = b.getParcelable("scroll_games");
            rc_games.getLayoutManager().onRestoreInstanceState(scrollPos);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*
        * Quando o usuario vai para outra pagina, salva o estado da pagina atual
        * salva os videosGames que ja foram buscados no recyclerview e salva tabmem a posição em que o recyclerview
        * estava mostrando.
        * */
        Bundle b = new Bundle();
        b.putString("chave", "on destroy view foi chamado e foi criado um bundle.");
        //TODO: Salva o estado do recycler view. (Posicao que esta sendo mostrada).
        Parcelable scrollPos = rc_games.getLayoutManager().onSaveInstanceState();
        b.putParcelable("scroll_games", scrollPos);



        setArguments(b);
    }

    @Override
    public String toString() {
        return TAG;
    }
}
