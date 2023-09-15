package com.abkhrr.common.base.navigator

import com.abkhrr.common.base.navigation.NavigationCommand

interface MainNavigator {
    fun homeToCamera(): NavigationCommand
    fun homeToResult(uri: String): NavigationCommand
    fun cameraToResult(uri: String): NavigationCommand
    fun resultToHome(): NavigationCommand
    fun back(): NavigationCommand
}