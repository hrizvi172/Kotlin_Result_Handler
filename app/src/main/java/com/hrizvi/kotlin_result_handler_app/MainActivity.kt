package com.hrizvi.kotlin_result_handler_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hrizvi.kotlin_result_handler_app.model.UiState
import com.hrizvi.kotlin_result_handler_app.repository.UserRepository
import com.hrizvi.kotlin_result_handler_app.ui.ResultScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val vm: MainViewModel = viewModel()
                LaunchedEffect(Unit) { vm.loadUser(1) }

                ResultScreen(
                    state = vm.state,
                    onRetry = { vm.loadUser(1) },
                    onTriggerError = { vm.loadUser(99) }
                )
            }
        }
    }
}

class MainViewModel(
    private val repo: UserRepository = UserRepository()
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun loadUser(id: Int = 1) {
        state = UiState.Loading
        viewModelScope.launch {
            delay(800) // simulate network
            repo.fetchUser(id)
                .onSuccess { state = UiState.Success(it) }
                .onFailure { state = UiState.Error(it.message ?: "Unknown error", it) }
        }
    }
}