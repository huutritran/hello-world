package com.huutri.demoproject.presentation.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.huutri.demoproject.R
import com.huutri.demoproject.di.Injectable
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        viewModel.loginLivData.observe(this, Observer {
            when (it) {
                is LoginViewModel.LoginState.LOADING -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                is LoginViewModel.LoginState.SUCCESS -> Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                is LoginViewModel.LoginState.ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                is LoginViewModel.LoginState.OTHER -> Toast.makeText(this, it.string, Toast.LENGTH_SHORT).show()
            }
        })

        btnLogin.setOnClickListener {
            viewModel.login("01247476768","1234567")
//            viewModel.getUser("aaaa")
        }
    }


}
