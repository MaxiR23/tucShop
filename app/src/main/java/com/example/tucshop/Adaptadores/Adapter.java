package com.example.tucshop.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tucshop.Modelo.ModelCategory;
import com.example.tucshop.R;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<ModelCategory> modelCategories;
    private Context context;

    public Adapter(List<ModelCategory> modelCategories, Context context)
    {
        this.modelCategories = modelCategories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelCategories.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_item, container, false);

        ImageView imageView;
        TextView tvTitle, tvDescripcion;

        imageView = view.findViewById(R.id.image);
        tvTitle = view.findViewById(R.id.tvTitleCategory);
        tvDescripcion = view.findViewById(R.id.tvDescripCategory);

        imageView.setImageResource(modelCategories.get(position).getImagenProducto());
        tvTitle.setText(modelCategories.get(position).getTitulo());
        tvDescripcion.setText(modelCategories.get(position).getDescripción());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
