package com.example.appliequipage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.sql.SQLException;

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

        return view;
    }

    private void initCounter() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Réinitialiser");
        builder.setMessage("Êtes-vous sûr de vouloir réinitialiser le compteur?");

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                counter = 0;
                counterTxt.setText(String.valueOf(counter));
            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // ne rien faire
            }
        });

        AlertDialog dialog = builder.show();

    }

    private void plusCounter() throws SQLException {
        counter++;
        counterTxt.setText(counter + "");
        DatabaseHelper.updateCounter(counter);
    }

    private void minusCounter() throws SQLException {
        if (counter > 0){
            counter--;
            counterTxt.setText(String.valueOf(counter));
            DatabaseHelper.updateCounter(counter);
        }

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.minusBtn:
                    try {
                        minusCounter();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.plusBtn:
                    try {
                        plusCounter();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.resetBtn:
                    initCounter();
                    break;
            }

        }
    };
}