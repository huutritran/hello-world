package com.huutri.demoproject.di

import com.huutri.demoproject.presentation.login.LoginActivity
import com.huutri.demoproject.presentation.notification.NotificationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeLoginActivityInjector():LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeNotificationActivityInjector():NotificationActivity

}