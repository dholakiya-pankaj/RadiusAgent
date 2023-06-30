package com.radiusagentassignment.model

data class FacilitiesAndExclusionResponse(
    val facilities: List<Facility>,
    val exclusions: List<List<Exclusion>>
) {

    data class Facility(
        val facility_id: String,
        val name: String,
        val options: List<Option>
    ) {
        data class Option(
            val icon: String,
            val id: String,
            val name: String
        )
    }

    data class Exclusion(
        val facility_id: String,
        val options_id: String
    )
}