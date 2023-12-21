/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex21_datastore.data.welcome

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import upv.dadm.ex21_datastore.data.model.WelcomePreferences
import java.io.IOException
import javax.inject.Inject

/**
 * Data source that provides access to the DataStore to get the user's preferences
 * about the visibility of the initial welcome dialog.
 * It implements the WelcomePreferencesDataSource interface.
 */
class WelcomePreferencesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : WelcomePreferencesDataSource {

    // Constant defining the preferences keys
    private object PreferenceKeys {
        val DIALOG_VISIBILITY = booleanPreferencesKey("DIALOG_VISIBILITY")
    }

    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun getInitialDialogVisibility() =
    // Obtain a single element from the provided Flow,
    // transform it from Preferences to WelcomePreferences,
        // and cancel the Flow
        mapToWelcomePreferences(dataStore.data.first())

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    override fun getDialogVisibility() =
        dataStore.data.catch { exception ->
            // Return empty Preferences if there is an error when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            // Transform the provided Preferences into WelcomePreferences
            mapToWelcomePreferences(preferences)
        }

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun setDialogVisibility(isVisible: Boolean) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.DIALOG_VISIBILITY] = isVisible
        }
    }

    /**
     * Maps the provided Preferences into a WelcomePreferences.
     */
    private fun mapToWelcomePreferences(preferences: Preferences) =
        WelcomePreferences(preferences[PreferenceKeys.DIALOG_VISIBILITY] ?: true)

}