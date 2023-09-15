package com.abkhrr.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abkhrr.common.base.loading.LoadingUtils
import com.abkhrr.common.base.navigation.NavigationCommand
import com.abkhrr.common.base.viewmodel.BaseViewModel
import com.abkhrr.common.utils.ext.activity.adjustFontScale
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    abstract val binding: VB
    abstract val viewModel: VM
    abstract val backToPreviousFragmentOnBackPressed: Boolean
    private var onBackPressedCallback: OnBackPressedCallback? = null

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.adjustFontScale(resources.configuration)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        listenToNavigation()
        performDataBinding()
        handleOnBackPressed()
    }

    private fun listenToNavigation() {
        viewModel.navigationCommand.observe(viewLifecycleOwner) { command ->
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

    private fun handleOnBackPressed() {
        onBackPressedCallback = object : OnBackPressedCallback(backToPreviousFragmentOnBackPressed) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback!!)
    }

    fun showLoading(){
        LoadingUtils.showDialog(requireContext(), false)
    }

    fun hideLoading(){
        LoadingUtils.hideDialog()
    }
}