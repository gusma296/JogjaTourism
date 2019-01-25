package corp.gusma.jogjatourism;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.RotatingCircle;

public class SplashActivity extends AppCompatActivity {

    private static int splashInterval = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        int[] colors = new int[]{
                android.graphics.Color.parseColor("#578dc9"),
        };

        DoubleBounce doubleBounce = new DoubleBounce();

        progressBar.setIndeterminateDrawable(doubleBounce);
//        doubleBounce.setBounds(0, 0, 100, 100);
        doubleBounce.setColor(colors[0]);
        progressBar.setIndeterminateDrawable(doubleBounce);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        }, splashInterval);

    }
}
