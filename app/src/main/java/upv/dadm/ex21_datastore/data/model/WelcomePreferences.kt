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

package upv.dadm.ex21_datastore.data.model

/**
 * An object containing the user's preference about
 * whether show (true) or hide (false) the initial welcome dialog.
 */
data class WelcomePreferences(val isDialogVisible: Boolean)
