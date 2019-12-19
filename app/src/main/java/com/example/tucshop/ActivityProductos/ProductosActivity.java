package com.example.tucshop.ActivityProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.tucshop.R;
import com.google.firebase.database.DatabaseReference;

public class ProductosActivity extends AppCompatActivity {

    private TextView nombreProducto, precioProducto;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        nombreProducto = findViewById(R.id.nombreProducto);

        Intent intent = getIntent();
        String title = intent.getStringExtra("titulo");
        nombreProducto.setText(title);

    }
}
