package com.example.tucshop.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tucshop.ActivityProductos.ActivityCategoriaNotebook;
import com.example.tucshop.ActivityProductos.ActivityCategoriaSmartPhones;
import com.example.tucshop.ActivityProductos.ActivityCategoriaSmartTV;
import com.example.tucshop.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private CardView cvSmartPhone, cvSmartTV, cvNotebook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        cvSmartPhone = root.findViewById(R.id.cvSmart);
        cvSmartPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityCategoriaSmartPhones.class));
            }
        });

        cvSmartTV = root.findViewById(R.id.cvSmartTV);
        cvSmartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityCategoriaSmartTV.class));
            }
        });

        cvNotebook = root.findViewById(R.id.cvNotebook);
        cvNotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityCategoriaNotebook.class));
            }
        });

        return root;
    }
}