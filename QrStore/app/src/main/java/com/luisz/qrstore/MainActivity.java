package com.luisz.qrstore;

import android.os.Bundle;

import com.luisz.qrstore.Fragments.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private ViewModel miViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // miViewModel = ViewModelProviders.of(this).get(miViewModel.getClass());

       if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();
        }
    }
}
