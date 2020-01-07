package com.example.paddit.dagger.modules

import com.example.paddit.activity.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [DashboardActivityModule::class])
    abstract fun contributeLoginActivity(): DashboardActivity
}