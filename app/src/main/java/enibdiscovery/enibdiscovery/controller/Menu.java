package enibdiscovery.enibdiscovery.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import enibdiscovery.enibdiscovery.R;

public class Menu extends AppCompatActivity {

    private Button mExploitsButton;
    private Button mInfosButton;
    private Button mJouerButton;
    private Button mScannerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mExploitsButton=findViewById(R.id.menu_exploits_button);
        mInfosButton=findViewById(R.id.menu_infos_button);
        mJouerButton=findViewById(R.id.menu_jouer_button);
        mScannerButton=findViewById(R.id.menu_scanner_button);

        mJouerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jouerActivity = new Intent(Menu.this, MenuJouer.class);
                startActivity(jouerActivity);
            }
        });

        mScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerActivity = new Intent(Menu.this, MenuPreScanner.class);
                startActivity(scannerActivity);
            }
        });

        mInfosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoActivity = new Intent(Menu.this, MenuInfo.class);
                startActivity(infoActivity);
            }
        });


    }

}
