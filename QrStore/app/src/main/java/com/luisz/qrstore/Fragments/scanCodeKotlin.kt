package com.luisz.qrstore.Fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment

class ScanFragment : Fragment() {
    companion object {
        const val REQUEST_CAMERA = 1729
    }

    private lateinit var binding: FragmentScanBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentScanBinding.inflate(inflater, container, false).also {
                binding = it
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cameraPreview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {

            }

            override fun surfaceCreated(holder: SurfaceHolder?) {

            }
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
}
