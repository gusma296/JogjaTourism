package corp.gusma.jogjatourism;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Context context;
    BaseApiService baseApiService;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    WisataAdapter wisataAdapter;
    List<DataWisata> dataWisata =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.icon2);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        baseApiService = ApiClient.getClient().create(BaseApiService.class);
        getWisata();

        int[] colors = new int[]{
                android.graphics.Color.parseColor("#578dc9"),
        };

        DoubleBounce doubleBounce = new DoubleBounce();

        progressBar.setIndeterminateDrawable(doubleBounce);
//        doubleBounce.setBounds(0, 0, 100, 100);
        doubleBounce.setColor(colors[0]);
        progressBar.setIndeterminateDrawable(doubleBounce);


    }
    public void getWisata(){
        progressBar.setVisibility(View.VISIBLE);
        baseApiService.getWisata().enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                List<DataWisata> ListWisata = response.body().getDataWisata();
                Log.d("Retrofit GET","Jumlah Store"+String.valueOf(ListWisata.size()));
                adapter = new WisataAdapter(ListWisata,getApplicationContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(MainActivity.this,"eror",Toast.LENGTH_SHORT).show();
                Log.e("Retrofit GET", t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                    signOut();
                return true;

        }
        return false;
    }

    private void signOut(){
        auth.signOut();
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda yakin ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                })
                .setNegativeButton("Batal",null)
                .show();
    }

    boolean doubleBackPressed=false;
    @Override
    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setMessage("Apakah anda yakin ingin keluar ?")
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("Batal",null)
//                .show();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)){
//            drawer.closeDrawer(GravityCompat.START);
//        }else {
            if(doubleBackPressed) {
                super.onBackPressed();
                finish();
            }
            else {
                doubleBackPressed=true;
                final CoordinatorLayout relativeLayout = (CoordinatorLayout) findViewById(R.id.relativeLayout);

                Snackbar.make(relativeLayout,getString(R.string.pressbackagain),Snackbar.LENGTH_SHORT).show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackPressed=false;
                        finish();

                    }
                },2000);
            }
        }



    }

