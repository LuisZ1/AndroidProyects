package com.luisz.qrstore.Fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Handler
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.databinding.DataBindingUtil
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.luisz.qrstore.R
import com.luisz.qrstore.databinding.FragmentScanCodeKotlinBinding

class ScanFragment : Fragment() {
    companion object {
        const val REQUEST_CAMERA = 1729
    }

    //private lateinit var binding: FragmentScanBinding
    private lateinit var binding: FragmentScanCodeKotlinBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentScanCodeKotlinBinding.inflate(inflater, container, false).also {
                binding = it
            }.root

/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan_code_kotlin, container, false)
        return binding.root
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cameraPreview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                startCameraPreview(width, height)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) { }

            override fun surfaceCreated(holder: SurfaceHolder?) { }
        })
    }

    override fun onStart() {
        super.onStart()

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        } else {
            // TODO: - Start live camera feed
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && requestCode == REQUEST_CAMERA) {
            // TODO: - Start live camera feed
        } else {
            NavHostFragment.findNavController(this).navigateUp()
        }
    }


    private fun startCameraPreview(desiredWidth: Int, desiredHeight: Int) {
        try {
            // TODO
            val cameraBkgHandler = Handler()

            val cameraManager = activity!!.getSystemService(Context.CAMERA_SERVICE) as CameraManager

            cameraManager.cameraIdList.find {
                val characteristics = cameraManager.getCameraCharacteristics(it)
                val cameraDirection = characteristics.get(CameraCharacteristics.LENS_FACING)

                return@find cameraDirection != null && cameraDirection == CameraCharacteristics.LENS_FACING_BACK
            }?.let {
                val cameraStateCallback = object : CameraDevice.StateCallback() {
                    override fun onOpened(camera: CameraDevice) {
                        val barcodeDetector = BarcodeDetector.Builder(context!!)
                                .setBarcodeFormats(Barcode.QR_CODE or Barcode.DATA_MATRIX)
                                .build()

                        if (!barcodeDetector.isOperational) {
                            // TODO
                        }

                        val imgReader = ImageReader.newInstance(desiredWidth, desiredHeight, ImageFormat.JPEG, 1)
                        imgReader.setOnImageAvailableListener({ reader ->
                            val cameraImage = reader.acquireNextImage()

                            val buffer = cameraImage.planes.first().buffer
                            val bytes = ByteArray(buffer.capacity())
                            buffer.get(bytes)

                            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.count(), null)
                            val frameToProcess = Frame.Builder().setBitmap(bitmap).build()
                            val barcodeResults = barcodeDetector.detect(frameToProcess)

                            if (barcodeResults.size() > 0) {
                               // Log.d(TAG, "Barcode detected!")
                                Toast.makeText(context!!, "Barcode detected!" + barcodeResults.valueAt(0).toString(), Toast.LENGTH_SHORT).show()
                            } else {
                                //Log.d(TAG, "No barcode found")
                            }

                            cameraImage.close()
                        }, cameraBkgHandler)


                        val captureStateCallback = object : CameraCaptureSession.StateCallback() {
                            override fun onConfigured(session: CameraCaptureSession) {
                                val builder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                                builder.addTarget(binding.cameraPreview.holder.surface)
                                builder.addTarget(imgReader.surface)
                                session.setRepeatingRequest(builder.build(), null, null)
                            }

                            override fun onConfigureFailed(session: CameraCaptureSession) {
                                // TODO
                                //Log.d(TAG, "onConfigureFailed")
                            }
                        }

                        camera.createCaptureSession(
                                listOf(binding.cameraPreview.holder.surface, imgReader.surface),
                                captureStateCallback,
                                cameraBkgHandler
                        )
                    }

                    override fun onClosed(camera: CameraDevice) {
                        // TODO
                       // Log.d(TAG, "onClosed"
                        camera.close()
                    }

                    override fun onDisconnected(camera: CameraDevice) {
                        // TODO
                        //Log.d(TAG, "onDisconnected")
                        camera.close()
                    }

                    override fun onError(camera: CameraDevice, error: Int) {
                        // TODO
                       // Log.d(TAG, "onError")
                        camera.close()
                    }
                }

                cameraManager.openCamera(it, cameraStateCallback, cameraBkgHandler)
                return
            }

            // TODO: - No available camera found case

        } catch (e: CameraAccessException) {
            // TODO
            //Log.e(TAG, e.message)
        } catch (e: SecurityException) {
            // TODO
            //Log.e(TAG, e.message)
        }
    }

}
