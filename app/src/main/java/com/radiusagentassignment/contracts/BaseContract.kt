package com.radiusagentassignment.contracts

interface BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
    }

    interface View {

    }
}