package com.huutri.demoproject.presentation.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ggg.common.ws.BaseResponse
import com.huutri.demoproject.data.remote.response.LoginResponse
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val loginLivData = MutableLiveData<LoginState>()

    fun login(phone:String,password:String){
        loginRepository.login(phone,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loginLivData.value = LoginState.LOADING
                }
                .subscribe({
                    loginLivData.value = LoginState.SUCCESS(it)
                },{
                    loginLivData.value = LoginState.ERROR("Login Failed")
                    it.printStackTrace()
                }).let { compositeDisposable.add(it) }
    }

    fun getUser(login:String){
        loginRepository.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loginLivData.value = LoginState.LOADING
                }
                .subscribe({
                    loginLivData.value = LoginState.OTHER("Other")
                },{
                    loginLivData.value = LoginState.ERROR("Login Failed")
                    it.printStackTrace()
                }).let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    sealed class LoginState{
        data class OTHER(val string: String): LoginState()
        data class SUCCESS(val data:BaseResponse<LoginResponse>): LoginState()
        data class ERROR(val message:String): LoginState()
        object LOADING : LoginState()
    }
}