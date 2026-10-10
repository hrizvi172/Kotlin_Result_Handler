package com.hrizvi.kotlin_result_handler_app.repository

import com.hrizvi.kotlin_result_handler_app.model.User

class UserRepository {

    // Custom exception type
    class UserNotFoundException(id: Int) :
        Exception("User with id=$id was not found in the database")


//     Returns a [Result] to signal success or failure
//     instead of throwing across layers.

    fun fetchUser(id: Int): Result<User> {
        return try {
            // Simulate validation
            if (id <= 0) throw IllegalArgumentException("Invalid id: $id")

            // Simulate "not found" scenario
            if (id == 99) throw UserNotFoundException(id)

            // Simulate successful fetch
            val user = User(
                id = id,
                name = "User",
                email = "user@example.com",
                role = "Android Developer"
            )
            Result.success(user)
        } catch (e: UserNotFoundException) {
            // Handle specific exception
            Result.failure(e)
        } catch (e: Exception) {
            // Catch any other exception
            Result.failure(e)
        }
    }
}