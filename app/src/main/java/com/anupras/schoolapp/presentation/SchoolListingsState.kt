package com.anupras.schoolapp.presentation

import com.anupras.schoolapp.domain.model.SchoolListing


/**
 * Created by anamika on 03,November,2022
 */
data class SchoolListingsState  (
    val schools: List<SchoolListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)