package com.example.paddit.dagger.modules

import com.example.paddit.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeLoginActivity(): MainActivity
}