package com.abkhrr.scanme.feature.navigation

import com.abkhrr.builtincamera.CameraFragmentDirections
import com.abkhrr.common.base.navigation.NavigationCommand
import com.abkhrr.common.base.navigator.MainNavigator
import com.abkhrr.result.ResultFragmentDirections
import com.abkhrr.scanme.feature.fragment.HomeFragmentDirections
import javax.inject.Inject

class MainNavigation @Inject constructor(): MainNavigator {
    override fun homeToCamera(): NavigationCommand {
        return NavigationCommand.To(HomeFragmentDirections.toCamera())
    }

    override fun homeToResult(uri: String): NavigationCommand {
        return NavigationCommand.To(HomeFragmentDirections.toResult(uri))
    }

    override fun cameraToResult(uri: String): NavigationCommand {
        return NavigationCommand.To(CameraFragmentDirections.toResult(uri))
    }

    override fun resultToHome(): NavigationCommand {
        return NavigationCommand.To(ResultFragmentDirections.backToMain())
    }

    override fun back(): NavigationCommand {
        return NavigationCommand.Back
    }
}