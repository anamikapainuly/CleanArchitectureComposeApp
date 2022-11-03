package com.anupras.schoolapp.data.repository

import com.anupras.schoolapp.data.local.SchoolDatabase
import com.anupras.schoolapp.data.mapper.toSchoolListing
import com.anupras.schoolapp.data.mapper.toSchoolListingEntity
import com.anupras.schoolapp.data.remote.APIService
import com.anupras.schoolapp.domain.model.SchoolListing
import com.anupras.schoolapp.domain.repository.SchoolRepository
import com.anupras.schoolapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anamika on 03,November,2022
 */
@Singleton
class SchoolRepositoryImpl @Inject constructor(
    val api: APIService,
    val db: SchoolDatabase
): SchoolRepository {

    private val dao = db.dao

    override suspend fun getSchoolListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<SchoolListing>>> {

        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchSchoolListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toSchoolListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getSchoolList()
                println(response.result.records)
                response.result.records

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearSchoolListings()
                dao.insertSchoolListings(
                    listings.map { it.toSchoolListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchSchoolListing("")
                        .map { it.toSchoolListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

}