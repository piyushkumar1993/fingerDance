package beans.red.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Outcome extends AppCompatActivity {
    int tiles;
    SharedPreferences s;
    SharedPreferences.Editor e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        ActionBar ab = getSupportActionBar();


        ab.setDisplayHomeAsUpEnabled(true);
        s=getApplicationContext().getSharedPreferences("Data",MODE_PRIVATE);
        e=s.edit();
        TextView tv=(TextView)findViewById(R.id.tv);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        Bundle b= getIntent().getExtras();
        int player = (int)b.get("player");
        tiles= (int)b.get("tiles");
        if(player==1)
        tv.setText("Player 2 Wins\nTiles Covered: "+tiles);
        else if(player==2)
            tv.setText("Player 1 Wins\nTiles Covered: "+tiles);
        else
            tv.setText("Game was a Tie\nTiles Covered: "+tiles);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* CoordinatorLayout coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout1);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Hello", Snackbar.LENGTH_SHORT);

                snackbar.show();*/

                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I covered "+tiles+" tiles in Finger Dance. Can you beat this? :D"); // Simple text and URL to share
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
