package com.example.tucshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tucshop.Dialogs.DialogRecoveryPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //EditText y Botones
    private TextInputLayout inputEmail, inputPass;
    private Button btnIngreso;
    private TextView tvRegistro, tvRecuperarContraseña;
    private CheckBox checkBox;

    //Firebase
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    //Para guardar datos
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public boolean guardarDatos, checked;
    private String email, pass;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                } else {
                    System.out.println("Error al ingresar sesión");
                }
            }
        };

        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        btnIngreso = findViewById(R.id.btnIngresar);
        tvRegistro = findViewById(R.id.tvRegistro);
        tvRecuperarContraseña = findViewById(R.id.tvRecuperarContraseña);
        tvRecuperarContraseña.setText(Html.fromHtml("<u> Recuperar contraseña </u>"));
        tvRegistro.setText(Html.fromHtml("<u> Registrate </u>"));
        checkBox = findViewById(R.id.checkboxlog);

        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvRecuperarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogRecoveryPass dialogRecoveryPass = new DialogRecoveryPass();
                dialogRecoveryPass.show(getSupportFragmentManager(), "recoverypass");
            }
        });

        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaciónes();
            }
        });

        //Preferencias de usuarios
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Validar si el usuario quiere guardar o no el usuario y contraseña

        guardarDatos = sharedPreferences.getBoolean("guardarDatos", true);
        if (guardarDatos == true)
        {
            inputEmail.getEditText().setText(sharedPreferences.getString("email", null));
            inputPass.getEditText().setText(sharedPreferences.getString("pass", null));
        } else {
            inputEmail.getEditText().setText("");
            inputPass.getEditText().setText("");
        }

        obtenerEstadoDeCheck();
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

    public void obtenerEstadoDeCheck(){

        checked = sharedPreferences.getBoolean("checked", false);

        if (checked == true)
        {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    public boolean validateEmail()
    {
        String emailInput = inputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty())
        {
            inputEmail.setError("Campo obligatorio vacío");
            return false;
        } else {
            inputEmail.setError(null);
            return true;
        }
    }

    public boolean validatePass()
    {
        String passwordInput = inputPass.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty())
        {
            inputPass.setError("Campo obligatorio vacío");
            return false;
        } else {
            inputPass.setError(null);
            return true;
        }
    }

    public void validaciónes()
    {

      if (!validateEmail() | !validatePass())
      {
          return;
      }

      progressDialog.setMessage("Ingresando");
      progressDialog.show();

      String email = inputEmail.getEditText().getText().toString();
      String password = inputPass.getEditText().getText().toString();

      firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              FirebaseUser user = firebaseAuth.getCurrentUser();

              if (task.isSuccessful())
              {
                  if (user.isEmailVerified())
                  {
                      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                      startActivity(intent);
                      finish();
                      validarCheckBox();
                      progressDialog.dismiss();

                  } else {
                      Toast.makeText(getApplicationContext(), "Email: " + user.getEmail() + ", no verificado", Toast.LENGTH_LONG).show();
                      progressDialog.dismiss();
                  }

              } else {
                  Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                  progressDialog.dismiss();
              }

              /*
              if (!task.isSuccessful())
              {
                  Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                  progressDialog.dismiss();
              } else {
                  FirebaseUser user = firebaseAuth.getCurrentUser();
                  if (!user.isEmailVerified())
                  {
                      Toast.makeText(getApplicationContext(), "Email: " + user.getEmail() + ", no verificado", Toast.LENGTH_LONG).show();
                      progressDialog.dismiss();
                  } else {
                      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                      startActivity(intent);
                      finish();
                      validarCheckBox();
                      progressDialog.dismiss();
                  }
              }*/
          }
      });
    }

    private void validarCheckBox() {

        if (checkBox.isChecked())
        {
            email = inputEmail.getEditText().getText().toString();
            pass = inputPass.getEditText().getText().toString();

            editor.putBoolean("guardarDatos",true);
            editor.putBoolean("checked",true);
            editor.putString("email",email);
            editor.putString("pass",pass);
            editor.apply();
            editor.commit();
        } else {
            editor.putBoolean("guardarDatos",false);
            editor.putBoolean("checked",false);
            editor.putString("email", null);
            editor.putString("pass", null);
            editor.apply();
            editor.commit();
        }
    }
}