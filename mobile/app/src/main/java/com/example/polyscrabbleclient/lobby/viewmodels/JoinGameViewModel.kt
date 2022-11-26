package com.example.polyscrabbleclient.lobby.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.polyscrabbleclient.NavPage
import com.example.polyscrabbleclient.lobby.sources.GameMode
import com.example.polyscrabbleclient.lobby.sources.JoinGame
import com.example.polyscrabbleclient.lobby.sources.LobbyRepository
import com.example.polyscrabbleclient.lobby.sources.OnlineGameSettings
import com.example.polyscrabbleclient.navigateTo

class JoinGameViewModel : ViewModel() {
    val password = mutableStateOf("")

    val selectedLobbyGame = mutableStateOf<OnlineGameSettings?>(null)

    fun isGameProtected(): Boolean {
        return selectedLobbyGame.value?.isProtected() ?: false
    }

    fun isGameSelected(lobbyGame: OnlineGameSettings): Boolean {
        return selectedLobbyGame.value == lobbyGame
    }

    fun getSelectedGameMode(): GameMode {
        return LobbyRepository.model.selectedGameMode.value
    }

    fun toggleSelectedGame(lobbyGame: OnlineGameSettings) {
        selectedLobbyGame.value =
            if (isGameSelected(lobbyGame)) {
                null
            } else {
                lobbyGame
            }
    }

    fun joinGame(
        navController: NavController
    ) {
        if (selectedLobbyGame.value === null) {
            return
        }
        LobbyRepository.emitJoinGame(
            JoinGame(selectedLobbyGame.value!!.id, password.value),
            navController
        )
    }
}
