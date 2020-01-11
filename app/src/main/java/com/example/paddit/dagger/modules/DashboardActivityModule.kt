package com.example.paddit.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.example.paddit.activity.DashboardActivity
import com.example.paddit.fragment.DashboardFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun provideDashboardActivity(activity: DashboardActivity): FragmentActivity

    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment
}