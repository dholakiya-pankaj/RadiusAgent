package com.radiusagentassignment.db.tables

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class FacilityAndExclusionTable(
    @PrimaryKey
    var _id: ObjectId = ObjectId(), // Primary key
    var facilities: RealmList<Facilities> = RealmList(),
    var exclusions: String? = null
): RealmObject()