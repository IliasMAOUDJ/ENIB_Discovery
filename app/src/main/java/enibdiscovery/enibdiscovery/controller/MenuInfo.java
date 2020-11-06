package enibdiscovery.enibdiscovery.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import enibdiscovery.enibdiscovery.R;

public class MenuInfo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button prog_cours = findViewById(R.id.programme_cours);
        Button formation = findViewById(R.id.formation_enib);
        Button eni = findViewById(R.id.groupe_eni);


        prog_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prog = new Intent(MenuInfo.this, ProgrammeCours.class);
                startActivity(prog);
            }
        });

        formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent form = new Intent(MenuInfo.this, FormationENIB.class);
                startActivity(form);
            }
        });

        eni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grp = new Intent(MenuInfo.this, GroupeEni.class);
                startActivity(grp);
            }
        });

    }
}