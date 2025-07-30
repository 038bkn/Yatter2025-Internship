package com.dmm.bootcamp.yatter2025.di

import com.dmm.bootcamp.yatter2025.ui.timeline.PublicTimelineViewModel
import org.koin.dsl.module

internal val viewModelModule = module {
//  viewModel { MainViewModel(get()) }
  viewModel { PublicTimelineViewModel(get()) }
//  viewModel { PostViewModel(get(), get()) }
//  viewModel { RegisterUserViewModel(get()) }
//  viewModel { LoginViewModel(get()) }
}
