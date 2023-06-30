package com.radiusagentassignment.contracts

import com.radiusagentassignment.db.tables.FacilityAndExclusionTable

interface MainViewContract {

    interface MainView: BaseContract.View {
        fun showLoader(isLoading: Boolean)
        fun setData(data: FacilityAndExclusionTable)
        fun showSnackBar(message: String)
    }

    interface Presenter: BaseContract.Presenter<MainView> {
        fun getFacilitiesAndExclusions()
        fun getCacheData()
        fun getCurrentDate(): String
        fun getPreferenceDate(): String?
        fun onDestroy()
    }
}