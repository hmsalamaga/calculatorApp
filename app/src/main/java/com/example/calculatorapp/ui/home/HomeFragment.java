package com.example.calculatorapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calculatorapp.R;

public class HomeFragment extends NavHostFragment {

    private HomeViewModel homeViewModel;
    private View root;
    private TextView currentTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    public void setCurrentTextView(StringBuilder editText) {
        currentTextView = root.findViewById(R.id.currentTextView);
        currentTextView.setText(editText);
    }
}
