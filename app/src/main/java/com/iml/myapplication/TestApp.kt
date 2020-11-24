package com.iml.myapplication

import android.app.Application
import com.iml.myapplication.injection.DaggerLoginComponent
import com.iml.myapplication.injection.LoginComponent
import com.iml.myapplication.injection.modules.DatabaseManagerModule
import com.iml.myapplication.injection.modules.LoginManagerModule

class TestApp : Application() {

    private var loginManager: LoginManagerModule? = null
    private var databaseManager: DatabaseManagerModule? = null
    private var loginComponent: LoginComponent? = null

    fun getLoginComponent(): LoginComponent {
        return loginComponent!!
    }

    private fun getLoginManagerModule(): LoginManagerModule {
        return loginManager!!
    }

    private fun getDatabaseManagerModule(): DatabaseManagerModule {
        return databaseManager!!
    }

    override fun onCreate() {
        super.onCreate()
        loginManager = LoginManagerModule()
        databaseManager = DatabaseManagerModule(this)
        loginComponent = DaggerLoginComponent.builder().loginManagerModule(getLoginManagerModule())
            .databaseManagerModule(getDatabaseManagerModule()).build()
    }

}