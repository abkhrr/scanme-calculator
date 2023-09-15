package com.abkhrr.scanme.handler

import androidx.appcompat.app.AppCompatActivity

interface PermissionHandler {
    fun requestPermissions(
        activity: AppCompatActivity,
        permissions: Array<String>,
        requestCode: Int
    )
}