package com.example.tucshop.ActivityProductos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProductosActivity extends AppCompatActivity {

    private TextView nombreProducto, precioProducto, tvAlto, tvAncho, tvBateria, tvcamFront, tvcamPrincipal, tvCantParlantes, tvCapacBateria,
            tvGpu, tvHMDI, tvLinea, tvMarca,tvMarcaProcesador, tvMemRam, tvMemInterna, tvModelProcesador, tvNuleos, tvPeso, tvRed, tvResolucion, tvSo, tvTamPantalla, tvTipoPantalla, tvUsb, tvversionSO, tvWifi;
    private ImageView imagenProducto;
    private DatabaseReference reference;
    private LinearLayout linearBateria, linearcamaraPrincipal, linearcamaraFront, linearCantidadParlantes, linearCapacBateria, linearGPU, linearHMDI, linearMemRam, linearMemInt, linearMarcaProcesador,
            linearModelProcesador, linearNucleos, linearRed, linearSO, linearUSB, linearVSO, linearWifi;
    String nameProduct, priceProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //TV Principales
        nombreProducto = findViewById(R.id.nombreProducto);
        precioProducto = findViewById(R.id.precioProducto);
        imagenProducto = findViewById(R.id.imageProducto);

        nameProduct = getIntent().getStringExtra("nombreProducto");
        priceProduct = getIntent().getStringExtra("montoProducto");

        nombreProducto.setText(nameProduct);
        precioProducto.setText(priceProduct);
        //

        //TV de las caracteristicas

        tvAlto = findViewById(R.id.tvAlto);
        tvAncho = findViewById(R.id.tvAncho);
        tvBateria = findViewById(R.id.tvBateria);
        tvcamFront = findViewById(R.id.tvCamFrontal);
        tvcamPrincipal = findViewById(R.id.tvCamPrincipal);
        tvCantParlantes = findViewById(R.id.tvCantidadParlantes);
        tvCapacBateria = findViewById(R.id.tvCapacidadBat);
        tvGpu = findViewById(R.id.tvGPU);
        tvHMDI = findViewById(R.id.tvHMDI);
        tvLinea = findViewById(R.id.tvlinea);
        tvMarca = findViewById(R.id.tvMarca);
        tvMarcaProcesador = findViewById(R.id.tvMarcaProcesador);
        tvMemInterna = findViewById(R.id.tvMemInterna);
        tvMemRam = findViewById(R.id.tvRAM);
        tvModelProcesador = findViewById(R.id.tvModeloProcesador);
        tvNuleos = findViewById(R.id.tvNucleos);
        tvPeso = findViewById(R.id.tvPeso);
        tvRed = findViewById(R.id.tvRed);
        tvResolucion = findViewById(R.id.tvResolucion);
        tvSo = findViewById(R.id.tvSO);
        tvTamPantalla = findViewById(R.id.tvTamañoPantalla);
        tvTipoPantalla = findViewById(R.id.tvTipoPantalla);
        tvRed = findViewById(R.id.tvRed);
        tvUsb = findViewById(R.id.tvUSB);
        tvversionSO = findViewById(R.id.tvVSO);
        tvWifi = findViewById(R.id.tvWIfi);
        //

        //Linears

        linearBateria = findViewById(R.id.bateriaLinearLay);
        linearcamaraFront = findViewById(R.id.camaraFrontLinear);
        linearcamaraPrincipal = findViewById(R.id.camaraPrinciLinear);
        linearCantidadParlantes = findViewById(R.id.cantParlantesLinear);
        linearCapacBateria = findViewById(R.id.capacidadBateriaLinear);
        linearGPU = findViewById(R.id.gpuLinear);
        linearHMDI = findViewById(R.id.hmdiLinear);
        linearMemInt = findViewById(R.id.memoriaInternaLinear);
        linearMemRam = findViewById(R.id.memoriaRamLinear);
        linearMarcaProcesador = findViewById(R.id.marcaProcesadorLinear);
        linearModelProcesador = findViewById(R.id.modeloProcesadorLinear);
        linearNucleos = findViewById(R.id.nucleosLinear);
        linearRed = findViewById(R.id.redLinear);
        linearSO = findViewById(R.id.soLinear);
        linearUSB = findViewById(R.id.usbLinear);
        linearVSO = findViewById(R.id.versionSOLinear);
        linearWifi = findViewById(R.id.wifiLinear);
        //

        reference = FirebaseDatabase.getInstance().getReference("Productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Productos productos = snapshot.getValue(Productos.class);

                    assert productos != null;
                    Glide.with(getApplicationContext()).load(productos.getImagenProducto()).into(imagenProducto);

                    /*
                    tvAlto.setText(productos.getAltura());
                    tvAncho.setText(productos.getAncho());
                    tvLinea.setText(productos.getLinea());
                    tvMarca.setText(productos.getMarca());
                    tvPeso.setText(productos.getPeso());
                    tvResolucion.setText(productos.getResolucion());
                    tvTamPantalla.setText(productos.getTamañoPantalla());
                    tvTipoPantalla.setText(productos.getTipoPantalla());
                    tvUsb.setText(productos.getUsb());


                    if (productos.getBateria().equals("") || productos.getCamaraFrontal().equals("") || productos.getCamaraPrincipal().equals("") ||
                            productos.getCantidadParlantes().equals("") || productos.getCapacidadBateria().equals("") || productos.getGpu().equals("") ||
                            productos.getHdmi().equals("") || productos.getMemoriaInterna().equals("") || productos.getMemoriaRam().equals("") ||
                            productos.getMarcaProcesador().equals("") || productos.getModeloProcesador().equals("") || productos.getNucleos().equals("") ||
                            productos.getRed().equals("") || productos.getSo().equals("") || productos.getVersionSO().equals("") || productos.getWifi().equals("")) {

                        linearBateria.setVisibility(View.GONE);
                        linearcamaraFront.setVisibility(View.GONE);
                        linearcamaraPrincipal.setVisibility(View.GONE);
                        linearCantidadParlantes.setVisibility(View.GONE);
                        linearCapacBateria.setVisibility(View.GONE);
                        linearGPU.setVisibility(View.GONE);
                        linearHMDI.setVisibility(View.GONE);
                        linearMemInt.setVisibility(View.GONE);
                        linearMemRam.setVisibility(View.GONE);
                        linearMarcaProcesador.setVisibility(View.GONE);
                        linearModelProcesador.setVisibility(View.GONE);
                        linearNucleos.setVisibility(View.GONE);
                        linearRed.setVisibility(View.GONE);
                        linearSO.setVisibility(View.GONE);
                        linearVSO.setVisibility(View.GONE);
                        linearWifi.setVisibility(View.GONE);

                    } else {

                        linearBateria.setVisibility(View.VISIBLE);
                        linearcamaraFront.setVisibility(View.VISIBLE);
                        linearcamaraPrincipal.setVisibility(View.VISIBLE);
                        linearCantidadParlantes.setVisibility(View.VISIBLE);
                        linearCapacBateria.setVisibility(View.VISIBLE);
                        linearGPU.setVisibility(View.VISIBLE);
                        linearHMDI.setVisibility(View.VISIBLE);
                        linearMemInt.setVisibility(View.VISIBLE);
                        linearMemRam.setVisibility(View.VISIBLE);
                        linearMarcaProcesador.setVisibility(View.VISIBLE);
                        linearModelProcesador.setVisibility(View.VISIBLE);
                        linearNucleos.setVisibility(View.VISIBLE);
                        linearRed.setVisibility(View.VISIBLE);
                        linearSO.setVisibility(View.VISIBLE);
                        linearVSO.setVisibility(View.VISIBLE);
                        linearWifi.setVisibility(View.VISIBLE);

                        tvBateria.setText(productos.getBateria());
                        tvcamFront.setText(productos.getCamaraFrontal());
                        tvcamPrincipal.setText(productos.getCamaraPrincipal());
                        tvCantParlantes.setText(productos.getCantidadParlantes());
                        tvCapacBateria.setText(productos.getCapacidadBateria());
                        tvGpu.setText(productos.getGpu());
                        tvHMDI.setText(productos.getHdmi());
                        tvMemInterna.setText(productos.getMemoriaInterna());
                        tvMemRam.setText(productos.getMemoriaRam());
                        tvMarcaProcesador.setText(productos.getMarcaProcesador());
                        tvModelProcesador.setText(productos.getModeloProcesador());
                        tvNuleos.setText(productos.getNucleos());
                        tvRed.setText(productos.getRed());
                        tvSo.setText(productos.getSo());
                        tvversionSO.setText(productos.getVersionSO());
                        tvWifi.setText(productos.getWifi());
                    }*/
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error: " + databaseError);
            }
        });
    }
}
