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

public class FragmentFutebol extends Fragment {
    public static final String TAG ="frag_futebol";

    private RecyclerView rc_futebol;
    private VideosAdapters adapterFutebol;
    private List<Video> videosFutebol;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_futebol, container, false);

        this.rc_futebol = v.findViewById(R.id.rc_futebol);
        videosFutebol = new ArrayList<>();
        for(int i = 0; i < 30; i++)
            videosFutebol.add(new Video());
        adapterFutebol = new VideosAdapters(videosFutebol, getContext());

        this.rc_futebol.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rc_futebol.setHasFixedSize( true );
        this.rc_futebol.setAdapter(adapterFutebol);

        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle b = getArguments();
        if(b == null){
            Log.d(TAG, "onStart fragmentos futebol: nenhum bundle foi recebido.");
        }else{
            /* Restaura os dados do fragmento atual. */
            String str = b.getString("chave");
            Parcelable scrollPos = b.getParcelable("scroll_fut");
            rc_futebol.getLayoutManager().onRestoreInstanceState(scrollPos);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*
         * Quando o usuario vai para outra pagina, salva o estado da pagina atual
         * salva os videosFutebol que ja foram buscados no recyclerview e salva tabmem a posição em que o recyclerview
         * estava mostrando.
         * */
        Bundle b = new Bundle();
        b.putString("chave", "on destroy view foi chamado e foi criado um bundle.");
        //TODO: Salva o estado do recycler view. (Posicao que esta sendo mostrada).
        Parcelable scrollPos = rc_futebol.getLayoutManager().onSaveInstanceState();
        b.putParcelable("scroll_fut", scrollPos);



        setArguments(b);
    }

    @Override
    public String toString() {
        return TAG;
    }


}
