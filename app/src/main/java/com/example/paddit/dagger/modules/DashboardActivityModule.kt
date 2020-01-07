package com.example.paddit.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.paddit.activity.DashboardActivity
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun provideDashboardActivity(activity: DashboardActivity): FragmentActivity
}