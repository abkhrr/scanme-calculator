package com.abkhrr.scanme.feature.fragment

import com.abkhrr.common.base.navigator.MainNavigator
import com.abkhrr.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: MainNavigator
): BaseViewModel() {
    fun navigateToResult(uri: String) {
        navigationCommand.value = navigator.homeToResult(uri)
    }

    fun navigateToCamera() {
        navigationCommand.value = navigator.homeToCamera()
    }
}