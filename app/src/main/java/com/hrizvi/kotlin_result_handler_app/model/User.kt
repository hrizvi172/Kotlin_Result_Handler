package com.hrizvi.kotlin_result_handler_app.model

interface Displayable {
    val displayName: String
}

// Base class demonstrating inheritance.

open class Person(
    val id: Int,
    val name: String,
    val email: String
) : Displayable {
    override val displayName: String
        get() = "Person: $name"

    open fun describe(): String = "id=$id, name=$name, email=$email"
}


//  Subclass inherits from Person and overrides behavior.

class User(
    id: Int,
    name: String,
    email: String,
    val role: String
) : Person(id, name, email) {

    override val displayName: String
        get() = "User: $name ($role)"

    override fun describe(): String =
        super.describe() + ", role=$role"
}