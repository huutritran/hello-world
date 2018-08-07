package com.huutri.demoproject.di

import android.app.Application
import com.huutri.demoproject.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,AppModule::class,ActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(appication:Application):Builder

        fun build() : AppComponent
    }

    fun inject(app:App)
}