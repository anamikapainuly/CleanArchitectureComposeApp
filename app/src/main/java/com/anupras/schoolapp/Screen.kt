package com.anupras.schoolapp

sealed class Screen(val route: String) {
    object SchoolListScreen: Screen("school_list_screen")
}
