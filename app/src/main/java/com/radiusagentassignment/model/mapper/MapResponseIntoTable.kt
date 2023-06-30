package com.radiusagentassignment.model.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radiusagentassignment.db.tables.*
import com.radiusagentassignment.model.FacilitiesAndExclusionResponse
import io.realm.RealmList


fun FacilitiesAndExclusionResponse.toTable(): FacilityAndExclusionTable {
    val table = FacilityAndExclusionTable()
    val realmFacilitiesList = RealmList<Facilities>()
    this.facilities.map {
        realmFacilitiesList.add(it.toFacility())
    }

    val mappedExclusionsObject = this.exclusions.map {
        it.map { exclusion ->
            exclusion.toExclusion()
        }
    }

    val listType = object : TypeToken<List<List<Exclusion>>>() {}.type
    table.facilities = realmFacilitiesList
    table.exclusions = Gson().toJson(mappedExclusionsObject, listType)
    return table
}


fun FacilitiesAndExclusionResponse.Facility.toFacility(): Facilities {
    val facilities = Facilities()
    facilities.facilityId = this.facility_id
    facilities.name = this.name
    facilities.options = getOptions(this.options)
    return facilities
}

private fun getOptions(options: List<FacilitiesAndExclusionResponse.Facility.Option>) : RealmList<Option> {
    val optionRealmList = RealmList<Option>()
    options.map {
        optionRealmList.add(it.toOption())
    }
    return optionRealmList
}

fun FacilitiesAndExclusionResponse.Facility.Option.toOption(): Option {
    val option = Option()
    option.icon = this.icon
    option.id = this.id
    option.name = this.name
    return option
}

fun FacilitiesAndExclusionResponse.Exclusion.toExclusion(): Exclusion {
    val exclusion = Exclusion()
    exclusion.facilityId = this.facility_id
    exclusion.optionId = this.options_id
    return exclusion
}