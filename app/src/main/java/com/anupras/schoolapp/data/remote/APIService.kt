package com.anupras.schoolapp.data.remote

import com.anupras.schoolapp.data.mapper.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by anamika on 03,November,2022
 */
interface APIService {
    @GET("/api/3/action/datastore_search")
    suspend fun getSchoolList(
        @Query("resource_id") resource_id: String = RESOURCE_ID,
        @Query("limit") limit: String = LIMIT
    ): BaseResponse

    companion object {
        const val BASE_URL = "https://catalogue.data.govt.nz"
        const val RESOURCE_ID = "20b7c271-fd5a-4c9e-869b-481a0e2453cd"
        const val LIMIT = "20"
    }
}