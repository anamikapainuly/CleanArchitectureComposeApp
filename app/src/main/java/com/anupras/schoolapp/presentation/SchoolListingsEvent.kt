package com.anupras.schoolapp.presentation

/**
 * Created by anamika on 03,November,2022
 */
sealed class SchoolListingsEvent  {
    object Refresh: SchoolListingsEvent()
    data class OnSearchQueryChange(val query: String): SchoolListingsEvent()
}