package beans.red.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public EditText text;
    public ImageView image;
    public CardView cardView;
    public int pos=0;
    boolean tileSelected=false;
    SharedPreferences s;
    SharedPreferences.Editor e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        s=getApplicationContext().getSharedPreferences("Data",MODE_PRIVATE);
        e=s.edit();
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
        image = (ImageView) findViewById(R.id.imageView);
        cardView=(CardView)findViewById(R.id.submit);
        seekBar.setProgress(0);
        //seekBar.incrementProgressBy(1);
        seekBar.setMax(3);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tileSelected=true;
                switch (progress) {

                    case 0:
                        image.setImageResource(R.drawable.f1);
                        pos=progress;
                        break;

                    case 1:
                        image.setImageResource(R.drawable.f3);
                        pos=progress;
                        break;

                    case 2:
                        image.setImageResource(R.drawable.f4);
                        pos=progress;
                        break;

                    case 3:
                        image.setImageResource(R.drawable.f5);
                        pos=progress;
                        break;

                }

                 }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

            seekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    tileSelected=true;
                    TextView tvImage=(TextView)findViewById(R.id.tvImage);
                    tvImage.setText("");
                    switch (pos) {

                        case 0:
                            image.setImageResource(R.drawable.f1);

                            break;

                        case 1:
                            image.setImageResource(R.drawable.f3);

                            break;

                        case 2:
                            image.setImageResource(R.drawable.f4);

                            break;

                        case 3:
                            image.setImageResource(R.drawable.f5);

                            break;

                    }
                    return false;
                }
            })
            ;
            cardView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {   Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                    if(tileSelected) {

                        Intent mainIntent = new Intent(MainActivity.this, Game.class);
                        mainIntent.putExtra("pos", pos + 2);
                        e.putInt("pos",pos+2);
                        e.commit();
                        startActivity(mainIntent);

                    }
                    else{
                        LinearLayout coordinatorLayout=(LinearLayout)findViewById(R.id.coordinatorLayout1);
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Please select the number of tiles", Snackbar.LENGTH_SHORT);

                        snackbar.show();
                    }
                }
            });


    }

}
