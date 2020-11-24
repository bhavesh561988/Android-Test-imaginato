package com.iml.myapplication

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iml.myapplication.injection.LoginComponent
import com.iml.myapplication.manager.LoginManager
import com.iml.myapplication.room.DatabaseManager
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import javax.inject.Inject

class LoginViewModel(private val app: TestApp) : AndroidViewModel(app) {
    val userName = MutableLiveData<String>()
    val userNameError = MutableLiveData<String>("")
    val userNameEnable = MutableLiveData<Boolean>(false)

    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>("")
    val passwordEnable = MutableLiveData<Boolean>(false)

    val isLoadingVisible = MutableLiveData<Boolean>(false)

    private var loginComponent: LoginComponent = app.getLoginComponent()

    @Inject
    lateinit var loginManager: LoginManager

    @Inject
    lateinit var databaseManager: DatabaseManager

    init {

        loginComponent.inject(this)
    }

    fun validateAndLogin(_v: View) {
        userNameEnable.value = false
        passwordEnable.value = false
        userNameError.value = ""
        passwordError.value = ""

        var isValidate = true
        if (userName.value.isNullOrEmpty()) {
            userNameEnable.value = true
            userNameError.value = "Please enter username"
            isValidate = false
        }
        if (password.value.isNullOrEmpty()) {
            passwordEnable.value = true
            passwordError.value = "Please enter password"
            isValidate = false
        }

        if (isValidate) {
            Log.e(javaClass.simpleName, "call api")
            val userName = userName.value!!
            val password = password.value!!
            isLoadingVisible.value = true
            loginManager.login(userName, password, Consumer {
                if (it != null) {
                    databaseManager.insertUser(it, Action {
                        Log.i(javaClass.simpleName, it.toString())
                        Toast.makeText(app.applicationContext, "User inserted", Toast.LENGTH_SHORT)
                            .show()
                    })
                }
                isLoadingVisible.value = false
            });
        }
    }
}