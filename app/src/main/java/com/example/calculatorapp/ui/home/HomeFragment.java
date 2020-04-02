package com.example.calculatorapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.calculatorapp.MainActivity;
import com.example.calculatorapp.R;
import org.mariuszgromada.math.mxparser.*;

public class HomeFragment extends Fragment {

    private TextView currentTextView;
    private TextView resultTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        currentTextView = root.findViewById(R.id.currentTextView);
        resultTextView = root.findViewById(R.id.resultTextView);
        ((MainActivity)getActivity()).setHomeFragment(this);
        return root;
    }

    public void setCurrentTextView(StringBuilder editText) {
        currentTextView.setText(editText);
        displayCalculatedValue(resultTextView, editText);
    }

    public void moveResultToCurrent(StringBuilder editText) {
        resultTextView.setText("");
        displayCalculatedValue(currentTextView, editText);
    }

    public void displayCalculatedValue(TextView textView, StringBuilder editText) {
        Expression e = new Expression(editText.toString());
        double calculatedValue = e.calculate();
        if (Double.toString(calculatedValue).equals("NaN")) {
            textView.setText("Error");
        } else if (calculatedValue % 1 == 0) {
            textView.setText(Integer.toString((int)calculatedValue));
        } else {
            textView.setText(Double.toString(calculatedValue));
        }
    }

    public void cleanTextViews() {
        currentTextView.setText("");
        resultTextView.setText("");
    }

    public String getCurrentTextViewValue() {
        return currentTextView.getText().toString();
    }
}
