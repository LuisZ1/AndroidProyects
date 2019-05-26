package com.luisz.qrstore.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.luisz.qrstore.R;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Utilidades {

    public static boolean crearCodigoQR(View view, String contenido) {
        boolean veredicto = false;

        String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
        ImageView qrImage = view.findViewById(R.id.imgCodigoGenerado);
        Bitmap bitmap = null;

        QRGEncoder qrgEncoder = new QRGEncoder(contenido, null, QRGContents.Type.TEXT, 500);

        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) { }

        boolean save;
        String result;

        String prefijo= "XX";
        switch(contenido.charAt(0)){
            case 'E':
                prefijo = "Estanteria-";
                break;
            case 'C':
                prefijo = "Caja-";
                break;
            case 'T':
                prefijo = "Objeto-";
                break;
        }

        try {
            save = QRGSaver.save(savePath, prefijo+contenido, bitmap, QRGContents.ImageType.IMAGE_JPEG);
            result = save ? "Image Saved" : "Image Not Saved";
            if(save){
                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Imagen guardada en la galería").show();
            }else{
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Imagen no guardada").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return veredicto;
    }

    public static boolean checkPermisos(Activity miActivity) {
        boolean veredicto = false;
        int MY_PERMISSIONS_REQUEST = 0;

        if (ContextCompat.checkSelfPermission(miActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(miActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(miActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);

            } else {
                ActivityCompat.requestPermissions(miActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);
            }
        }else{
            veredicto = true;
        }

        if (ContextCompat.checkSelfPermission(miActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            veredicto = true;
        }

        return veredicto;
    }

}
