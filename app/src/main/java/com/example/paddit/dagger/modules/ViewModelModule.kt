package com.example.paddit.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paddit.viewmodel.PostViewModel
import com.example.paddit.viewmodel.ViewModelFactory
import com.example.paddit.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Modules for View Model
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun bindingPostModel(viewModel: PostViewModel): ViewModel

}