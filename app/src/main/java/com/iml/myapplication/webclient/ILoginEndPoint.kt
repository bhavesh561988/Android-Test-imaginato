package com.iml.myapplication.webclient

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginEndPoint {

    @POST("api/login")
    fun login(@Body jsonObject: JsonObject): Observable<Response<JsonObject>>
}
