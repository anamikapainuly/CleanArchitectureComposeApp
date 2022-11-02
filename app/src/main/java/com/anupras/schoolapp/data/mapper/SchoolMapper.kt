package com.anupras.schoolapp.data.mapper

import com.anupras.schoolapp.data.local.SchoolListingEntity
import com.anupras.schoolapp.data.mapper.dto.Records
import com.anupras.schoolapp.domain.model.SchoolListing

/**
 * Created by anamika on 03,November,2022
 */

fun SchoolListingEntity.toSchoolListing(): SchoolListing {
    return SchoolListing(
        schoolId = schoolId,
        org_Name = org_Name,
        telephone = telephone,
        email = email
    )
}

fun SchoolListing.toSchoolListingEntity(): SchoolListingEntity {
    return SchoolListingEntity(
        schoolId  = schoolId,
        org_Name = org_Name,
        telephone = telephone,
        email = email
    )
}

fun Records.toSchoolListingEntity(): SchoolListingEntity {
    return SchoolListingEntity(
        schoolId  = schoolId,
        org_Name = org_Name,
        telephone = telephone,
        email = email
    )
}