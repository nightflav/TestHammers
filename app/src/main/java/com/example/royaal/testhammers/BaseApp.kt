package com.example.royaal.testhammers

import android.app.Application
import com.example.royaal.testhammers.di.AppComponent
import com.example.royaal.testhammers.di.DaggerAppComponent

class BaseApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }

}