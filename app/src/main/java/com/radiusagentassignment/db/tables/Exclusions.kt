package com.radiusagentassignment.db.tables

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Exclusions: RealmModel {
    var exclusionList: RealmList<Exclusion> = RealmList()
}