package com.example.calculatorapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.calculatorapp.ui.calculator.CalculatorFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    StringBuilder buffer = new StringBuilder();
    private CalculatorFragment calculatorFragment;

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
                R.id.nav_calculator, R.id.nav_author)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
        finish();
        System.exit(0);
    }

    public void onButtonCharClicked(View view) {
        Button clickedButton = (Button) view;
        buffer.append(clickedButton.getText().toString());
        setCurrentTextView();
    }

    public void onButtonFunctionClicked(View view) {
        Button clickedButton = (Button) view;
        buffer.append(clickedButton.getText().toString()).append('(');
        setCurrentTextView();
    }

    public void onPiClicked(View view) {
        buffer.append("pi");
        setCurrentTextView();
    }

    public void onSqrtClicked(View view) {
        buffer.append("sqrt(");
        setCurrentTextView();
    }

    public void setCurrentTextView() {
        calculatorFragment.setCurrentTextView(buffer);
    }

    public void onEqualClicked(View view) {
        calculatorFragment.moveResultToCurrent(buffer);
        buffer = new StringBuilder(calculatorFragment.getCurrentTextViewValue());
    }

    public void onClearClicked(View view) {
        calculatorFragment.cleanTextViews();
        buffer = new StringBuilder(calculatorFragment.getCurrentTextViewValue());
    }

    public void onDeleteClicked(View view) {
        if (buffer.length() > 0)
            buffer.deleteCharAt(buffer.length() - 1);
        setCurrentTextView();
    }

    public void setCalculatorFragment(CalculatorFragment calculatorFragment) {
        this.calculatorFragment = calculatorFragment;
    }

    public void clearBuffer() {
        buffer = new StringBuilder();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("currentTextView", buffer.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        buffer = new StringBuilder(savedInstanceState.getString("currentTextView"));
        setCurrentTextView();
    }
}
