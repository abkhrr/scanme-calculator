package com.abkhrr.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abkhrr.common.base.navigator.MainNavigator
import com.abkhrr.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val mainNavigator: MainNavigator
): BaseViewModel() {

    private val _recognizedTextResult = MutableLiveData<String>()
    val recognizedTextResult: LiveData<String>
        get() = _recognizedTextResult

    fun setRecognizedText(text: String) {
        _recognizedTextResult.value = text
    }

    fun navigateToMain() {
        navigationCommand.value = mainNavigator.resultToHome()
    }
}