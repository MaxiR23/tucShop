package com.example.tucshop.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tucshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Dialogconfirmación_compra extends AppCompatDialogFragment {

    private TextView tvnombreProducto, tvmontoProducto;
    private Button btnConfirmar;

    private String nombreProducto, montoProducto, horarioCompra;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirmacioncompra, null);
        builder.setView(view);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final String date = format.format(calendar.getTime());

        tvnombreProducto = view.findViewById(R.id.tvNombreProducto);
        tvmontoProducto = view.findViewById(R.id.tvMontoProducto);
        btnConfirmar = view.findViewById(R.id.btnConfirmaciónCompra);
        nombreProducto = getActivity().getIntent().getStringExtra("nombreProducto");
        montoProducto = getActivity().getIntent().getStringExtra("montoProducto");

        tvnombreProducto.setText(nombreProducto);
        tvmontoProducto.setText(montoProducto);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmaciónCompra(nombreProducto, montoProducto, date);

                DialogCompra compra = new DialogCompra();
                compra.show(getFragmentManager(), "compra_exitosa");
                dismiss();
            }
        });

        return builder.create();
    }

    private void confirmaciónCompra(String nombreProducto, String montoProducto, String date) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String ruta = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Compras");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("nombreProducto", nombreProducto);
        hashMap.put("montoProducto", montoProducto);
        hashMap.put("date", date);
        reference.child(ruta).push().setValue(hashMap);
    }
}
