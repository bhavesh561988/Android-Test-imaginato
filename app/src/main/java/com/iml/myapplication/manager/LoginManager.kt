package com.iml.myapplication.manager

import android.util.Log
import com.google.gson.JsonObject
import com.iml.myapplication.webclient.ILoginEndPoint
import com.iml.myapplication.webclient.WebClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class LoginManager {

    private val webClient = WebClient()
    private val compositeDisposable = CompositeDisposable()

    fun login(userName: String, password: String, consumer: Consumer<JsonObject>) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("username", userName)
        jsonObject.addProperty("password", password)

        val loginEndPoint = webClient.getRetrofit().create(ILoginEndPoint::class.java)

        Log.e(javaClass.simpleName, jsonObject.toString())


        compositeDisposable.add(
            loginEndPoint.login(jsonObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
//                    Log.e(javaClass.simpleName, it.toString())
                    val responseObject = JsonObject()
                    val headers = it.headers()
                    if (!headers["X-Acc"].isNullOrEmpty()) {
                        responseObject.addProperty("X-Acc", headers["X-Acc"])
                    }
                    if (it.body() != null) {
                        val body = it.body()!!
                        val user = body["user"] as JsonObject
                        responseObject.addProperty("userId", user["userId"].asString)
                        responseObject.addProperty("userName", user["userName"].asString)
                    }

                    consumer.accept(responseObject)
                }, {
                    it.printStackTrace()
                    consumer.accept(null)
                })
        )
    }
}