package com.example.tucshop.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tucshop.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SendFragment extends Fragment {

    private FirebaseUser firebaseUser;
    private SendViewModel sendViewModel;
    private EditText receptor, asunto, mensaje;
    private Button btnEnviarMail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_send, container, false);

       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

       String emailReceptor = "maximilianorebolo@gmail.com";

       receptor = root.findViewById(R.id.editTextRECEPTOREmail);
       asunto = root.findViewById(R.id.editTextASUNTOEmail);
       mensaje = root.findViewById(R.id.editTextMENSAJEEmail);
       btnEnviarMail = root.findViewById(R.id.btnEnviarMail);

       receptor.setText(emailReceptor);

       btnEnviarMail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendMail();
           }
       });

        return root;
    }

    private void sendMail() {
        String receptorMail = receptor.getText().toString();
        String asuntoMail = asunto.getText().toString();
        String mensajeMail = mensaje.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{receptorMail});
        intent.putExtra(Intent.EXTRA_SUBJECT, asuntoMail);
        intent.putExtra(Intent.EXTRA_TEXT, mensajeMail);

        intent.setType("mesaage/rfc822");

        startActivity(Intent.createChooser(intent,"Hola"));

        try {
            startActivity(Intent.createChooser(intent, "Enviando E-mail"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No tiene instalado ningun proveedor de mensaje", Toast.LENGTH_LONG).show();
        }
    }
}