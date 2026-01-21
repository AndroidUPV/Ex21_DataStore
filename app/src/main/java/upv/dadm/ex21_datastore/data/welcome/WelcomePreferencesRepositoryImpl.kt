/*
 * Copyright (c) 2022-2026 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex21_datastore.data.welcome

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
    override suspend fun getInitialDialogVisibility() =
        welcomePreferencesDataSource.getInitialDialogVisibility()

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    override fun getDialogVisibility() =
        welcomePreferencesDataSource.getDialogVisibility()

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun setDialogVisibility(isVisible: Boolean) {
        welcomePreferencesDataSource.setDialogVisibility(isVisible)
    }
}