package com.example.appliequipage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRCodeFragment extends Fragment {

    // Déclaration d'un bouton appelé "btn_scan"
    Button btn_scan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Création d'une vue qui est basée sur le fichier XML "fragment_q_r_code"
        View view = inflater.inflate(R.layout.fragment_q_r_code, container, false);
        // Récupération de la référence au bouton "btn_scan" à partir de la vue créée
        btn_scan = view.findViewById(R.id.btn_scan);
        // Configuration d'un listener sur le bouton "btn_scan" qui appelle la méthode "scanQR" lorsque le bouton est cliqué
        btn_scan.setOnClickListener(v-> {
            scanQR(barLauncher);
        });
        return view;
    }

    // Méthode qui configure les options pour la numérisation de code QR
    void scanQR(ActivityResultLauncher<ScanOptions> barLauncher) {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volumn up to flash on ");
        // Bruit emis lors du scan du qrcode
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

        barLauncher.launch(options);
    }

    // Initialisation d'une ActivityResultLauncher pour gérer les résultats de la numérisation de code QR
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        // Vérification que le résultat de la numérisation est non nul
        if (result.getContents() != null) {
            // Si le résultat de la numérisation n'est pas nul, création d'un AlertDialog qui affiche le résultat
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });

}



