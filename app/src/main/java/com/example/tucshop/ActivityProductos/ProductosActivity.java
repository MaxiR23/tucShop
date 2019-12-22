package com.example.tucshop.ActivityProductos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tucshop.Modelo.Productos;
import com.example.tucshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductosActivity extends AppCompatActivity {

    private TextView nombreProducto, precioProducto, gpu, tvgName;
    private ImageView imagenProducto;
    private DatabaseReference reference;
    private LinearLayout linearGpu;
    String nameProduct, priceProduct, imageProd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        nombreProducto = findViewById(R.id.nombreProducto);
        precioProducto = findViewById(R.id.precioProducto);
        imagenProducto = findViewById(R.id.imageProducto);

        linearGpu = findViewById(R.id.linearGPU);
        gpu = findViewById(R.id.tvGPU);
        tvgName = findViewById(R.id.tvGpuName);

        nameProduct = getIntent().getStringExtra("nombreProducto");
        priceProduct = getIntent().getStringExtra("montoProducto");

        nombreProducto.setText(nameProduct);
        precioProducto.setText(priceProduct);

        reference = FirebaseDatabase.getInstance().getReference("Productos").child("1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Productos productos = dataSnapshot.getValue(Productos.class);

                assert productos != null;
                if (productos.getGpu().equals(""))
                {
                    linearGpu.setVisibility(View.GONE);
                    tvgName.setVisibility(View.GONE);
                 gpu.setVisibility(View.GONE);
                } else {
                    linearGpu.setVisibility(View.VISIBLE);
                    tvgName.setVisibility(View.VISIBLE);
                    gpu.setVisibility(View.VISIBLE);
                }

                Glide.with(getApplicationContext()).load(productos.getImagenProducto()).into(imagenProducto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
