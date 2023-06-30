package com.radiusagentassignment.db.tables

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Option: RealmModel {
    var icon: String? = null
    var id: String? = null
    var name: String? = null
}