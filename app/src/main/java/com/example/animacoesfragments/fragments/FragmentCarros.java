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

public class FragmentCarros extends Fragment {
    public static final String TAG = "frag_carros";

    private RecyclerView rc_carros;
    private VideosAdapters adapterCarros;
    private List<Video> videosCarros;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_carros, container, false);

        this.rc_carros = v.findViewById(R.id.rc_carros);
        videosCarros = new ArrayList<>();
        for(int i = 0; i < 30; i++)
            videosCarros.add(new Video());
        adapterCarros = new VideosAdapters(videosCarros, getContext());

        this.rc_carros.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rc_carros.setHasFixedSize( true );
        this.rc_carros.setAdapter(adapterCarros);

        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle b = getArguments();
        if(b == null){
            Log.d(TAG, "onStart Fragmento carros: nenhum bundle foi recebido.");
        }else{
            /* Restaura os dados do fragmento atual. */
            String str = b.getString("chave");
            Parcelable scrollPos = b.getParcelable("scroll_cars");
            rc_carros.getLayoutManager().onRestoreInstanceState(scrollPos);
            rc_carros.scrollToPosition(3);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*
         * Quando o usuario vai para outra pagina, salva o estado da pagina atual
         * salva os videosCarros que ja foram buscados no recyclerview e salva tabmem a posição em que o recyclerview
         * estava mostrando.
         * */
        Bundle b = new Bundle();
        b.putString("chave", "on destroy view foi chamado e foi criado um bundle.");
        //TODO: Salva o estado do recycler view. (Posicao que esta sendo mostrada).
        Parcelable scrollPos = rc_carros.getLayoutManager().onSaveInstanceState();
        b.putParcelable("scroll_cars", scrollPos);



        setArguments(b);
    }

    @Override
    public String toString() {
        return TAG;
    }
}
