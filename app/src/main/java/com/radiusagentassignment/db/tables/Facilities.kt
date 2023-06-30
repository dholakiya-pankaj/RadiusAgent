package com.radiusagentassignment.db.tables

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Facilities: RealmModel {
    var facilityId: String? = null
    var name: String? = null
    var options: RealmList<Option>? = RealmList()
}