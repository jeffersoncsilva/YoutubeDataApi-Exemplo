package com.example.animacoesfragments.utilities;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenHelper {

    public static float getHeithDisplayInPixles(Context c){
        return c.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getWidhDisplayInPixels(Context c){
        return c.getResources().getDisplayMetrics().widthPixels;
    }

    public static float getHeighDisplayInDp(Context c){
        DisplayMetrics m = c.getResources().getDisplayMetrics();
        return (m.heightPixels / m.density);
    }

    public static float getWidtDisplayInDp(Context c){
        DisplayMetrics m = c.getResources().getDisplayMetrics();
        return (m.widthPixels / m.density);
    }

    public static float convertDpToPixels(Context c, int dp){
        return dp * ((float) c.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(Context c, int px){
        return px / ((float) c.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int getHeighRecyclerview(Context context, float percent){
        /*
         * recebe o context, o percentual de tela para calculo
         * Retorna a altura equivalente para um componente recyclerview na pagina da videoteca, seguindo a taxa de proporcao 16/9,
         * onde a largura de um elemente e o equivalente em percent (sendo de 0.85 para os ultimos videos e de 0.65 para os demais)
         * podendo esse percentuais serem alterados. Somando ainda uma altura que sera para ser colocado as informacoes
         * como nome do video, numero de visualizacoes, likes e comentarios.
         * Para isso, o metodo funciona da seguinte forma:
         *           ->Pega a largura da tela do dispositivo
         *           ->Pega o percentual passado desse valor
         *           ->Realiza o calculo para obter a altura equivalente seguindo a proporcao 16/9
         *           ->Ajusta a altura do recycler_view(s)
         */
        float width = getWidhDisplayInPixels(context) * percent;//pega o 'percent' % da tela
        float heigh_rec = ((width * 9)/16);//converte para a proporcao 16/9
        return (int)Math.round(heigh_rec);
    }

    public static int getDensytiScreen(Context con){
        return con.getResources().getDisplayMetrics().densityDpi;
    }


}
