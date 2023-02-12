/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex21_datastore.data.welcome

import kotlinx.coroutines.flow.Flow
import upv.dadm.ex21_datastore.data.model.WelcomePreferences

/**
 * Interface declaring the methods that the Repository exposes to ViewModels.
 */
interface WelcomePreferencesRepository {
    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    suspend fun getInitialDialogVisibility(): WelcomePreferences

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    fun getDialogVisibility(): Flow<WelcomePreferences>

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    suspend fun setDialogVisibility(isVisible: Boolean)
}