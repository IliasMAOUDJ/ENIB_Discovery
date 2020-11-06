package enibdiscovery.enibdiscovery.controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Arrays;

import enibdiscovery.enibdiscovery.R;
import enibdiscovery.enibdiscovery.model.QuestionBankEdm;
import enibdiscovery.enibdiscovery.model.QuestionEdm;

public class GameEdm extends AppCompatActivity implements View.OnClickListener {

    Dialog popup;

    public static final String EXPLOIT = "EXPLOIT";

    private ImageView mRulesEdm;
    private ImageView mEdmTestImage;
    private ImageButton mEdmSol1Button;
    private ImageButton mEdmSol2Button;
    private ImageButton mEdmSol3Button;
    private ImageButton mEdmSol4Button;

    private QuestionBankEdm mQuestionBankEdm;
    private QuestionEdm mCurrentQuestionEdm;

    private int mScoreEdm;
    private int mNombreQuestionEdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edm);

        final View anyView = findViewById(R.id.rulesEdm);
        anyView.post(new Runnable() {
            @Override
            public void run() {
                ShowGameRules(anyView);
            }
        });

        popup= new Dialog(this);
        popup.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        mScoreEdm=0;
        mNombreQuestionEdm=3;
        mQuestionBankEdm = this.generateQuestionsEdm();



        mEdmTestImage= findViewById(R.id.edm_test_image);
        mEdmSol1Button= findViewById(R.id.edm_sol1_button);
        mEdmSol2Button= findViewById(R.id.edm_sol2_button);
        mEdmSol3Button= findViewById(R.id.edm_sol3_button);
        mEdmSol4Button= findViewById(R.id.edm_sol4_button);

        ImageButton[] answers={mEdmSol1Button,mEdmSol2Button,mEdmSol3Button,mEdmSol4Button};
        int i=0;
        for (ImageButton a: answers)
        {a.setOnClickListener(this);
            a.setTag(i++);}

        mCurrentQuestionEdm=mQuestionBankEdm.getQuestionEdm();
        createQuestionEdm(mCurrentQuestionEdm);
    }

    @Override
    public void onClick(View v) {
        int responseIndexEdm=(int) v.getTag();
        if (responseIndexEdm==mCurrentQuestionEdm.getAnswerIndexEdm()){
            Toast.makeText(this, "Bon",Toast.LENGTH_SHORT).show();
            mScoreEdm++;
        }
        else{
            Toast.makeText(this, "Faux",Toast.LENGTH_SHORT).show();
        }
        if (--mNombreQuestionEdm<0){
            finish();
        }
        else{
            mCurrentQuestionEdm=mQuestionBankEdm.getQuestionEdm();
            createQuestionEdm(mCurrentQuestionEdm);


        }

    }

    private void createQuestionEdm(final QuestionEdm question) {
        mEdmTestImage.setImageResource(mCurrentQuestionEdm.getQuestionEdm());
        ImageButton[] answers={mEdmSol1Button,mEdmSol2Button,mEdmSol3Button,mEdmSol4Button};
        int i=0;
        for (ImageButton a: answers)
        {a.setImageResource(mCurrentQuestionEdm.getChoiceListEdm().get(i++));}
    }
    private QuestionBankEdm generateQuestionsEdm(){
        QuestionEdm question1=new QuestionEdm(R.drawable.edm1,Arrays.asList(R.drawable.edmsol1,R.drawable.edmsol2,R.drawable.edmsol3,R.drawable.edmsol4),1);
        QuestionEdm question2=new QuestionEdm(R.drawable.edm2,Arrays.asList(R.drawable.edmsol4,R.drawable.edmsol3,R.drawable.edmsol1,R.drawable.edmsol2),2);
        QuestionEdm question3=new QuestionEdm(R.drawable.edm3,Arrays.asList(R.drawable.edmsol3,R.drawable.edmsol2,R.drawable.edmsol4,R.drawable.edmsol1),0);
        return new QuestionBankEdm(Arrays.asList(question1,question2,question3));
    }

    public void ShowGameRules(View v){
        final Button Retour, PageSuivante;
        popup.setContentView(R.layout.edm_rules_p1);
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
                ShowEdmRules();
            }


        });
        popup.show();
    }

    public void ShowEdmRules(){
        final Button Retour;

        popup.setContentView(R.layout.edm_rules_p2);
        Retour= popup.findViewById(R.id.Retour);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }

    public void finish(){
        if (mScoreEdm>3) {

            final View anyView = findViewById(R.id.edm_test_image);
            anyView.post(new Runnable() {
                @Override
                public void run() {
                    popup.setContentView(R.layout.edm_victory);
                    popup.setCancelable(false);
                    popup.show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popup.dismiss();
                            Intent i = new Intent(GameEdm.this, Menu.class);
                            startActivity(i);// retour au menu principal
                        }
                    }, 4000);
                }
            });
        }
        else {
            final View anyView = findViewById(R.id.edm_test_image);
            anyView.post(new Runnable() {
                @Override
                public void run() {
                    popup.setContentView(R.layout.edm_defait);
                    popup.setCancelable(false);
                    popup.show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popup.dismiss();
                            Intent i = new Intent(GameEdm.this, Menu.class);
                            startActivity(i);// retour au menu principal
                        }
                    }, 4000);
                }
            });
        }

    }

}


