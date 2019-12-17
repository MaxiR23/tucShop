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

public class DialogEditUsuario extends AppCompatDialogFragment {

    private TextInputLayout inputNombre, inputApellido;
    private DatabaseReference reference;
    FirebaseUser firebaseUser;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editdatos_usuario, null);
        builder.setView(view);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        inputNombre = view.findViewById(R.id.inputNombre);
        inputApellido = view.findViewById(R.id.inputApellido);

        Bundle bundle = getArguments();

        if (bundle != null)
        {
            inputNombre.getEditText().setText(bundle.getString("nombre"));
            inputApellido.getEditText().setText(bundle.getString("apellido"));
        }

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String nombre = inputNombre.getEditText().getText().toString();
                String apellido = inputApellido.getEditText().getText().toString();

                modificarDatos(nombre, apellido);
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

    private void modificarDatos(String nombre, String apellido) {

        reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("nombre", nombre);
        hashMap.put("apellido", apellido);
        reference.updateChildren(hashMap);
    }
}

