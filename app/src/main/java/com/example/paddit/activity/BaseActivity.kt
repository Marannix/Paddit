package com.example.paddit.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.paddit.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

    inline fun <reified T : Fragment> FragmentManager.createFragment(@IdRes fragmentId: Int, factory: () -> T): T {
        val fragment = factory()
        this.inTransaction { replace(fragmentId, fragment) }
        return fragment
    }

    // TODO: Since I am using commit now.... i wonder how im going to handle backstack,
    // I think i would need to track the previous page transition
    // Wait... then I'd have to create a dashboard model to handle the transitioning between pages ;O
    // Then... override the onbackpress and handle that myself in the viewmodel....
    // Is this worth it??
    fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commitNow()
    }

}