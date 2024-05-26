package com.example.fabrikatepla.data

data class Profile(
    val id: Long,
    val name: String,
    val surname: String,
    val discount: String,
    val balance: String,
    val code: String,
    val icon: String,
) {

    companion object {
        val DEFAULT_VALUE = Profile(
            id = 0L,
            name = "",
            surname = "",
            discount = "",
            balance = "",
            code = "",
            icon = "",
        )
    }

}
