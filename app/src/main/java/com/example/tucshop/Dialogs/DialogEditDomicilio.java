package com.example.tucshop.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.tucshop.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class DialogEditDomicilio extends AppCompatDialogFragment {

    FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private TextInputLayout inputProvincia, inputLocalidad, inputCódigoPostal;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editardomicilio, null);
        builder.setView(view);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        inputProvincia = view.findViewById(R.id.inputProvincia);
        inputLocalidad = view.findViewById(R.id.inputLocalidad);
        inputCódigoPostal = view.findViewById(R.id.inputCodigoPostal);

        Bundle bundle = getArguments();

        if (builder != null)
        {
            inputProvincia.getEditText().setText(bundle.getString("provincia"));
            inputLocalidad.getEditText().setText(bundle.getString("localidad"));
            inputCódigoPostal.getEditText().setText(bundle.getString("codigoPostal"));
        }

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String provincia = inputProvincia.getEditText().getText().toString();
                String localidad = inputLocalidad.getEditText().getText().toString();
                String codigoPostal = inputCódigoPostal.getEditText().getText().toString();

                modificaciónDeDatos(provincia, localidad, codigoPostal);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private void modificaciónDeDatos(String provincia, String localidad, String codigoPostal) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("provincia",provincia);
        hashMap.put("localidad", localidad);
        hashMap.put("códigoPostal", codigoPostal);
        databaseReference.updateChildren(hashMap);
    }
}
