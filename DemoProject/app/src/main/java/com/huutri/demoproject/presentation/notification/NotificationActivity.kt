package com.huutri.demoproject.presentation.notification

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.huutri.demoproject.R
import com.huutri.demoproject.di.Injectable

class NotificationActivity : AppCompatActivity(),Injectable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }
}
