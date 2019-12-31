package com.example.tucshop.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tucshop.ActivityProductos.ProductosActivity;
import com.example.tucshop.Modelo.ProductoDestacado;
import com.example.tucshop.R;

import java.util.List;

public class AdapterProductoDestacado extends RecyclerView.Adapter<AdapterProductoDestacado.ViewHolder> {

    private List<ProductoDestacado> productoDestacadoList;
    Context context;

    public AdapterProductoDestacado(Context context, List<ProductoDestacado> productoDestacadoList)
    {
        this.context = context;
        this.productoDestacadoList = productoDestacadoList;
    }

    @NonNull
    @Override
    public AdapterProductoDestacado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.idea_destacado, null, false);
        return new AdapterProductoDestacado.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductoDestacado.ViewHolder holder, int position) {
        final ProductoDestacado productoDestacado = productoDestacadoList.get(position);
        holder.nombreProductoDestacado.setText(productoDestacado.getNombreProducto());
        holder.montoProductoDestacado.setText(productoDestacado.getMontoProducto());

        //tv Ocultos para pasarlos como caracteristicas al sig activity
        holder.tvAlto.setText(productoDestacado.getAltura());
        holder.tvAncho.setText(productoDestacado.getAncho());
        holder.tvLinea.setText(productoDestacado.getLinea());
        holder.tvMarca.setText(productoDestacado.getMarca());
        holder.tvPeso.setText(productoDestacado.getPeso());
        holder.tvResolucion.setText(productoDestacado.getResolucion());
        holder.tvTamPantalla.setText(productoDestacado.getTamañoPantalla());
        holder.tvTipoPantalla.setText(productoDestacado.getTipoPantalla());
        holder.tvUsb.setText(productoDestacado.getUsb());
        holder.tvBateria.setText(productoDestacado.getBateria());
        holder.tvcamFront.setText(productoDestacado.getCamaraFrontal());
        holder.tvcamPrincipal.setText(productoDestacado.getCamaraPrincipal());
        holder.tvCantParlantes.setText(productoDestacado.getCantidadParlantes());
        holder.tvCapacBateria.setText(productoDestacado.getCapacidadBateria());
        holder.tvGpu.setText(productoDestacado.getGpu());
        holder.tvHMDI.setText(productoDestacado.getHdmi());
        holder.tvMemInterna.setText(productoDestacado.getMemoriaInterna());
        holder.tvMemRam.setText(productoDestacado.getMemoriaRam());
        holder.tvMarcaProcesador.setText(productoDestacado.getMarcaProcesador());
        holder.tvModelProcesador.setText(productoDestacado.getModeloProcesador());
        holder.tvNuleos.setText(productoDestacado.getNucleos());
        holder.tvRed.setText(productoDestacado.getRed());
        holder.tvSo.setText(productoDestacado.getSo());
        holder.tvversionSO.setText(productoDestacado.getVersionSO());
        holder.tvWifi.setText(productoDestacado.getWifi());
        //

        Glide.with(context).load(productoDestacado.getImagenProducto()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imagenProductoDestacado);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductosActivity.class);
                intent.putExtra("nombreProducto", productoDestacado.getNombreProducto());
                intent.putExtra("montoProducto", productoDestacado.getMontoProducto());
                intent.putExtra("altura", productoDestacado.getAltura());
                intent.putExtra("ancho", productoDestacado.getAncho());
                intent.putExtra("linea", productoDestacado.getLinea());
                intent.putExtra("marca", productoDestacado.getMarca());
                intent.putExtra("peso", productoDestacado.getPeso());
                intent.putExtra("resolucion", productoDestacado.getResolucion());
                intent.putExtra("tamañoPantalla", productoDestacado.getTamañoPantalla());
                intent.putExtra("tipoPantalla", productoDestacado.getTipoPantalla());
                intent.putExtra("usb", productoDestacado.getUsb());
                intent.putExtra("bateria", productoDestacado.getBateria());
                intent.putExtra("camFront", productoDestacado.getCamaraFrontal());
                intent.putExtra("camPrincipal", productoDestacado.getCamaraPrincipal());
                intent.putExtra("cantParlantes", productoDestacado.getCantidadParlantes());
                intent.putExtra("capBateria", productoDestacado.getCapacidadBateria());
                intent.putExtra("gpu", productoDestacado.getGpu());
                intent.putExtra("hmdi", productoDestacado.getHdmi());
                intent.putExtra("memInterna", productoDestacado.getMemoriaInterna());
                intent.putExtra("memRam", productoDestacado.getMemoriaRam());
                intent.putExtra("marcaProcesador", productoDestacado.getMarcaProcesador());
                intent.putExtra("modelProcesador", productoDestacado.getModeloProcesador());
                intent.putExtra("nucleos", productoDestacado.getNucleos());
                intent.putExtra("red", productoDestacado.getRed());
                intent.putExtra("so", productoDestacado.getSo());
                intent.putExtra("vso", productoDestacado.getVersionSO());
                intent.putExtra("wifi", productoDestacado.getWifi());
                intent.putExtra("image", productoDestacado.getImagenProducto());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoDestacadoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreProductoDestacado, montoProductoDestacado, tvAlto, tvAncho, tvBateria, tvcamFront, tvcamPrincipal, tvCantParlantes, tvCapacBateria,
                tvGpu, tvHMDI, tvLinea, tvMarca,tvMarcaProcesador, tvMemRam, tvMemInterna, tvModelProcesador, tvNuleos, tvPeso, tvRed, tvResolucion, tvSo, tvTamPantalla, tvTipoPantalla, tvUsb, tvversionSO, tvWifi;;
        private ImageView imagenProductoDestacado;
        private RelativeLayout relativeLayout;
        private LinearLayout linearLayoutGONE;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreProductoDestacado = itemView.findViewById(R.id.tvNombreArticuloDestacado);
            montoProductoDestacado = itemView.findViewById(R.id.tvmontoProductoDestacado);
            imagenProductoDestacado = itemView.findViewById(R.id.imgProductodeSemana);

            //Las Caracteristicas que estarán escondidas..

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
