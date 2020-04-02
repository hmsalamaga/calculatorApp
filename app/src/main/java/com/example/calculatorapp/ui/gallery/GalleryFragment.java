package com.example.calculatorapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.calculatorapp.MainActivity;
import com.example.calculatorapp.R;

public class GalleryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).clearEditText();
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }
}
