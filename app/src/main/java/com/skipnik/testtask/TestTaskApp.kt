package com.skipnik.testtask

import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

const val ONESIGNAL_APP_ID = "bc60ecd6-0417-4c75-ad93-cff332bc9503"
const val AF_DEV_KEY = "YhDXmrsWa7MKg2bzN6TpCj"


@HiltAndroidApp
class TestTaskApp : Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)
    }
}