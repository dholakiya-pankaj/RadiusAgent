package com.radiusagentassignment.api

import com.radiusagentassignment.model.FacilitiesAndExclusionResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/iranjith4/ad-assignment/db")
    fun getUsersList(): Single<FacilitiesAndExclusionResponse>
}