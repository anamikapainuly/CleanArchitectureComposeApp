package com.anupras.schoolapp.domain.repository

import com.anupras.schoolapp.domain.model.SchoolListing
import com.anupras.schoolapp.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by anamika on 03,November,2022
 */
interface SchoolRepository {
    suspend fun getSchoolListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<SchoolListing>>>
}