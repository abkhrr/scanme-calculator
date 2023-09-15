package com.abkhrr.scanme.feature.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.abkhrr.common.base.BaseFragment
import com.abkhrr.common.utils.ext.view.gone
import com.abkhrr.common.utils.ext.view.visible
import com.abkhrr.scanme.BuildConfig
import com.abkhrr.scanme.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @FragmentScoped
    override val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val viewModel: HomeViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = false
    private var uri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        if (BuildConfig.FLAVOR_NAME.contains("BuiltInCamera")) {
            setupBuiltInCameraType()
        } else {
            setupFileSystemType()
        }
    }

    private fun setupFileSystemType() {
        binding.openCameraBtn.gone()
        binding.selectFileBtn.visible()
        binding.selectFileBtn.setOnClickListener {
            val intent  = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startImagePicker.launch(intent)
        }
    }

    private fun setupBuiltInCameraType() {
        binding.selectFileBtn.gone()
        binding.openCameraBtn.visible()
        binding.openCameraBtn.setOnClickListener {
            viewModel.navigateToCamera()
        }
    }

    private val startImagePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            uri = result.data?.data as Uri
            if(uri != null){
                viewModel.navigateToResult(uri.toString())
            }
        }
    }
}