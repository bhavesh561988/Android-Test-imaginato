package com.iml.myapplication.injection.modules

import com.iml.myapplication.manager.LoginManager
import dagger.Module
import dagger.Provides

@Module
class LoginManagerModule {

    val loginManager: LoginManager
        @Provides
        get() = LoginManager()
}