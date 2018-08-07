package com.huutri.demoproject.presentation.login

import com.ggg.common.ws.BaseResponse
import com.huutri.demoproject.data.remote.MyService
import com.huutri.demoproject.data.remote.request.LoginRequest
import com.huutri.demoproject.data.remote.request.User
import com.huutri.demoproject.data.remote.response.LoginResponse
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor (private val myService: MyService) {

    fun login(phone:String, password:String): Single<BaseResponse<LoginResponse>>{
        val user = User(password,phone)
        val request = LoginRequest(user)
        return  myService.login(request)
    }

    fun getUser(login:String): Single<Any>{
        return  myService.getUser(login)
    }
}