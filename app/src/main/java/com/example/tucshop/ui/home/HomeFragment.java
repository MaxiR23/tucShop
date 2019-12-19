package com.example.tucshop.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tucshop.Adaptadores.ListaProductosAdapter;
import com.example.tucshop.Modelo.Productos;
import com.example.tucshop.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private DatabaseReference reference;
    private HomeViewModel homeViewModel;
    private RecyclerView rvHomeProductos;
    private List<Productos> productosArrayList;
    private ListaProductosAdapter adapter;
    private ShimmerFrameLayout shimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
   /*     homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        productosArrayList = new ArrayList<>();
        rvHomeProductos = root.findViewById(R.id.rvHomeProductos);
        rvHomeProductos.setHasFixedSize(true);
        rvHomeProductos.setNestedScrollingEnabled(false);
        rvHomeProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerFrameLayout = root.findViewById(R.id.shimmer_view_container);

        rvHomeProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        consultaProductos();

        return root;
    }

    private void consultaProductos() {
        shimmerFrameLayout.startShimmer();
        reference = FirebaseDatabase.getInstance().getReference("Productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productosArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Productos productos = snapshot.getValue(Productos.class);
                    productosArrayList.add(productos);

                    adapter = new ListaProductosAdapter(getContext(), productosArrayList);
                    adapter.notifyDataSetChanged();
                    rvHomeProductos.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}