package com.rivibi.audrion.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
}
