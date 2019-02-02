package com.example.animacoesfragments.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.animacoesfragments.R;

public class FragmentController {
    final String TAG = "frag_controller";

    private FragmentManager manager;
    private Activity act;
    private int oldFrag, newFrag;


    public FragmentController(FragmentManager manager, Activity act) {
        this.manager = manager;
        this.act = act;
    }

    public void changeFragment(int newFragId, int oldFragId) {
        /*
         * Como funciona: na main activity, as variaveis que controlam qual tela devera ser mostrada sao iniciadas com -1,
         * logo se qualquer uma das duas variaveis forem menores do que zero nada sera feito. Caso nao seja,
         * decide qual animacao que devera ser exibida, caso o valor do id do novo fagmento (que e a posição escolhida no
         * TabLayout que esta na MainActivity) seja maior do que o anterior, entao executa uma animação de saida para a esquerda do
         * fragmento anterior e uma animação de entrada para o novo fragmento. Caso o id do novo fragmento nao seja maior que o antigo,
         * realiza a mesma animação com sentido oposto, o velho fragmento sai para a direita e o novo entra pela direita.
         * apos definido a animação, cria o fragmento na chamada a { getNewFragment() } passando o id (podicao da TabLayout
         * selecionada na MainActivity escolhida pelo usuario), caso a posição seja invalida (Nesse caso somente seria possivel
         * se ocorrese algum erro na hora de converter int para byte ou seja adicionado novas opções ao menu e nao adicionado a função
         * que cria o fragmento) ele retorna nulo, informa uma mensagem no log e para a execução do metodo {um bom lugar para lançar uma
         * exceção } caso nao seja nulo, mostra o fragment para o usuario.
         * */
        if ((newFragId < 0) || (oldFragId < 0))
            Log.d(TAG, "changeFragment: fragmentos invalidos. Nao foi possivel ler as posiçoes dos novos fragments.");
        else {
            FragmentTransaction ft = this.manager.beginTransaction();
            if (newFragId > oldFragId)
                ft.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_rigth_exit);
            else
                ft.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_rigth_exit, R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);

            Log.d(TAG, "changeFragment: oldId: " + oldFragId + " | NewID: "+newFragId);
            Fragment f = getNewFragment(newFragId);
            if (f == null) {
                Log.d(TAG, "changeFragment: ERRO: nao foi possivel encontrar o fragmento indicado.");
                return;
            }
            ft.replace(R.id.fragmento_conteiner, f, f.toString());
            ft.addToBackStack(f.toString());
            ft.commit();
        }
    }

    public void setHomeFragment() {
        FragmentTransaction ft = this.manager.beginTransaction();
        Fragment f = getNewFragment(0);
        ft.add(R.id.fragmento_conteiner, f, FragmentGames.TAG);
        ft.commit();
    }

    private Fragment getNewFragment(int id) {
        Fragment f = null;
        switch (id) {
            case 0:
                f = this.manager.findFragmentByTag(FragmentGames.TAG);
                return (f == null) ? new FragmentGames() : f;
            case 1:
                f = this.manager.findFragmentByTag(FragmentFutebol.TAG);
                return (f == null) ? new FragmentFutebol() : f;
            case 2:
                f = this.manager.findFragmentByTag(FragmentCarros.TAG);
                return (f == null) ? new FragmentCarros() : f;
            default:
                return null;
        }
    }


}
