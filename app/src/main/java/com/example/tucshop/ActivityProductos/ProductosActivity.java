package com.example.tucshop.ActivityProductos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tucshop.Dialogs.Dialogconfirmación_compra;
import com.example.tucshop.R;
import com.google.firebase.database.DatabaseReference;

public class ProductosActivity extends AppCompatActivity {

    private TextView nombreProducto, precioProducto, tvAlto, tvAncho, tvBateria, tvcamFront, tvcamPrincipal, tvCantParlantes, tvCapacBateria,
            tvGpu, tvHMDI, tvLinea, tvMarca,tvMarcaProcesador, tvMemRam, tvMemInterna, tvModelProcesador, tvNuleos, tvPeso, tvRed, tvResolucion, tvSo, tvTamPantalla, tvTipoPantalla, tvUsb, tvversionSO, tvWifi;

    private ImageView imagenProducto;

    private Button btnComprar;

    private DatabaseReference reference;
    private LinearLayout linearBateria, linearcamaraPrincipal, linearcamaraFront, linearCantidadParlantes, linearCapacBateria, linearGPU, linearHMDI, linearMemRam, linearMemInt, linearMarcaProcesador,
            linearModelProcesador, linearNucleos, linearRed, linearSO, linearUSB, linearVSO, linearWifi;

    Context context;

    String nameProduct, priceProduct, ancho, altura, batería, camaraFrontal, camaraPrincipal, cantidadParlantes, capacidadBateria, gpu, hdmi, linea, marca,
     marcaProcesador, memoriaRam, memoriaInterna, modeloProcesador, nucleos, peso, red, resolucion, so, tamañoPantalla,
      tipoPantalla, usb, versionSO, wifi, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        btnComprar = findViewById(R.id.btn_comprar);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogconfirmación_compra confirmación_compra = new Dialogconfirmación_compra();
                confirmación_compra.show(getSupportFragmentManager(), "confirmacion_compra");
            }
        });

        //TV Principales
        nombreProducto = findViewById(R.id.nombreProducto);
        precioProducto = findViewById(R.id.precioProducto);
        imagenProducto = findViewById(R.id.imageProducto);

        nameProduct = getIntent().getStringExtra("nombreProducto");
        priceProduct = getIntent().getStringExtra("montoProducto");

        //Prubas
        ancho = getIntent().getStringExtra("ancho");
        altura = getIntent().getStringExtra("altura");
        batería = getIntent().getStringExtra("bateria");
        camaraFrontal = getIntent().getStringExtra("camFront");
        camaraPrincipal = getIntent().getStringExtra("camPrincipal");
        cantidadParlantes = getIntent().getStringExtra("cantParlantes");
        capacidadBateria = getIntent().getStringExtra("capBateria");
        gpu = getIntent().getStringExtra("gpu");
        hdmi = getIntent().getStringExtra("hmdi");
        linea = getIntent().getStringExtra("linea");
        marca = getIntent().getStringExtra("marca");
        marcaProcesador = getIntent().getStringExtra("marcaProcesador");
        memoriaRam = getIntent().getStringExtra("memRam");
        memoriaInterna = getIntent().getStringExtra("memInterna");
        modeloProcesador = getIntent().getStringExtra("modelProcesador");
        nucleos = getIntent().getStringExtra("nucleos");
        peso = getIntent().getStringExtra("peso");
        red = getIntent().getStringExtra("red");
        resolucion = getIntent().getStringExtra("resolucion");
        so = getIntent().getStringExtra("so");
        tamañoPantalla = getIntent().getStringExtra("tamañoPantalla");
        tipoPantalla = getIntent().getStringExtra("tipoPantalla");
        usb = getIntent().getStringExtra("usb");
        versionSO = getIntent().getStringExtra("vso");
        wifi = getIntent().getStringExtra("wifi");
        url = getIntent().getStringExtra("image");
        //

        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagenProducto);

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

        tvAncho.setText(ancho);
        tvAlto.setText(altura);
        tvBateria.setText(batería);
        tvcamFront.setText(camaraFrontal);
        tvcamPrincipal.setText(camaraPrincipal);
        tvCantParlantes.setText(cantidadParlantes);
        tvCapacBateria.setText(capacidadBateria);
        tvGpu.setText(gpu);
        tvHMDI.setText(hdmi);
        tvLinea.setText(linea);
        tvMarca.setText(marca);
        tvMarcaProcesador.setText(marcaProcesador);
        tvMemRam.setText(memoriaRam);
        tvMemInterna.setText(memoriaInterna);
        tvModelProcesador.setText(modeloProcesador);
        tvNuleos.setText(nucleos);
        tvPeso.setText(peso);
        tvRed.setText(red);
        tvResolucion.setText(resolucion);
        tvSo.setText(so);
        tvTamPantalla.setText(tamañoPantalla);
        tvTipoPantalla.setText(tipoPantalla);
        tvUsb.setText(usb);
        tvversionSO.setText(versionSO);
        tvWifi.setText(wifi);

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

        //Condiciones para cada Producto

        if (batería.equals("") && gpu.equals("") && marcaProcesador.equals("")){

            linearBateria.setVisibility(View.GONE);
            linearGPU.setVisibility(View.GONE);
            linearMarcaProcesador.setVisibility(View.GONE);

        } else {

            linearBateria.setVisibility(View.VISIBLE);
            linearGPU.setVisibility(View.VISIBLE);
            linearMarcaProcesador.setVisibility(View.VISIBLE);

        }

        if (camaraFrontal.equals("") && camaraPrincipal.equals("") && capacidadBateria.equals("") && memoriaInterna.equals("") && red.equals(""))
        {
            linearcamaraFront.setVisibility(View.GONE);
            linearcamaraPrincipal.setVisibility(View.GONE);
            linearCapacBateria.setVisibility(View.GONE);
            linearMemInt.setVisibility(View.GONE);
            linearRed.setVisibility(View.GONE);

        } else {

            linearcamaraFront.setVisibility(View.VISIBLE);
            linearcamaraPrincipal.setVisibility(View.VISIBLE);
            linearCapacBateria.setVisibility(View.VISIBLE);
            linearMemInt.setVisibility(View.VISIBLE);
            linearRed.setVisibility(View.VISIBLE);
        }

        if (cantidadParlantes.equals("") && wifi.equals(""))
        {

            linearCantidadParlantes.setVisibility(View.GONE);
            linearWifi.setVisibility(View.GONE);

        } else {

            linearCantidadParlantes.setVisibility(View.VISIBLE);
            linearWifi.setVisibility(View.VISIBLE);

        }

        if (hdmi.equals(""))
        {
            linearHMDI.setVisibility(View.GONE);
        } else {
            linearHMDI.setVisibility(View.VISIBLE);
        }

        if (memoriaRam.equals(""))
        {
            linearMemRam.setVisibility(View.GONE);
        } else {
            linearMemRam.setVisibility(View.VISIBLE);
        }

        if (modeloProcesador.equals(""))
        {
            linearModelProcesador.setVisibility(View.GONE);
        } else {
            linearModelProcesador.setVisibility(View.VISIBLE);
        }

        if (nucleos.equals(""))
        {
            linearNucleos.setVisibility(View.GONE);
        } else {
            linearNucleos.setVisibility(View.VISIBLE);
        }

        if (so.equals(""))
        {
            linearSO.setVisibility(View.GONE);
        } else {
            linearSO.setVisibility(View.VISIBLE);
        }

        if (versionSO.equals(""))
        {
            linearVSO.setVisibility(View.GONE);
        } else {
            linearSO.setVisibility(View.VISIBLE);
        }
    }
}
