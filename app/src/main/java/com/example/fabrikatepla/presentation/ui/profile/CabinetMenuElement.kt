package com.example.fabrikatepla.presentation.ui.profile

sealed class CabinetMenuElement {

    class ClickableElement(
        val title: String = "",
        val image: String? = null,
        val onClick: () -> Unit = {},
    ) : CabinetMenuElement()

    object Space : CabinetMenuElement()

}
