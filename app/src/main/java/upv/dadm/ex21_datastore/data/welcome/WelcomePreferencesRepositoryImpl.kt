/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex21_datastore.data.welcome

import kotlinx.coroutines.flow.Flow
import upv.dadm.ex21_datastore.data.model.WelcomePreferences
import javax.inject.Inject

/**
 * Repository for retrieving the user's preference about the visibility of the initial welcome dialog.
 * It implements the WelcomePreferencesRepository interface.
 */
// @Inject enables Hilt to provide the required dependencies
class WelcomePreferencesRepositoryImpl @Inject constructor(
    private val welcomePreferencesDataSource: WelcomePreferencesDataSource
) : WelcomePreferencesRepository {

    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun getInitialDialogVisibility(): WelcomePreferences =
        welcomePreferencesDataSource.getInitialDialogVisibility()

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    override fun getDialogVisibility(): Flow<WelcomePreferences> =
        welcomePreferencesDataSource.getDialogVisibility()

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun setDialogVisibility(isVisible: Boolean) {
        welcomePreferencesDataSource.setDialogVisibility(isVisible)
    }
}