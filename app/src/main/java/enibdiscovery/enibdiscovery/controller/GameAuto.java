package enibdiscovery.enibdiscovery.controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import enibdiscovery.enibdiscovery.R;
import enibdiscovery.enibdiscovery.model.VerrinAuto;



public class GameAuto extends AppCompatActivity implements View.OnClickListener{
    Dialog popup;

    private ImageView mVerrinImage1;
    private ImageView mVerrinImage2;
    private ImageView mVerrinImage3;
    private ImageView mVerrinImage4;

    private ImageView mBallImage;

    private VerrinAuto mVerrin1;
    private VerrinAuto mVerrin2;
    private VerrinAuto mVerrin3;
    private VerrinAuto mVerrin4;

    private ImageButton mDistri1;
    private ImageButton mDistri2;
    private ImageButton mDistri3;
    private ImageButton mDistri4;

    private float X1,X2,X3;
    private float Y1,Y2,Y3;
    private float initX;
    private float initY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_auto);

        final View anyView = findViewById(R.id.rulesAuto);
        anyView.post(new Runnable() {
            @Override
            public void run() {
                ShowGameRules(anyView);
            }
        });

        popup= new Dialog(this);
        popup.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        mVerrin1=new VerrinAuto(1,0,2,Arrays.asList(R.drawable.verrin1e,R.drawable.verrin1s),Arrays.asList(210,105));
        mVerrin2=new VerrinAuto(2,0,1,Arrays.asList(R.drawable.verrin2e,R.drawable.verrin2s),Arrays.asList(105,210));
        mVerrin3=new VerrinAuto(3,1,1,Arrays.asList(R.drawable.verrin3e,R.drawable.verrin3s),Arrays.asList(105,630));
        mVerrin4=new VerrinAuto(4,0,4,Arrays.asList(R.drawable.verrin4e,R.drawable.verrin4s),Arrays.asList(315,105));

        mVerrinImage1=findViewById(R.id.imageVerrin1);
        mVerrinImage2=findViewById(R.id.imageVerrin2);
        mVerrinImage3=findViewById(R.id.imageVerrin3);
        mVerrinImage4=findViewById(R.id.imageVerrin4);
        mDistri1=findViewById(R.id.buttonDistri1);
        mDistri2=findViewById(R.id.buttonDistri2);
        mDistri3=findViewById(R.id.buttonDistri3);
        mDistri4=findViewById(R.id.buttonDistri4);

        ImageView[] distrib={mDistri1,mDistri2,mDistri3,mDistri4};
        int i=0;

        for (ImageView d: distrib)
        {d.setOnClickListener(this);
        d.setTag(i++);}

        mBallImage=findViewById(R.id.imageBall);

    }
    @Override
    public void onClick(View v) {
        int clickIndex=(int) v.getTag();
        switch (clickIndex) {
            case 0:
                changeStateVerrin(mVerrin1,mVerrinImage1);
                moveBall(mBallImage,mVerrin1,mVerrinImage1);
                break;
            case 1:
                changeStateVerrin(mVerrin2,mVerrinImage2);
                moveBall(mBallImage,mVerrin2,mVerrinImage2);
                break;
            case 2:
                if (mVerrin4.getState()==1){
                    Toast.makeText(this,"Impossible",Toast.LENGTH_SHORT).show();
                }
                else{
                    changeStateVerrin(mVerrin3,mVerrinImage3);
                    moveBall(mBallImage,mVerrin3,mVerrinImage3);}

                break;
            case 3:
                if (mVerrin3.getState()==1|(mVerrin2.getState()==1 & mBallImage.getX()==567)){
                    Toast.makeText(this,"Impossible",Toast.LENGTH_SHORT).show();
                }
                else{
                    changeStateVerrin(mVerrin4,mVerrinImage4);
                    moveBall(mBallImage,mVerrin4,mVerrinImage4);}
                break;
        }
    }

    private void changeStateVerrin(VerrinAuto verrin,ImageView verrinImage) {
        int newState = 1-verrin.getState();
        verrin.setState(newState);

        verrinImage.setImageResource(verrin.getStateImage().get(newState));
        android.view.ViewGroup.LayoutParams params = verrinImage.getLayoutParams();

        List<Integer> newDim = changeDimensionVerrin(verrin,newState,verrinImage);
        params.width= newDim.get(0);
        params.height= newDim.get(1);
        verrinImage.setLayoutParams(params);
    }

    private List<Integer> changeDimensionVerrin(final VerrinAuto verrin, int state, ImageView verrinImage) {
        int height = verrinImage.getMeasuredHeight();
        int width = verrinImage.getMeasuredWidth();
        switch (verrin.getDirection()){
            case 1:
                if (state==1){height=height*2;}
                else {height=height/2;}
                break;
            case 2:
                if (state==1){width=width*2;}
                else {width=width/2;}
                break;
            case 3:
                if (state==1){height=height*2;}
                else {height=height/2;}
                break;
            case 4:
                if (state==1){width=width*2;}
                else {width=width/2;}
                break;
        }
        List<Integer> newDim = Arrays.asList(width,height);
        verrin.setStateDimension(newDim);
        return newDim;
    }

    private void moveBall(ImageView ball,VerrinAuto verrin, ImageView verrinImage){
        if (initX==0.0&initY==0.0){
            initX=ball.getX();
            initY=ball.getY();}
        if (ball.getX()==X2&ball.getY()==Y2& verrin==mVerrin1) {
            ball.setX(ball.getX()+ verrinImage.getMeasuredWidth());
            X3=ball.getX();
            Y3=ball.getY();
        }

        else if (ball.getX()==X1&ball.getY()==Y1& verrin==mVerrin2) {
            ball.setY(ball.getY()-verrinImage.getMeasuredHeight());
            X2=ball.getX();
            Y2=ball.getY();
        }

        else if (ball.getX()==X3&ball.getY()==Y3& verrin==mVerrin3) {
            ball.setY(ball.getY()-verrinImage.getMeasuredHeight()/3);
            /// Winner
            final View anyView = findViewById(R.id.rulesAuto);
            anyView.post(new Runnable() {
                @Override
                public void run() {
                    popup.setContentView(R.layout.edm_victory);
                    popup.show();
                    popup.setCancelable(false);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popup.dismiss();
                            Intent i = new Intent(GameAuto.this, Menu.class);
                            startActivity(i);// retour au menu principal
                        }
                    }, 4000);
                }
            });

        }

        else if (ball.getX()==initX & ball.getY()==initY & verrin==mVerrin4) {
            ball.setX(ball.getX()- verrinImage.getMeasuredWidth());
            X1=ball.getX();
            Y1=ball.getY();
        }

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
        Retour=popup.findViewById(R.id.Retour);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }


}