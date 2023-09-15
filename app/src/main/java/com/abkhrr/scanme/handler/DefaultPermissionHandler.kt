package com.abkhrr.scanme.handler

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class DefaultPermissionHandler : PermissionHandler {
    override fun requestPermissions(
        activity: AppCompatActivity,
        permissions: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
}