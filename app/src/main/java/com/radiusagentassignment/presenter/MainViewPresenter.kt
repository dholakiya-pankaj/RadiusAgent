package com.radiusagentassignment.presenter

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.radiusagentassignment.contracts.MainViewContract
import com.radiusagentassignment.db.tables.FacilityAndExclusionTable
import com.radiusagentassignment.model.mapper.toTable
import com.radiusagentassignment.util.AppConstants
import com.radiusagentassignment.util.AppConstants.DATE_FORMAT
import com.radiusagentassignment.view.usecase.MainUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewPresenter @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val realm: Realm,
    private val preferences: SharedPreferences,
    private val prefEditor: Editor
) : MainViewContract.Presenter {

    private lateinit var view: MainViewContract.MainView
    private val subscriptions = CompositeDisposable()

    override fun attach(view: MainViewContract.MainView) {
        this.view = view
    }

    override fun getFacilitiesAndExclusions() {
        view.showLoader(true)
        val previousApiCallDate = getPreferenceDate()
        if(getCurrentDate() != previousApiCallDate) {
            getDataFromRemoteServer()
        } else {
            getCacheData()
        }
    }

    private fun getDataFromRemoteServer() {
        val subscribe = mainUseCase.getFacilities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    val data = result.toTable()
                    realm.executeTransactionAsync({
                        it.insertOrUpdate(data)
                    }, {
                        view.showLoader(false)
                        view.setData(data)
                    }, {
                        view.showLoader(false)
                    })
                    prefEditor.putString(AppConstants.KEY_DATE, getCurrentDate()).apply()
                },
                { error ->
                    view.showLoader(false)
                    view.showSnackBar(error.message.toString().ifEmpty {
                        "Something went Wrong! Please try again."
                    })
                }
            )
        subscriptions.add(subscribe)
    }

    override fun getCacheData() {
        val subscribe =
            realm.where(FacilityAndExclusionTable::class.java)
                .findFirstAsync()
                .asChangesetObservable<FacilityAndExclusionTable>()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.`object`?.let { data ->
                        val originalData = realm.copyFromRealm(data)
                        view.setData(originalData)
                    }
                    view.showLoader(false)
                }, {
                    view.showLoader(false)
                    view.showSnackBar("Something went Wrong!")
                })
        subscriptions.add(subscribe)
    }

    @SuppressLint("SimpleDateFormat")
    override fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        return dateFormat.format(Date())
    }

    override fun getPreferenceDate(): String? {
        return preferences.getString(AppConstants.KEY_DATE, "")
    }

    override fun onDestroy() {
        subscriptions.dispose()
    }
}