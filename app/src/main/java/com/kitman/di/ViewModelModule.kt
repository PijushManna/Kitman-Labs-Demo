package com.kitman.di

import com.kitman.ui.details.DetailsViewModel
import com.kitman.ui.home.HomeViewModel
import com.kitman.ui.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}