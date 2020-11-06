package enibdiscovery.enibdiscovery.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import enibdiscovery.enibdiscovery.R;

public class MenuJouer extends AppCompatActivity {

    private Button mMenuAutoButton;
    private Button mMenuEdmButton;
    private Button mMenuElecButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jouer);

        mMenuAutoButton= findViewById(R.id.menu_auto_button);
        mMenuEdmButton= findViewById(R.id.menu_edm_button);
        mMenuElecButton= findViewById(R.id.menu_elec_button);

        mMenuEdmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edmActivity = new Intent(MenuJouer.this, GameEdm.class);
                startActivity(edmActivity);
            }
        });

        mMenuElecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent elecActivity = new Intent(MenuJouer.this, GameElec.class);
                startActivity(elecActivity);

            }
        });

        mMenuAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent autoActivity = new Intent(MenuJouer.this, GameAuto.class);
                startActivity(autoActivity);

            }
        });


    }

}
