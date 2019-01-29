package ruach.test_mk3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    private FirebaseDatabase mFirebaseDatabase;

    private TextView txtEmail, txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Button buttongame = (Button) findViewById(R.id.ButtonGame);
        Button buttonfinding = (Button) findViewById(R.id.ButtonFinding);
        Button buttonprofile = (Button) findViewById(R.id.ButtonProfile);
        Button buttonqr = (Button) findViewById(R.id.ButtonQR);

        buttongame.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameLayout.class);
                startActivity(intent);
            }
        });
        buttonfinding.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TastelistLayout.class);
                startActivity(intent);
            }
        });
        buttonprofile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StatusLayout.class);
                startActivity(intent);
            }
        });
        buttonqr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameLayout.class);//수정필요
                startActivity(intent);
            }
        });


        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtEmail = (TextView) headerView.findViewById(R.id.txtEmail);
        txtName = (TextView) headerView.findViewById(R.id.txtName);
        navigationView.setNavigationItemSelectedListener(this);
        if(mFirebaseUser != null) {
            profileupdate();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dic_layout) {
            Intent intent = new Intent(getApplicationContext(), DicLayout.class);
            startActivity(intent);
        } else if (id == R.id.nav_lab_layout) {
            Intent intent = new Intent(getApplicationContext(), LabLayout.class);
            startActivity(intent);
        } else if (id == R.id.nav_store_layout) {
            Intent intent = new Intent(getApplicationContext(), StoreLayout.class);
            startActivity(intent);
        } else if (id == R.id.nav_follow_layout) {
            Intent intent = new Intent(getApplicationContext(), FollowLayout.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting_layout) {
            Intent intent = new Intent(getApplicationContext(), SettingLayout.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void profileupdate() {
        txtEmail.setText(mFirebaseUser.getEmail());
        txtName.setText(mFirebaseUser.getDisplayName());
    }
}
