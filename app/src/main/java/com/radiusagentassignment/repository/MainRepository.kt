package com.radiusagentassignment.repository

import com.radiusagentassignment.model.FacilitiesAndExclusionResponse
import io.reactivex.Single


interface MainRepository {

    fun getFacilities(): Single<FacilitiesAndExclusionResponse>
}