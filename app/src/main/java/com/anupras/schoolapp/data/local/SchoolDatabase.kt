package com.anupras.schoolapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by anamika on 03,November,2022
 */

@Database(
    entities = [SchoolListingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase: RoomDatabase() {
    abstract val dao: SchoolDao
}