package com.iml.myapplication.room

import android.content.Context
import androidx.room.Room
import client.modules.room.db.UserDatabase
import client.modules.room.entity.User
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class DatabaseManager(context: Context) {

    private var mUserDatabase: UserDatabase
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val DB_NAME = "users"
    }

    init {
        mUserDatabase = Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME).build()
    }

    fun insertUser(jsonObject: JsonObject, action: Action) {
        val user = User()
        user.token = jsonObject["X-Acc"].asString
        user.userName = jsonObject["userName"].asString
        user.userId = jsonObject["userId"].asString
        compositeDisposable.add(
            mUserDatabase.userDao().insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    action.run()
                }
        )
    }
}