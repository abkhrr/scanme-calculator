package com.abkhrr.common.base.viewmodel

import androidx.lifecycle.ViewModel
import com.abkhrr.common.base.navigation.NavigationCommand
import com.abkhrr.common.utils.event.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val isError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnack: SingleLiveEvent<String> = SingleLiveEvent()
    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
}