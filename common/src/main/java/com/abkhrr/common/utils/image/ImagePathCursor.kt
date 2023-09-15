package com.abkhrr.common.utils.image

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.provider.MediaStore

class ImagePathCursor {
    @SuppressLint("Recycle")
    fun getRealPathFromURIPath(contentURI: Uri?, activity: Activity): String? {
        val cursor = activity.contentResolver.query(contentURI!!, null, null, null, null);
        return if (cursor == null) {
            contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }
}