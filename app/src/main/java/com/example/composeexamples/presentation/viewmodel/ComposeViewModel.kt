package com.example.composeexamples.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@Immutable
data class GodUiState(
    val id: Int = 0,
    val userName: String = "",
    val email: String = "",
    val timerSeconds: Int = 0
)

@Immutable
data class UserInfo(val id: Int, val userName: String = "", val email: String = "")

@Immutable
data class TimerInfo(val seconds: Int = 0)

@Immutable
data class CleanUiState(
    val userInfo: UserInfo = UserInfo(id = 0, userName = "", email = ""),
    val timer: TimerInfo = TimerInfo()
)

@HiltViewModel
class ComposeViewModel @Inject constructor() : ViewModel() {

    private val _godUiState = MutableStateFlow(
        GodUiState(userName = "Betsson", email = "betsson@gmail.com")
    )
    val godUiState = _godUiState.asStateFlow()

    private val _cleanUiState = MutableStateFlow(
        CleanUiState(userInfo = UserInfo(1, "Betsson", "betsson@gmail.com"))
    )
    val cleanUiState = _cleanUiState.asStateFlow()

    private val _users = MutableStateFlow(generateUsersList())
    val users = _users.asStateFlow()

    private val _lastAction = MutableStateFlow("Waiting for updates...")
    val lastAction = _lastAction.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _godUiState.update { it.copy(timerSeconds = it.timerSeconds + 1) }
                _cleanUiState.update {
                    it.copy(timer = it.timer.copy(seconds = it.timer.seconds + 1))
                }
            }
        }

        viewModelScope.launch {
            while (true) {
                delay(3000)
                modifyUsersList()
            }
        }
    }

    private fun modifyUsersList() {
        val current = _users.value.toMutableList()
        val action = Random.nextInt(3)

        when (action) {
            0 -> { // Add
                val nextId = (current.maxOfOrNull { it.id } ?: 0) + 1
                val newUser = UserInfo(nextId, "New User #$nextId", "new$nextId@gmail.com")
                val insertIndex = Random.nextInt(0, current.size + 1)
                current.add(insertIndex, newUser)
                _lastAction.value = "Added ${newUser.userName} at position ${insertIndex + 1}"
            }

            1 -> { // Remove
                if (current.isNotEmpty()) {
                    val removeIndex = Random.nextInt(current.size)
                    val removed = current.removeAt(removeIndex)
                    _lastAction.value =
                        "Removed ${removed.userName} from position ${removeIndex + 1}"
                }
            }

            2 -> { // Update
                if (current.isNotEmpty()) {
                    val updateIndex = Random.nextInt(current.size)
                    val user = current[updateIndex]
                    val updated = user.copy(userName = "* ${user.userName}")
                    current[updateIndex] = updated
                    _lastAction.value = "Updated ${updated.userName} at position ${updateIndex + 1}"
                }
            }
        }

        _users.value = current
    }

    private fun generateUsersList(): List<UserInfo> = listOf(
        UserInfo(1, "New User #1", "new1@gmail.com"),
        UserInfo(2, "New User #2", "new2@gmail.com"),
        UserInfo(3, "New User #3", "new3@gmail.com"),
        UserInfo(4, "New User #4", "new4@gmail.com"),
        UserInfo(5, "New User #5", "new5@gmail.com"),
    )
}
