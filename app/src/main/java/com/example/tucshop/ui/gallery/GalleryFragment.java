package com.example.tucshop.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tucshop.Adaptadores.ListaMisComprasAdapter;
import com.example.tucshop.Modelo.Compras;
import com.example.tucshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private TextView tvFechaCompra;
    private RecyclerView recyclerView;
    private List<Compras> comprasList;
    private ListaMisComprasAdapter misComprasAdapter;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /*
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class); */
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        tvFechaCompra = root.findViewById(R.id.fechaCompra);

        comprasList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.rvCompras);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        consultarCompras();
        /*
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

    private void consultarCompras() {

        reference = FirebaseDatabase.getInstance().getReference("Compras");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comprasList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Compras compras = snapshot.getValue(Compras.class);
                    comprasList.add(compras);

                    misComprasAdapter = new ListaMisComprasAdapter(getContext(), comprasList);
                    misComprasAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(misComprasAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
}