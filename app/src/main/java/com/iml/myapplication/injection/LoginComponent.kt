package com.iml.myapplication.injection

import com.iml.myapplication.LoginViewModel
import com.iml.myapplication.injection.modules.DatabaseManagerModule
import com.iml.myapplication.injection.modules.LoginManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        LoginManagerModule::class,
        DatabaseManagerModule::class
    ]
)
interface LoginComponent {
    fun inject(viewModel: LoginViewModel)
}