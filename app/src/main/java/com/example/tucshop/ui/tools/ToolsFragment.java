package com.example.tucshop.ui.tools;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.tucshop.Dialogs.DialogEditContacto;
import com.example.tucshop.Dialogs.DialogEditDomicilio;
import com.example.tucshop.Dialogs.DialogEditUsuario;
import com.example.tucshop.Modelo.Usuarios;
import com.example.tucshop.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ToolsFragment extends Fragment {

    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;

    private CardView cvDatosUsuario, cvContacto, cvDomicilio, cvTarjeta;

    private TextView tvNombre, tvApellido,tvEmail, tvTelefono, tvProvincia, tvLocalidad, tvCodigoPostal, tvNumTarjeta, tvValidez, tvNombreImpreso, tvDocumento;
    private CircleImageView imageProfile;
    private Button btnOpciones;

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    StorageReference storageReference;

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        /*
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //Imagen de perfil

        imageProfile = root.findViewById(R.id.imageprofile);

        // --- TextViews ---

        //CardView Datos Personales
        tvNombre = root.findViewById(R.id.tvUsuarioNav);
        tvApellido = root.findViewById(R.id.tvApellido);
        //CardView Contacto
        tvEmail = root.findViewById(R.id.tvEmailNav);
        tvTelefono = root.findViewById(R.id.tvTelefonoUsuario);
        //CardView Domicilio
        tvProvincia = root.findViewById(R.id.tvProvincia);
        tvLocalidad = root.findViewById(R.id.tvLocalidad);
        tvCodigoPostal = root.findViewById(R.id.tvCodigoPostal);
        //CardView Tarjeta de crédito
        tvNumTarjeta = root.findViewById(R.id.tvNumTarjeta);
        tvValidez = root.findViewById(R.id.tvValidezTarjeta);
        tvNombreImpreso = root.findViewById(R.id.tvNombreYApellidoImpresosTarjeta);
        tvDocumento = root.findViewById(R.id.tvDocumento);

        //TV No disponibles
        tvNumTarjeta.setEnabled(false);
        tvValidez.setEnabled(false);
        tvNombreImpreso.setEnabled(false);
        tvDocumento.setEnabled(false);

        //CardViews
        cvDatosUsuario = root.findViewById(R.id.cvDatosDeUsuarios);
        cvContacto = root.findViewById(R.id.cvContacto);
        cvDomicilio = root.findViewById(R.id.cvDomicilio);
        cvTarjeta = root.findViewById(R.id.cvTarjeta);

        //CardView No habilitada por el momento
        cvTarjeta.setEnabled(false);

        storageReference = FirebaseStorage.getInstance().getReference("upload");

        cvDatosUsuario.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String nombre = tvNombre.getText().toString();
                String apellido = tvApellido.getText().toString();

                DialogEditUsuario editUsuario = new DialogEditUsuario();
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombre);
                bundle.putString("apellido", apellido);
                editUsuario.setArguments(bundle);
                editUsuario.show(getFragmentManager(), "editDatosUsuarios");
                return false;
            }
        });

        cvContacto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String email = tvEmail.getText().toString();
                String numeroTelefono = tvTelefono.getText().toString();

                DialogEditContacto editContacto = new DialogEditContacto();
                Bundle bundle = new Bundle();
                bundle.putString("e-mail", email);
                bundle.putString("telefono", numeroTelefono);
                editContacto.setArguments(bundle);
                editContacto.show(getFragmentManager(), "editContacto");
                return false;
            }
        });

        cvDomicilio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String provincia = tvProvincia.getText().toString();
                String localidad = tvLocalidad.getText().toString();
                String codigoPostal = tvCodigoPostal.getText().toString();

                DialogEditDomicilio editDomicilio = new DialogEditDomicilio();
                Bundle bundle = new Bundle();
                bundle.putString("provincia", provincia);
                bundle.putString("localidad", localidad);
                bundle.putString("codigoPostal", codigoPostal);
                editDomicilio.setArguments(bundle);
                editDomicilio.show(getFragmentManager(), "editDomicilio");
                return false;
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        tvEmail.setText(firebaseUser.getEmail());

        reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);

                assert usuarios != null;
                tvNombre.setText(usuarios.getNombre());
                    tvApellido.setText(usuarios.getApellido());
                    tvTelefono.setText(usuarios.getTelefono());
                    tvProvincia.setText(usuarios.getProvincia());
                    tvLocalidad.setText(usuarios.getLocalidad());
                    tvCodigoPostal.setText(usuarios.getCódigoPostal());

                    if (usuarios.getImageURL().equals("default")) {
                        imageProfile.setImageResource(R.drawable.user);
                    } else {
                        Glide.with(getContext()).load(usuarios.getImageURL()).into(imageProfile);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        return root;
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    public void uploadImage()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Subiendo..");
        progressDialog.show();

        if (imageUri != null)
        {
            String ruta = "profile_image" + "-" + firebaseUser.getUid();
            final StorageReference fileReference = storageReference.child(ruta);

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){

                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseUser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", mUri);
                        reference.updateChildren(map);

                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(getContext(), "Fallo", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });

        } else {
            Toast.makeText(getContext(), "Imagen no seleccionada", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress())
            {
                Toast.makeText(getContext(), "Subida en curso", Toast.LENGTH_LONG).show();
            } else {
                uploadImage();
            }
        }
    }
}