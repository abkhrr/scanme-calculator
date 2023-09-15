package com.abkhrr.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.abkhrr.common.utils.ext.activity.adjustFontScale

abstract class BaseActivity<VB: ViewDataBinding> : AppCompatActivity() {
    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding(){
        setContentView(binding.root)
        adjustFontScale(resources?.configuration!!)
        binding.executePendingBindings()
    }
}