package com.example.tucshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //EditText y Botones
    private TextInputLayout inputTextEmail, inputTextNombre, inputTextApellido, inputTextPass;
    private Button backLogin, btnRegistro;

    //Firebase
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {
                    System.out.println("Registro");
                } else {
                    System.out.println("Error");
                }
            }
        };

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registro en curso");

        inputTextNombre = findViewById(R.id.inputNombre);
        inputTextApellido = findViewById(R.id.inputApellido);
        inputTextEmail = findViewById(R.id.inputEmailR);
        inputTextPass = findViewById(R.id.inputPassR);
        backLogin = findViewById(R.id.backLogin);
        btnRegistro = findViewById(R.id.btnRegistro);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaciones();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public boolean validateEmail()
    {
        String inputEmail = inputTextEmail.getEditText().getText().toString().trim();

        if (inputEmail.isEmpty())
        {
            inputTextEmail.setError("Campo obligatorio vacío");
            return false;
        } else {
            inputTextEmail.setError(null);
            return true;
        }
    }

    public boolean validateName()
    {
        String inputname = inputTextNombre.getEditText().getText().toString().trim();

        if (inputname.isEmpty())
        {
            inputTextNombre.setError("Campo obligatorio vacío");
            return false;
        } else if (inputname.length() > 15)
        {
            inputTextNombre.setError("Nombre de usuario demasiado largo");
            return false;
        } else {
            inputTextNombre.setError(null);
            return true;
        }
    }

    public boolean validateLastName()
    {
        String inputlastname = inputTextApellido.getEditText().getText().toString().trim();

        if (inputlastname.isEmpty())
        {
            inputTextApellido.setError("Campo obligatorio vacío");
            return false;
        } else if (inputlastname.length() > 15)
        {
            inputTextApellido.setError("Nombre de usuario demasiado largo");
            return false;
        } else {
            inputTextApellido.setError(null);
            return true;
        }
    }

    public boolean validatePass()
    {
        String inputPass = inputTextPass.getEditText().getText().toString().trim();

        if (inputPass.isEmpty())
        {
            inputTextPass.setError("Campo obligatorio vacío");
            return false;
        } else {
            inputTextPass.setError(null);
            return true;
        }
    }

    public void validaciones()
    {

        if (!validateEmail() | !validatePass() | !validateName() | !validateLastName())
        {
            return;
        }

        String email = inputTextEmail.getEditText().getText().toString();
        String nombre = inputTextNombre.getEditText().getText().toString();
        String apellido = inputTextApellido.getEditText().getText().toString();
        String pass = inputTextPass.getEditText().getText().toString();

        registroDeUsuarios(email, nombre, apellido, pass);
    }

    private void registroDeUsuarios(String email, final String nombre, final String apellido, final String pass) {
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "No se pudo registrar exitosamente, por favor intente mas tarde", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                } else {

                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    firebaseUser.sendEmailVerification();
                    String userId = firebaseUser.getUid();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userId);
                    hashMap.put("nombre", nombre);
                    hashMap.put("apellido", apellido);
                    hashMap.put("imageURL", "default");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                          Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(intent);
                          Toast.makeText(getApplicationContext(), "Se envió un correo para verificación de la cuenta", Toast.LENGTH_LONG).show();
                          finish();

                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

    }
}
