package com.radiusagentassignment.api.repoImpl

import com.radiusagentassignment.api.ApiService
import com.radiusagentassignment.model.FacilitiesAndExclusionResponse
import com.radiusagentassignment.repository.MainRepository
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {

    override fun getFacilities(): Single<FacilitiesAndExclusionResponse> {
        return apiService.getUsersList()
    }
}