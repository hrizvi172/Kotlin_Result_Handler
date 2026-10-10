package com.hrizvi.kotlin_result_handler_app.model
//Sealed class representing the three UI states.
//The compiler enforces exhaustive `when` handling.

sealed class UiState {
    //    Data is being loaded
    data object Loading : UiState()

    //    Data loaded successfully
    data class Success(val user: User) : UiState()

    //    Something went wrong 
    data class Error(val message: String, val cause: Throwable? = null) : UiState()
}