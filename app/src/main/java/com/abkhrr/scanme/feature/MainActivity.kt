package com.abkhrr.scanme.feature

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.abkhrr.scanme.databinding.ActivityMainBinding
import com.abkhrr.scanme.handler.DefaultPermissionHandler
import com.abkhrr.scanme.handler.PermissionHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val requestCode = 1
    val permission  = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    var permissionHandler: PermissionHandler = DefaultPermissionHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkAppPermission()
        binding.executePendingBindings()
    }

    fun checkAppPermission() {
        if (!isPermissionGranted()) {
            permissionHandler.requestPermissions(this, permission, requestCode)
        }
    }

    fun isPermissionGranted(): Boolean {
        permission.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }
}