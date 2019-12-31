package com.example.tucshop.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tucshop.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private CardView cvSmartPhone, cvSmartTV, cvNotebook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        cvSmartPhone = root.findViewById(R.id.cvSmart);

        cvSmartTV = root.findViewById(R.id.cvSmartTV);

        cvNotebook = root.findViewById(R.id.cvNotebook);

        return root;
    }
}