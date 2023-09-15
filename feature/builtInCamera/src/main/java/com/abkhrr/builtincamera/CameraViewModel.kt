package com.abkhrr.builtincamera

import com.abkhrr.common.base.navigator.MainNavigator
import com.abkhrr.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val navigator: MainNavigator
): BaseViewModel() {
    fun navigateToResult(uri: String) {
        navigationCommand.value = navigator.cameraToResult(uri)
    }

    fun back() {
        navigationCommand.value = navigator.back()
    }
}