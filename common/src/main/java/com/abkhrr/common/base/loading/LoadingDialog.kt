package com.abkhrr.common.base.loading

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.abkhrr.common.databinding.LoaderBinding

class LoadingDialog(context: Context): Dialog(context) {

    private val binding: LoaderBinding by lazy { LoaderBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}