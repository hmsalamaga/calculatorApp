package com.example.calculatorapp.ui.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.calculatorapp.MainActivity;
import com.example.calculatorapp.R;

public class AuthorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).clearBuffer();
        View root = inflater.inflate(R.layout.fragment_author, container, false);
        return root;
    }
}
