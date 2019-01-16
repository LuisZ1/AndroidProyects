package com.luisz.simpleclicker.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import com.luisz.simpleclicker.Adapter.AdapterMejoras;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    TextView txtSumador, txtPuntos, lblClicks, lblSumador, txtContadorPulsaciones;
    Switch swAutoWalk, swAutoRun;
    Button btnClick;
    ViewModel miViewModel;
    boolean isAutoClickComprado;

    int x = 0;


    //    ViewModel miViewModel;
    RecyclerView miRecyclerView;
    AdapterMejoras adaptador;
    Typeface font;
    int delay = 1000;
    int period = delay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.inicio));

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");

        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtSumador = view.findViewById(R.id.txtSumador);
        lblClicks = view.findViewById(R.id.lblN_clicks);
        lblSumador = view.findViewById(R.id.lblSumador);
        swAutoWalk = view.findViewById(R.id.swAutoWalk);
        swAutoRun = view.findViewById(R.id.swAutoRun);
        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtSumador = view.findViewById(R.id.txtSumador);
        txtContadorPulsaciones = view.findViewById(R.id.txtPulsaciones);

        //ocultar switches temporalmente TODO
        isAutoClickComprado = miViewModel.isAutoClickComprado();
        if(isAutoClickComprado){
            //swAutoWalk.setVisibility(View.VISIBLE);
            swAutoRun.setVisibility(View.VISIBLE);
        }else{
            //swAutoWalk.setVisibility(View.INVISIBLE);
            swAutoRun.setVisibility(View.INVISIBLE);
        }

        lblClicks.setTypeface(font);
        lblSumador.setTypeface(font);
        swAutoWalk.setTypeface(font);
        swAutoRun.setTypeface(font);

        btnClick = view.findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miViewModel.sumatron();
                displayForPuntos(miViewModel.getPuntos());
            }
        });

        swAutoWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((Switch) v).isChecked();
                swAutoRun.setChecked(false);
                delay = 1000;
                period = 1000;
                if (on) {
                    stopTimer();
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });

        swAutoRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((Switch) v).isChecked();
                swAutoWalk.setChecked(false);
                delay = 100;
                period = 100;
                if (on) {
                    stopTimer();
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });

        miRecyclerView = view.findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2, LinearLayoutManager.HORIZONTAL, false));

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

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

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

    public void onSwitchWalkClicked(View v) {
        boolean on = ((Switch) v).isChecked();
        swAutoRun.setChecked(false);
        delay = 1000;
        period = 1000;
        if (on) {
            stopTimer();
            startTimer();
        } else {
            stopTimer();
        }
    }

    public void onSwitchRunClicked(View v) {
        boolean on = ((Switch) v).isChecked();
        swAutoWalk.setChecked(false);
        delay = 100;
        period = 100;
        if (on) {
            stopTimer();
            startTimer();
        } else {
            stopTimer();
        }
    }
}
