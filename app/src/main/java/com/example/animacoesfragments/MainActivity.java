package com.example.animacoesfragments;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.example.animacoesfragments.fragments.FragmentController;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "main_activity";
    //Representa o menu inferior.
    private TabLayout bottomMenu;

    private FragmentController fcontrol;
    
    private byte oldPosition = -1, newPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenu = findViewById(R.id.bottom_menu);

        fcontrol = new FragmentController(getSupportFragmentManager(), this);
        fcontrol.setHomeFragment();


        /* pega uma referencia ao menu inferior e adiciona a mudança de
         * fragment de acordo com a seleção do menu inferior.
         */
        bottomMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                newPosition = ( byte ) tab.getPosition();
                tab.getIcon().setColorFilter((getResources().getColor(R.color.white)), PorterDuff.Mode.SRC_IN);
                fcontrol.changeFragment(newPosition, oldPosition);
                //setStatusBarMenuBottomColor(newPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                oldPosition = ( byte ) tab.getPosition();
                tab.getIcon().setColorFilter((getResources().getColor(R.color.black)), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
