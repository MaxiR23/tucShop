package com.example.tucshop.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tucshop.MainActivity;
import com.example.tucshop.R;

public class DialogCompra extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.compra_exitosa, null);

        builder.setView(view);

        final Intent intent = new Intent(getContext(), MainActivity.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(2300) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    dismiss();
                }
            }
        };
        timer.start();

        return builder.create();
    }
}
