package com.abkhrr.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.abkhrr.common.base.navigation.NavigationCommand
import com.abkhrr.common.base.viewmodel.BaseViewModel
import com.abkhrr.common.utils.ext.activity.adjustFontScale
import com.google.android.material.snackbar.Snackbar

abstract class BaseDialogFragment<VM: BaseViewModel, VB: ViewDataBinding>: DialogFragment() {

    abstract val binding: VB
    abstract val viewModel: VM

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(this) { Toast.makeText(activity, it, Toast.LENGTH_SHORT).show() }
        viewModel.showSnack.observe(this) {
            Snackbar.make(
                this.requireView(),
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.adjustFontScale(resources.configuration)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToNavigation()
        performDataBinding()
    }

    private fun listenToNavigation() {
        viewModel.navigationCommand.observe(this) { command ->
            when (command) {
                is NavigationCommand.To     -> findNavController().navigate(command.directions)
                is NavigationCommand.Back   -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, false)
            }
        }
    }

    private fun performDataBinding(){
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

}