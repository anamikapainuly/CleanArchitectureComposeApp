package com.anupras.schoolapp.data.mapper.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by anamika on 03,November,2022
 */
data class BaseResponse(
    @SerializedName("help")
    var help: String,
    @SerializedName("result")
    var result: Result,
    @SerializedName("success")
    var success: Boolean
)
