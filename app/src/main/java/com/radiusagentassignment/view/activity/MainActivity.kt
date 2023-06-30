package com.radiusagentassignment.view.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radiusagentassignment.R
import com.radiusagentassignment.contracts.MainViewContract
import com.radiusagentassignment.databinding.ActivityMainBinding
import com.radiusagentassignment.db.tables.Exclusion
import com.radiusagentassignment.db.tables.FacilityAndExclusionTable
import com.radiusagentassignment.presenter.MainViewPresenter
import com.radiusagentassignment.view.adapter.FacilityAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainViewContract.MainView {

    @Inject
    lateinit var mainPresenter: MainViewPresenter
    private var binding: ActivityMainBinding? = null
    private val facilityAndExclusionAdapter: FacilityAdapter by lazy {
        FacilityAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainPresenter.attach(this)
        mainPresenter.getFacilitiesAndExclusions()
    }

    override fun showLoader(isLoading: Boolean) {
        binding?.loader?.isVisible = isLoading
    }

    override fun setData(data: FacilityAndExclusionTable) {
        val listType =
            object : TypeToken<List<List<Exclusion>>>() {}.type
        val exclusionList: List<List<Exclusion>> =
            Gson().fromJson(
                data.exclusions,
                listType
            )
        binding?.rvFacilityAndExclusionList?.adapter = facilityAndExclusionAdapter
        facilityAndExclusionAdapter.setData(
            facilitiesRealmList = data.facilities,
            exclusionList = exclusionList
        )
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(binding?.root as ViewGroup, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
        mainPresenter.onDestroy()
    }
}