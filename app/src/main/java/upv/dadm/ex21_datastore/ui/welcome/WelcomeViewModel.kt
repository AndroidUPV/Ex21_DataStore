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

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _showInitialDialog = MutableLiveData<Boolean>()

    // Whether to show or hide the initial dialog
    val showInitialDialog: LiveData<Boolean>
        get() = _showInitialDialog

    // Initialise the property for displaying the initial dialog
    init {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            // Get the value from the repository
            _showInitialDialog.value =
                welcomePreferencesRepository.getInitialDialogVisibility().isDialogVisible
        }
    }

    // Whether to show the display or hide option menu
    // according to the preference selected by the user for displaying the initial dialog
    val showDialogMenu: LiveData<Boolean> =
        // Transform the received Flow into LiveData to update the UI in a lifecycle aware manner
        welcomePreferencesRepository.getDialogVisibility().asLiveData().map { prefs ->
            prefs.isDialogVisible
        }

    /**
     * Sets the preference to display the initial dialog according to the user selection.
     */
    fun setDialogVisibility(isVisible: Boolean) {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            welcomePreferencesRepository.setDialogVisibility(isVisible)
        }
    }


}