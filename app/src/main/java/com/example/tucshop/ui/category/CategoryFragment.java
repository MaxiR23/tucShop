package com.example.tucshop.ui.category;


import android.animation.ArgbEvaluator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.tucshop.Adaptadores.Adapter;
import com.example.tucshop.Modelo.ModelCategory;
import com.example.tucshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    ViewPager viewPager;
    Adapter adapter;
    List<ModelCategory> modelCategories;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        modelCategories = new ArrayList<>();
        modelCategories.add(new ModelCategory(R.drawable.category_smartphones, "Smartphones", "Encontrá los mejores smartphones aquí!"));
        modelCategories.add(new ModelCategory(R.drawable.category_smarttv, "Smart TV", "Encontrá el Smart TV adecuado aquí"));
        modelCategories.add(new ModelCategory(R.drawable.category_laptop, "Laptops", "Encontrá aqui tu laptop deseada"));

        adapter = new Adapter(modelCategories, getContext());

        viewPager = view.findViewById(R.id.viewPagerCategory);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 139, 0);

        /*
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colo))
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
     */
        return view;
    }
    }
