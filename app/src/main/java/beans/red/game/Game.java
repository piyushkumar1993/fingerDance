package beans.red.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    public int n=5, k=0,x,c=0,tiles=0,tileToTap;
    public int pos;
    int flag[]=new int[n*n];
    int newflag[]=new int[n*n];
    boolean gameOver=false;
    Button temp;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent1 = getIntent();
        pos =intent1.getIntExtra("pos",1);
        n=pos;

        for (int h=0;h<n*n;h++)
        {
            flag[h]=1;
        }
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.majorRelative);
        rl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        LinearLayout l1 = new LinearLayout(Game.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        l1.setOrientation(LinearLayout.VERTICAL);
        l1.setWeightSum((float) n);
        l1.setLayoutParams(lp);
        rl.addView(l1);

        final Random ran = new Random();
        final int color = Color.argb(255, 255, 255, 255);


        final View.OnTouchListener ob = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {



                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


                        vibe.vibrate(100);

                    Button b=(Button)v;
                    c++;tiles++;
                    k++;
                    //b.setEnabled(false);
                    if(tiles==(n*n)&&!gameOver){
                        Intent intent = new Intent(Game.this, Outcome.class);
                        intent.putExtra("player",0);
                        intent.putExtra("tiles",tiles);
                        startActivity(intent);
                        gameOver=true;
                        finish();
                    }

                    //Toast.makeText(Game.this,""+tiles,Toast.LENGTH_LONG).show();
                    if(tileToTap!=v.getId()&&!gameOver){

                        int player=0;
                        if(newflag[tileToTap]%2==0)
                            player=1;
                        else
                            player=2;

                        Intent intent = new Intent(Game.this, Outcome.class);
                        intent.putExtra("player",player);
                        intent.putExtra("tiles",tiles-1);
                        startActivity(intent);
                        gameOver=true;
                        finish();
                    }

                    while (1 > 0&&!gameOver) {
                       pos = ran.nextInt(n*n);
                        if(x==pos)
                            pos=ran.nextInt(n*n);
                        b= (Button)findViewById(pos);


                        newflag[pos]=k;

                        if (flag[pos] != 0) {
                            tileToTap=pos;
                            break;
                        }
                    }

                    flag[pos]=0;
                    if(!gameOver){
                        CardView cv=(CardView)findViewById(x+n*n);
                        cv.setCardElevation(8);
                        b.setBackgroundColor(color);
                    }


                }
                else if(action == MotionEvent.ACTION_UP&&!gameOver){
                    int x=v.getId();
                    int player=0;
                    if(newflag[x]%2==0)
                        player=1;
                    else
                        player=2;
                    Intent intent = new Intent(Game.this, Outcome.class);
                    intent.putExtra("player",player);
                    intent.putExtra("tiles",tiles);
                    startActivity(intent);
                    gameOver=true;
                    finish();

                }
                return false;
            }
        };

        for (int i = 0; i <n; i++) {

            LinearLayout l2 = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            l2.setOrientation(LinearLayout.HORIZONTAL);
            params.weight = 1.0f;
            l2.setWeightSum(n);
            l2.setLayoutParams(params);
            l1.addView(l2);
            for(int j=0;j<n;j++)
            {

                LinearLayout.LayoutParams bparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                bparams.weight=1.0f;
                CardView cv=new CardView(this);
                cv.setLayoutParams(bparams);
                cv.setCardElevation(4);
                cv.setId(j+n*i+(n*n));
                cv.setUseCompatPadding(true);
                Button btn = new Button(this);
                LinearLayout.LayoutParams bparams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                btn.setLayoutParams(bparams2);
                btn.setGravity(Gravity.CENTER_HORIZONTAL);
                btn.setId(j+n*i);
                final int id_ = btn.getId();

                final Random rancolor = new Random();
                final int x = rancolor.nextInt(230);
                final int y = rancolor.nextInt(230);
                final int z = rancolor.nextInt(230);
                btn.setBackgroundColor(Color.rgb(x, y, z));

                l2.addView(cv);
                cv.addView(btn);



            }



        }
        final Random ranid = new Random();
        x = ranid.nextInt(n*n);

        tileToTap=x;
        Button btn = (Button)findViewById(x);
        btn.setBackgroundColor(color);
        for(int i=0;i<(n*n);i++) {
            btn = (Button)findViewById(i);
            btn.setOnTouchListener(ob);
        }
        CardView cv=(CardView)findViewById(x+n*n);
        cv.setCardElevation(8);
        newflag[x]=k;
        flag[x]=0;

    }


}
