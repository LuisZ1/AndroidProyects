package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class PantallaPrincipal extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private EditText txtEmail, txtContraseña;
    private Button btnInicio, btnRegistro;

    private FirebaseAuth.AuthStateListener mAuthListener;

    public PantallaPrincipal() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_pantalla_principal, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        txtEmail = view.findViewById(R.id.etEmail);
        txtContraseña = view.findViewById(R.id.etPassword);
        btnInicio = view.findViewById(R.id.btnInicioSesion);
        btnRegistro = view.findViewById(R.id.btnRegistro);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicioSesion();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    miViewModel.setNombreUsuario(user.getEmail());
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Home()).commit();
                }
            }
        };

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void registro() {
        String email = txtEmail.getText().toString();
        String password = txtContraseña.getText().toString();

        if (email.matches("") || password.matches("")) {
            DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Rellena los dos campos").show();
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.i("Sesion", "Usuario creado correctamente");
                    } else {
                        Log.e("Sesion", task.getException().getMessage() + "");
                        DynamicToast.makeWarning(view.getContext().getApplicationContext(), task.getException().getMessage()).show();
                    }
                }
            });
        }
    }

    private void inicioSesion() {
        String email = txtEmail.getText().toString();
        String password = txtContraseña.getText().toString();

        if (email.matches("") || password.matches("")) {
            DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Rellena los dos campos").show();
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.i("Sesion", "Sesion iniciada");
                    } else {
                        Log.e("Sesion", task.getException().getMessage() + "");
                        DynamicToast.makeError(view.getContext().getApplicationContext(), task.getException().getMessage()).show();
                    }
                }
            });
        }
    }
}