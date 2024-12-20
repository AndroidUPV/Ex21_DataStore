/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex21_datastore.data.welcome

import kotlinx.coroutines.flow.Flow
import upv.dadm.ex21_datastore.data.model.WelcomePreferences

/**
 * Interface declaring the methods that the DataSource exposes to Repositories.
 */
interface WelcomePreferencesDataSource {

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