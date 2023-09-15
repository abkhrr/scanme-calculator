package com.abkhrr.common.base.loading

import android.content.Context

open class LoadingUtils {
    companion object {
        private var loadingDialog: LoadingDialog? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    loadingDialog = LoadingDialog(context)
                    loadingDialog?.let { jarvisLoader->
                        jarvisLoader.setCanceledOnTouchOutside(true)
                        jarvisLoader.setCancelable(isCancelable)
                        jarvisLoader.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (loadingDialog !=null && loadingDialog?.isShowing!!) {
                loadingDialog = try {
                    loadingDialog?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}