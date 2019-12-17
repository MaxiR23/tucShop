package com.example.tucshop.Dialogs;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import com.example.tucshop.R;

public class DialogEditMiCuenta extends AppCompatDialogFragment {

    private CardView cardModificarDatos, cardModificarDomicílio, cardTarjetaDeCredito;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_edit_cuenta, null);
        builder.setView(view);

        cardModificarDatos = view.findViewById(R.id.cardDatosUsuario);
        cardModificarDomicílio = view.findViewById(R.id.cardDomicilio);
        cardTarjetaDeCredito = view.findViewById(R.id.cardTarjetaDeCreditos);

        cardModificarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditUsuario editUsuario = new DialogEditUsuario();
                editUsuario.show(getFragmentManager(), "editUsuario");
            }
        });

        return builder.create();
    }
}
