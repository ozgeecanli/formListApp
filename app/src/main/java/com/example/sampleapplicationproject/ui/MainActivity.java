package com.example.sampleapplicationproject.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.ui.form.FragmentFormScreen;
import com.example.sampleapplicationproject.ui.formlist.FragmentFormListScreen;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    public final static String TAG_FORM_SCREEN = "form fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //remove default action bar
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                    FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                        FragmentHome()).commit();
                toolbar.setTitle("Ana Sayfa");
                break;
            case R.id.nav_form_list_screen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                        FragmentFormListScreen()).commit();
                toolbar.setTitle("Form Listesi Ekranı");
                break;
            case R.id.nav_form_screen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                        FragmentFormScreen(), TAG_FORM_SCREEN).commit();
                toolbar.setTitle("Form Ekranı");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}