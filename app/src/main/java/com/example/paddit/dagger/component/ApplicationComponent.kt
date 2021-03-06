package com.example.paddit.dagger.component

import android.app.Application
import com.example.paddit.MainApplication
import com.example.paddit.dagger.modules.ActivityBuilder
import com.example.paddit.dagger.modules.ApiModule
import com.example.paddit.dagger.modules.RoomModule
import com.example.paddit.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This component is responsible for injecting the Application class.
 */
@Singleton
@Component(
    modules = [ActivityBuilder::class,
        ApiModule::class,
        ViewModelModule::class,
        RoomModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: MainApplication)
}