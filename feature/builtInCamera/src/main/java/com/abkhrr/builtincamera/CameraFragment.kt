package com.abkhrr.builtincamera

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.abkhrr.builtincamera.databinding.FragmentCameraBinding
import com.abkhrr.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import java.io.File
import java.util.concurrent.ExecutorService

@AndroidEntryPoint
@FragmentScoped
class CameraFragment : BaseFragment<FragmentCameraBinding, CameraViewModel>() {
    override val binding: FragmentCameraBinding by lazy { FragmentCameraBinding.inflate(layoutInflater) }
    override val viewModel: CameraViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = true

    private lateinit var imageCapture: ImageCapture
    private lateinit var cameraExecutor: ExecutorService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCamera()
        binding.captureButton.setOnClickListener {
            takePhoto()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // Set up the Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraPreviewView.surfaceProvider)
                }

            // Set up the ImageCapture
            imageCapture = ImageCapture.Builder().build()

            // Select the back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture

        val directoryName = requireContext().packageName // Get your app's package name
        val storageDir = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), directoryName)

        // Create the directory if it doesn't exist
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }

        val photoFile = File(storageDir, "IMG_${System.currentTimeMillis()}.jpg")

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
            .build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = photoFile.toUri().toString()
                    Log.d("URI", savedUri)
                    viewModel.navigateToResult(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Error capturing image: ${exception.message}", exception)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraFragment"
    }
}