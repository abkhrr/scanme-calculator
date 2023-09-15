package com.abkhrr.scanme.di

import android.content.Context
import com.abkhrr.common.base.navigator.MainNavigator
import com.abkhrr.scanme.App
import com.abkhrr.scanme.feature.navigation.MainNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    fun provideMainNavigator(mainNavigation: MainNavigation): MainNavigator {
        return mainNavigation
    }
}