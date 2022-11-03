package com.anupras.schoolapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by anamika on 03,November,2022
 */

@Entity
data class SchoolListingEntity(
    val schoolId : Int?,
    val org_Name : String?,
    val telephone : String?,
    val email : String?,
    @PrimaryKey val id: Int? = null
)
