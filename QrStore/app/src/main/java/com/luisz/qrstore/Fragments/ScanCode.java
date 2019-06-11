package com.luisz.qrstore.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ScanCode extends Fragment {

    private View view;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    private TextView textoResultado;
    private boolean vibrating = false;
    private FirebaseFirestore db;
    private ViewModel miViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scan_code, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        textoResultado = (TextView) view.findViewById(R.id.txtCodigoEscaneado);
        surfaceView = (SurfaceView) view.findViewById(R.id.camerapreview);

        barcodeDetector = new BarcodeDetector.Builder(view.getContext()).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(view.getContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920, 1024)
                .build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ContextCompat.checkSelfPermission(view.getContext().getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    textoResultado.setText("La app no tiene permiso para usar la cámara :(");
                    return;
                }
                try {
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                    textoResultado.setText("Error al acceder a la cámara :(");
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                //miViewModel.reiniciarVariables();

                final SparseArray<Barcode> qrCode = detections.getDetectedItems();

                if (qrCode.size() != 0 && !vibrating) {
                    textoResultado.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!vibrating) {
                                Vibrator vibrator = (Vibrator) getContext().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(200);
                                vibrating = true;

                                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Codigo detectado ").show();
                                consultarCodigo(qrCode.valueAt(0).displayValue);
                            }
                        }
                    });
                }
            }
        });

        return view;
    }

    public void consultarCodigo(final String codigo) {
        switch (codigo.charAt(0)) {
            case 'E':
                //DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una estanteria").show();
                if (db == null) {
                    db = FirebaseFirestore.getInstance();
                }
                if (miViewModel == null) {
                    miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
                }

                DocumentReference docRef = db.collection("estanterias").document(codigo);

                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Estanteria estanteria = documentSnapshot.toObject(Estanteria.class);
                        estanteria.setIdestanteria(documentSnapshot.getId());

                        miViewModel.setEstanteriaEscaneada(estanteria);

                        //consultar Cajas de la estantería -----------------------------

                        Query queryCajas = db.collection("cajas").whereEqualTo("idestanteria", codigo);

                        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot snapshot,
                                                @Nullable FirebaseFirestoreException e) {
                                if (e != null) {
                                    // Handle error
                                    return;
                                }

                                List<Caja> cajas = snapshot.toObjects(Caja.class);

                                miViewModel.setListadoCajas((ArrayList<Caja>) cajas);

                                vibrating = false;

                                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        new mostrarEstanteria()).addToBackStack(null).commit();
                            }
                        });
                    }
                });

                break;
            case 'C':
                //DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una caja").show();
                if (db == null) {
                    db = FirebaseFirestore.getInstance();
                }
                if (miViewModel == null) {
                    miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
                }

                DocumentReference docRefCaja = db.collection("cajas").document(codigo);
                docRefCaja.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Caja caja = documentSnapshot.toObject(Caja.class);
                        caja.setidcaja(documentSnapshot.getId());

                        miViewModel.setCajaEscaneada(caja);
                        //miViewModel.setEstanteriaEscaneada(null);
                       // miViewModel.setObjetoEscaneado(null);


                        //consultar Cajas de la estantería -----------------------------

                        Query queryCajas = db.collection("objetos").whereEqualTo("idcaja", codigo);

                        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                                if (e != null) {
                                    return;
                                }

                                List<Objeto> objetos = snapshot.toObjects(Objeto.class);

                                miViewModel.setListadoObjetos((ArrayList<Objeto>) objetos);

                                vibrating = false;

                                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new mostrarCaja()).addToBackStack(null).commit();
                            }
                        });
                    }
                });

                break;
            case 'T':
                //DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una cosa").show();
                if (db == null) {
                    db = FirebaseFirestore.getInstance();
                }
                if (miViewModel == null) {
                    miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
                }
                DocumentReference docRefObjeto = db.collection("objetos").document(codigo);
                docRefObjeto.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Objeto objeto = documentSnapshot.toObject(Objeto.class);
                        objeto.setidobjeto(documentSnapshot.getId());

                        //miViewModel.setCajaEscaneada(null);
                        //miViewModel.setEstanteriaEscaneada(null);
                        miViewModel.setObjetoEscaneado(objeto);

                        vibrating = false;

                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new mostrarObjeto()).addToBackStack(null).commit();

                    }
                });

                break;
            default:
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Código no reconocido").show();
                vibrating = false;
                break;
        }
    }

}
