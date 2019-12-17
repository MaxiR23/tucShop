package com.example.tucshop.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.tucshop.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class DialogEditContacto extends AppCompatDialogFragment {

    private TextInputLayout inputEmail, inputTelefono;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_editcontacto, null);
        builder.setView(view);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        inputEmail = view.findViewById(R.id.inputEmail);
        inputTelefono = view.findViewById(R.id.inputTelefono);

        inputEmail.setEnabled(false);

        Bundle bundle = getArguments();

        if (bundle != null)
        {
            inputEmail.getEditText().setText(bundle.getString("e-mail"));
            inputTelefono.getEditText().setText(bundle.getString("telefono"));
        }

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String numeroTelefono = inputTelefono.getEditText().getText().toString();

                modificacionDeDatos(numeroTelefono);
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

    private void modificacionDeDatos(String numeroTelefono) {

        reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("telefono", numeroTelefono);
        reference.updateChildren(hashMap);
    }
}
