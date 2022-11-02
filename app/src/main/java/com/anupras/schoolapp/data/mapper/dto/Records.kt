package com.anupras.schoolapp.data.mapper.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by anamika on 03,November,2022
 */
data class Records(
    @SerializedName("_id")
    var id: Int?,
    @SerializedName("School_Id")
    var schoolId: Int?,
    @SerializedName("Org_Name")
    val org_Name : String?,
    @SerializedName("Email")
    var email: String?,
    @SerializedName("Telephone")
    var telephone: String?
)
