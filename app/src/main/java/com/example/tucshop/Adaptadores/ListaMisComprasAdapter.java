package com.example.tucshop.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tucshop.Modelo.Compras;
import com.example.tucshop.R;

import java.util.List;

public class ListaMisComprasAdapter extends RecyclerView.Adapter<ListaMisComprasAdapter.ViewHolder> {

    private Context context;
    private List<Compras> comprasList;

    public ListaMisComprasAdapter(Context context, List<Compras> comprasList)
    {
        this.context = context;
        this.comprasList = comprasList;
    }

    @NonNull
    @Override
    public ListaMisComprasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_miscompras_recyclerview, null,false);
        return new ListaMisComprasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Compras compras = comprasList.get(position);
        holder.tvFechaDeCompra.setText(compras.getDate());
        holder.tvNombreProducto.setText(compras.getNombreProducto());
        holder.tvMontoProducto.setText(compras.getMontoProducto());
    }


    @Override
    public int getItemCount() {
        return comprasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFechaDeCompra, tvNombreProducto, tvMontoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvFechaDeCompra = itemView.findViewById(R.id.fechaCompra);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvMontoProducto = itemView.findViewById(R.id.tvMontoProducto);
        }
    }
}
