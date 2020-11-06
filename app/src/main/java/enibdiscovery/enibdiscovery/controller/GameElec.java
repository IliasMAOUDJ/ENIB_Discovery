package enibdiscovery.enibdiscovery.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.*;
import android.widget.*;


import enibdiscovery.enibdiscovery.R;
import enibdiscovery.enibdiscovery.model.InputTension;

public class GameElec extends AppCompatActivity {
    Dialog popup;
    static int tries=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_elec);

        //on explique les règles du jeu au lancement de l'activité
        final View anyView = findViewById(R.id.schema);
        anyView.post(new Runnable() {
            @Override
            public void run() {
                ShowGameRules(anyView);
            }
        });


        popup= new Dialog(this);
        popup.getWindow().setBackgroundDrawableResource(android.R.color.transparent); // permet de masquer le "cadre" rectangulaire autour du popup


        ImageView R1, R2, R8, R10, RTarget; // Declaration des images
        EditText tension;

        //Source de tension fournissant 1-15V
        tension = findViewById(R.id.tension);
        tension.setFilters(new InputFilter[]{new InputTension("1","15")});

        // On affecte les images à leur id
        R1 = findViewById(R.id.r1);
        R2 = findViewById(R.id.r2);
        R8 = findViewById(R.id.r8);
        R10 = findViewById(R.id.r10);
        RTarget = findViewById(R.id.target);

        ImageView[] resistances={R1,R2,R8,R10,RTarget};


        // "Autorisation" des événements
        for (ImageView r : resistances)
        {r.setOnDragListener(new replaceImage());
            r.setOnLongClickListener(new moveObject());
            r.setOnTouchListener(new Rotate());}


    }


    // Methode sur le jeu

    public void ConfirmLevel(View v){
        Button oui, non;
        final EditText tension=findViewById(R.id.tension);
        final String V=tension.getText().toString();
        final ImageView Resistance =findViewById(R.id.target);
        popup.setContentView(R.layout.popupverif);
        oui=popup.findViewById(R.id.Oui);
        non=popup.findViewById(R.id.Non);

        // gestion de l'appui sur Oui
        if((!V.isEmpty())&&(null!=Resistance.getDrawable()))
        {
            oui.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    boolean win = isGameWon(Resistance, V);
                    if (win)
                    {
                        gameWon();
                    }
                    else
                    {
                        isGameLost();
                        giveHint();
                    }
                    TextView essais =findViewById(R.id.essai);
                    String t=Integer.toString(tries);
                    essais.setText(t);
                }
            });
        }else
        {popup.setContentView(R.layout.champs_incomplet);}
        //gestion de l'appui sur non
        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        popup.show();
    }

    public void giveHint(){
        if (tries == 3) {
            popup.setContentView(R.layout.elec_hint1);
            tries--;
        } else if (tries == 2) {
            popup.setContentView(R.layout.elec_hint2);
            tries--;
        }
    }

    public void ShowGameRules(View v){
        final Button Retour, PageSuivante;
        popup.setContentView(R.layout.game_rules);
        Retour=popup.findViewById(R.id.Retour);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        PageSuivante=popup.findViewById(R.id.PageSuivante);
        PageSuivante.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ShowElecRules();
            }


        });
        popup.show();
    }

    public void ShowElecRules(){
        final Button Retour;
        popup.setContentView(R.layout.elec_rules);
        Retour=popup.findViewById(R.id.Retour);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }

    public void isGameLost() {
        if (tries == 1) {
            popup.setContentView(R.layout.elec_lose);
            popup.setCancelable(false);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    popup.dismiss();
                    Intent i = new Intent(GameElec.this, Menu.class);
                    startActivity(i);
                }
            }, 4000);
        }
    }

    public boolean isGameWon(ImageView resistance, String tension){
        int finalTension=Integer.parseInt(tension);
        double Res=0;
        if (resistance.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.r8k).getConstantState())) // si la resistance choisie est celle de 8k alors..
        {Res=8;}
        else if (resistance.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.r1k).getConstantState()))
        {Res=1;}
        else if (resistance.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.r2k).getConstantState()))
        {Res=2;}
        else if (resistance.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.r10k).getConstantState()))
        {Res=10;}
        double i=(double)finalTension/(Res+7);

        return i == 1.0 && (resistance.getRotation() == 0 % 180);
    }

    public void gameWon() {
        popup.setContentView(R.layout.elec_victory);
        popup.setCancelable(false);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popup.dismiss();
                Intent i = new Intent(GameElec.this, Menu.class);
                startActivity(i);// retour au menu principal
            }
        }, 4000); // On attend 4 sec avant de revenir au menu principal}
    }

    //Methode sur les objets

    private final class Rotate implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if ((event.getAction() == MotionEvent.ACTION_UP)) {
                v.setRotation((v.getRotation() + 90)%180);
                return true;
            } else {
                return false;
            }
        }
    }

    private final class moveObject implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    }

    private final class replaceImage implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView view = (ImageView) event.getLocalState();
                    Drawable img =view.getDrawable();
                    ImageView.ScaleType scl = view.getScaleType();
                    if (((ImageView) v).getDrawable()!=null)
                    { Drawable tmp=((ImageView) v).getDrawable();
                        view.setImageDrawable(tmp);
                        ((ImageView) v).setImageDrawable(img);
                        ((ImageView) v).setScaleType(scl);
                        break;}
                    else {
                        view.setImageDrawable(null);
                        view.setScaleType((((ImageView) v).getScaleType()));
                        ((ImageView) v).setImageDrawable(img);
                        ((ImageView) v).setScaleType(scl);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }
            return true;
        }
    }


}
