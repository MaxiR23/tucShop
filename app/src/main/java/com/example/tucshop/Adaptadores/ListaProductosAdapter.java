package com.example.tucshop.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tucshop.ActivityProductos.ProductosActivity;
import com.example.tucshop.Modelo.Productos;
import com.example.tucshop.R;
import java.util.List;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ViewHolder> {

    private Context context;
    private List<Productos> productosList;

    public ListaProductosAdapter(Context context, List<Productos> productosList)
    {
        this.context = context;
        this.productosList = productosList;
    }

    @NonNull
    @Override
    public ListaProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homeproductos_recyclerview, null,false);
        return new ListaProductosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Productos productos = productosList.get(position);
        holder.nombreProducto.setText(productos.getNombreProducto());
        holder.montoProducto.setText(productos.getMontoProducto());

        //tv Ocultos para pasarlos como caracteristicas al sig activity
       holder.tvAlto.setText(productos.getAltura());
       holder.tvAncho.setText(productos.getAncho());
       holder.tvLinea.setText(productos.getLinea());
       holder.tvMarca.setText(productos.getMarca());
       holder.tvPeso.setText(productos.getPeso());
       holder.tvResolucion.setText(productos.getResolucion());
       holder.tvTamPantalla.setText(productos.getTamañoPantalla());
       holder.tvTipoPantalla.setText(productos.getTipoPantalla());
       holder.tvUsb.setText(productos.getUsb());
       holder.tvBateria.setText(productos.getBateria());
       holder.tvcamFront.setText(productos.getCamaraFrontal());
       holder.tvcamPrincipal.setText(productos.getCamaraPrincipal());
       holder.tvCantParlantes.setText(productos.getCantidadParlantes());
       holder.tvCapacBateria.setText(productos.getCapacidadBateria());
       holder.tvGpu.setText(productos.getGpu());
       holder.tvHMDI.setText(productos.getHdmi());
       holder.tvMemInterna.setText(productos.getMemoriaInterna());
       holder.tvMemRam.setText(productos.getMemoriaRam());
       holder.tvMarcaProcesador.setText(productos.getMarcaProcesador());
       holder.tvModelProcesador.setText(productos.getModeloProcesador());
       holder.tvNuleos.setText(productos.getNucleos());
       holder.tvRed.setText(productos.getRed());
       holder.tvSo.setText(productos.getSo());
       holder.tvversionSO.setText(productos.getVersionSO());
       holder.tvWifi.setText(productos.getWifi());
        //

        Glide.with(context).load(productos.getImagenProducto()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imagenProducto);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductosActivity.class);
                intent.putExtra("nombreProducto", productos.getNombreProducto());
                intent.putExtra("montoProducto", productos.getMontoProducto());
                intent.putExtra("altura", productos.getAltura());
                intent.putExtra("ancho", productos.getAncho());
                intent.putExtra("linea", productos.getLinea());
                intent.putExtra("marca", productos.getMarca());
                intent.putExtra("peso", productos.getPeso());
                intent.putExtra("resolucion", productos.getResolucion());
                intent.putExtra("tamañoPantalla", productos.getTamañoPantalla());
                intent.putExtra("tipoPantalla", productos.getTipoPantalla());
                intent.putExtra("usb", productos.getUsb());
                intent.putExtra("bateria", productos.getBateria());
                intent.putExtra("camFront", productos.getCamaraFrontal());
                intent.putExtra("camPrincipal", productos.getCamaraPrincipal());
                intent.putExtra("cantParlantes", productos.getCantidadParlantes());
                intent.putExtra("capBateria", productos.getCapacidadBateria());
                intent.putExtra("gpu", productos.getGpu());
                intent.putExtra("hmdi", productos.getHdmi());
                intent.putExtra("memInterna", productos.getMemoriaInterna());
                intent.putExtra("memRam", productos.getMemoriaRam());
                intent.putExtra("marcaProcesador", productos.getMarcaProcesador());
                intent.putExtra("modelProcesador", productos.getModeloProcesador());
                intent.putExtra("nucleos", productos.getNucleos());
                intent.putExtra("red", productos.getRed());
                intent.putExtra("so", productos.getSo());
                intent.putExtra("vso", productos.getVersionSO());
                intent.putExtra("wifi", productos.getWifi());
                intent.putExtra("image", productos.getImagenProducto());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreProducto, montoProducto, tvAlto, tvAncho, tvBateria, tvcamFront, tvcamPrincipal, tvCantParlantes, tvCapacBateria,
                tvGpu, tvHMDI, tvLinea, tvMarca,tvMarcaProcesador, tvMemRam, tvMemInterna, tvModelProcesador, tvNuleos, tvPeso, tvRed, tvResolucion, tvSo, tvTamPantalla, tvTipoPantalla, tvUsb, tvversionSO, tvWifi;
        private ImageView imagenProducto;
        private LinearLayout linearLayout, linearLayoutGONE;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            montoProducto = itemView.findViewById(R.id.tvMontoProducto);
            imagenProducto = itemView.findViewById(R.id.imageProducto);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            //Caracteristicas Escondidas

            linearLayoutGONE = itemView.findViewById(R.id.linearProductosRVGONE);

            tvAlto = itemView.findViewById(R.id.tvAlto);
            tvAncho = itemView.findViewById(R.id.tvAncho);
            tvBateria = itemView.findViewById(R.id.tvBateria);
            tvcamFront = itemView.findViewById(R.id.tvCamFrontal);
            tvcamPrincipal = itemView.findViewById(R.id.tvCamPrincipal);
            tvCantParlantes = itemView.findViewById(R.id.tvCantidadParlantes);
            tvCapacBateria = itemView.findViewById(R.id.tvCapacidadBat);
            tvGpu = itemView.findViewById(R.id.tvGPU);
            tvHMDI = itemView.findViewById(R.id.tvHMDI);
            tvLinea = itemView.findViewById(R.id.tvlinea);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvMarcaProcesador = itemView.findViewById(R.id.tvMarcaProcesador);
            tvMemInterna = itemView.findViewById(R.id.tvMemInterna);
            tvMemRam = itemView.findViewById(R.id.tvRAM);
            tvModelProcesador = itemView.findViewById(R.id.tvModeloProcesador);
            tvNuleos = itemView.findViewById(R.id.tvNucleos);
            tvPeso = itemView.findViewById(R.id.tvPeso);
            tvRed = itemView.findViewById(R.id.tvRed);
            tvResolucion = itemView.findViewById(R.id.tvResolucion);
            tvSo = itemView.findViewById(R.id.tvSO);
            tvTamPantalla = itemView.findViewById(R.id.tvTamañoPantalla);
            tvTipoPantalla = itemView.findViewById(R.id.tvTipoPantalla);
            tvRed = itemView.findViewById(R.id.tvRed);
            tvUsb = itemView.findViewById(R.id.tvUSB);
            tvversionSO = itemView.findViewById(R.id.tvVSO);
            tvWifi = itemView.findViewById(R.id.tvWIfi);

            linearLayoutGONE.setVisibility(View.GONE);
        }
    }
}
