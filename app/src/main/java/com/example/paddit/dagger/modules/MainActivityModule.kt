package com.example.paddit.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.paddit.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity
}