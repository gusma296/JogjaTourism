package corp.gusma.jogjatourism;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ScrollingActivity extends AppCompatActivity {
    String nama,alamat,detail,gambar;
    ImageView gambarwisata;
    TextView alamat2,detail2;

    CollapsingToolbarLayout collapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent =getIntent();
        nama =intent.getStringExtra("NAMA");
        alamat =intent.getStringExtra("ALAMAT");
        detail =intent.getStringExtra("DETAIL");
        gambar =intent.getStringExtra("GAMBAR");

        alamat2 = (TextView)findViewById(R.id.alamat);
        detail2 = (TextView)findViewById(R.id.info);

        alamat2.setText(alamat);
        detail2.setText(detail);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(nama);
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#83FFFFFF"));


        gambarwisata = (ImageView) findViewById(R.id.gambar);

        Glide.with(this)
                .load(gambar)
                .into(gambarwisata);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt();

            }
        });

    }
    private void shareIt(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,detail);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
