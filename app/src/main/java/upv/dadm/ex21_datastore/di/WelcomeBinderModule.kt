/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex21_datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import upv.dadm.ex21_datastore.data.welcome.WelcomePreferencesDataSource
import upv.dadm.ex21_datastore.data.welcome.WelcomePreferencesDataSourceImpl
import upv.dadm.ex21_datastore.data.welcome.WelcomePreferencesRepository
import upv.dadm.ex21_datastore.data.welcome.WelcomePreferencesRepositoryImpl
import javax.inject.Singleton

/**
 * Hilt module that determines how to provide required dependencies for interfaces.
 */
@Module
// The Hilt annotation @SingletonComponent creates and destroy instances following the lifecycle of the Application
@InstallIn(SingletonComponent::class)
abstract class WelcomeBinderModule {

    /**
     * Provides an instance of a WelcomePreferenceRepository.
     */
    @Binds
    @Singleton
    abstract fun bindWelcomePreferencesRepository(
        welcomePreferencesRepositoryImpl: WelcomePreferencesRepositoryImpl
    ): WelcomePreferencesRepository

    /**
     * Provides an instance of a WelcomePreferenceDataSource.
     */
    @Binds
    @Singleton
    abstract fun bindWelcomePreferencesDataSource(
        welcomePreferencesDataSourceImpl: WelcomePreferencesDataSourceImpl
    ): WelcomePreferencesDataSource
}