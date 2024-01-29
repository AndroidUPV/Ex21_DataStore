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

package upv.dadm.ex21_datastore.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import upv.dadm.ex21_datastore.data.welcome.WelcomePreferencesRepository
import javax.inject.Inject

/**
 * Holds information about the preference of the user to show or hide the initial welcome dialog.
 */
// The Hilt annotation @HiltEntryPoint is required to receive dependencies from its parent class
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val welcomePreferencesRepository: WelcomePreferencesRepository
) : ViewModel() {

    // Backing property for displaying the initial dialog (default empty until initialised)
    private val _showInitialDialog = MutableStateFlow(false)

    // Whether to show or hide the initial dialog
    val showInitialDialog = _showInitialDialog.asStateFlow()

    // Initialise the property for displaying the initial dialog
    init {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            // Get the value from the repository
            _showInitialDialog.update {
                welcomePreferencesRepository.getInitialDialogVisibility().isDialogVisible
            }
        }
    }

    // Whether to show the display or hide option menu
    // according to the preference selected by the user for displaying the initial dialog
    val showDialogMenu =
        // Transform the received Flow into StateFlow to update the UI in a lifecycle aware manner
        welcomePreferencesRepository.getDialogVisibility().map { prefs ->
            prefs.isDialogVisible
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = true
        )

    /**
     * Sets the preference to display the initial dialog according to the user selection and
     * hides the initial dialog.
     */
    fun setDialogVisibility(isVisible: Boolean) {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            welcomePreferencesRepository.setDialogVisibility(isVisible)
            _showInitialDialog.update { false }
        }
    }


}