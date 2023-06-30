package com.radiusagentassignment.view.usecase

import com.radiusagentassignment.model.FacilitiesAndExclusionResponse
import com.radiusagentassignment.repository.MainRepository
import io.reactivex.Single
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun getFacilities(): Single<FacilitiesAndExclusionResponse> {
        return mainRepository.getFacilities()
    }
}