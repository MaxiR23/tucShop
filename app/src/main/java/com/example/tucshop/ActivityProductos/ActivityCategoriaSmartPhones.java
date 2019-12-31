package com.example.tucshop.ActivityProductos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tucshop.Adaptadores.AdapterProductoDestacado;
import com.example.tucshop.Dialogs.DialogActualizacion;
import com.example.tucshop.Dialogs.DialogRecoveryPass;
import com.example.tucshop.LoginActivity;
import com.example.tucshop.MainActivity;
import com.example.tucshop.Modelo.ProductoDestacado;
import com.example.tucshop.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityCategoriaSmartPhones extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private AdapterProductoDestacado adapterProductoDestacado;
    private List<ProductoDestacado> productoDestacadoList;
    private RecyclerView rvCSmartPhones, rvDestacado;
    private ShimmerFrameLayout shimmerDestacado;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_smart_phones);

        toolbar = findViewById(R.id.toolbarCSM);
        setSupportActionBar(toolbar);

        productoDestacadoList = new ArrayList<>();
        rvDestacado = findViewById(R.id.rvProductoDestacado);
        rvDestacado.setHasFixedSize(true);
        rvDestacado.setNestedScrollingEnabled(false);
        rvDestacado.setLayoutManager(new LinearLayoutManager(this));
        shimmerDestacado = findViewById(R.id.shimmer_productodestacado);

        consultarProductoDestacado();

    }
        private void consultarProductoDestacado() {
            shimmerDestacado.startShimmer();
            reference = FirebaseDatabase.getInstance().getReference("ProductoDestacado");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    productoDestacadoList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ProductoDestacado productoDestacado = snapshot.getValue(ProductoDestacado.class);
                        productoDestacadoList.add(productoDestacado);

                        adapterProductoDestacado = new AdapterProductoDestacado(ActivityCategoriaSmartPhones.this, productoDestacadoList);
                        adapterProductoDestacado.notifyDataSetChanged();
                        rvDestacado.setAdapter(adapterProductoDestacado);
                        shimmerDestacado.stopShimmer();
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
                Intent intent = new Intent(ActivityCategoriaSmartPhones.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
