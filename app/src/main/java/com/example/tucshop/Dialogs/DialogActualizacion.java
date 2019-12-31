package com.example.tucshop.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tucshop.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DialogActualizacion extends AppCompatDialogFragment {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String version = "1";
    private long tamaño;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private static String DOWNLOAD_URL = "";

    private static List<String> apk_url = new ArrayList<String>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        inicializarFirebase();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_actualizacion, null);
/*
        final ImageButton btnActualizar = view.findViewById(R.id.btnActualizarac);
        final TextView tv_version = view.findViewById(R.id.tvinforme);
        final TextView tvActualizacion = view.findViewById(R.id.tvActualización);

        btnActualizar.setVisibility(View.INVISIBLE);
        tv_version.setText("Versión " + version);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference actualizacion = firebaseDatabase.getReference("Version"); //Instancia a la version desde firebase
        DatabaseReference apkDescargas = firebaseDatabase.getReference("Apk"); //Instancia al enlace de descarga apk


        actualizacion.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String valor = dataSnapshot.getValue().toString();
                System.out.println("Version de apk" + valor);
                if (valor.equals(version)) {
                    tvActualizacion.setText("Tiene instalada la última versión");
                    System.out.println("La version esta actualizada");
                } else {
                    btnActualizar.setVisibility(View.VISIBLE);
                    tvActualizacion.setText("Hay una nueva versión, actualice");
                    Toast.makeText(getContext(), "Nueva actualización disponible", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        apkDescargas.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String valor = dataSnapshot.getValue().toString();
                apk_url.add(valor);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Boton de descarga que ejecuta la acción de la descarga y guarda el apk en una dirección
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DOWNLOAD_URL = apk_url.get(0);

                downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                request = new DownloadManager.Request(Uri.parse(DOWNLOAD_URL));
                String fileExtencion = MimeTypeMap.getFileExtensionFromUrl(DOWNLOAD_URL);
                String name = URLUtil.guessFileName(DOWNLOAD_URL, null, fileExtencion);
                request.setDestinationInExternalPublicDir("/apk", name);
                String h = request.setDestinationInExternalPublicDir("/apk", name).toString();
                System.out.println(h);
                tamaño = downloadManager.enqueue(request);
            }
        });

        //Notificacion con el estado de la descarga


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0); //intent para mandar al downloadmanager
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(tamaño);
                    Cursor cursor = downloadManager.query(query);
                    if (cursor.moveToFirst()) //el cursor va a ir mostrando el avance de la descarga
                    {
                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                            String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));

                            File file = new File(uriString);
                            System.out.println(file);
                            Intent promptInstall = new Intent(Intent.ACTION_VIEW); //Intent para ejecutar el instalador
                            promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            promptInstall.setDataAndType(Uri.parse(uriString), "application/vns.android.package-archive"); //Mandamos el path o ruta donde se descargo el archivo como un tipo file
                            startActivity(promptInstall); //Para que una vez que se descargue el archivo lo abra automaticamente
                            System.out.println("apk descargando");
                        }
                    }
                }
            }
        };
        getActivity().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)); //Ejecuta el registro de la descarga completa
*/
        builder.setView(view);
        return builder.create();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getActivity());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

}
