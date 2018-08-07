package com.huutri.demoproject.data.remote

import com.ggg.common.ws.ApiResponse
import com.ggg.common.ws.BaseResponse
import com.huutri.demoproject.data.remote.request.LoginRequest
import com.huutri.demoproject.data.remote.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyService {

    @POST("/api/auth/login")
    fun login(@Body param: LoginRequest): Single<BaseResponse<LoginResponse>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<Any>
}