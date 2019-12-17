package com.example.tucshop.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.tucshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DialogRecoveryPass extends AppCompatDialogFragment {

    private TextInputLayout inputEmail;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_recoverypass, null);

        auth = FirebaseAuth.getInstance();

        builder.setPositiveButton("Recuperar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = inputEmail.getEditText().getText().toString().trim();
                recuperarciónEnCurso(email);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Enviando email");

        inputEmail = view.findViewById(R.id.inputEmailRecover);

        builder.setView(view);
        return builder.create();
    }

    private void recuperarciónEnCurso(String email) {
        progressDialog.show();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Enviamos un email a su cuenta, revíse", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Algo aslió mal, intente nuevamente mas tarde..", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

    }

    public boolean validateEmail()
    {
        String emailinput =  inputEmail.getEditText().getText().toString();

        if (emailinput.isEmpty())
        {
            inputEmail.setError("Campo obligatorio vacío");
            return false;
        } else {
            inputEmail.setError(null);
            return true;
        }
    }

    public void validate()
    {
        if (!validateEmail()){
            return;
        }
    }
}
