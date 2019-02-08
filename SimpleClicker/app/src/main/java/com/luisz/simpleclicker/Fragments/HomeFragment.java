package com.luisz.simpleclicker.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.luisz.simpleclicker.Adapter.AdapterMejoras;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.Util.formateoDeNumeros;
import com.luisz.simpleclicker.ViewModel.ViewModel;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.OnTargetStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private TextView txtSumador, txtPuntos, lblClicks, lblSumador, txtContadorPulsaciones, lblMultiplicador, txtMultiplicador;
    private Switch swAutoClick;
    private Button btnClick;
    private ViewModel miViewModel;
    private boolean isAutoClickComprado;

    //minero auto
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();
    private int delay;
    private int period;

    //ViewModel miViewModel;
    private RecyclerView miRecyclerView;
    private AdapterMejoras adaptador;
    private Typeface font;
    private int rowNumber;

    //spotlight

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
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
        lblMultiplicador = view.findViewById(R.id.lblMultiplicador);
        txtMultiplicador = view.findViewById(R.id.txtMultiplicador);
        swAutoClick = view.findViewById(R.id.swAutoClick);
        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtSumador = view.findViewById(R.id.txtSumador);
        txtContadorPulsaciones = view.findViewById(R.id.txtPulsaciones);

        //ocultar switches temporalmente TODO
        isAutoClickComprado = miViewModel.isAutoClickComprado();
        if (isAutoClickComprado) {
            swAutoClick.setVisibility(View.VISIBLE);
        } else {
            swAutoClick.setVisibility(View.INVISIBLE);
        }

        lblClicks.setTypeface(font);
        lblSumador.setTypeface(font);
        swAutoClick.setTypeface(font);
        lblMultiplicador.setTypeface(font);

        btnClick = view.findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miViewModel.sumatron();
                displayForPuntos(miViewModel.getPuntos());
            }
        });

        swAutoClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(miViewModel.isAutoClickActivo()){
                    swAutoClick.setChecked(true);
                }

                if (isChecked) {
                    stopTimer();
                    startTimer();
                    miViewModel.setAutoClickActivo(true);
                } else {
                    stopTimer();
                    miViewModel.setAutoClickActivo(false);
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
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), miViewModel.getRowNumber(), LinearLayoutManager.HORIZONTAL, false));

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

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (miViewModel.isFirstLauch()) {

                    //PUNTOS
                    int[] spotPuntos = new int[2];
                    view.findViewById(R.id.txtPuntos).getLocationInWindow(spotPuntos);
                    float spotPuntosX = spotPuntos[0] + view.findViewById(R.id.txtPuntos).getWidth() / 2F;
                    float spotPuntosY = spotPuntos[1] + view.findViewById(R.id.txtPuntos).getHeight() / 2F;
                    float spotPuntosRadius = 120F;

                    SimpleTarget targetPuntos = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotPuntosX, spotPuntosY)
                            .setShape(new Circle(spotPuntosRadius)) // or RoundedRectangle()
                            .setTitle("Puntos")
                            .setDescription("Haz clic para conseguirlos")
                            .setOverlayPoint(100f, view.findViewById(R.id.txtPuntos).getWidth() + spotPuntosRadius + 100f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();

                    //SUMADOR
                    int[] spotSumador = new int[2];
                    view.findViewById(R.id.txtSumador).getLocationInWindow(spotSumador);
                    float spotSumadorX = spotSumador[0] + view.findViewById(R.id.txtSumador).getWidth() / 2F;
                    float spotSumadorY = spotSumador[1] + view.findViewById(R.id.txtSumador).getHeight() / 2F;
                    float spotSumadorRadius = 100F;

                    SimpleTarget targetSumador = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotSumadorX, spotSumadorY)
                            .setShape(new Circle(spotSumadorRadius)) // or RoundedRectangle()
                            .setTitle("Sumador")
                            .setDescription("Cantidad de puntos que\nganas con cada clic")
                            .setOverlayPoint(100f, view.findViewById(R.id.txtSumador).getWidth() + spotSumadorRadius + 100f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();

                    //MULTIPLICADORS
                    int[] spotMultiplicador = new int[2];
                    view.findViewById(R.id.txtMultiplicador).getLocationInWindow(spotMultiplicador);
                    float spotMultiplicadorX = spotMultiplicador[0] + view.findViewById(R.id.txtMultiplicador).getWidth() / 2F;
                    float spotMultiplicadorY = spotMultiplicador[1] + view.findViewById(R.id.txtMultiplicador).getHeight() / 2F;
                    float spotMultiplicadorRadius = 100F;

                    SimpleTarget targetMultiplicador = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotMultiplicadorX, spotMultiplicadorY)
                            .setShape(new Circle(spotMultiplicadorRadius)) // or RoundedRectangle()
                            .setTitle("Multiplicador")
                            .setDescription("Cada vez que hagas clic el sumador\nse multiplica por este número")
                            .setOverlayPoint(100f, view.findViewById(R.id.txtMultiplicador).getWidth() + spotMultiplicadorRadius + 100f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();

                    //MULTIPLICADOR
                    int[] spotNClicks = new int[2];
                    view.findViewById(R.id.txtPulsaciones).getLocationInWindow(spotNClicks);
                    float spotNClicksX = spotNClicks[0] + view.findViewById(R.id.txtPulsaciones).getWidth() / 2F;
                    float spotNClicksY = spotNClicks[1] + view.findViewById(R.id.txtPulsaciones).getHeight() / 2F;
                    float spotNClicksRadius = 100F;

                    SimpleTarget targetNClicks = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotNClicksX, spotNClicksY)
                            .setShape(new Circle(spotNClicksRadius)) // or RoundedRectangle()
                            .setTitle("Nº de clicks")
                            .setDescription("Número de click que has dado\npara que no pierdas la cuenta")
                            .setOverlayPoint(100f, view.findViewById(R.id.txtPulsaciones).getWidth() + spotNClicksRadius + 100f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();

                    //METALES
                    int[] spotRecycler = new int[2];
                    view.findViewById(R.id.myRecyclerView).getLocationInWindow(spotRecycler);
                    float spotRecyclerX = spotRecycler[0] + view.findViewById(R.id.myRecyclerView).getWidth() / 2F;
                    float spotRecyclerY = spotRecycler[1] + view.findViewById(R.id.myRecyclerView).getHeight() / 2F;
                    float spotRecyclerRadius = 600F;

                    SimpleTarget targetRecycler = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotRecyclerX, spotRecyclerY)
                            .setShape(new Circle(spotRecyclerRadius)) // or RoundedRectangle()
                            .setTitle("Metales")
                            .setDescription("Compra metales para aumentar el sumador\ny ganar más puntos")
                            .setOverlayPoint(50f, 50f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();

                    //MEJORAS
                    int[] spotMejoras = new int[2];
                    view.findViewById(R.id.guideline28).getLocationInWindow(spotMejoras);
                    float spotMejorasX = spotMejoras[0] + view.findViewById(R.id.guideline28).getWidth() / 2F;
                    float spotMejorasY = getActivity().getWindow().getDecorView().getBottom() - 250L;
                    float spotMejorasRadius = 100F;

                    SimpleTarget targetMejoras = new SimpleTarget.Builder(getActivity())
                            .setPoint(spotMejorasX, spotMejorasY)
                            .setShape(new Circle(spotMejorasRadius)) // or RoundedRectangle()
                            .setTitle("Mejoras")
                            .setDescription("Compralas para aumentar el\nmultiplicador y ganar más puntos")
                            .setOverlayPoint(100f, 100f)
                            .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                @Override
                                public void onStarted(SimpleTarget target) { }
                                @Override
                                public void onEnded(SimpleTarget target) { }
                            }).build();




                    Spotlight.with(getActivity())
                            .setOverlayColor(R.color.background_spotlight)
                            .setDuration(800L)
                            .setAnimation(new DecelerateInterpolator(2f))
                            .setTargets(targetPuntos, targetNClicks, targetSumador, targetMultiplicador, targetRecycler
                                        ,targetMejoras)
                            .setClosedOnTouchedOutside(true)
                            .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
                                @Override
                                public void onStarted() { }
                                @Override
                                public void onEnded() { }
                            }).start();

                    miViewModel.setFirstLauch(false);
                }
            }
        });

        displayForPuntos(miViewModel.getPuntos());

        return view;
    }

    @Override
    public void onStart() {
        if(miViewModel.isAutoClickActivo()){
            swAutoClick.setChecked(true);
        }

        if (swAutoClick.isChecked()) {
            stopTimer();
            startTimer();
            miViewModel.setAutoClickActivo(true);
        } else {
            stopTimer();
            miViewModel.setAutoClickActivo(false);
        }

        super.onStart();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            miViewModel.setRowNumber(1);
            miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), miViewModel.getRowNumber(), LinearLayoutManager.HORIZONTAL, false));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
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

        txtPuntos.setText(formateoDeNumeros.formatterV2(puntos));
        txtSumador.setText(formateoDeNumeros.formatterV2(miViewModel.getSumador()));
        txtMultiplicador.setText(formateoDeNumeros.formatterDecimales.format(miViewModel.getMultiplicador()));
        txtContadorPulsaciones.setText(formateoDeNumeros.formatterV1.format(miViewModel.getContadorPulsacionesPartida()));
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
