package com.radiusagentassignment.db.tables

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Exclusion: RealmModel {
    var facilityId: String? = null
    var optionId: String? = null
}