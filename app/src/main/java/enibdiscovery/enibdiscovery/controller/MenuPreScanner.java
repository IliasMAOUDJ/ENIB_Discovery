package enibdiscovery.enibdiscovery.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import enibdiscovery.enibdiscovery.R;

public class MenuPreScanner extends AppCompatActivity {

    private Button mQRCode;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pre_scanner);

        mQRCode= findViewById(R.id.button_qrCode);
        mPassword= findViewById(R.id.password);

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (s.toString()) {
                    case "3t3": {
                        Intent newActivity = new Intent(MenuPreScanner.this, GameAuto.class);
                        startActivity(newActivity);
                        break;
                    }
                    case "3l3": {
                        Intent newActivity = new Intent(MenuPreScanner.this, GameElec.class);
                        startActivity(newActivity);
                        break;
                    }
                    case "3d3": {
                        Intent newActivity = new Intent(MenuPreScanner.this, GameEdm.class);
                        startActivity(newActivity);
                        break;
                    }
                }
            }
        });

        mQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerActivity = new Intent(MenuPreScanner.this, MenuScanner.class);
                startActivity(scannerActivity);
            }
        });


    }

}
