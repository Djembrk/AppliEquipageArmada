package com.example.appliequipage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CompteurFragment extends Fragment {

    private TextView counterTxt;
    private Button minusBtn;
    private Button plusBtn;
    private Button resetBtn;
    private int counter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compteur, container, false);

        counterTxt = (TextView) view.findViewById(R.id.counterTxt);

        minusBtn = (Button) view.findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(clickListener);

        plusBtn = (Button) view.findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(clickListener);


        resetBtn = (Button) view.findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(clickListener);
        
        initCounter();

        return view;
    }

    private void initCounter() {
        counter = 0;
        counterTxt.setText(counter + "");
    }

    private void plusCounter(){
        counter++;
        counterTxt.setText(counter + "");
    }

    private void minusCounter(){
        counter--;
        counterTxt.setText(counter + "");
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.minusBtn:
                minusCounter();
                break;
                case R.id.plusBtn:
                    plusCounter();
                    break;
                case R.id.resetBtn:
                    initCounter();
                    break;
            }

        }
    };
}