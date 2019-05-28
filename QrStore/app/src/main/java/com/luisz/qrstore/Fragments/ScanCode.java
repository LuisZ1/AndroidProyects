package com.luisz.qrstore.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.MainActivity;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Util.Utilidades;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.io.IOException;

public class ScanCode extends Fragment {

    View view;
    SurfaceView surfaceView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    TextView textoResultado;
    boolean vibrating = false;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scan_code, container, false);

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
                    //cameraSource.start(holder);

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
            public void release() { }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCode = detections.getDetectedItems();


                if (qrCode.size() != 0) {
                    textoResultado.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!vibrating) {
                                Vibrator vibrator = (Vibrator) getContext().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(200);
                                vibrating = true;

                                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Codigo: " + qrCode.valueAt(0).displayValue).show();
                                consultarCodigo(qrCode.valueAt(0).displayValue);
                            }
                        }
                    });
                }
            }
        });

        return view;
    }

    public void consultarCodigo(String codigo) {
        switch (codigo.charAt(0)) {
            case 'E':
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una estanteria").show();

                DocumentReference docRef = db.collection("estanterias").document(codigo);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Estanteria estanteria = documentSnapshot.toObject(Estanteria.class);
                        estanteria.setIdestanteria(documentSnapshot.getId());
                    }
                });

                break;
            case 'C':
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una caja").show();

                DocumentReference docRefCaja = db.collection("cajas").document(codigo);
                docRefCaja.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Caja caja = documentSnapshot.toObject(Caja.class);
                        caja.setIdCaja(documentSnapshot.getId());

                        DocumentReference docRef = db.collection("estanterias").document(caja.getIdestanteria());
                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Estanteria estanteria = documentSnapshot.toObject(Estanteria.class);
                                estanteria. setIdestanteria(documentSnapshot.getId());
                            }
                        });

                    }
                });

                break;
            case 'T':
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Has escaneado una cosa").show();
                break;
            default:
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Código no reconocido").show();
                break;
        }
    }

}
