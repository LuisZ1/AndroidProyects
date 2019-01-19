package com.luisz.simpleclicker.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.simpleclicker.Adapter.AdapterMejoras;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private TextView txtSumador, txtPuntos, lblClicks, lblSumador, txtContadorPulsaciones;
    private Switch swAutoClick;
    private Button btnClick;
    private ViewModel miViewModel;
    private boolean isAutoClickComprado;

    //minero auto
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    //ViewModel miViewModel;
    private RecyclerView miRecyclerView;
    private AdapterMejoras adaptador;
    private Typeface font;
    private int delay;
    private int period;
    private int rowNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.inicio));

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");
        rowNumber = miViewModel.getRowNumber();

        delay = miViewModel.getDelay();
        period = delay;

        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtSumador = view.findViewById(R.id.txtSumador);
        lblClicks = view.findViewById(R.id.lblN_clicks);
        lblSumador = view.findViewById(R.id.lblSumador);
        swAutoClick = view.findViewById(R.id.swAutoClick);
        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtSumador = view.findViewById(R.id.txtSumador);
        txtContadorPulsaciones = view.findViewById(R.id.txtPulsaciones);

        //ocultar switches temporalmente TODO
        isAutoClickComprado = miViewModel.isAutoClickComprado();
        if(isAutoClickComprado){
            swAutoClick.setVisibility(View.VISIBLE);
        }else{
            swAutoClick.setVisibility(View.INVISIBLE);
        }

        lblClicks.setTypeface(font);
        lblSumador.setTypeface(font);
        swAutoClick.setTypeface(font);

        btnClick = view.findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miViewModel.sumatron();
                displayForPuntos(miViewModel.getPuntos());
            }
        });

        swAutoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((Switch) v).isChecked();
                if (on) {
                    stopTimer();
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            miViewModel.setRowNumber(2);
        } else {
            miViewModel.setRowNumber(1);
        }

        miRecyclerView = view.findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),miViewModel.getRowNumber() , LinearLayoutManager.HORIZONTAL, false));

        adaptador = new AdapterMejoras(miViewModel.getListaMejorasMutable().getValue());
        miRecyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                    gestionClickRecycler(mejoraSeleccionada);
                }
            }
        });


        final Observer<ArrayList<Mejora>> miVMobserver = new Observer<ArrayList<Mejora>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Mejora> listadoMejoras) {
                adaptador.setListaMejoras(listadoMejoras);
            }
        };

        miViewModel.getListaMejorasMutable().observe(this, miVMobserver);
        displayForPuntos(miViewModel.getPuntos());

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            miViewModel.setRowNumber(1);
            miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), miViewModel.getRowNumber(), LinearLayoutManager.HORIZONTAL, false));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            miViewModel.setRowNumber(2);
            miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), miViewModel.getRowNumber(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopTimer();
    }

    synchronized void gestionClickRecycler(int item) {
        miViewModel.sumadorMejora(item);
        displayForPuntos(miViewModel.getPuntos());
    }

    public void displayForPuntos(long puntos) {

        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

        txtPuntos.setText(formatter.format(puntos));
        txtSumador.setText(formatter.format(miViewModel.getSumador()));
        txtContadorPulsaciones.setText(formatter.format(miViewModel.getContadorPulsacionesPartida()));
    }

    //MINERO AUTOMATICO

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        miViewModel.sumatron();
                        displayForPuntos(miViewModel.getPuntos());
                    }
                });
            }
        };

        timer.schedule(timerTask, delay, period);
    }

}
