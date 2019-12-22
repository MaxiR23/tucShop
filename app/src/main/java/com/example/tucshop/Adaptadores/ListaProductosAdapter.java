package com.example.tucshop.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tucshop.ActivityProductos.ProductosActivity;
import com.example.tucshop.Modelo.Productos;
import com.example.tucshop.R;
import com.example.tucshop.ui.home.HomeFragment;
import com.facebook.shimmer.Shimmer;

import java.io.Serializable;
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
        Glide.with(context).load(productos.getImagenProducto()).into(holder.imagenProducto);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductosActivity.class);
                intent.putExtra("imagenProducto", productos.getImagenProducto());
                intent.putExtra("nombreProducto", productos.getNombreProducto());
                intent.putExtra("montoProducto", productos.getMontoProducto());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreProducto, montoProducto;
        private ImageView imagenProducto;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            montoProducto = itemView.findViewById(R.id.tvMontoProducto);
            imagenProducto = itemView.findViewById(R.id.imageProducto);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
