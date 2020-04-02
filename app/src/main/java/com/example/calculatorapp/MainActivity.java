package com.example.calculatorapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorapp.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    StringBuilder editText = new StringBuilder();
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FragmentManager fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, homeFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onExitClicked(MenuItem item) {
        System.exit(0);
    }

    public void onButtonCharClicked(View view) {
        Button clickedButton = (Button) view;
        editText.append(clickedButton.getText().toString());
        setCurrentTextView();
    }

    public void onButtonFunctionClicked(View view) {
        Button clickedButton = (Button) view;
        editText.append(clickedButton.getText().toString()).append('(');
        setCurrentTextView();
    }

    public void onPiClicked(View view) {
        editText.append("pi");
        setCurrentTextView();
    }

    public void onSqrtClicked(View view) {
        editText.append("sqrt(");
        setCurrentTextView();
    }

    public void setCurrentTextView() {
        homeFragment.setCurrentTextView(editText);
    }

    public void onEqualClicked(View view) {
        homeFragment.moveResultToCurrent(editText);
        editText = new StringBuilder(homeFragment.getCurrentTextViewValue());
    }

    public void onClearClicked(View view) {
        homeFragment.cleanTextViews();
        editText = new StringBuilder(homeFragment.getCurrentTextViewValue());
    }

    public void onDeleteClicked(View view) {
        if (editText.length() > 0)
            editText.deleteCharAt(editText.length() - 1);
        setCurrentTextView();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("currentTextView", editText.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editText = new StringBuilder(savedInstanceState.getString("currentTextView"));
//        setCurrentTextView();
    }
}
