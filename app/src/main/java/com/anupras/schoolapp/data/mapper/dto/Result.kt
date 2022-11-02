package com.anupras.schoolapp.data.mapper.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by anamika on 03,November,2022
 */
data class Result(
    @SerializedName("records")
    var records: List<Records>
)
