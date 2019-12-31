package com.example.tucshop.ActivityProductos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tucshop.Adaptadores.ListaProductosAdapter;
import com.example.tucshop.Dialogs.DialogActualizacion;
import com.example.tucshop.Dialogs.DialogRecoveryPass;
import com.example.tucshop.LoginActivity;
import com.example.tucshop.Modelo.Productos;
import com.example.tucshop.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityCategoriaSmartTV extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    List<Productos> productosList;
    ListaProductosAdapter adapter;
    private RecyclerView rvCSTV;
    private ShimmerFrameLayout shimmerProductos;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_smart_tv);

        productosList = new ArrayList<>();
        rvCSTV = findViewById(R.id.rvCSmartTV);
        rvCSTV.setHasFixedSize(true);
        rvCSTV.setNestedScrollingEnabled(false);
        rvCSTV.setLayoutManager(new LinearLayoutManager(this));

        shimmerProductos = findViewById(R.id.shimmer_productos);

        consultarProductos();

    }

    private void consultarProductos() {
        shimmerProductos.startShimmer();

        Query query = FirebaseDatabase.getInstance().getReference("Productos").orderByChild("categoria").equalTo("SmartTV");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productosList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Productos productos = snapshot.getValue(Productos.class);
                    productosList.add(productos);

                    adapter = new ListaProductosAdapter(ActivityCategoriaSmartTV.this, productosList);
                    adapter.notifyDataSetChanged();
                    rvCSTV.setAdapter(adapter);

                    shimmerProductos.stopShimmer();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_versión :
                DialogActualizacion dialogActualizacion = new DialogActualizacion();
                dialogActualizacion.show(getSupportFragmentManager(), "dialogActualización");
                finish();
                break;
            case R.id.action_opcióncuents :
                break;
            case R.id.opción_restablecerC :
                DialogRecoveryPass dialogRecoveryPass = new DialogRecoveryPass();
                dialogRecoveryPass.show(getSupportFragmentManager(), "dialogRecovery");
                finish();
                break;
            case R.id.opción_cerrar_sesión :
                firebaseAuth.signOut();
                Intent intent = new Intent(ActivityCategoriaSmartTV.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
