package com.anupras.schoolapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by anamika on 03,November,2022
 */
@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolListings(
        schoolListingEntities: List<SchoolListingEntity>
    )

    @Query("DELETE FROM schoollistingentity")
    suspend fun clearSchoolListings()

    @Query(
        """
            SELECT * 
            FROM schoollistingentity
            WHERE LOWER(org_Name) LIKE '%' || LOWER(:query) || '%' 
        """
    )
    suspend fun searchSchoolListing(query: String): List<SchoolListingEntity>
}